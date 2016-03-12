package com.mbl.rest.order.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.vo.OrderDetailVO;

public interface OrderRestDetailBiz {

	/***
	 * 查询订单明细列表
	 * 
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<OrderDetailVO> findOrderDetailList(Map<String, Object> map);

}
