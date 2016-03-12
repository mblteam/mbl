package com.mbl.rest.car.vo;

import com.mbl.common.bean.UserCar;



/**
 * 
 * 注册请求参数
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class UserCarRequestVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private UserCar userCar;
	
	private String accountId;
	
	private String carId;
	
	private String cbId;

	public UserCar getUserCar() {
		return userCar;
	}

	public void setUserCar(UserCar userCar) {
		this.userCar = userCar;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCbId() {
		return cbId;
	}

	public void setCbId(String cbId) {
		this.cbId = cbId;
	}
	
}
