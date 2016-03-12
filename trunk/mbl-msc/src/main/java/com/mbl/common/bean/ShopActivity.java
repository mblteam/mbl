package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 店铺活动对应关系(M_SHOP_ACTIVITY)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class ShopActivity extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 6096592139116603125L;
    
    /** 主键id */
    private String saId;
    
    /** 店铺id */
    private String shopId;
    
    /** 活动id */
    private String activityId;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getSaId() {
        return this.saId;
    }
     
    /**
     * 设置主键id
     * 
     * @param saId
     *          主键id
     */
    public void setSaId(String saId) {
        this.saId = saId;
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
     *          店铺id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    
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
}