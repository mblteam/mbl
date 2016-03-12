package com.mbl.msc.plat.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.vo.ShopPackageVO;

public interface ShopPkgBiz {
	Long countShopPackageListByParams(Map<String, Object> map);
	/****
	 * 查询门店套餐列表
	 * 
	 * @param map
	 * @return
	 */
	List<ShopPackageVO> findShopPackageList(Map<String, Object> map,Integer page,Integer pageSize);

	/****
	 * 批量删除套餐,门店套餐软删除
	 * 
	 * @param ids
	 * @return
	 */
	int batchDeleteShopPackageList(List<String> ids);

	/***
	 * 批量更新套餐,门店套餐软删除
	 * 
	 * @param shopId
	 * @param list
	 * @return
	 */
	int saveShopPackage(ShopPackageVO shopPackageVO);

	/****
	 * 根据id删除套餐
	 * 
	 * @param id
	 * @return
	 */
	int delById(String id);

	/***
	 * 根据id查询套餐
	 * 
	 * @param spId
	 * @return
	 */
	ShopPackageVO getShopPackageById(String spId);

}
