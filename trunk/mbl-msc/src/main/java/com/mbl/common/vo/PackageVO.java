package com.mbl.common.vo;

import com.mbl.common.framework.mapper.BaseEntity;

public class PackageVO extends BaseEntity{
    
	/**  
	 * serialVersionUID  
	 */  
	private static final long serialVersionUID = -4787239326383022755L;

	/** 套餐id */
    private String pkgId;
    
    /** 套餐简称 */
    private String pkgName;
    
    /** 套餐类型（月卡、年卡、一次性） */
    private String pkgType;
    
    /** 套餐限制数 */
    private String pkgNum;
    /** 套餐内容 */
    private String pkgContent;
    /** 备注 */
    private String remark;
    
    /** 删除标记 */
    private String deleteFlag;
    
    public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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
     * 获取套餐简称
     * 
     * @return 套餐简称
     */
    public String getPkgName() {
        return this.pkgName;
    }
     
    /**
     * 设置套餐简称
     * 
     * @param pkgName
     *          套餐简称
     */
    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
    
    /**
     * 获取套餐类型（月卡、年卡、一次性）
     * 
     * @return 套餐类型（月卡、年卡、一次性）
     */
    public String getPkgType() {
        return this.pkgType;
    }
     
    /**
     * 设置套餐类型（月卡、年卡、一次性）
     * 
     * @param pkgType
     *          套餐类型（月卡、年卡、一次性）
     */
    public void setPkgType(String pkgType) {
        this.pkgType = pkgType;
    }
    
    /**
     * 获取套餐限制数
     * 
     * @return 套餐限制数
     */
    public String getPkgNum() {
        return this.pkgNum;
    }
     
    /**
     * 设置套餐限制数
     * 
     * @param pkgNum
     *          套餐限制数
     */
    public void setPkgNum(String pkgNum) {
        this.pkgNum = pkgNum;
    }

	public String getPkgContent() {
		return pkgContent;
	}

	public void setPkgContent(String pkgContent) {
		this.pkgContent = pkgContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
