package com.mbl.common.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 
 * 店铺请求参数
 * 
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class ShopPackageVO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5787239326383022759L;

	/** 主键id */
	private String spId;

	/** 店铺id */
	private String shopId;

	/** 套餐id */
	private String pkgId;

	/** 套餐描述 */
	private String description;

	/** 套餐价格 */
	private BigDecimal pkgPrice;

	/** 套餐开始时间 */
	private Timestamp pkgStartDate;

	/** 套餐结束时间 */
	private Timestamp pkgEndDate;
	
	/** 套餐开始时间 */
	private String pkgStartDateStr;

	/** 套餐结束时间 */
	private String pkgEndDateStr;

	/** 套餐简称 */
	private String pkgName;

	/** 套餐类型（月卡、年卡、一次性、维修） */
	private String pkgType;

	/** 套餐限制数 */
	private String pkgNum;
	/** 套餐类型名称**/
	private String pkgTypeName;
	/*** 门店名称 **/
	private String shopName;
	/** 套餐内容 */
    private String pkgContent;
    
    /** 适用车型 */
    private String applyModel;

	public String getPkgTypeName() {
		return pkgTypeName;
	}

	public void setPkgTypeName(String pkgTypeName) {
		this.pkgTypeName = pkgTypeName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 获取套餐id
	 * 
	 * @return 套餐id
	 */
	public String getPkgId() {
		return this.pkgId;
	}

	/**
	 * 设置套餐id
	 * 
	 * @param pkgId
	 *            套餐id
	 */
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}

	/**
	 * 获取套餐简称
	 * 
	 * @return 套餐简称
	 */
	public String getPkgName() {
		return this.pkgName;
	}

	/**
	 * 设置套餐简称
	 * 
	 * @param pkgName
	 *            套餐简称
	 */
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	/**
	 * 获取套餐类型（月卡、年卡、一次性）
	 * 
	 * @return 套餐类型（月卡、年卡、一次性）
	 */
	public String getPkgType() {
		return this.pkgType;
	}

	/**
	 * 设置套餐类型（月卡、年卡、一次性）
	 * 
	 * @param pkgType
	 *            套餐类型（月卡、年卡、一次性）
	 */
	public void setPkgType(String pkgType) {
		this.pkgType = pkgType;
	}

	/**
	 * 获取套餐限制数
	 * 
	 * @return 套餐限制数
	 */
	public String getPkgNum() {
		return this.pkgNum;
	}

	/**
	 * 设置套餐限制数
	 * 
	 * @param pkgNum
	 *            套餐限制数
	 */
	public void setPkgNum(String pkgNum) {
		this.pkgNum = pkgNum;
	}

	/**
	 * 获取主键id
	 * 
	 * @return 主键id
	 */
	public String getSpId() {
		return this.spId;
	}

	/**
	 * 设置主键id
	 * 
	 * @param spId
	 *            主键id
	 */
	public void setSpId(String spId) {
		this.spId = spId;
	}

	/**
	 * 获取店铺id
	 * 
	 * @return 店铺id
	 */
	public String getShopId() {
		return this.shopId;
	}

	/**
	 * 设置店铺id
	 * 
	 * @param shopId
	 *            店铺id
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 获取套餐描述
	 * 
	 * @return 套餐描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置套餐描述
	 * 
	 * @param description
	 *            套餐描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取套餐价格
	 * 
	 * @return 套餐价格
	 */
	public BigDecimal getPkgPrice() {
		return this.pkgPrice;
	}

	/**
	 * 设置套餐价格
	 * 
	 * @param pkgPrice
	 *            套餐价格
	 */
	public void setPkgPrice(BigDecimal pkgPrice) {
		this.pkgPrice = pkgPrice;
	}

	/**
	 * 获取套餐开始时间
	 * 
	 * @return 套餐开始时间
	 */
	public Timestamp getPkgStartDate() {
		return this.pkgStartDate;
	}

	/**
	 * 设置套餐开始时间
	 * 
	 * @param pkgStartDate
	 *            套餐开始时间
	 */
	public void setPkgStartDate(Timestamp pkgStartDate) {
		this.pkgStartDate = pkgStartDate;
	}

	/**
	 * 获取套餐结束时间
	 * 
	 * @return 套餐结束时间
	 */
	public Timestamp getPkgEndDate() {
		return this.pkgEndDate;
	}

	/**
	 * 设置套餐结束时间
	 * 
	 * @param pkgEndDate
	 *            套餐结束时间
	 */
	public void setPkgEndDate(Timestamp pkgEndDate) {
		this.pkgEndDate = pkgEndDate;
	}

	public String getPkgContent() {
		return pkgContent;
	}

	public void setPkgContent(String pkgContent) {
		this.pkgContent = pkgContent;
	}

	public String getApplyModel() {
		return applyModel;
	}

	public void setApplyModel(String applyModel) {
		this.applyModel = applyModel;
	}

	public String getPkgStartDateStr() {
		return pkgStartDateStr;
	}

	public void setPkgStartDateStr(String pkgStartDateStr) {
		this.pkgStartDateStr = pkgStartDateStr;
	}

	public String getPkgEndDateStr() {
		return pkgEndDateStr;
	}

	public void setPkgEndDateStr(String pkgEndDateStr) {
		this.pkgEndDateStr = pkgEndDateStr;
	}
	
}
