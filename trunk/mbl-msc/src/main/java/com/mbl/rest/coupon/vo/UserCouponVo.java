package com.mbl.rest.coupon.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * 优惠券返回参数
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class UserCouponVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 主键id */
    private String ucId;
    
    /** 优惠券id */
    private String couponId;
    
    /** 用户id */
    private String userId;
    
    /** 关联订单id */
    private String orderId;
    
    /** 发放日期 */
    private Timestamp sendDate;
    
    private String sendDateStr;
    
    /** 失效日期 */
    private Timestamp disableDate;
    
    private String disableDateStr;
    
    /** 状态1-有效 0-失效2-已使用 */
    private String status;
    
    private String statusName;
    
    /** 优惠券类型 */
    private String couponType;
    
    /** 优惠券类型 */
    private String couponTypeName;
    
    /** 优惠券简称 */
    private String couponName;
    
    /** 优惠券描述 */
    private String description;
    
    /** 优惠券价值 */
    private BigDecimal couponWorth;
    
    /** 开始时间 */
    private Timestamp couponStartDate;
    
    /** 结束时间 */
    private Timestamp couponEndDate;
    
    /** 优惠券状态1-有效 0-失效 */
    private String couponStatus;
    
    /** 有效天数 */
    private Integer effectiveDay;
    
    /**
     * 获取优惠券id
     * 
     * @return 优惠券id
     */
    public String getCouponId() {
        return this.couponId;
    }
     
    /**
     * 设置优惠券id
     * 
     * @param couponId
     *          优惠券id
     */
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
    
    /**
     * 获取优惠券类型
     * 
     * @return 优惠券类型
     */
    public String getCouponType() {
        return this.couponType;
    }
     
    /**
     * 设置优惠券类型
     * 
     * @param couponType
     *          优惠券类型
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
    
    /**
     * 获取优惠券简称
     * 
     * @return 优惠券简称
     */
    public String getCouponName() {
        return this.couponName;
    }
     
    /**
     * 设置优惠券简称
     * 
     * @param couponName
     *          优惠券简称
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    
    /**
     * 获取优惠券描述
     * 
     * @return 优惠券描述
     */
    public String getDescription() {
        return this.description;
    }
     
    /**
     * 设置优惠券描述
     * 
     * @param description
     *          优惠券描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 获取优惠券价值
     * 
     * @return 优惠券价值
     */
    public BigDecimal getCouponWorth() {
        return this.couponWorth;
    }
     
    /**
     * 设置优惠券价值
     * 
     * @param couponWorth
     *          优惠券价值
     */
    public void setCouponWorth(BigDecimal couponWorth) {
        this.couponWorth = couponWorth;
    }
    
    /**
     * 获取开始时间
     * 
     * @return 开始时间
     */
    public Timestamp getCouponStartDate() {
        return this.couponStartDate;
    }
     
    /**
     * 设置开始时间
     * 
     * @param couponStartDate
     *          开始时间
     */
    public void setCouponStartDate(Timestamp couponStartDate) {
        this.couponStartDate = couponStartDate;
    }
    
    /**
     * 获取结束时间
     * 
     * @return 结束时间
     */
    public Timestamp getCouponEndDate() {
        return this.couponEndDate;
    }
     
    /**
     * 设置结束时间
     * 
     * @param couponEndDate
     *          结束时间
     */
    public void setCouponEndDate(Timestamp couponEndDate) {
        this.couponEndDate = couponEndDate;
    }
    
    /**
     * 获取优惠券状态1-有效 0-失效
     * 
     * @return 优惠券状态1-有效 0-失效
     */
    public String getCouponStatus() {
        return this.couponStatus;
    }
     
    /**
     * 设置优惠券状态1-有效 0-失效
     * 
     * @param couponStatus
     *          优惠券状态1-有效 0-失效
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }
    
    /**
     * 获取有效天数
     * 
     * @return 有效天数
     */
    public Integer getEffectiveDay() {
        return this.effectiveDay;
    }
     
    /**
     * 设置有效天数
     * 
     * @param effectiveDay
     *          有效天数
     */
    public void setEffectiveDay(Integer effectiveDay) {
        this.effectiveDay = effectiveDay;
    }
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getUcId() {
        return this.ucId;
    }
     
    /**
     * 设置主键id
     * 
     * @param ucId
     *          主键id
     */
    public void setUcId(String ucId) {
        this.ucId = ucId;
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
     *          用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取关联订单id
     * 
     * @return 关联订单id
     */
    public String getOrderId() {
        return this.orderId;
    }
     
    /**
     * 设置关联订单id
     * 
     * @param orderId
     *          关联订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    /**
     * 获取发放日期
     * 
     * @return 发放日期
     */
    public Timestamp getSendDate() {
        return this.sendDate;
    }
     
    /**
     * 设置发放日期
     * 
     * @param sendDate
     *          发放日期
     */
    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }
    
    /**
     * 获取失效日期
     * 
     * @return 失效日期
     */
    public Timestamp getDisableDate() {
        return this.disableDate;
    }
     
    /**
     * 设置失效日期
     * 
     * @param disableDate
     *          失效日期
     */
    public void setDisableDate(Timestamp disableDate) {
        this.disableDate = disableDate;
    }
    
    /**
     * 获取状态1-有效 0-失效2-已使用
     * 
     * @return 状态1-有效 0-失效2-已使用
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态1-有效 0-失效2-已使用
     * 
     * @param status
     *          状态1-有效 0-失效2-已使用
     */
    public void setStatus(String status) {
        this.status = status;
    }

	public String getSendDateStr() {
		return sendDateStr;
	}

	public void setSendDateStr(String sendDateStr) {
		this.sendDateStr = sendDateStr;
	}

	public String getDisableDateStr() {
		return disableDateStr;
	}

	public void setDisableDateStr(String disableDateStr) {
		this.disableDateStr = disableDateStr;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCouponTypeName() {
		return couponTypeName;
	}

	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
	}
	
}
