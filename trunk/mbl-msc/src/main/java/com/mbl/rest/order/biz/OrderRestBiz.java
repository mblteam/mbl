package com.mbl.rest.order.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.OrderVO;

public interface OrderRestBiz {

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
	
	/**
	 * 通过订单编号查询订单
	 * 功能详细描述
	 * @param query
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	OrderVO getOrderByOrderNo(Map<String, Object> query);

	/****
	 * 更新订单状态
	 * 
	 * @param params
	 * @return
	 */
	int updateOrderStatus(Map<String, Object> params);
	
	
	/**
	 * 取消订单
	 * @param query
	 * @throws BizException 
	 */
	void cancelOrder(Map<String, Object> query) throws BizException;
	
	/****
	 * 支付完成后更新订单状态
	 * @param params
	 * @return
	 */
	void payAfterSuccess(HttpServletRequest request) throws Exception ;

	/****
	 * 微信支付完成后更新订单状态
	 * @param params
	 * @return
	 */
	void wxPayAfterSuccess(HttpServletRequest request) throws Exception;

	List<OrderVO> findListPageByParams(Map<String, Object> map, Integer page,
			Integer pageSize);
}
