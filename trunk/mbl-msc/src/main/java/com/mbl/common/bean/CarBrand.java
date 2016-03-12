package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

public class CarBrand extends BaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6578618966961435499L;

	/***
	 * 主键id
	 */
	private String cbId;
	/***
	 * 品牌编码
	 */
	private String brandCode;
	/***
	 * 品牌名
	 */
	private String brandName;
	/***
	 * 图片url
	 */
	private String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCbId() {
		return cbId;
	}
	public void setCbId(String cbId) {
		this.cbId = cbId;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
