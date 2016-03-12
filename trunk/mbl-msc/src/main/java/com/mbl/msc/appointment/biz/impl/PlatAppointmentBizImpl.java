package com.mbl.msc.appointment.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.AppointmentMapper;
import com.mbl.common.mapper.AppointmentPackageMapper;
import com.mbl.common.mapper.AppointmentSerMapper;
import com.mbl.common.vo.AppointmentPackageVO;
import com.mbl.common.vo.AppointmentServiceVO;
import com.mbl.common.vo.AppointmentVO;
import com.mbl.msc.appointment.biz.PlatAppointmentBiz;

/**
 * 维修预约
 * 
 * @author jjj
 * @create 2015年12月23日 下午16:12:04
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Transactional
@Service(value = "platAppointmentBiz")
public class PlatAppointmentBizImpl implements PlatAppointmentBiz {

	@Resource
	private AppointmentMapper appointmentMapper;

	@Resource
	private AppointmentPackageMapper appointmentPackageMapper;

	@Resource
	private AppointmentSerMapper appointmentSerMapper;

	public List<AppointmentVO> findAppointmentListByParams(Map<String, Object> map, Integer page, Integer pageSize) {
		return appointmentMapper.findAppointmentListByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}

	public Long countAppointmentListByParams(Map<String, Object> map) {
		return appointmentMapper.countAppointmentList(map);
	}
	
	public AppointmentVO getAppointmentById(String appointmentId) {
		return appointmentMapper.getAppointmentById(appointmentId);
	}
	
	
	public List<AppointmentPackageVO> getAppointmentPackageById(String appointmentId) {
		return appointmentPackageMapper.getAppointmentPackageById(appointmentId);
	}
	
	public List<AppointmentServiceVO> getAppointmentServiceById(String appointmentId) {
		return appointmentSerMapper.getAppointmentServiceById(appointmentId);
	}
	
	
	public int updateAppointmentStatusById(String appointmentId) {
		return appointmentMapper.updateAppointmentStatusById(appointmentId);
	}
}
