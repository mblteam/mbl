package com.mbl.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * 功能详细描述
 * @author zl
 * @create 2015年12月27日 上午1:47:16 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SSOUtil {

	/**
	 * session id name
	 */
	public static final String SESSION_USERID = "userid";
	
	public static final String SESSION_ACCOUNTID = "userid";
	
	/**
	 * cookie id name
	 */
	public static final String SSO_COOKIE_KEY = "km_s";
	
	public static final String SSO_LOGIN_COOKIE ="_login_cookie_kinged";  // sessionid
	
	public static final String SSO_LOGIN_USER_COOKIE ="_login_user_kinged";
	
	public static final String SSO_CURRENT_ACCOUNT_COOKIE ="_login_current_account_kinged"; 
	
	public static final String SSO_ACCOUNTS_COOKIE ="_login_accounts_kinged";  //account1,account2,account3
	
	public static final String SSO_LOGINTIME_COOKIE ="_login_time_cookie_kinged";
	
	public static final int SSO_COOKIE_AGE = 3600;
	
	public static final String SSO_COOKIE_DOMAIN = ".kingmed.com.cn";
	
	/**
	 * 取当前登录用户COOKIE
	 * @param request
	 * @return
	 */
	public static Cookie getSsoCookie(HttpServletRequest request,String cookieName){
		Cookie loginCookie = null;		
		javax.servlet.http.Cookie[] diskCookies = request.getCookies();
		if (diskCookies != null) {
			for (int i = 0; i < diskCookies.length; i++) {

				if (diskCookies[i].getName().equals(cookieName)) {
					loginCookie = diskCookies[i];
					break;
				}
			}
		}
		return loginCookie;
	}
	
	public static void putCookie(String name,String value,HttpServletResponse res){
		Cookie loginCookie = new Cookie(name,value);
		//loginCookie.setDomain(SSOUtil.SSO_COOKIE_DOMAIN);
		loginCookie.setPath("/");
		loginCookie.setMaxAge(-1);
		res.addCookie(loginCookie);
	}
	public static void removeCookie(String name,HttpServletResponse res){
		Cookie loginCookie = new Cookie(name,null);
		//loginCookie.setDomain(SSOUtil.SSO_COOKIE_DOMAIN);
		loginCookie.setPath("/");
		loginCookie.setMaxAge(-1);
		res.addCookie(loginCookie);
	}
	
}
