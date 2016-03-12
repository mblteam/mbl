package com.mbl.common.mapper;

import java.util.List;

import com.mbl.common.bean.AppointmentService;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.AppointmentServiceVO;

public interface AppointmentSerMapper extends BaseMapper<AppointmentService> {

	void insertbatch(List<AppointmentService> appointmentServices);

	// 获取预约服务
	List<AppointmentServiceVO> findAppointmentServices(String appointmentId);

	List<AppointmentServiceVO> getAppointmentServiceById(String appointmentId);
}