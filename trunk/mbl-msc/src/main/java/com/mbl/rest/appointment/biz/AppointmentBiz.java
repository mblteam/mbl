package com.mbl.rest.appointment.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.util.wx2.SignModel;
import com.mbl.common.vo.AppointmentVO;
import com.mbl.rest.appointment.vo.AppointmentRequestVo;
import com.mbl.rest.pkg.vo.UserPackageVo;



/**
 * 维修预约
 * @author xjs
 * @create 2015年12月05日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AppointmentBiz {


	/**
	 * 新增维修预约
	 */
	Map<String,Object> addAppointment(AppointmentRequestVo requestVo) throws BizException;
	
	/**
	 * 获取预约服务
	 * @return
	 */
	List<DictLine> searchAppointmentServices();


	/**
	 * 查询改用户选择的套餐是否已存在付款的预约套餐
	 * @param requestVo
	 * @return
	 */
	public List<UserPackageVo> searchUserPackageList(AppointmentRequestVo requestVo);
	
	/**
	 * 检查套餐是否已经支付（0：未支付  1：部分支付  2：全部支付）
	 * @param requestVo
	 * @return
	 */
	public int checkPackageIsPay(AppointmentRequestVo requestVo,List<UserPackageVo> userPackageList);

	/**
	 * 查询预约信息
	 * @param requestVo
	 * @return
	 */
	List<AppointmentVO> searchAppointments(AppointmentRequestVo requestVo);

	/**
	 * 获取支付宝签名
	 * @param requestVo
	 * @return
	 * @throws BizException 
	 */
	Map<String, Object> alipayGetSign(AppointmentRequestVo requestVo) throws BizException;
	
	/**
	 * 获取微信签名
	 * @param requestVo
	 * @return
	 * @throws BizException 
	 */
	Map<String, Object> wxpayGetSign(AppointmentRequestVo requestVo, String ip) throws BizException;

	/**
	 * 获取用户预约明细
	 * @param requestVo
	 * @return
	 */
	Map<String, Object> searchAppointmentDetail(AppointmentRequestVo requestVo);

	/**
	 * 取消预约
	 * @param query
	 */
	void cancelAppointment(Map<String, String> query);
}
