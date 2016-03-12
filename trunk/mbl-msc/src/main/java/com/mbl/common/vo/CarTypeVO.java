package com.mbl.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.mbl.common.bean.CarSeries;

/**
 * 车类型VO
 * 功能详细描述
 * @author zl
 * @create 2015年12月16日 下午4:38:28 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CarTypeVO {

	/***
	 * 品牌id
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
	
	/**
	 * 车系list
	 */
	private List<CarSeries> carSeriesList = new ArrayList<CarSeries>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<CarSeries> getCarSeriesList() {
		return carSeriesList;
	}

	public void setCarSeriesList(List<CarSeries> carSeriesList) {
		this.carSeriesList = carSeriesList;
	}
	
}
