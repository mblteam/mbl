package com.mbl.common.bean;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 预约服务表(M_APPOINTMENT)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Appointment extends BaseEntity implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 449541351539314486L;

	/** 主键id */
	private String appointmentId;

	/** 预约时间 */
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:dd")
	private Timestamp appointmentTime;

	/** 留言 */
	private String message;

	/** 车辆id */
	private String carId;

	/** 店铺id */
	private String shopId;

	/** 状态 */
	private String status;
	
	/** 预约单号 **/
	private String appointmentNo;
	
	/*** 账号id **/
	private String accountId;

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	/**
	 * 获取主键id
	 * 
	 * @return 主键id
	 */
	public String getAppointmentId() {
		return this.appointmentId;
	}

	/**
	 * 设置主键id
	 * 
	 * @param appointmentId
	 *            主键id
	 */
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * 获取预约时间
	 * 
	 * @return 预约时间
	 */
	public Timestamp getAppointmentTime() {
		return this.appointmentTime;
	}

	/**
	 * 设置预约时间
	 * 
	 * @param appointmentTime
	 *            预约时间
	 */
	public void setAppointmentTime(Timestamp appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	/**
	 * 获取留言
	 * 
	 * @return 留言
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 设置留言
	 * 
	 * @param message
	 *            留言
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取车辆id
	 * 
	 * @return 车辆id
	 */
	public String getCarId() {
		return this.carId;
	}

	/**
	 * 设置车辆id
	 * 
	 * @param carId
	 *            车辆id
	 */
	public void setCarId(String carId) {
		this.carId = carId;
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
	 *            店铺id
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 获取状态
	 * 
	 * @return 状态
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}