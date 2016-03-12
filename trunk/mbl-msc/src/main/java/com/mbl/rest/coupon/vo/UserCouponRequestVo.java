package com.mbl.rest.coupon.vo;

import com.mbl.common.bean.User;
import com.mbl.common.bean.UserCoupon;

/**
 * 
 * 优惠券请求参数
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class UserCouponRequestVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private UserCoupon userCoupon;
	
	private String userId;
	
	private User user;

	public UserCoupon getUserCoupon() {
		return userCoupon;
	}

	public void setUserCoupon(UserCoupon userCoupon) {
		this.userCoupon = userCoupon;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
