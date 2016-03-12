package com.mbl.msc.plat.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Coupon;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.CouponMapper;
import com.mbl.common.vo.CouponVO;
import com.mbl.msc.plat.biz.CouponBiz;

/**
 * 优惠券管理逻辑类
 * 
 * @author jjj
 * @create 2015年12月19日 上午12:22:01
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "couponBiz")
@Transactional
public class CouponBizImpl implements CouponBiz {
	@Resource
	private CouponMapper couponMapper;
	
	/***
	 * 保存
	 * @param couponVO
	 * @return
	 */
	public int saveCoupon(CouponVO couponVO) {
		if(couponVO == null) {
			return 0;
		}
		
		Coupon coupon = new Coupon();
		
		BeanUtils.copyProperties(couponVO, coupon);
		
		if(StringUtils.isEmpty(coupon.getCouponId())) {
			coupon.setCouponId(UUID.randomUUID().toString());
			return couponMapper.save(coupon);
		} else {
			return couponMapper.update(coupon);
		}
	}
	
	/****
	 * 查询优惠券
	 * @param couponId
	 * @return
	 */
	public CouponVO getCouponById(String couponId) {
		return couponMapper.getCouponById(couponId);
	}

	/***
	 * 分页查询优惠券列表
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<CouponVO> findCouponListByParams(Map<String, Object> map,Integer page,Integer pageSize) {
		return couponMapper.findCouponListByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}

	/***
	 * 分页统计总数
	 * @param map
	 * @return
	 */
	public Long countCouponByParams(Map<String, Object> map) {
		return couponMapper.countCouponByParams(map);
	}
	
	/****
	 * 状态失效
	 * @param couponId
	 * @return
	 */
	public int disabledCouponStatusById(String couponId){
		return couponMapper.disabledCouponStatusById(couponId);
	}
}
