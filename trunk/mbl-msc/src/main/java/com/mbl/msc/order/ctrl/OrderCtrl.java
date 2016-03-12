package com.mbl.msc.order.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.SSOUtil;
import com.mbl.common.vo.AccountVO;
import com.mbl.common.vo.OrderDetailVO;
import com.mbl.common.vo.OrderVO;
import com.mbl.msc.account.account.AccountConstant;
import com.mbl.msc.order.biz.OrderBiz;
import com.mbl.msc.order.biz.OrderDetailBiz;

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
@RequestMapping(value = "/order")
public class OrderCtrl {

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(OrderCtrl.class);

	@Resource
	private OrderBiz orderBiz;

	@Resource
	private OrderDetailBiz orderDetailBiz;

//	/***
//	 * 商铺结算
//	 * @param query
//	 * @return
//	 */
//	@RequestMapping(value = "/settleByParams")
//	public ResultVO settleByParams(@RequestBody(required = true) Map<String, Object> query) {
//		ResultVO resultVO = new ResultVO();
//		try {
//			if (null == query) {
//				query = new HashMap<String, Object>();
//			}
//			orderBiz.settleByParams(query);
//			resultVO.setErrorCode(ResultVO.SUCCESS);
//		} catch (Exception e) {
//			LOGGER.error("e:", e);
//			resultVO.setErrorCode(ResultVO.FAIL);
//			resultVO.setErrorMsg(e.getMessage());
//		}
//		return resultVO;
//	}
//	
	/**
	 * 查询订单列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findOrderList")
	public PageVO<OrderVO> findOrderList(PageVO<OrderVO> page,
			@RequestBody(required = false) Map<String, Object> query,HttpServletRequest req) {
		try {
			page.setSearch("use", "order");
			page.setSearch("opt", "findOrderList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			AccountVO account = (AccountVO)req.getSession().getAttribute(SSOUtil.SSO_ACCOUNTS_COOKIE);
			if(AccountConstant.ACCOUNT_TYPE_SHOP.equals(account.getAccountType())){
				query.put("accountId", account.getAccountId());
			}else if(AccountConstant.ENQUIRE_TYPE_PLAT.equals(account.getAccountType())){
			}else{
				return null;
			}
			page.setQuery(query);
			page.setTotalSize(orderBiz.countListByParams(query));
			List<OrderVO> orderList = orderBiz.findOrderList(query, page.getPage(), page.getPageSize());
			page.setData(orderList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
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
			OrderVO order = orderBiz.getOrderById(query);
			List<OrderDetailVO> orderDetailList = orderDetailBiz.findOrderDetailList(query);
			query.put("order", order);
			query.put("orderDetailList", orderDetailList);

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
			orderBiz.updateOrderStatus(query);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
}
