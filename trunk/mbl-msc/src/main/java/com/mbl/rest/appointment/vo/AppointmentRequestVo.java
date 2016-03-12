package com.mbl.rest.appointment.vo;

import java.util.List;

import com.mbl.common.bean.Appointment;
import com.mbl.common.bean.AppointmentService;

/**
 * 
 * 维修预约请求参数
 * @author xjs
 * @create 2015年12月05日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AppointmentRequestVo {
	
	/** 预约信息 */
	private Appointment appointment;
	
	/** 店铺套餐id 多个用逗号分隔 */
	private List<String> spIds;
	
	/** 店铺套餐pkgId 多个用逗号分隔 */
	private List<String> pkgIds;
	
	/** 预约服务 */
	private List<AppointmentService> appointmentServices;
	
	/** 预约服务 */
	private List<String> serviceCodes;
	
	private String userId;
	
	private String accountId;
	
	private String appointmentId;
	
	/** 是否全部都够标示 */
	private boolean orderAllflag;
	
	/** 订单id */
	private String orderId;
	
	private String plat;
	
	/** 订单No */
	private String orderNo;

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<AppointmentService> getAppointmentServices() {
		return appointmentServices;
	}

	public void setAppointmentServices(List<AppointmentService> appointmentServices) {
		this.appointmentServices = appointmentServices;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getSpIds() {
		return spIds;
	}

	public void setSpIds(List<String> spIds) {
		this.spIds = spIds;
	}

	public List<String> getServiceCodes() {
		return serviceCodes;
	}

	public void setServiceCodes(List<String> serviceCodes) {
		this.serviceCodes = serviceCodes;
	}

	public List<String> getPkgIds() {
		return pkgIds;
	}

	public void setPkgIds(List<String> pkgIds) {
		this.pkgIds = pkgIds;
	}

	public boolean isOrderAllflag() {
		return orderAllflag;
	}

	public void setOrderAllflag(boolean orderAllflag) {
		this.orderAllflag = orderAllflag;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

}
