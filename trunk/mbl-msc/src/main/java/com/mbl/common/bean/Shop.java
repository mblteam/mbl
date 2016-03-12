package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 店铺(M_SHOP)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Shop extends BaseEntity implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = -5600936652637037747L;

	/** 主键id */
	private String shopId;

	/** 店铺介绍 */
	private String introduce;

	/** 店铺地址 */
	private String address;

	/** 经度 */
	private BigDecimal longitude;

	/** 纬度 */
	private BigDecimal latitude;

	/** 合同折扣上限 */
	private BigDecimal discountUpperLimit;

	/** 合同折扣下限 */
	private BigDecimal discountLowerLimit;

	/** 合作状态 */
	private String cooperateStatus;

	/** 合作开始时间 */
	private Date cooperateStartDate;

	/** 合作结束时间 */
	private Date cooperateEndDate;

	/** 店铺状态1-有效 0-失效 */
	private String shopStatus;

	/** 结算方式 */
	private String balanceType;

	/** 推荐等级 */
	private String level;
	/** 店铺名称 **/
	private String shopName;
	/** 店铺编号 **/
	private String shopCode;
	/** 联系电话 **/
	private String tel;
	/** 店铺类型 **/
	private String shopType;
	/** 图片地址 **/
	private String url;

	/** 返点 **/
	private BigDecimal rebate;
	/** 结算日期 **/
	private String rebateDate;
	/** 奖池 **/
	private BigDecimal pond;
	
	public String getRebateDate() {
		return rebateDate;
	}

	public void setRebateDate(String rebateDate) {
		this.rebateDate = rebateDate;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public String getUrl() {
		return url;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 获取主键id
	 * 
	 * @return 主键id
	 */
	public String getShopId() {
		return this.shopId;
	}

	/**
	 * 设置主键id
	 * 
	 * @param shopId
	 *            主键id
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 获取店铺介绍
	 * 
	 * @return 店铺介绍
	 */
	public String getIntroduce() {
		return this.introduce;
	}

	/**
	 * 设置店铺介绍
	 * 
	 * @param introduce
	 *            店铺介绍
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * 获取店铺地址
	 * 
	 * @return 店铺地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 设置店铺地址
	 * 
	 * @param address
	 *            店铺地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取经度
	 * 
	 * @return 经度
	 */
	public BigDecimal getLongitude() {
		return this.longitude;
	}

	/**
	 * 设置经度
	 * 
	 * @param longitude
	 *            经度
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取纬度
	 * 
	 * @return 纬度
	 */
	public BigDecimal getLatitude() {
		return this.latitude;
	}

	/**
	 * 设置纬度
	 * 
	 * @param latitude
	 *            纬度
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取合同折扣上限
	 * 
	 * @return 合同折扣上限
	 */
	public BigDecimal getDiscountUpperLimit() {
		return this.discountUpperLimit;
	}

	/**
	 * 设置合同折扣上限
	 * 
	 * @param discountUpperLimit
	 *            合同折扣上限
	 */
	public void setDiscountUpperLimit(BigDecimal discountUpperLimit) {
		this.discountUpperLimit = discountUpperLimit;
	}

	/**
	 * 获取合同折扣下限
	 * 
	 * @return 合同折扣下限
	 */
	public BigDecimal getDiscountLowerLimit() {
		return this.discountLowerLimit;
	}

	/**
	 * 设置合同折扣下限
	 * 
	 * @param discountLowerLimit
	 *            合同折扣下限
	 */
	public void setDiscountLowerLimit(BigDecimal discountLowerLimit) {
		this.discountLowerLimit = discountLowerLimit;
	}

	/**
	 * 获取合作状态
	 * 
	 * @return 合作状态
	 */
	public String getCooperateStatus() {
		return this.cooperateStatus;
	}

	/**
	 * 设置合作状态
	 * 
	 * @param cooperateStatus
	 *            合作状态
	 */
	public void setCooperateStatus(String cooperateStatus) {
		this.cooperateStatus = cooperateStatus;
	}

	/**
	 * 获取合作开始时间
	 * 
	 * @return 合作开始时间
	 */
	public Date getCooperateStartDate() {
		return this.cooperateStartDate;
	}

	/**
	 * 设置合作开始时间
	 * 
	 * @param cooperateStartDate
	 *            合作开始时间
	 */
	public void setCooperateStartDate(Date cooperateStartDate) {
		this.cooperateStartDate = cooperateStartDate;
	}

	/**
	 * 获取合作结束时间
	 * 
	 * @return 合作结束时间
	 */
	public Date getCooperateEndDate() {
		return this.cooperateEndDate;
	}

	/**
	 * 设置合作结束时间
	 * 
	 * @param cooperateEndDate
	 *            合作结束时间
	 */
	public void setCooperateEndDate(Date cooperateEndDate) {
		this.cooperateEndDate = cooperateEndDate;
	}

	/**
	 * 获取店铺状态1-有效 0-失效
	 * 
	 * @return 店铺状态1-有效 0-失效
	 */
	public String getShopStatus() {
		return this.shopStatus;
	}

	/**
	 * 设置店铺状态1-有效 0-失效
	 * 
	 * @param shopStatus
	 *            店铺状态1-有效 0-失效
	 */
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	/**
	 * 获取结算方式
	 * 
	 * @return 结算方式
	 */
	public String getBalanceType() {
		return this.balanceType;
	}

	/**
	 * 设置结算方式
	 * 
	 * @param balanceType
	 *            结算方式
	 */
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	/**
	 * 获取推荐等级
	 * 
	 * @return 推荐等级
	 */
	public String getLevel() {
		return this.level;
	}

	/**
	 * 设置推荐等级
	 * 
	 * @param level
	 *            推荐等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getPond() {
		return pond;
	}

	public void setPond(BigDecimal pond) {
		this.pond = pond;
	}

}