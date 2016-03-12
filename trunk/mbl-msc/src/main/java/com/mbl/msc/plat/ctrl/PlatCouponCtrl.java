package com.mbl.msc.plat.ctrl;

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
import com.mbl.common.vo.CouponVO;
import com.mbl.msc.plat.biz.CouponBiz;

/**
 * 优惠券类
 * 
 * @author jj
 * @create 2015年12月12日 下午11:43:33
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/plat/platcoupon")
public class PlatCouponCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(PlatCouponCtrl.class);
	@Resource
	private CouponBiz couponBiz;
	
	/**
	 * 查询优惠券列表
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/findCouponList")
	public PageVO<CouponVO> findCouponList(PageVO<CouponVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setSearch("md", "plat");
			page.setSearch("use", "platcoupon");
			page.setSearch("opt", "findCouponList");
			page.setQuery(query);
			page.setTotalSize(couponBiz.countCouponByParams(query));
			List<CouponVO> couponList =  couponBiz.findCouponListByParams(query, page.getPage(), page.getPageSize());
			page.setData(couponList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}
	
	/***
	 * 失效
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/disabledCoupon")
	public ResultVO disabledCoupon(@RequestBody Map<String,Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String couponId = (String)map.get("couponId");
			couponBiz.disabledCouponStatusById(couponId);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 查询
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getCouponById")
	public ResultVO getCouponById(@RequestBody Map<String,Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String couponId = (String)map.get("couponId");
			resultVO.setResult(couponBiz.getCouponById(couponId));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 保存
	 * 
	 * @param couponVO
	 * @return
	 */
	@RequestMapping(value = "/saveCoupon")
	public ResultVO saveCoupon(@RequestBody CouponVO couponVO) {
		ResultVO resultVO = new ResultVO();
		try {
			couponBiz.saveCoupon(couponVO);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

}
