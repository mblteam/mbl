package com.mbl.msc.plat.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.ShopCar;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.CarBrandMapper;
import com.mbl.common.mapper.ShopCarMapper;
import com.mbl.common.vo.ShopCarVO;
import com.mbl.msc.plat.biz.ShopCarBiz;

/**
 * 门店车辆逻辑类
 * 
 * @author jjj
 * @create 2015年12月20日 上午15:58
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "shopCarBiz")
@Transactional
public class ShopCarBizImpl implements ShopCarBiz {
	@Resource
	private ShopCarMapper shopCarMapper;
	
	@Resource
	private CarBrandMapper carBrandMapper;
	
	@Override
	public List<CarBrand> selectBrandList(){
		return carBrandMapper.findListByParams(new HashMap<String,Object>());
	}
	
	@Override
	public List<ShopCarVO> selectShopCarListByParams(Map<String, Object> map, Integer page, Integer pageSize) {
		return shopCarMapper.selectListByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}

	@Override
	public Long countShopCarListByParams(Map<String, Object> map) {
		return shopCarMapper.countListByParams(map);
	}

	@Override
	public int updateDeleteFlagById(Map<String, Object> map) {
		return shopCarMapper.updateDeleteFlagById(map);
	}

	@Override
	public int saveShopCar(ShopCarVO shopCarVO) {
		if(shopCarVO == null) {
			return 0;
		}
		ShopCar shopCar = new ShopCar();
		BeanUtils.copyProperties(shopCarVO, shopCar);
		if(StringUtils.isEmpty(shopCar.getScId())) {
			shopCar.setScId(UUID.randomUUID().toString());
			return shopCarMapper.save(shopCar);
		} else {
			return shopCarMapper.update(shopCar);
		}
	}

	@Override
	public ShopCarVO getShopCarById(Map<String, Object> map) {
		return shopCarMapper.getShopCarById(map);
	}

}
