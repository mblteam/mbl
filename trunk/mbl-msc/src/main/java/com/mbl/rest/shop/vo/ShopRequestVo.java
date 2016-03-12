package com.mbl.rest.shop.vo;

/**
 * 
 * 店铺请求参数
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class ShopRequestVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	/* 经度 */
	private String longitude;
	
	/* 纬度 */
	private String latitude;
	
	/* 搜索条件 位置 店名 */
	private String searchStr;
	
	private String shopId;
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	
}
