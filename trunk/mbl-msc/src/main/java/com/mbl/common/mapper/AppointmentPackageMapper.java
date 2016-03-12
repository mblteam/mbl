package com.mbl.common.mapper;

import java.util.List;

import com.mbl.common.bean.AppointmentPackage;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.AppointmentPackageVO;

public interface AppointmentPackageMapper extends BaseMapper<AppointmentPackage>{

	/**
	 * 批量插入预约套餐
	 * @param appointmentPackages
	 */
	void insertbatch(List<AppointmentPackage> appointmentPackages);

	//获取预约套餐
	List<AppointmentPackage> findAppointmentPackagebyParams(String appointmentId);
	
	
	List<AppointmentPackageVO> getAppointmentPackageById(String appointmentId);

}