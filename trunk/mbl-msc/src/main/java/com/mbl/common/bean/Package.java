package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 店铺套餐(M_PACKAGE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Package extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 4194750226903385274L;
    
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