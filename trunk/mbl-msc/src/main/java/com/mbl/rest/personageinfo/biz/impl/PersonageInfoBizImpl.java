package com.mbl.rest.personageinfo.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.User;
import com.mbl.common.bean.UserCar;
import com.mbl.common.bean.UserCoupon;
import com.mbl.common.mapper.UserCarMapper;
import com.mbl.common.mapper.UserCouponMapper;
import com.mbl.common.mapper.UserMapper;
import com.mbl.rest.personageinfo.biz.PersonageInfoBiz;
import com.mbl.rest.personageinfo.vo.PersonageInfoResponseVo;

/**
 * 个人信息
 * @author xjs
 * @create 2015年12月05日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "personageInfoBiz")
@Transactional
public class PersonageInfoBizImpl implements PersonageInfoBiz {

	@Resource
	UserMapper userMapper;
	
	@Resource
	UserCarMapper userCarMapper;
	
	@Resource
	UserCouponMapper userCouponMapper;
	
	/**
	 * 个人信息查询
	 */
	@Override
	public PersonageInfoResponseVo searchPersonageInfoList(String userId) {
		
		PersonageInfoResponseVo responvo=new PersonageInfoResponseVo();
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		//用户信息
		User user=userMapper.getById(userId);
		
		//用户车辆信息
		List<UserCar> userCarList=userCarMapper.findListByParams(map);
		
		//优惠券信息
		List<UserCoupon> userCouponList=userCouponMapper.findListByParams(map);
		
		responvo.setUser(user);
		responvo.setUserCarList(userCarList);
		responvo.setUserCouponList(userCouponList);
		
		return responvo;
	}
	
	/**
	 * 个人信息查询
	 */
	@Override
	public User searchPersonageInfo(String userId) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		//用户信息
		User user=userMapper.getById(userId);
		
		return user;
	}

	/**
	 * 修改个人信息
	 */
	@Override
	public void updateUser(User user) {
		userMapper.update(user);
	}
}
