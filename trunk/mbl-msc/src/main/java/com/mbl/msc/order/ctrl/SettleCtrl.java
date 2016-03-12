package com.mbl.msc.order.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.OrderVO;
import com.mbl.common.vo.SettleVO;
import com.mbl.msc.order.biz.OrderBiz;

/**
 * 结算管理
 * 
 * @author jjj
 * @create 2015年12月27日
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/settle")
public class SettleCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(SettleCtrl.class);

	@Resource
	private OrderBiz orderBiz;

	/***
	 * 商铺结算
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/settleByParams")
	public ResultVO settleByParams(@RequestBody SettleVO settleVO) {
		ResultVO resultVO = new ResultVO();
		try {
			if (StringUtils.isEmpty(settleVO.getOrderTimeBegin()) || StringUtils.isEmpty(settleVO.getOrderTimeEnd())) {
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("请选择订单开始日期和订单结束日期");
			} else {
				orderBiz.settleByParams(settleVO);
				resultVO.setErrorCode(ResultVO.SUCCESS);
			}

		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 查询订单列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findOrderList")
	public PageVO<OrderVO> findOrderList(PageVO<OrderVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("use", "settle");
			page.setSearch("opt", "findOrderList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			query.put("isPay", "1");
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
}
