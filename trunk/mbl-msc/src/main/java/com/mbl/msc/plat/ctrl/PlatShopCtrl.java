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

import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.AccountVO;
import com.mbl.common.vo.ShopCarVO;
import com.mbl.common.vo.ShopPackageVO;
import com.mbl.common.vo.ShopVO;
import com.mbl.msc.account.account.biz.AccountBiz;
import com.mbl.msc.plat.biz.PlatShopBiz;
import com.mbl.msc.plat.biz.ShopCarBiz;
import com.mbl.msc.plat.biz.ShopPkgBiz;

@RestController
@RequestMapping(value = "/plat/shop")
public class PlatShopCtrl {

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(PlatShopCtrl.class);
	
	@Resource
	private PlatShopBiz platShopBiz;
	
	@Resource
	private ShopPkgBiz shopPkgBiz;
	
	@Resource
	private ShopCarBiz shopCarBiz;
	
	@Resource
	private AccountBiz accountBiz;
	
	@RequestMapping(value = "/getShopCarById")
	public ResultVO getShopCarById(@RequestBody Map<String, Object> map){
		ResultVO resultVO = new ResultVO();
		try {
			resultVO.setResult(shopCarBiz.getShopCarById(map));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 查询门店车辆列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findShopCarList")
	public PageVO<ShopCarVO> findShopCarList(PageVO<ShopCarVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		page.setSearch("md", "plat");
		page.setSearch("use", "shop");
		page.setSearch("opt", "findShopCarList");

		ResultVO resultVO = new ResultVO();
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			page.setPageSize(5);
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			
			List<ShopCarVO> resultList = shopCarBiz.selectShopCarListByParams(query, page.getPage(), page.getPageSize());
			resultVO.setResult(resultList);
			page.setQuery(query);
			page.setTotalSize(shopCarBiz.countShopCarListByParams(query));
			page.setData(resultList);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/saveOrUpdateShopCar")
	public ResultVO saveOrUpdateShopCar(@RequestBody ShopCarVO shopCarVO){
		ResultVO resultVO = new ResultVO();
		try {
			shopCarBiz.saveShopCar(shopCarVO);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/deleteShopCar")
	public ResultVO deleteShopCar(@RequestBody Map<String,Object> map){
		ResultVO resultVO = new ResultVO();
		try {
			shopCarBiz.updateDeleteFlagById(map);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/getBrandList")
	public ResultVO getBrandList(@RequestBody(required = false) Map<String,Object> map){
		ResultVO resultVO = new ResultVO();
		try {
			resultVO.setResult(shopCarBiz.selectBrandList());
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 查询门店套餐
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/getShopPackage")
	public ResultVO getShopPackage(@RequestBody Map<String,Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String spId = (String) map.get("spId");
			ShopPackageVO shopPackageVO = shopPkgBiz.getShopPackageById(spId);
			resultVO.setResult(shopPackageVO);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 删除门店套餐，软删除
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/delShopPackage")
	public ResultVO delShopPackage(@RequestBody Map<String,Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String id = (String) map.get("spId");
			shopPkgBiz.delById(id);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 新增修改门店套餐列表
	 * @param shopId
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateShopPackage")
	public ResultVO saveOrUpdateShopPackage(@RequestBody ShopPackageVO shopPackageVO) {
		ResultVO resultVO = new ResultVO();
		try {
			shopPkgBiz.saveShopPackage(shopPackageVO);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 查询门店套餐列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findShopPackageList")
	public PageVO<ShopPackageVO> findShopPackageList(PageVO<ShopPackageVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		page.setSearch("md", "plat");
		page.setSearch("use", "shop");
		page.setSearch("opt", "findShopPackageList");
		ResultVO resultVO = new ResultVO();
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			page.setPageSize(5);
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			List<ShopPackageVO> resultList = shopPkgBiz.findShopPackageList(query, page.getPage(), page.getPageSize());
			page.setTotalSize(shopPkgBiz.countShopPackageListByParams(query));
			page.setData(resultList);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return null;
		}
	}
	/****
	 * 根据code查询门店编号是否存在
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/checkShopCodeIsExsist")
	public ResultVO checkShopCodeIsExsist(@RequestBody Map<String, Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String shopCode = (String) map.get("shopCode");
			String shopId = (String) map.get("shopId");
			String accountCode = (String) map.get("accountCode");
			String accountId =  (String) map.get("accountId");
			String result = platShopBiz.checkShopCodeIsExists(shopCode, shopId);
			resultVO.setErrorCode(result);
			if(ResultVO.FAIL.equals(result)) {
				resultVO.setErrorMsg("门店编号为" + shopCode +"的门店已存在");
				return resultVO;
			}
			if(StringUtils.isNotEmpty(accountCode)) {
				///校验账号是否存在
				result = platShopBiz.checkAccountCodeIsExists(accountCode,accountId);
				resultVO.setErrorCode(result);
				if(ResultVO.FAIL.equals(result)) {
					resultVO.setErrorCode(result);
					resultVO.setErrorMsg("账号"+accountCode+"已存在");
					return resultVO;
				}
			}
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 保存门店信息
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateShop")
	public ResultVO saveOrUpdateShop(@RequestBody ShopVO shopVO) {
		ResultVO resultVO = new ResultVO();
		try {
			platShopBiz.saveShop(shopVO);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (BizException e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 通过shopId删除门店详情
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/delShopById")
	public ResultVO delShopById(@RequestBody Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			platShopBiz.delShopById((String) query.get("shopId"));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		}catch (BizException e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/****
	 * 查询门店详情
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/getShopById")
	public ResultVO getShopById(@RequestBody Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			ShopVO shopVO = platShopBiz.getShopById((String) query.get("shopId"));

			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(shopVO);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	/**
	 * 查询门店列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findShopList")
	public PageVO<ShopVO> findShopList(PageVO<ShopVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "plat");
			page.setSearch("use", "shop");
			page.setSearch("opt", "findShopList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(platShopBiz.countShopListByParams(query));
			List<ShopVO> pkgList =platShopBiz.findShopListByParams(query, page.getPage(), page.getPageSize());
			page.setData(pkgList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}
}
