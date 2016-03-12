package com.mbl.msc.order.biz.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Order;
import com.mbl.common.bean.Shop;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.mapper.OrderMapper;
import com.mbl.common.mapper.ShopMapper;
import com.mbl.common.vo.OrderVO;
import com.mbl.common.vo.SettleVO;
import com.mbl.common.vo.ShopPackageVO;
import com.mbl.msc.order.biz.OrderBiz;

@Service(value = "orderBiz")
@Transactional
public class OrderBizImpl implements OrderBiz {

	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private ShopMapper shopMapper;
	
	/***
	 * 查询订单翻页
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<OrderVO> findOrderList(Map<String, Object> map,Integer page,Integer pageSize){
		return orderMapper.findListPageByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}

	@Override
	public Long countListByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderMapper.countListByParams(params);
	}
	
	@Override
	public OrderVO getOrderById(Map<String, Object> params) {
		return orderMapper.getOrderById(params);
	}
	
	/***
	 * 跟商铺支付结算接口
	 * @param params
	 * @return
	 */
	@Override
	public int settleByParams(SettleVO settleVO) {
		return orderMapper.settleByParams(settleVO);
	}
	/****
	 * 更新订单状态
	 * @param params
	 * @return
	 */
	@Override
	public int updateOrderStatus(Map<String, Object> params) {
		return orderMapper.updateOrderStatus(params);
	}
	
	/**
	 * 设置维修单价格
	 * @param query  
	 * @throws BizException 
	 * @see com.mbl.msc.order.biz.OrderBiz#setRepairOrderPrice(java.util.Map)
	 */
	@Override
	public Order setRepairOrderPrice(Map<String, Object> query) throws BizException {
		if(null!=query&&null!=query.get("orderId")&&null!=query.get("paid")){
			String orderId = query.get("orderId").toString();
			String price = query.get("paid").toString();
			if(!StringUtils.isNumeric(price)){
				throw new BizException("-1","价格不为数字");
			}
			Order order = orderMapper.getById(orderId);
			
			if(!ConstantClass.ODER_STATUS_ONE.equals(order.getStatus())){
				throw new BizException("-1","该订单处于"
						+ConstantClass.getOrderStatusDesc(order.getStatus())+"状态");
			}
			if(!DictCode.VEHICLE_MAINTENANCE_ORDER.equals(order.getOrderType())){
				throw new BizException("-1","非维修订单");
			}
			
			BigDecimal totalPrice = new BigDecimal(price);
			//计算折扣
			BigDecimal discount = arithmeticalDiscount(order.getShopId(), totalPrice);
			order.setPaid(totalPrice.multiply(discount).setScale(2,BigDecimal.ROUND_UP));
			order.setPrice(totalPrice);
			order.setStatus(ConstantClass.ODER_STATUS_TWO);
			orderMapper.update(order);
			return order;
		}else{
			throw new BizException("-1","参数错误");
		}
	}
	
	 /**
     * 计算折扣
     * @return
     */
    private synchronized BigDecimal arithmeticalDiscount(String shopId,BigDecimal price){
        //获取店铺的折扣上线，折扣下线
        Shop shop = shopMapper.getById(shopId);
        
		//折扣上线
		BigDecimal upperLimit = new BigDecimal("0.95");
		//折扣下线(折扣上线-订单金额/奖池 )
		BigDecimal lowerLimit = upperLimit.subtract(shop.getPond().divide(price)).setScale(2,BigDecimal.ROUND_UP);
		
		//当奖池金额超过订单金额时设置折扣下线为0，既“免单”
		if(lowerLimit.compareTo(BigDecimal.valueOf(0))<0){
			lowerLimit = BigDecimal.valueOf(0);
		}
		
		//随机生成两个折扣,取平均值(折扣  = （上线-下线）* 0到1的随机数 + 下线)
		BigDecimal pkgDiscountOne = upperLimit.subtract(lowerLimit).multiply(BigDecimal.valueOf(RandomUtils.nextDouble())).add(lowerLimit);
		BigDecimal pkgDiscountTwo = upperLimit.subtract(lowerLimit).multiply(BigDecimal.valueOf(RandomUtils.nextDouble())).add(lowerLimit);
		BigDecimal pkgDiscount = pkgDiscountOne.add(pkgDiscountTwo).divide(BigDecimal.valueOf(2)).setScale(2,BigDecimal.ROUND_UP);
	
		//计算订单优惠金额（95折之后根据奖金池优惠的金额）
		BigDecimal diff = price.subtract(price.multiply(pkgDiscount.add(BigDecimal.valueOf(0.05)))).setScale(2,BigDecimal.ROUND_UP);
		//奖池中剩余的金额  = 原奖池金额 - 订单优惠金额 
		shop.setPond(shop.getPond().subtract(diff));
		shop.setLastUpdateDate(new Timestamp(new Date().getTime()));
		shopMapper.update(shop);
        
        return pkgDiscount;
    }
}
