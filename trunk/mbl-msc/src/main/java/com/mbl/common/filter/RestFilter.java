package com.mbl.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.TokenUtil;
import com.mbl.rest.account.biz.LoginBiz;

/**
 * rest过滤器
 * @author zl
 * @create 2015年12月6日 下午4:20:19 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RestFilter implements Filter {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * filterConfig
	 */
	private FilterConfig filterConfig = null;
	
	/**
	 * 登录
	 */
	private LoginBiz loginBiz;
	

	/**
	 * @see javax.servlet.Filter#destroy()
	 */

	@Override
	public void destroy() {

	}
	
	public static void main(String[] args) {
		System.out.println("/rest/account/login1".endsWith("/rest/account/login"));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		logger.info("start do sso filter");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String origiUrl =request.getRequestURL().toString();
		
		String tokenValue = request.getHeader("token");
		
		if (origiUrl.endsWith("/rest/account/login")
				||origiUrl.endsWith("/rest/account/sendCode")
				||origiUrl.endsWith("/rest/account/register")
				||origiUrl.endsWith("/rest/order/payAfterSuccess")
				||origiUrl.endsWith("/rest/order/wxPayAfterSuccess")) {	
			try {
				chain.doFilter(req, res);
				return;
			} catch (Throwable t) {
				logger.info("Do chain filter exception after sso filter", t);
			}
		} else {
			boolean result = false;
			
			String accountId = null;
			
			if (StringUtils.isNotBlank(tokenValue)) {
				tokenValue = tokenValue.replace(" ", "+");
				try {
					accountId = TokenUtil.validateToken(tokenValue);
					
					if (StringUtils.isNotBlank(accountId)) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("accountId", accountId);
						if(loginBiz.existAccount(map)){
							result = true;
						}
					}
				} catch (Exception e) {
					logger.info("Do chain filter exception", e);
					ResultVO resultVO = new ResultVO();
					resultVO.setErrorCode(ResultVO.FAIL);
					resultVO.setErrorMsg("");
					resultVO.setResult("TOKEN_ERR");
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json;charset=UTF-8");
					response.getWriter().print((new ObjectMapper()).writeValueAsString(resultVO));
					return;
				}
			}
			if (result) {
				try {
					chain.doFilter(req, res);
					return;
				} catch (Throwable t) {
					logger.info("Do chain filter exception", t);
				}
			} else {
				ResultVO resultVO = new ResultVO();
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("");
				resultVO.setResult("NO_TOKEN");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print((new ObjectMapper()).writeValueAsString(resultVO));
			}
		}
	}

	
	/**  
	 * 返回 filterConfig 的�?   
	 * @return filterConfig  
	 */
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**  
	 * 设置 filterConfig
	 * @param filterConfig
	 */
	
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			logger.info("SSOFilter:Initializing filter");
		}
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		loginBiz = (LoginBiz) ctx.getBean("loginBiz");
	}

}
