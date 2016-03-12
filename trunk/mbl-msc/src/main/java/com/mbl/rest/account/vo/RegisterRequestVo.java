package com.mbl.rest.account.vo;

/**
 * 
 * 注册请求参数
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class RegisterRequestVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	/* accountId */
	private String accountId;
	
	/* 电话号码 */
	private String tel;
	
	/* 验证码  */
	private String sendCode;
	
	/* 密码  */
	private String password;

	/**验证码获取来源**/
	private String flag;
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
