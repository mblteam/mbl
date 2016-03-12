package com.mbl.common.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.mbl.common.framework.mapper.BaseEntity;

public class OrderVO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787239326383022757L;

	/** 订单日期 */
	private String orderId;

	/** 用户id */
	private String userId;
	/** 用户 */
	private String userName;
	/** 描述 */
	private String description;

	/** 店铺id */
	private String shopId;

	/** 店铺名称 */
	private String shopName;

	/** 下单时间 */
	private Timestamp orderTime;
	/** 下单时间 */
	private String orderTimeStr;
	/** 是否支付 */
	private String isPay;
	/** 是否支付 */
	private String isPayName;
	/** 应付金额 */
	private BigDecimal price;

	/** 折扣 */
	private BigDecimal discount;

	/** 实付金额 */
	private BigDecimal paid;

	/** 是否使用积分 */
	private String isUsePoint;

	/** 是否使用积分 */
	private String isUsePointName;

	/** 是否使用优惠券 */
	private String isUseCoupon;
	/** 是否使用优惠券 */
	private String isUseCouponName;
	/** 支付方式 */
	private String payType;
	/** 支付方式 */
	private String payTypeName;
	/** 订单类型 */
	private String orderType;
	/** 订单类型 */
	private String orderTypeName;
	/** 使用积分数 */
	private BigDecimal usePoint;

	/** 是否结算 */
	private String isSettle;
	/** 是否结算 */
	private String isSettleName;
	/** 结算时间 */
	private Timestamp settleTime;

	/** 支付周期 **/
	private String balanceType;
	/** 支付周期 **/
	private String balanceTypeName;

	/** 订单状态 ***/
	private String status;
	/** 订单状态 ***/
	private String statusName;

	/****
	 * 订单编号
	 */
	private String orderNo;
	
	/** 经度 */
	private BigDecimal longitude;

	/** 纬度 */
	private BigDecimal latitude;
	
	/****
	 * 预约id
	 */
	private String appointmentId;
	
	private String appointmentNo;
	
	private String accountId;
	
	private String carId; //车id
	private String brandName; //品牌名字
	private String seriesName; //系列名称
	private String carPlateNo; //车牌

	public String getOrderTimeStr() {
		return orderTimeStr;
	}

	public void setOrderTimeStr(String orderTimeStr) {
		this.orderTimeStr = orderTimeStr;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public String getBalanceTypeName() {
		return balanceTypeName;
	}

	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}

	/**
	 * 获取订单日期
	 * 
	 * @return 订单日期
	 */
	public String getOrderId() {
		return this.orderId;
	}

	/**
	 * 设置订单日期
	 * 
	 * @param orderId
	 *            订单日期
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取用户id
	 * 
	 * @return 用户id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置用户id
	 * 
	 * @param userId
	 *            用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 获取下单时间
	 * 
	 * @return 下单时间
	 */
	public Timestamp getOrderTime() {
		return this.orderTime;
	}

	/**
	 * 设置下单时间
	 * 
	 * @param orderTime
	 *            下单时间
	 */
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 获取是否支付
	 * 
	 * @return 是否支付
	 */
	public String getIsPay() {
		return this.isPay;
	}

	/**
	 * 设置是否支付
	 * 
	 * @param isPay
	 *            是否支付
	 */
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	/**
	 * 获取应付金额
	 * 
	 * @return 应付金额
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * 设置应付金额
	 * 
	 * @param price
	 *            应付金额
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取折扣
	 * 
	 * @return 折扣
	 */
	public BigDecimal getDiscount() {
		return this.discount;
	}

	/**
	 * 设置折扣
	 * 
	 * @param discount
	 *            折扣
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * 获取实付金额
	 * 
	 * @return 实付金额
	 */
	public BigDecimal getPaid() {
		return this.paid;
	}

	/**
	 * 设置实付金额
	 * 
	 * @param paid
	 *            实付金额
	 */
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	/**
	 * 获取是否使用积分
	 * 
	 * @return 是否使用积分
	 */
	public String getIsUsePoint() {
		return this.isUsePoint;
	}

	/**
	 * 设置是否使用积分
	 * 
	 * @param isUsePoint
	 *            是否使用积分
	 */
	public void setIsUsePoint(String isUsePoint) {
		this.isUsePoint = isUsePoint;
	}

	/**
	 * 获取是否使用优惠券
	 * 
	 * @return 是否使用优惠券
	 */
	public String getIsUseCoupon() {
		return this.isUseCoupon;
	}

	/**
	 * 设置是否使用优惠券
	 * 
	 * @param isUseCoupon
	 *            是否使用优惠券
	 */
	public void setIsUseCoupon(String isUseCoupon) {
		this.isUseCoupon = isUseCoupon;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return 支付方式
	 */
	public String getPayType() {
		return this.payType;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param payType
	 *            支付方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 获取订单类型
	 * 
	 * @return 订单类型
	 */
	public String getOrderType() {
		return this.orderType;
	}

	/**
	 * 设置订单类型
	 * 
	 * @param orderType
	 *            订单类型
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * 获取使用积分数
	 * 
	 * @return 使用积分数
	 */
	public BigDecimal getUsePoint() {
		return this.usePoint;
	}

	/**
	 * 设置使用积分数
	 * 
	 * @param usePoint
	 *            使用积分数
	 */
	public void setUsePoint(BigDecimal usePoint) {
		this.usePoint = usePoint;
	}

	/**
	 * 获取是否结算
	 * 
	 * @return 是否结算
	 */
	public String getIsSettle() {
		return this.isSettle;
	}

	/**
	 * 设置是否结算
	 * 
	 * @param isSettle
	 *            是否结算
	 */
	public void setIsSettle(String isSettle) {
		this.isSettle = isSettle;
	}

	/**
	 * 获取结算时间
	 * 
	 * @return 结算时间
	 */
	public Timestamp getSettleTime() {
		return this.settleTime;
	}

	/**
	 * 设置结算时间
	 * 
	 * @param settleTime
	 *            结算时间
	 */
	public void setSettleTime(Timestamp settleTime) {
		this.settleTime = settleTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getIsPayName() {
		return isPayName;
	}

	public void setIsPayName(String isPayName) {
		this.isPayName = isPayName;
	}

	public String getIsUsePointName() {
		return isUsePointName;
	}

	public void setIsUsePointName(String isUsePointName) {
		this.isUsePointName = isUsePointName;
	}

	public String getIsUseCouponName() {
		return isUseCouponName;
	}

	public void setIsUseCouponName(String isUseCouponName) {
		this.isUseCouponName = isUseCouponName;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getIsSettleName() {
		return isSettleName;
	}

	public void setIsSettleName(String isSettleName) {
		this.isSettleName = isSettleName;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getCarPlateNo() {
		return carPlateNo;
	}

	public void setCarPlateNo(String carPlateNo) {
		this.carPlateNo = carPlateNo;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
}
