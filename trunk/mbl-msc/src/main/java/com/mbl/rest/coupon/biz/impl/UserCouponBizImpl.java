package com.mbl.rest.coupon.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.UserCoupon;
import com.mbl.common.mapper.UserCouponMapper;
import com.mbl.rest.coupon.biz.UserCouponBiz;
import com.mbl.rest.coupon.vo.UserCouponVo;

/**
 * 优惠券
 * @author xjs
 * @create 2015年12月02日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "userCouponBiz")
@Transactional
public class UserCouponBizImpl implements UserCouponBiz {

	@Resource
	UserCouponMapper userCouponMapper;
	
	/**
	 * 查询用户的优惠券信息
	 */
	@Override
	public List<UserCouponVo> searchUserCouponList(String userId) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		List<UserCouponVo> userCouponList=userCouponMapper.findUserCouponListByParams(map);
		return userCouponList;
	}
}
