package com.mbl.common.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 店铺活动(M_ACTIVITY)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Activity extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 1853880639851813406L;
    
    /** 活动id */
    private String activityId;
    
    /** 活动简称 */
    private String activityName;
    
    /** 活动开始日期 */
    private Timestamp activityStartDate;
    
    /** 活动结束日期 */
    private Timestamp activityEndDate;
    
    /** 活动描述 */
    private String description;
    
    /** 是否可以使用优惠券 */
    private String isUseCoupon;
    
    /** 活动类型 */
    private String activityType;
    
    /** 活动数值上限 */
    private BigDecimal upperNum;
    
    /** 活动数值下限 */
    private BigDecimal lowerNum;
    
    /** 状态1-有效 0-失效 */
    private String status;
    
    /** 是否可以使用积分 */
    private String isUsePoint;
    
    /**
     * 获取活动id
     * 
     * @return 活动id
     */
    public String getActivityId() {
        return this.activityId;
    }
     
    /**
     * 设置活动id
     * 
     * @param activityId
     *          活动id
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    
    /**
     * 获取活动简称
     * 
     * @return 活动简称
     */
    public String getActivityName() {
        return this.activityName;
    }
     
    /**
     * 设置活动简称
     * 
     * @param activityName
     *          活动简称
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    
    /**
     * 获取活动开始日期
     * 
     * @return 活动开始日期
     */
    public Timestamp getActivityStartDate() {
        return this.activityStartDate;
    }
     
    /**
     * 设置活动开始日期
     * 
     * @param activityStartDate
     *          活动开始日期
     */
    public void setActivityStartDate(Timestamp activityStartDate) {
        this.activityStartDate = activityStartDate;
    }
    
    /**
     * 获取活动结束日期
     * 
     * @return 活动结束日期
     */
    public Timestamp getActivityEndDate() {
        return this.activityEndDate;
    }
     
    /**
     * 设置活动结束日期
     * 
     * @param activityEndDate
     *          活动结束日期
     */
    public void setActivityEndDate(Timestamp activityEndDate) {
        this.activityEndDate = activityEndDate;
    }
    
    /**
     * 获取活动描述
     * 
     * @return 活动描述
     */
    public String getDescription() {
        return this.description;
    }
     
    /**
     * 设置活动描述
     * 
     * @param description
     *          活动描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 获取是否可以使用优惠券
     * 
     * @return 是否可以使用优惠券
     */
    public String getIsUseCoupon() {
        return this.isUseCoupon;
    }
     
    /**
     * 设置是否可以使用优惠券
     * 
     * @param isUseCoupon
     *          是否可以使用优惠券
     */
    public void setIsUseCoupon(String isUseCoupon) {
        this.isUseCoupon = isUseCoupon;
    }
    
    /**
     * 获取活动类型
     * 
     * @return 活动类型
     */
    public String getActivityType() {
        return this.activityType;
    }
     
    /**
     * 设置活动类型
     * 
     * @param activityType
     *          活动类型
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    
    /**
     * 获取活动数值上限
     * 
     * @return 活动数值上限
     */
    public BigDecimal getUpperNum() {
        return this.upperNum;
    }
     
    /**
     * 设置活动数值上限
     * 
     * @param upperNum
     *          活动数值上限
     */
    public void setUpperNum(BigDecimal upperNum) {
        this.upperNum = upperNum;
    }
    
    /**
     * 获取活动数值下限
     * 
     * @return 活动数值下限
     */
    public BigDecimal getLowerNum() {
        return this.lowerNum;
    }
     
    /**
     * 设置活动数值下限
     * 
     * @param lowerNum
     *          活动数值下限
     */
    public void setLowerNum(BigDecimal lowerNum) {
        this.lowerNum = lowerNum;
    }
    
    /**
     * 获取状态1-有效 0-失效
     * 
     * @return 状态1-有效 0-失效
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态1-有效 0-失效
     * 
     * @param status
     *          状态1-有效 0-失效
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取是否可以使用积分
     * 
     * @return 是否可以使用积分
     */
    public String getIsUsePoint() {
        return this.isUsePoint;
    }
     
    /**
     * 设置是否可以使用积分
     * 
     * @param isUsePoint
     *          是否可以使用积分
     */
    public void setIsUsePoint(String isUsePoint) {
        this.isUsePoint = isUsePoint;
    }
}