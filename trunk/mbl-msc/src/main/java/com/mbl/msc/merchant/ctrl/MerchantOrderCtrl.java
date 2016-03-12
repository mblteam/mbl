package com.mbl.msc.merchant.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.OrderVO;
import com.mbl.msc.order.biz.OrderBiz;

/**
 * 商家订单管理
 * 功能详细描述
 * @author zl
 * @create 2016年1月10日 上午12:45:47 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/merchant/order")
public class MerchantOrderCtrl {
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(MerchantOrderCtrl.class);
	
	@Resource
	private OrderBiz orderBiz;
	
	/**
	 * 查询订单列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findRepairOrderList")
	public PageVO<OrderVO> findOrderList(PageVO<OrderVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "merchant");
			page.setSearch("use", "order");
			page.setSearch("opt", "findRepairOrderList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			query.put("orderType", DictCode.VEHICLE_MAINTENANCE_ORDER);
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
	
	@RequestMapping(value = "/setRepairOrderPrice")
	public ResultVO setRepairOrderPrice(@RequestBody(required = false) Map<String, Object> query) {
		
		ResultVO resultVO = new ResultVO();
		try {
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(orderBiz.setRepairOrderPrice(query));
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
}
