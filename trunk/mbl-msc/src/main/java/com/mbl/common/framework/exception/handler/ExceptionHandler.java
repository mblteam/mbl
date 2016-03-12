package com.mbl.common.framework.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mbl.common.framework.exception.excepts.AppException;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.exception.excepts.DataAccessException;
import com.mbl.common.framework.exception.excepts.RemoteAccessException;
import com.mbl.common.framework.exception.excepts.ServiceException;
import com.mbl.common.framework.exception.excepts.UnCheckedException;

/**
 * 处理异常，适用于前端ajax请求后，后台出现异常的场景 异常处理后以JSON方式返回异常信息
 * 
 * @author Administrator
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionHandler.class);

	private HttpServletResponse response;
	private String message;
	private String logMsg;

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		this.response = response;
		HandlerMethod h = (HandlerMethod) handler;
		logMsg = h.toString();

		// 应用异常
		if (ex instanceof AppException) {
			this.message = "应用层异常：" + getMessage(ex);
			process();

			// 数据访问异常
		} else if (ex instanceof DataAccessException) {
			this.message = "数据访问层异常：" + getMessage(ex);
			process();

			// 服务层异常
		} else if (ex instanceof ServiceException) {
			this.message = "服务层异常：" + getMessage(ex);
			process();

			// 业务逻辑异常
		} else if (ex instanceof BizException) {
			this.message = "业务逻辑异常：" + getMessage(ex);
			process();

			// 远程访问异常
		} else if (ex instanceof RemoteAccessException) {
			this.message = "远程访问异常：" + getMessage(ex);
			process();

			// UnChecked异常
		} else if (ex instanceof UnCheckedException) {
			this.message = "UnChecked异常：" + getMessage(ex);
			process();

		} else {
			this.message = "异常：" + ex.toString() + "，异常信息：" + ex.getMessage();
			process();
		}
		return null;
	}

	/**
	 * 统一将异常信息转为JSON写回前端
	 * 
	 * @param ex
	 * @param response
	 */
	private void process() {
		PrintWriter writer;
		try {
			writer = response.getWriter();
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			writer.write(this.message);
			writer.flush();
			logger.debug(this.message);
			logger.debug("异常位置：" + logMsg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private String getMessage(Exception ex) {
		String code = ((AppException) ex).getCode();
		String msg = AppException.getExceptMsg(code);
		if (msg != null)
			return msg;
		else
			return ex.getMessage();
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
