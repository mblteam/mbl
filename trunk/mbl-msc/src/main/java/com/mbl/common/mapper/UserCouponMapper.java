package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.UserCoupon;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.rest.coupon.vo.UserCouponVo;

public interface UserCouponMapper extends BaseMapper<UserCoupon>{

	List<UserCouponVo>  findUserCouponListByParams(Map<String, Object> map);
}