package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.Order;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.OrderVO;
import com.mbl.common.vo.SettleVO;

public interface OrderMapper extends BaseMapper<Order> {

	List<OrderVO> findListPageByParams(Map<String, Object> map, RowBounds rowBounds);
	
	List<OrderVO> findListPageByParams(Map<String, Object> map);

	Long countListByParams(Map<String, Object> params);
	
	OrderVO getOrderById(Map<String, Object> params);
	
	OrderVO getOrderByOrderNo(Map<String, Object> query);
	
	/***
	 * 结算
	 * @param settleVO
	 * @return
	 */
	int settleByParams(SettleVO settleVO);
	
	int updateOrderStatus(Map<String, Object> params);

	String findMaxOrderNo();

	int payAfterUpdateOrderStatus(Map<String, Object> params);
}