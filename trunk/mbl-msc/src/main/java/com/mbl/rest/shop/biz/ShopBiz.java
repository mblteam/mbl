package com.mbl.rest.shop.biz;

import java.util.List;

import com.mbl.common.bean.Shop;
import com.mbl.common.vo.ShopVO;
import com.mbl.rest.shop.vo.ShopDetailVo;
import com.mbl.rest.shop.vo.ShopRequestVo;



/**
 * 店铺接口
 * @author xjs
 * @create 2015年12月06日 下午22:02:37 
 * @version 1.0.0
 */
public interface ShopBiz {

	/**
	 * 获取店铺详情信息
	 * @param shopId
	 * @return
	 */
	ShopDetailVo searchShopDetail(String shopId);

	/**
	 * 获取4公里内的店铺
	 * @param requestVo
	 * @return
	 */
	List<ShopVO> searchScopeShop(ShopRequestVo requestVo);
	
}
