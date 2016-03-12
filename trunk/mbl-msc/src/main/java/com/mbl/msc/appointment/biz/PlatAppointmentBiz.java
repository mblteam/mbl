package com.mbl.msc.appointment.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.vo.AppointmentPackageVO;
import com.mbl.common.vo.AppointmentServiceVO;
import com.mbl.common.vo.AppointmentVO;

public interface PlatAppointmentBiz {

	List<AppointmentVO> findAppointmentListByParams(Map<String, Object> map, Integer page, Integer pageSize);

	Long countAppointmentListByParams(Map<String, Object> map);

	AppointmentVO getAppointmentById(String appointmentId);

	List<AppointmentPackageVO> getAppointmentPackageById(String appointmentId);
	
	List<AppointmentServiceVO> getAppointmentServiceById(String appointmentId);
	
	int updateAppointmentStatusById(String appointmentId);
}
