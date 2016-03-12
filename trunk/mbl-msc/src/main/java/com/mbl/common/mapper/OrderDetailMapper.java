package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.OrderDetail;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.OrderDetailVO;

public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

	List<OrderDetailVO> findOrderDetailListByParams(Map<String, Object> map);
	
	int delByOrderId(String orderId);

	void insertbatch(List<OrderDetail> detailList);

	void batchUpdate(String orderId);

}
