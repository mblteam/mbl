package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.Shop;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.ShopVO;

public interface ShopMapper extends BaseMapper<Shop>{
    
	ShopVO getShopById(String shopId);
	
	ShopVO getShopByCode(String shopCode);
	
	List<ShopVO> findShopListByParams(Map<String, Object> map, RowBounds rowBounds);
	
	Long countShopListByParams(Map<String, Object> map);
}