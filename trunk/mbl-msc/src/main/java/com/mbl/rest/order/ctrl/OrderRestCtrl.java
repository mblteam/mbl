package com.mbl.rest.order.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.util.wx2.CommonUtilPub;
import com.mbl.common.vo.OrderDetailVO;
import com.mbl.common.vo.OrderVO;
import com.mbl.rest.order.biz.OrderRestBiz;
import com.mbl.rest.order.biz.OrderRestDetailBiz;

/**
 * 订单管理
 * 
 * @author zl
 * @create 2015年12月31日
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/order")
public class OrderRestCtrl {

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(OrderRestCtrl.class);

	@Resource
	private OrderRestBiz orderRestBiz;

	@Resource
	private OrderRestDetailBiz orderRestDetailBiz;
	
	/**
	 * 查询订单列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findOrderList")
	public ResultVO findOrderList(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			List<OrderVO> orderList = orderRestBiz.findListPageByParams(query, 1, 50);
			List list = new ArrayList();
			for (OrderVO ov : orderList) {
				String discountStr = null;
				if(null!=ov.getDiscount()){
					discountStr = new DecimalFormat("#.00").format(ov.getDiscount().multiply(BigDecimal.valueOf(10)));
				}else{
					discountStr = "";
				}
				Map map = BaseFunction.convertBean(ov);
				map.put("discount", discountStr);
				list.add(map);
			}
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(list);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 查询订单明细
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findOrderDetail")
	public ResultVO findOrderDetail(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			OrderVO order = orderRestBiz.getOrderByOrderNo(query);
			query.put("orderId", order.getOrderId());
			List<OrderDetailVO> orderDetailList = orderRestDetailBiz.findOrderDetailList(query);
			String discountStr = null;
			if(null!=order.getDiscount()){
				discountStr = new DecimalFormat("#.00").format(order.getDiscount().multiply(BigDecimal.valueOf(10)));
			}else{
				discountStr = "";
			}
			Map map = BaseFunction.convertBean(order);
			map.put("discount", discountStr);
			query.put("order", map);
			
			List list = new ArrayList();
			for (OrderDetailVO odv : orderDetailList) {
				list.add(BaseFunction.convertBean(odv));
			}
			query.put("orderDetailList", list);

			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(query);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/updateOrderStatus")
	public ResultVO updateOrderStatus(@RequestBody(required = true) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			orderRestBiz.updateOrderStatus(query);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	
	@RequestMapping(value = "/payAfterSuccess")
	public ResultVO payAfterSuccess(HttpServletRequest request) {
		ResultVO resultVO = new ResultVO();
		try {
			LOGGER.error("pay return begin");
			orderRestBiz.payAfterSuccess(request);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			LOGGER.error("pay return end");
		} catch (Exception e) {
			LOGGER.error("pay return error:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/wxPayAfterSuccess")
	public void wxPayAfterSuccess(HttpServletRequest request,HttpServletResponse respon) {
		HashMap<String,Object> wxPayMap = new HashMap<String,Object>();
		try {
			PrintWriter out = respon.getWriter();
			LOGGER.error("wxpay return begin");
			orderRestBiz.wxPayAfterSuccess(request);
			wxPayMap.put("return_code", "SUCCESS");
			wxPayMap.put("return_msg", "OK");
			out.println(CommonUtilPub.mapToXml(wxPayMap));
			out.flush();
			out.close();
			LOGGER.error("wx pay return end");
		} catch (BizException e) {
			try {
				PrintWriter out = respon.getWriter();
				wxPayMap.put("return_code", "FAIL");
				out.println(CommonUtilPub.mapToXml(wxPayMap));
				LOGGER.error("wx pay return error:", e);
				out.flush();
				out.close();
			} catch (IOException e1) {}
		} catch (Exception e) {
			try {
				PrintWriter out = respon.getWriter();
				wxPayMap.put("return_code", "FAIL");
				wxPayMap.put("return_msg", e.getMessage());
				out.write(CommonUtilPub.mapToXml(wxPayMap));
				out.flush();
				out.close();
				LOGGER.error("wx pay return error:", e);
			} catch (IOException e1) {}
		}
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder")
	public ResultVO cancelOrder(@RequestBody Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			orderRestBiz.cancelOrder(query);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setErrorMsg("订单已取消！");
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
}
