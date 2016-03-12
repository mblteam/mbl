package com.mbl.rest.shop.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.Shop;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.util.wx2.CommonUtilPub;
import com.mbl.common.vo.ShopVO;
import com.mbl.rest.shop.biz.ShopBiz;
import com.mbl.rest.shop.vo.ShopDetailVo;
import com.mbl.rest.shop.vo.ShopRequestVo;

/**
 * 店铺接口
 * 
 * @author xjs
 * @create 2015年12月05日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/shop")
public class ShopCtrl {
	
	@Resource
	ShopBiz shopBiz;
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(ShopCtrl.class);
	
	/**
	 * 4公里方位内的店铺查询
	 * @return
	 */
	@RequestMapping(value = "/searchScopeShop",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchScopeShop(@RequestBody ShopRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("4公里方位内店铺查询");
		
		try {
			List resultList = new ArrayList();
			List<ShopVO> shopList=shopBiz.searchScopeShop(requestVo);
			for (ShopVO sv : shopList) {
				resultList.add(BaseFunction.convertBean(sv));
			}
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(resultList);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	
	/**
	 * 获取店铺详情
	 * @return
	 */
	@RequestMapping(value = "/searchShopDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchShopDetail(@RequestBody ShopRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("店铺详情查询");
		
		try {
			ShopDetailVo responseVo = shopBiz.searchShopDetail(requestVo.getShopId());
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(BaseFunction.convertBean(responseVo));
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
}
