package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单表(M_ORDER)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Order extends BaseEntity implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 3816975476710824351L;

	/** 订单日期 */
	private String orderId;

	/** 用户id */
	private String userId;

	/** 描述 */
	private String description;

	/** 店铺id */
	private String shopId;

	/** 下单时间 */
	private Timestamp orderTime;

	/** 是否支付 */
	private String isPay;

	/** 应付金额 */
	private BigDecimal price;

	/** 折扣 */
	private BigDecimal discount;

	/** 实付金额 */
	private BigDecimal paid;

	/** 是否使用积分 */
	private String isUsePoint;

	/** 是否使用优惠券 */
	private String isUseCoupon;

	/** 支付方式 */
	private String payType;

	/** 订单类型 */
	private String orderType;

	/** 使用积分数 */
	private BigDecimal usePoint;

	/** 是否结算 */
	private String isSettle;

	/** 结算时间 */
	private Timestamp settleTime;

	/** 订单状态 ***/
	private String status;
	
	/****
	 * 订单编号
	 */
	private String orderNo;
	
	/****
	 * 预约id
	 */
	private String appointmentId;
	
	/****
	 * 账号id
	 */
	private String accountId;
	
	private String carId;

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

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}
	
}