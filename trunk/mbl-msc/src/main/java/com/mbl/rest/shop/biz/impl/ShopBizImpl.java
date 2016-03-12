package com.mbl.rest.shop.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.DictLine;
import com.mbl.common.bean.Shop;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.mapper.DictMapper;
import com.mbl.common.mapper.ShopMapper;
import com.mbl.common.mapper.ShopPackageMapper;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.vo.ShopPackageVO;
import com.mbl.common.vo.ShopVO;
import com.mbl.rest.shop.biz.ShopBiz;
import com.mbl.rest.shop.vo.ShopDetailVo;
import com.mbl.rest.shop.vo.ShopRequestVo;

/**
 * 店铺
 * @author xjs
 * @create 2015年12月06日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "shopBiz")
@Transactional
public class ShopBizImpl implements ShopBiz {

	@Resource
	ShopMapper shopMapper;
	
	@Resource
	ShopPackageMapper shopPackageMapper;
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(ShopBizImpl.class);
	
	/**
	 * 获取4公里范围内的店铺
	 */
	@Override
	public List<ShopVO> searchScopeShop(ShopRequestVo srVo) {
		
		String longitude = srVo.getLongitude(), latitude = srVo.getLatitude();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("longitude", longitude);
		map.put("latitude", latitude);
	    map.put("searchStr", srVo.getSearchStr());
		List<ShopVO> shopList=shopMapper.findShopListByParams(map, new RowBounds());
		
		List<ShopVO> newShopList=new LinkedList<ShopVO>();
		for(ShopVO shop:shopList){
			//计算两点之间的距离
			double distance=CommonUtils.getDistance(longitude, latitude, shop.getLongitude().toString(), shop.getLatitude().toString());
			//if(distance<=4000){
				shop.setDistance(String.format("%.2f", distance/1000));
				newShopList.add(shop);
			//}
		}
		
		return newShopList;
	}
	
	@Resource
	DictMapper dictMapper;

	/**
	 * 获取店铺详情信息
	 */
	@Override
	public ShopDetailVo searchShopDetail(String shopId) {
		
		ShopDetailVo shopDetailVo=new ShopDetailVo();
		Shop shop = shopMapper.getById(shopId);
		try {
			BeanUtils.copyProperties(shopDetailVo, shop);
			List<DictLine> dicts = dictMapper.findListByHeadCode(ConstantClass.DICT_SHOP_TYPE);
			shopDetailVo.setShopTypeStr(ConstantClass.getDictValueByDictCode(dicts,shopDetailVo.getShopType()));
		} catch (Exception e) {
			LOGGER.info("对象属性复制时发生错误！");
		} 
		 // 店铺套餐信息 
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
//		map.put("currentTimestamp", new Timestamp(new Date().getTime()));
	    List<ShopPackageVO> packages = shopPackageMapper.findShopPackageListByParams(map);
		
		ShopPackageVO spVo = new ShopPackageVO();
    	spVo.setSpId("000000000000000000000000000000000000");
    	spVo.setShopId(shopId);
    	spVo.setShopName(shop.getShopName());
    	spVo.setPkgType(DictCode.MAINTENANCE);
    	spVo.setPkgTypeName(ConstantClass.getDictValueByDictCode(dictMapper.findListByHeadCode("pkg_type"), DictCode.MAINTENANCE));
    	spVo.setPkgId("000000000000000000000000000000000000");
    	spVo.setPkgName("维修");
    	spVo.setPkgContent("维修");
    	spVo.setPkgPrice(BigDecimal.valueOf(0));
    	
		packages.add(0, spVo);
		
		 List<ShopPackageVO> shopPackages = new ArrayList<ShopPackageVO>();
		    for (ShopPackageVO spv : packages) {
				BaseFunction.nullConverNullString(spv);
				shopPackages.add(spv);
			}
		    shopDetailVo.setPackages(shopPackages);
		
		return shopDetailVo;
	}
}
