package com.mbl.msc.order.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.Order;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.OrderVO;
import com.mbl.common.vo.SettleVO;

public interface OrderBiz {

	/***
	 * 查询订单翻页
	 * 
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<OrderVO> findOrderList(Map<String, Object> map, Integer page, Integer pageSize);

	/***
	 * 翻页查询总数
	 * 
	 * @param params
	 * @return
	 */
	Long countListByParams(Map<String, Object> params);

	/****
	 * 查询订单头
	 * 
	 * @param params
	 * @return
	 */
	OrderVO getOrderById(Map<String, Object> params);

	/***
	 * 
	 * @param settleVO
	 * @return
	 */
	int settleByParams(SettleVO settleVO);

	/****
	 * 更新订单状态
	 * 
	 * @param params
	 * @return
	 */
	int updateOrderStatus(Map<String, Object> params);

	Order setRepairOrderPrice(Map<String, Object> query) throws BizException;
}
