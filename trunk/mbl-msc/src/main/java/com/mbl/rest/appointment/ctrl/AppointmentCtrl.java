package com.mbl.rest.appointment.ctrl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.vo.AppointmentVO;
import com.mbl.rest.appointment.biz.AppointmentBiz;
import com.mbl.rest.appointment.vo.AppointmentRequestVo;


/**
 * 维修预约
 * @author xjs
 * @create 2015年12月02日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/appointment")
public class AppointmentCtrl {

	@Resource
	private AppointmentBiz appointmentBiz;

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AppointmentCtrl.class);

	/**
	 * 新增维修预约
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/saveAppointment",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO saveAppointment(@RequestBody AppointmentRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("维修预约");
		
		try {
			Map<String,Object> resultMap = appointmentBiz.addAppointment(requestVo);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(resultMap);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 获取预约服务
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/searchAppointmentServices",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchAppointmentServices() {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("维修预约");
		
		try {
			List<DictLine> appointmentServices = appointmentBiz.searchAppointmentServices();
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(appointmentServices);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 获取用户预约
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/searchAppointments",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchAppointments(@RequestBody AppointmentRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("预约查询");
		
		try {
			List resultList = new ArrayList();
			List<AppointmentVO> appointments = appointmentBiz.searchAppointments(requestVo);
			for (AppointmentVO av : appointments) {
				resultList.add(BaseFunction.convertBean(av));
			}
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(resultList);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 获取用户预约明细
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/searchAppointmentDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchAppointmentDetail(@RequestBody AppointmentRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("预约明细查询");
		
		try {
			Map<String,Object>  appointmentDetail= appointmentBiz.searchAppointmentDetail(requestVo);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(appointmentDetail);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cancelAppointment")
	public ResultVO cancelAppointment(@RequestBody Map<String, String> query) {
		ResultVO resultVO = new ResultVO();
		try {
			appointmentBiz.cancelAppointment(query);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setErrorMsg("预约已取消！");
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 支付宝支付
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/alipayGetSign",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO alipayGetSign(@RequestBody AppointmentRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("维修预约");
		
		try {
			Map<String,Object> resultMap = appointmentBiz.alipayGetSign(requestVo);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(resultMap);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 微信支付
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/wxpayGetSign",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO wxGetSign(HttpServletRequest request, @RequestBody AppointmentRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.debug("微信支付接口");
		String ip = getIp(request);
		try {
			Map<String, Object> sign = appointmentBiz.wxpayGetSign(requestVo, ip);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(sign);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
