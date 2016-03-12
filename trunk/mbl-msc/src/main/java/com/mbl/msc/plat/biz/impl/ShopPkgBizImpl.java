package com.mbl.msc.plat.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.ShopPackage;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.ShopPackageMapper;
import com.mbl.common.vo.ShopPackageVO;
import com.mbl.msc.plat.biz.ShopPkgBiz;

/**
 * 门店套餐
 * 
 * @author jjj
 * @create 2015年12月10日 上午12:22:01
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "shopPkgBiz")
@Transactional
public class ShopPkgBizImpl implements ShopPkgBiz {
	@Resource
	private ShopPackageMapper shopPackageMapper;

	public ShopPackageVO getShopPackageById(String spId) {
		return shopPackageMapper.getShopPackageById(spId);
	}
	@Override
	public Long countShopPackageListByParams(Map<String, Object> map) {
		return shopPackageMapper.countShopPackageListByParams(map);
	}
	@Override
	public List<ShopPackageVO> findShopPackageList(Map<String, Object> map,Integer page,Integer pageSize) {
		return shopPackageMapper.findShopPackageListByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}

	@Override
	public int batchDeleteShopPackageList(List<String> ids) {
		return shopPackageMapper.delByIds(ids);
	}

	@Override
	public int delById(String id) {
		return shopPackageMapper.delById(id);
	}
	
	@Override
	public int saveShopPackage(ShopPackageVO shopPackageVo) {
		ShopPackage shopPackage = new ShopPackage();

		BeanUtils.copyProperties(shopPackageVo, shopPackage);

		if (StringUtils.isNotEmpty(shopPackage.getSpId())) {
			shopPackage.setPkgId(shopPackage.getSpId());
			return shopPackageMapper.update(shopPackage);
		} else {
			shopPackage.setSpId(UUID.randomUUID().toString());
			shopPackage.setPkgId(shopPackage.getPkgId());
			return shopPackageMapper.save(shopPackage);
		}
	}
}
