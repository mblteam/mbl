package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 优惠券(M_COUPON)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Coupon extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 1788823530187309385L;
    
    /** 优惠券id */
    private String couponId;
    
    /** 优惠券类型 */
    private String couponType;
    
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
}