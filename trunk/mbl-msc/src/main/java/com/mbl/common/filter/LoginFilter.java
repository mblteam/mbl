package com.mbl.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mbl.common.constant.UrlConfig;
import com.mbl.common.util.SSOUtil;

public class LoginFilter implements Filter {
	
	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UrlConfig urlConfig; 
	
	private String[]  excludePatternUrl;
	
	/**
	 * filterConfig
	 */
	private FilterConfig filterConfig = null;
	
	private org.springframework.util.PathMatcher pathMatcher = new org.springframework.util.AntPathMatcher();
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			logger.info("SSOFilter:Initializing filter");
		}
		
		String excludeUrl = this.getFilterConfig().getInitParameter("excludeUrl");
		this.excludePatternUrl = excludeUrl == null? null: excludeUrl.split(",");
		
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		urlConfig = (UrlConfig) ctx.getBean("urlConfig");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		
		String path = req.getServletPath()!= null ?  req.getServletPath() : "";
		if('/' == path.charAt(0)){
			path = path.substring(1);}
		
		if (path.indexOf("static")!=-1 
				|| path.indexOf("bizorg")!=-1){
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			res.setHeader("Access-Control-Max-Age", "3600");
			res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			
			//logger.info("doFilter 1 >>>>>>>>>>>>>>"+request.getServletPath());		
			chain.doFilter(req, res);
			return;
		}
		
		// 登录前即可访问的资源(目前包括：登录首页、登录验证)，直接通过
		if(path.endsWith(".js") 
				|| path.endsWith(".css") 
				|| path.endsWith(".jpg") 
				|| path.endsWith("png") 
				|| path.endsWith("toLogin") 
				|| path.endsWith("loginPost")
				|| path.endsWith("validateCode")
				|| path.endsWith(".doc") 
				|| path.endsWith(".xls")
				|| path.endsWith("changePasswordByLoginName")
				|| path.length()==0
				){
			chain.doFilter(req, res);
			return;
		}
		
		//排除WEB.XML中配置可以直接通过的url，如restful、esb
		String url = req.getRequestURI();
		if (excludePatternUrl != null && matchUrl(url)){
			//logger.info("doFilter 2 >>>>>>>>>>>>>>"+request.getServletPath());
			chain.doFilter(req, res);
			return;
		} 

		// 从session里取的用户名信息
		Object obj = session.getAttribute(SSOUtil.SESSION_ACCOUNTID);// 这里获取session，为了检查session里有没有保存用户信息，没有的话回转发到登陆页面

		// 判断如果没有取到用户信息,就跳转到登陆页面
		if (obj == null) {
			res.sendRedirect(urlConfig.getLoginDns());
			/*
			 * if(req.getRequestURI().contains("login.jsp")){
			 * chain.doFilter(request,response); }else{
			 */
			// res.sendRedirect(req.getRequestURI()+"/login.jsp");
			/*res.setHeader("Cache-Control", "no-store");
			res.setDateHeader("Expires", 0);
			res.setHeader("Pragma", "no-cache");*/
			// }
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * ant style路径匹配
	 * @param url
	 * @return
	 */
	private boolean matchUrl(String url){
		for (String partter:excludePatternUrl){
		  if (pathMatcher.match(partter,url)){
			  return true;
		  }
		}
	    return false;	
	}

	public void destroy() {

	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
