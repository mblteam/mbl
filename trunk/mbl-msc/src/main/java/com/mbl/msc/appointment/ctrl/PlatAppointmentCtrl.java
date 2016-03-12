package com.mbl.msc.appointment.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.AppointmentVO;
import com.mbl.msc.appointment.biz.PlatAppointmentBiz;

/**
 * 登录
 * 功能详细描述
 * @author jjj
 * @create 2015年12月23日 下午16:41:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/plat/platAppointment")
public class PlatAppointmentCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(PlatAppointmentCtrl.class);
	
	@Resource
	private PlatAppointmentBiz platAppointmentBiz;
	
	
	/****
	 * 获取服务
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateAppointmentStatusById")
	public ResultVO updateAppointmentStatusById(@RequestBody Map<String, Object> map){
		ResultVO resultVO = new ResultVO();
		try {
			String appointmentId = (String)map.get("appointmentId");
			platAppointmentBiz.updateAppointmentStatusById(appointmentId);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	} 
	
	
	/***
	 * 预约列表查询
	 * @param page
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/findAppointmentList")
	public PageVO<AppointmentVO> findAppointmentList(PageVO<AppointmentVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setSearch("md", "plat");
			page.setSearch("use", "platAppointment");
			page.setSearch("opt", "findAppointmentList");
			page.setQuery(query);
			page.setTotalSize(platAppointmentBiz.countAppointmentListByParams(query));
			List<AppointmentVO> appointmentList =  platAppointmentBiz.findAppointmentListByParams(query, page.getPage(), page.getPageSize());
			page.setData(appointmentList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}
	
	/****
	 * 获取服务
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getAppointmentSerById")
	public ResultVO getAppointmentSerById(@RequestBody Map<String, Object> map){
		ResultVO resultVO = new ResultVO();
		try {
			String appointmentId = (String)map.get("appointmentId");
			resultVO.setResult(platAppointmentBiz.getAppointmentServiceById(appointmentId));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/***
	 * 获取套餐
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getAppointmentPkgById")
	public ResultVO getAppointmentPkgById(@RequestBody Map<String, Object> map){
		ResultVO resultVO = new ResultVO();
		try {
			String appointmentId = (String)map.get("appointmentId");
			resultVO.setResult(platAppointmentBiz.getAppointmentPackageById(appointmentId));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/***
	 * 获取预约信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getAppointmentById")
	public ResultVO getAppointmentById(@RequestBody Map<String, Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String appointmentId = (String)map.get("appointmentId");
			resultVO.setResult(platAppointmentBiz.getAppointmentById(appointmentId));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
}
