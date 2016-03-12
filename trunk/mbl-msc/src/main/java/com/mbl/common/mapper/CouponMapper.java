package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.Coupon;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.CouponVO;

public interface CouponMapper extends BaseMapper<Coupon> {

	List<CouponVO> searchUserCouponByParams(Map<String, Object> map);

	CouponVO getCouponById(String couponId);

	List<CouponVO> findCouponListByParams(Map<String, Object> map, RowBounds rowBounds);

	Long countCouponByParams(Map<String, Object> map);
	
	int disabledCouponStatusById(String couponId);
}