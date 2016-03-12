package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 预约套餐表(M_APPOINTMENT_PACKAGE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class AppointmentPackage extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2151877355182454159L;
    
    /**  */
    private String apId;
    
    /**  */
    private String appointmentId;
    
    /**  */
    private String pkgId;
    
    /** 套餐描述 */
    private String description;
    
    /** 套餐简称 */
    private String pkgName;
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getApId() {
        return this.apId;
    }
     
    /**
     * 设置
     * 
     * @param apId
     *          
     */
    public void setApId(String apId) {
        this.apId = apId;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getAppointmentId() {
        return this.appointmentId;
    }
     
    /**
     * 设置
     * 
     * @param appointmentId
     *          
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getPkgId() {
        return this.pkgId;
    }
     
    /**
     * 设置
     * 
     * @param pkgId
     *          
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
}