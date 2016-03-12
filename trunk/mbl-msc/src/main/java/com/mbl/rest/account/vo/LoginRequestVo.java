package com.mbl.rest.account.vo;

import com.mbl.common.bean.Account;

/**
 * 
 * 用户登录请求参数
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LoginRequestVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	/* 登录账号 */
	private String accountCode;
	
	/* 登录密码 */
	private String password;
	
	/* 登录TOKEN */
	private String token;
	
	/* 电话号码 */
	private String tel;
	
	/* 验证码  */
	private String sendCode;
	
	private Account account;
	
	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSendCode() {
		return sendCode;
	}

	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
	}
}
