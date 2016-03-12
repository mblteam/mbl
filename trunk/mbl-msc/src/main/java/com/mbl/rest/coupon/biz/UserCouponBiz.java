package com.mbl.rest.coupon.biz;

import java.util.List;

import com.mbl.rest.coupon.vo.UserCouponVo;


/**
 * 优惠券接口
 * @author xjs
 * @create 2015年12月05日 下午22:02:37 
 * @version 1.0.0
 */
public interface UserCouponBiz {

	/**
	 * 查询用户的优惠券
	 * @param userId
	 * @return
	 */
	List<UserCouponVo> searchUserCouponList(String userId);

}
