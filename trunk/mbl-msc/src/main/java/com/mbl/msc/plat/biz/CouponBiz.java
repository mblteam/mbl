package com.mbl.msc.plat.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.vo.CouponVO;

public interface CouponBiz {

	/***
	 * 保存
	 * @param couponVO
	 * @return
	 */
	public int saveCoupon(CouponVO couponVO);
	/****
	 * 查询优惠券
	 * @param couponId
	 * @return
	 */
	CouponVO getCouponById(String couponId) ;

	/***
	 * 分页查询优惠券列表
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<CouponVO> findCouponListByParams(Map<String, Object> map,Integer page,Integer pageSize) ;

	/***
	 * 分页统计总数
	 * @param map
	 * @return
	 */
	Long countCouponByParams(Map<String, Object> map) ;
	
	/****
	 * 状态失效
	 * @param couponId
	 * @return
	 */
	int disabledCouponStatusById(String couponId);
}
