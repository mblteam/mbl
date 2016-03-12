package com.mbl.rest.order.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.mapper.OrderDetailMapper;
import com.mbl.common.vo.OrderDetailVO;
import com.mbl.rest.order.biz.OrderRestDetailBiz;

@Service
@Transactional
public class OrderRestDetailBizImpl implements OrderRestDetailBiz {

	@Resource
	private OrderDetailMapper orderDetailMapper;

	/***
	 * 查询订单明细
	 * 
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<OrderDetailVO> findOrderDetailList(Map<String, Object> map) {
		return orderDetailMapper.findOrderDetailListByParams(map);
	}

}
