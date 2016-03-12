package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.ShopPackage;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.ShopPackageVO;

public interface ShopPackageMapper extends BaseMapper<ShopPackage> {

	ShopPackageVO getShopPackageById(String spId);
	
	Long countShopPackageListByParams(Map<String, Object> map);
	/****
	 * 查询套餐列表,分页
	 * 
	 * @param map
	 * @return
	 */
	List<ShopPackageVO> findShopPackageListByParams(Map<String, Object> map, RowBounds rowBounds);

	/****
	 * 查询套餐列表
	 * 
	 * @param map
	 * @return
	 */
	List<ShopPackageVO> findShopPackageListByParams(Map<String, Object> map);
	/****
	 * 根据条件删除数据
	 * @param shopId
	 * @param ids
	 * @return
	 */
	int delByParams(@Param("shopId") String shopId, @Param("ids") List<String> ids);

	/***
	 * 根据id集合删除套餐信息
	 * @param ids
	 * @return
	 */
	int delByIds(@Param("ids") List<String> ids);
	
	/****
	 * 根据id删除套餐
	 * @param id
	 * @return
	 */
	int delById(String id);
}