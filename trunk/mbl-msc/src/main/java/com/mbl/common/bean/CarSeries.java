package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 车系
 * 功能详细描述
 * @author zl
 * @create 2015年12月15日 下午1:10:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CarSeries extends BaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6578618966961435499L;

	/***
	 * 主键id
	 */
	private String csId;
	/**
	 * 品牌关联id
	 */
	private String cbId;
	/**
	 * 车系code
	 */
	private String seriesCode;
	/***
	 * 车系
	 */
	private String series;
	/***
	 * 厂家
	 */
	private String factory;
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
	public String getCsId() {
		return csId;
	}
	public void setCsId(String csId) {
		this.csId = csId;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getCbId() {
		return cbId;
	}
	public void setCbId(String cbId) {
		this.cbId = cbId;
	}
	public String getSeriesCode() {
		return seriesCode;
	}
	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}
}
