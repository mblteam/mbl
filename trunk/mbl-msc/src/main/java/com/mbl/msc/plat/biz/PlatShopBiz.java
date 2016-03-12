package com.mbl.msc.plat.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.ShopVO;

public interface PlatShopBiz {
	
	/***
	 * 查询accountCode是否存在
	 * @param accountCode
	 * @return
	 */
	String checkAccountCodeIsExists(String accountCode,String accountId);

	/****
	 * 根据code查询门店是否存在
	 * @param shopCode
	 * @return
	 */
	String checkShopCodeIsExists(String shopCode,String shopId);
	
	/****
	 * 根据code获得门店详情
	 * 
	 * @param shopCode
	 * @return
	 */
	ShopVO getShopByCode(String shopCode);

	/****
	 * 详情
	 * 
	 * @param shopId
	 * @return
	 */
	ShopVO getShopById(String shopId);

	/****
	 * 保存门店信息
	 * @param shopVO
	 * @return
	 * @throws BizException 
	 */
	int saveShop(ShopVO shopVO) throws Exception;

	/****
	 * 分页查询
	 * 
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<ShopVO> findShopListByParams(Map<String, Object> map, Integer page, Integer pageSize);

	/***
	 * 分页查询总数
	 * 
	 * @param map
	 * @return
	 */
	Long countShopListByParams(Map<String, Object> map);

	/**
	 * 通过店铺id删除店铺
	 * 功能详细描述
	 * @param string
	 * @throws BizException 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	void delShopById(String shopId) throws BizException;
}
