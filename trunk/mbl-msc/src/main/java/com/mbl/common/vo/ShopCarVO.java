package com.mbl.common.vo;

import com.mbl.common.framework.mapper.BaseEntity;

public class ShopCarVO  extends BaseEntity {
    /** 版本号 */
    private static final long serialVersionUID = 6683015927680017747L;
    
    /** 主键ID */
    private String scId;
    
    /** 车辆类型 */
    private String brand;
    /** 车辆类型 */
    private String brandName;
    /** 车辆名称 */
    private String carName;
    
    /** 店铺id */
    private String shopId;
    /** 店铺名称 */
    private String shopName;
    /****
     * 删除标记
     */
    private String deleteFlag;
    
    
    public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
     * 获取主键ID
     * 
     * @return 主键ID
     */
    public String getScId() {
        return this.scId;
    }
     
    /**
     * 设置主键ID
     * 
     * @param scId
     *          主键ID
     */
    public void setScId(String scId) {
        this.scId = scId;
    }
    
    /**
     * 获取车辆类型
     * 
     * @return 车辆类型
     */
    public String getBrand() {
        return this.brand;
    }
     
    /**
     * 设置车辆类型
     * 
     * @param carType
     *          车辆类型
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    /**
     * 获取车辆名称
     * 
     * @return 车辆名称
     */
    public String getCarName() {
        return this.carName;
    }
     
    /**
     * 设置车辆名称
     * 
     * @param carName
     *          车辆名称
     */
    public void setCarName(String carName) {
        this.carName = carName;
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
}
