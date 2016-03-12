package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 订单预约服务表(M_APPOINTMENT_SERVICE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class AppointmentService extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 6578618966961435498L;
    
    /** 主键id */
    private String osId;
    
    /** 服务字典code */
    private String serviceCode;
    
    /** 预约id */
    private String appointmentId;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getOsId() {
        return this.osId;
    }
     
    /**
     * 设置主键id
     * 
     * @param osId
     *          主键id
     */
    public void setOsId(String osId) {
        this.osId = osId;
    }
    
    /**
     * 获取服务字典code
     * 
     * @return 服务字典code
     */
    public String getServiceCode() {
        return this.serviceCode;
    }
     
    /**
     * 设置服务字典code
     * 
     * @param serviceCode
     *          服务字典code
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    /**
     * 获取预约id
     * 
     * @return 预约id
     */
    public String getAppointmentId() {
        return this.appointmentId;
    }
     
    /**
     * 设置预约id
     * 
     * @param appointmentId
     *          预约id
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}