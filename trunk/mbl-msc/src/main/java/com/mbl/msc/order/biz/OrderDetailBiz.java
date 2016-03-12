package com.mbl.msc.order.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.vo.OrderDetailVO;

public interface OrderDetailBiz {

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
