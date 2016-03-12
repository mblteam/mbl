package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.Appointment;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.AppointmentVO;

public interface AppointmentMapper extends BaseMapper<Appointment>{

	//查询当天最大预约编号
	String findMaxAppNo();

	//查询预约信息
	List<AppointmentVO> findAppointmentListByParams(Map<String, Object> map);
	
	
	List<AppointmentVO> findAppointmentListByParams(Map<String, Object> map, RowBounds rowBounds);

	Long countAppointmentList(Map<String, Object> map);

	AppointmentVO getAppointmentById(String appointmentId);
	
	
	int updateAppointmentStatusById(String appointmentId);

}