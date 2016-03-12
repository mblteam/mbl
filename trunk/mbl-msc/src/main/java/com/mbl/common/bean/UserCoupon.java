package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
import java.sql.Timestamp;

/**
 * 用户优惠券对应关系(M_USER_COUPON)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class UserCoupon extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1872415778691622565L;
    
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
    
    /** 失效日期 */
    private Timestamp disableDate;
    
    /** 状态1-有效 0-失效2-已使用 */
    private String status;
    
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
}