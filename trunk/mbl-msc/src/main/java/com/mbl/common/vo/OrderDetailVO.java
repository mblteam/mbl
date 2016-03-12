package com.mbl.common.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 订单明细表实体(M_ORDER_DETAIL)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class OrderDetailVO extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787239326383022756L;

	/**  */
	private String orderDetailId;

	/**  */
	private String orderId;

	/**  */
	private String pkgId;

	/** 套餐描述 */
	private String description;

	/** 套餐折扣 */
	private BigDecimal pkgDiscount;

	/** 套餐价格 */
	private BigDecimal pkgPrice;

	/** 套餐开始时间 */
	private Timestamp pkgStartDate;

	/** 套餐结束时间 */
	private Timestamp pkgEndDate;
	
	/** 套餐开始时间Str */
	private String pkgStartDateStr;

	/** 套餐结束时间Str */
	private String pkgEndDateStr;

	/** 套餐类型（月卡、年卡、一次性） */
	private String pkgType;

	/** 套餐限制数 */
	private String pkgNum;
	/** 套餐类型（月卡、年卡、一次性） */
	private String pkgTypeName;

	/****
	 * 套餐名称
	 */
	private String pkgName;
	
	/**
	 * 适用车型
	 */
	private String applyModel;

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getOrderDetailId() {
		return this.orderDetailId;
	}

	/**
	 * 设置
	 * 
	 * @param orderDetailId
	 * 
	 */
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getOrderId() {
		return this.orderId;
	}

	/**
	 * 设置
	 * 
	 * @param orderId
	 * 
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getPkgId() {
		return this.pkgId;
	}

	/**
	 * 设置
	 * 
	 * @param pkgId
	 * 
	 */
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
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
	 * 获取套餐折扣
	 * 
	 * @return 套餐折扣
	 */
	public BigDecimal getPkgDiscount() {
		return this.pkgDiscount;
	}

	/**
	 * 设置套餐折扣
	 * 
	 * @param pkgDiscount
	 *            套餐折扣
	 */
	public void setPkgDiscount(BigDecimal pkgDiscount) {
		this.pkgDiscount = pkgDiscount;
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

	public String getPkgTypeName() {
		return pkgTypeName;
	}

	public void setPkgTypeName(String pkgTypeName) {
		this.pkgTypeName = pkgTypeName;
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