package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.ShopCar;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.ShopCarVO;

public interface ShopCarMapper  extends BaseMapper<ShopCar>{

	List<ShopCarVO> selectListByParams(Map<String, Object> map, RowBounds rowBounds);
	
	Long countListByParams(Map<String, Object> map);
	
	int updateDeleteFlagById(Map<String, Object> map);
	
	ShopCarVO getShopCarById(Map<String, Object> map);

	void delByShopId(String shopId);
}
