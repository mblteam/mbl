package com.mbl.msc.plat.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.vo.ShopCarVO;

public interface ShopCarBiz {
	
	/***
	 * 查询车辆品牌
	 * @return
	 */
	List<CarBrand> selectBrandList();
	/***
	 * 分页查询
	 * 
	 * @param map
	 * @param rowBounds
	 * @return
	 */
	List<ShopCarVO> selectShopCarListByParams(Map<String, Object> map, Integer page, Integer pageSize);

	/***
	 * 
	 * @param map
	 * @return
	 */
	Long countShopCarListByParams(Map<String, Object> map);

	/***
	 * 删除用，更新删除标记
	 * 
	 * @param map
	 * @return
	 */
	int updateDeleteFlagById(Map<String, Object> map);

	/***
	 * 保存门店车辆
	 * 
	 * @param shopCarVO
	 * @return
	 */
	int saveShopCar(ShopCarVO shopCarVO);
	
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	ShopCarVO getShopCarById(Map<String, Object> map);
}
