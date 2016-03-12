package com.mbl.rest.personageinfo.vo;

import java.util.List;

import com.mbl.common.bean.User;
import com.mbl.common.bean.UserCar;
import com.mbl.common.bean.UserCoupon;

/**
 * 
 * 个人信息返回参数
 * @author xjs
 * @create 2015年12月05日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class PersonageInfoResponseVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	//用户信息
	private User user;
	
	//用户车辆信息
	private List<UserCar> userCarList;
	
	//优惠券信息
	private List<UserCoupon> userCouponList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserCar> getUserCarList() {
		return userCarList;
	}

	public void setUserCarList(List<UserCar> userCarList) {
		this.userCarList = userCarList;
	}

	public List<UserCoupon> getUserCouponList() {
		return userCouponList;
	}

	public void setUserCouponList(List<UserCoupon> userCouponList) {
		this.userCouponList = userCouponList;
	}
	
}
