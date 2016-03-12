package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 店铺套餐对应(M_SHOP_PACKAGE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class ShopPackage extends BaseEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 4948887879254342202L;
    
    /** 主键id */
    private String spId;
    
    /** 店铺id */
    private String shopId;
    
    /** 套餐id */
    private String pkgId;
    
    /** 套餐描述 */
    private String description;
    
    /** 套餐价格 */
    private BigDecimal pkgPrice;
    
    /** 套餐开始时间 */
    private Timestamp pkgStartDate;
    
    /** 套餐结束时间 */
    private Timestamp pkgEndDate;
    
    /** 套餐简称 */
    private String pkgName;
    
    /** 套餐类型（月卡、年卡、一次性） */
    private String pkgType;
    
    /** 套餐限制数 */
    private String pkgNum;
    
    /** 套餐内容 */
    private String pkgContent;
    
    /** 适用车型 */
    private String applyModel;
    
    /** 删除标记 */
    private String deleteFlag;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getSpId() {
        return this.spId;
    }
     
    /**
     * 设置主键id
     * 
     * @param spId
     *          主键id
     */
    public void setSpId(String spId) {
        this.spId = spId;
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
     * 获取套餐id
     * 
     * @return 套餐id
     */
    public String getPkgId() {
        return this.pkgId;
    }
     
    /**
     * 设置套餐id
     * 
     * @param pkgId
     *          套餐id
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
     *          套餐描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     *          套餐价格
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
     *          套餐开始时间
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
     *          套餐结束时间
     */
    public void setPkgEndDate(Timestamp pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public String getPkgType() {
		return pkgType;
	}

	public void setPkgType(String pkgType) {
		this.pkgType = pkgType;
	}

	public String getPkgNum() {
		return pkgNum;
	}

	public void setPkgNum(String pkgNum) {
		this.pkgNum = pkgNum;
	}

	public String getPkgContent() {
		return pkgContent;
	}

	public void setPkgContent(String pkgContent) {
		this.pkgContent = pkgContent;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getApplyModel() {
		return applyModel;
	}

	public void setApplyModel(String applyModel) {
		this.applyModel = applyModel;
	}
    
}