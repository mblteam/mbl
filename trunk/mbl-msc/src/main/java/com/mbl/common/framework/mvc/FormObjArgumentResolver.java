package com.mbl.common.framework.mvc;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 提供springMvc的Controller方法处理多个Bean的能力
 * @author zl
 * @create 2015年11月28日 下午8:28:24 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FormObjArgumentResolver implements HandlerMethodArgumentResolver {

	private static final char NESTED_PROPERTY_SEPARATOR_CHAR = '.';

	private static final char PROPERTY_KEY_PREFIX_CHAR = '[';

	private static final char PROPERTY_KEY_SUFFIX_CHAR = ']';

	/**
	 * @param parameter
	 * @return
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	 */

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(FormObj.class);
	}

	/**
	 * @param parameter
	 * @param mavContainer
	 * @param webRequest
	 * @param binderFactory
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter,
	 *      org.springframework.web.method.support.ModelAndViewContainer,
	 *      org.springframework.web.context.request.NativeWebRequest,
	 *      org.springframework.web.bind.support.WebDataBinderFactory)
	 */

	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		FormObj formObj = parameter.getParameterAnnotation(FormObj.class);

		String alias = getAlias(formObj, parameter);

		// 拿到obj, 先从ModelAndViewContainer中拿，若没有则new1个参数类型的实例
		Object obj = (mavContainer.containsAttribute(alias)) ? mavContainer
				.getModel().get(alias) : createAttribute(alias, parameter,
				binderFactory, webRequest);

		// 获得WebDataBinder，这里的具体WebDataBinder是ExtendedServletRequestDataBinder
		WebDataBinder binder = binderFactory.createBinder(webRequest, obj,
				alias);

		Object target = binder.getTarget();
		if (target != null) {
			// 绑定参数
			bindParameters(webRequest, binder, alias);
			// JSR303 验证
			validateIfApplicable(binder, parameter);
			if (binder.getBindingResult().hasErrors()) {
				if (isBindExceptionRequired(binder, parameter)) {
					throw new BindException(binder.getBindingResult());
				}
			}
		}

		if (formObj.show()) {
			mavContainer.addAttribute(alias, target);
		}

		return target;
	}

	/**
	 * 绑定参数
	 * 
	 * @param binder
	 * @param request
	 * @param alias
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@SuppressWarnings("unchecked")
	protected void bindParameters(NativeWebRequest request,
			WebDataBinder binder, String alias) {

		ServletRequest servletRequest = request
				.getNativeRequest(ServletRequest.class);

		MockHttpServletRequest newRequest = new MockHttpServletRequest();

		Map<String, String[]> pm = servletRequest.getParameterMap();
		Iterator<String> iterator = pm.keySet().iterator();
		while (iterator.hasNext()) {
			String paramName = iterator.next();
			String[] values = pm.get(paramName);
			if (paramName.startsWith(alias)) {

				String newParamName = paramName.substring(alias.length());
				bindRequestParameters(newRequest, newParamName, values);
			}
		}

		ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
		servletBinder.bind(newRequest);
	}

	/**
	 * 扫描parameter的key，绑定单个参数。 将key的形式有a[b][c][]转化为a.b.c[0]
	 * 
	 * @param request
	 * @param param
	 * @param values
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private void bindRequestParameters(MockHttpServletRequest request,
			String param, String[] values) {
		byte[] bytes = param.getBytes();

		boolean in = false;

		int start = -1;
		int end = -1;

		List<String> list = new ArrayList<String>();

		boolean isArray = false;
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];

			if (PROPERTY_KEY_PREFIX_CHAR == b) {
				if (in) {
					end = i;
					in = false;

					String sub = param.substring(start, end);
					list.add(sub);
				} else {
					start = i + 1;
					in = true;
				}
			} else if (PROPERTY_KEY_SUFFIX_CHAR == b) {
				end = i;
				in = false;

				String sub = param.substring(start, end);

				if (StringUtils.isBlank(sub)) {
					sub = "" + PROPERTY_KEY_PREFIX_CHAR
							+ PROPERTY_KEY_SUFFIX_CHAR;
					isArray = true;

				}
				list.add(sub);
			} else {
				if (!in) {
					start = i;
					in = true;
				}
			}
		}
		String key = StringUtils.join(list, NESTED_PROPERTY_SEPARATOR_CHAR);
		if (isArray) {
			for (int i = 0; i < values.length; i++) {
				String oldStr = "\\" + NESTED_PROPERTY_SEPARATOR_CHAR + "\\"
						+ PROPERTY_KEY_PREFIX_CHAR + "\\"
						+ PROPERTY_KEY_SUFFIX_CHAR;

				String newStr = "\\" + PROPERTY_KEY_PREFIX_CHAR + i + "\\"
						+ PROPERTY_KEY_SUFFIX_CHAR;

				// 将.[]replace为[i]
				String newKey = key.replaceAll(oldStr, newStr);

				request.setParameter(newKey, values[i]);

			}
		} else {
			if (values.length > 0) {
				request.setParameter(key, values[0]);
			}
		}

	}

	private Object createAttribute(String alias, MethodParameter parameter,
			WebDataBinderFactory binderFactory, NativeWebRequest webRequest) {
		return BeanUtils.instantiateClass(parameter.getParameterType());
	}

	protected void validateIfApplicable(WebDataBinder binder,
			MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation annot : annotations) {
			if (annot.annotationType().getSimpleName().startsWith("Valid")) {
				Object hints = AnnotationUtils.getValue(annot);
				binder.validate(hints instanceof Object[] ? (Object[]) hints
						: new Object[] { hints });
				break;
			}
		}
	}

	protected boolean isBindExceptionRequired(WebDataBinder binder,
			MethodParameter parameter) {
		int i = parameter.getParameterIndex();
		Class<?>[] paramTypes = parameter.getMethod().getParameterTypes();
		boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class
				.isAssignableFrom(paramTypes[i + 1]));

		return !hasBindingResult;
	}

	private String getAlias(FormObj formObj, MethodParameter parameter) {
		// 得到FormObj的属性value，也就是对象参数的简称
		String alias = formObj.value();
		if (alias == null || StringUtils.isBlank(alias)) {
			// 如果简称为空，取对象简称的首字母小写开头
			String simpleName = parameter.getParameterType().getSimpleName();
			alias = simpleName.substring(0, 1).toLowerCase()
					+ simpleName.substring(1);
		}
		return alias;
	}
}
