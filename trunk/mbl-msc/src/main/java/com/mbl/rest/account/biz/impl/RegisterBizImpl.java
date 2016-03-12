/**    
 * Copyright (C),Kingmed
 * @FileName: GroupBizImpl.java  
 * @Package: com.kingmed.ms.customer.biz.impl  
 * @Description: //模块目的、功能描述  
 * @Author jerryhuang  
 * @Date 2015年3月13日 下午4:48:23  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.mbl.rest.account.biz.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Account;
import com.mbl.common.bean.IdentifyingCode;
import com.mbl.common.bean.User;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.mapper.AccountMapper;
import com.mbl.common.mapper.IdentifyingCodeMapper;
import com.mbl.common.mapper.UserMapper;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.util.CommonUtils;
import com.mbl.rest.account.biz.RegisterBiz;
import com.mbl.rest.account.vo.RegisterRequestVo;

/**
 * 注册
 * @author xjs
 * @create 2015年12月02日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "registerBiz")
@Transactional
public class RegisterBizImpl implements RegisterBiz {
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(RegisterBizImpl.class);

	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private IdentifyingCodeMapper idetifyCodeMapper;
	
	//有效状态
	public final static String VALID_STATUS="1";
	
	//普通账号类型
	public final static String PLAIN_ACCOUNT_TYPE="1";

	/**
	 * 用户注册
	 */
	@Override
	public boolean register(RegisterRequestVo requestVo) {
		
		try {
			User user=getUser(requestVo);
			//新增用户信息
			userMapper.save(user);
			
			Account account=getAccount(user,requestVo.getPassword());
			//新增用户账号信息
			accountMapper.save(account);
			
			//修改验证码状态
			updateSendCodeStatus(requestVo.getTel(),requestVo.getFlag());
			
		} catch (Exception e) {
			LOGGER.error("e:",e);
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 更换手机号码
	 */
	@Override
	public void updateTel(RegisterRequestVo rvo){
		Account account = accountMapper.getById(rvo.getAccountId());
		account.setLastUpdateDate(new Timestamp(new Date().getTime()));
		account.setAccountCode(rvo.getTel());
		accountMapper.update(account);

		User user = new User();
		user.setTel(account.getAccountCode());
		user.setUserId(account.getUserId());
		user.setLastUpdateDate(new Timestamp(new Date().getTime()));
		userMapper.update(user);
		
		//修改验证码状态
		updateSendCodeStatus(rvo.getTel(),rvo.getFlag());
	}

	/**
	 * 判断账号是否已存在
	 * @return
	 */
	@Override
	public boolean existUser(String tel) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("tel", tel);
		Long count=userMapper.getCountByParams(map);
		return !Long.valueOf(0).equals(count);
	}


	/**
	 * 验证数据合法性
	 */
	@Override
	public String validate(RegisterRequestVo requestVo) {
		String errorMsg="";
		
		//验证手机号码是否合法
		if(!CommonUtils.isTel(requestVo.getTel())){
			errorMsg="手机号码不合法！";
			return errorMsg;
		}
		
		//判断是否已经注册
		if((ConstantClass.VERIFY_CODE_UPDATETEL.equals(requestVo.getFlag())
				||ConstantClass.VERIFY_CODE_REGISTER.equals(requestVo.getFlag()))
				&&existUser(requestVo.getTel()) ){
			errorMsg="手机号码已注册！";
			return errorMsg;
		}
		
		if(StringUtils.isNotEmpty(requestVo.getSendCode())){
			errorMsg = validateCode(requestVo);
		}
		
		return errorMsg;
	}
	
	/**
	 * 保存验证码
	 * @param requestVo
	 * @return  
	 * @throws Exception 
	 * @see com.mbl.rest.account.biz.RegisterBiz#saveSendCode(com.mbl.rest.account.vo.RegisterRequestVo)
	 */
	@Override
	public int sendCode(RegisterRequestVo requestVo) throws Exception{
		//生成验证码（生成6个随机数）
		String validCode = String.valueOf((int)((Math.random()*9+1)*100000));
		
		//编辑发送验证码的信息
		StringBuffer sbBuffer = new StringBuffer("您的验证码是:");
		sbBuffer.append(validCode);
		sbBuffer.append(",你现在使用的是免不了手机客户端.请勿将验证码透露给他人,以免造成不必要的风险.");
		try {
			BaseFunction.sendSMS(requestVo.getTel(), sbBuffer.toString());
		} catch (Exception e) {
			throw new BizException("-1","验证码发送失败");
		}
		
		//保存验证码到数据库
		requestVo.setSendCode(validCode);
		
		//将当前号码当前操作的验证码置为无效
		updateSendCodeStatus(requestVo.getTel(),requestVo.getFlag());
		
		//首先当前操作之前的验证码
		List<IdentifyingCode> ics = idetifyCodeMapper.findByTelAndFlag(requestVo.getTel(),requestVo.getFlag());
		IdentifyingCode ic = null;
		if(null!=ics&&ics.size()>0){
			ic = ics.get(0);
			ic.setSendCode(requestVo.getSendCode());
			ic.setFlag(requestVo.getFlag());
			ic.setCodeStatus(DictCode.VALID_STATUS);
		}else{
			ic = new IdentifyingCode();
			BeanUtils.copyProperties(requestVo, ic);
			ic.setFlag(requestVo.getFlag());
			ic.setCodeStatus(DictCode.VALID_STATUS);
		}
	
		Calendar cld = Calendar.getInstance();
		cld.add(Calendar.MINUTE, 20);//验证码有效期20分钟
		ic.setDisabledDate(new Timestamp(cld.getTimeInMillis()));
		ic.setCreationDate(new Timestamp(new Date().getTime()));
		ic.setLastUpdateDate(new Timestamp(new Date().getTime()));
		if(null==ic.getCodeId()){
			ic.setCodeId(UUID.randomUUID().toString());
			return idetifyCodeMapper.save(ic);
		}else{
			return idetifyCodeMapper.update(ic);
		}
	}
	
	//修改验证码状态
	public void updateSendCodeStatus(String tel,String flag) {
		List<IdentifyingCode> ics = idetifyCodeMapper.findByTelAndFlag(tel,flag);
		for (IdentifyingCode ic : ics) {
			ic.setCodeStatus(DictCode.INVALID_STATUS);
			idetifyCodeMapper.update(ic);
		}
	}
	
	
	/**
	 * 校验验证码
	 * @return
	 */
	private String validateCode(RegisterRequestVo requestVo){
		String errorMsg="";
		List<IdentifyingCode> ics = idetifyCodeMapper.findByTelAndFlag(requestVo.getTel(),requestVo.getFlag());
		IdentifyingCode ic = null;
		if(null!=ics&&ics.size()>0){
			ic = ics.get(0);
		}
		if(null!=ic&&StringUtils.isNotEmpty(ic.getSendCode())){
		}else{
			errorMsg="请重新发送验证码！";
			return errorMsg;
		}
		if(!ic.getSendCode().equals(requestVo.getSendCode())){
			errorMsg="验证码不正确！";
			return errorMsg;
		}
		//当前时间与失效时间比较
		if(ic.getDisabledDate().getTime()<(new Date()).getTime()){
			errorMsg="验证码过期，请重新发送！";
			return errorMsg;
		}
		return errorMsg;
	}
	
	

	/**
	 * 获取设置的用户信息
	 * @return
	 */
	private User getUser(RegisterRequestVo requestVo){
		User user=new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setTel(requestVo.getTel());
		user.setCreationDate(new Timestamp(new Date().getTime()));
		user.setLastUpdateDate(new Timestamp(new Date().getTime()));
		user.setUserStatus(VALID_STATUS);
		return user;
	}
	
	/**
	 * 获取设置的账号信息
	 * @return
	 * @throws Exception 
	 */
	private Account getAccount(User user,String password) throws Exception{
		Account account=new Account();
		account.setAccountId(UUID.randomUUID().toString());
		account.setAccountCode(user.getTel());
		account.setPwd(CommonUtils.md5Encode(password));
		account.setStatus(VALID_STATUS);
		account.setAccountType(PLAIN_ACCOUNT_TYPE);
		account.setUserId(user.getUserId());
		account.setCreationDate(new Timestamp(new Date().getTime()));
		account.setLastUpdateDate(new Timestamp(new Date().getTime()));
		
		return account;
	}
}
