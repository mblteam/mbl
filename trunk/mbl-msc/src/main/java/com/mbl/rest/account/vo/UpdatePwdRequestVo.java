package com.mbl.rest.account.vo;

import com.mbl.common.util.JsonUtil;


/**
 * 修改密码VO
 * 功能详细描述
 * @author zl
 * @create 2015年12月18日 下午11:15:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class UpdatePwdRequestVo {
	
	public static void main(String[] args) {
		UpdatePwdRequestVo vo =new UpdatePwdRequestVo();
		System.out.println(JsonUtil.javaObjToJson(vo));
	}
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	/* 登录账号 */
	private String accountCode;
	
	/* 登录密码 */
	private String password;
	
	/**
	 * 新密码
	 */
	private String newPassword;
	
	/**
	 * 确认新密码
	 */
	private String newPasswordConfirm;
	
	/**
	 * 验证码
	 */
	private String sendCode;
	
	/**
	 * 标识
	 */
	private String flag;

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public String getSendCode() {
		return sendCode;
	}

	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
