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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Account;
import com.mbl.common.bean.IdentifyingCode;
import com.mbl.common.bean.User;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.mapper.AccountMapper;
import com.mbl.common.mapper.IdentifyingCodeMapper;
import com.mbl.common.mapper.UserMapper;
import com.mbl.common.vo.AccountVO;
import com.mbl.rest.account.biz.LoginBiz;
import com.mbl.rest.account.vo.UpdatePwdRequestVo;

/**
 * 用户注册
 * 
 * @author xjs
 * @create 2015年12月02日 下午1:12:04
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "loginBiz")
@Transactional
public class LoginBizImpl implements LoginBiz {

	@Resource
	UserMapper userMapper;

	@Resource
	AccountMapper accountMapper;

	@Resource
	IdentifyingCodeMapper idetifyCodeMapper;

	/**
	 * 判断是否存在 true：已存在（count！=0） false：不存在（count==0）
	 */
	@Override
	public boolean existAccount(Map<String, Object> map) {
		Long count = accountMapper.getCountByParams(map);
		return count > 0 ? true : false;
	}

	/**
	 * 判断是否登录成功 true：登录成功（count！=0） false：登录失败（count==0）
	 */
	@Override
	public AccountVO login(Map<String, Object> map) {

		AccountVO accountVo = accountMapper.findAccountByParams(map);

		return accountVo;
	}

	/**
	 * 修改密码
	 * 
	 * @throws BizException
	 */
	@Override
	public void updatePassword(UpdatePwdRequestVo requestVo) throws BizException {
		// 原密码是否正确
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountCode", requestVo.getAccountCode());
		map.put("password", requestVo.getPassword());
		AccountVO av = accountMapper.findAccountByParams(map);
		if (null == av) {
			throw new BizException("-1", "原密码错误");
		}
		String newPwd = requestVo.getNewPassword();
		String newPwdConfirm = requestVo.getNewPasswordConfirm();
		if (StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(newPwdConfirm)) {
			throw new BizException("-1", "新密码错误,请确认");
		}
		if (!StringUtils.equals(newPwd, newPwdConfirm)) {
			throw new BizException("-1", "两密码不一致");
		}
		av.setPwd(newPwd);
		Account t = accountMapper.getById(av.getAccountId());
		t.setPwd(newPwd);
		accountMapper.update(t);
	}

	/**
	 * 忘记密码
	 */
	@Override
	public void forgetPassword(UpdatePwdRequestVo requestVo) throws BizException {
		//String newPwd = requestVo.getNewPassword();
		String newPwdConfirm = requestVo.getNewPasswordConfirm();
		if (StringUtils.isEmpty(newPwdConfirm)) {
			throw new BizException("-1", "新密码错误,请确认");
		}
		/*if (!StringUtils.equals(newPwd, newPwdConfirm)) {
			throw new BizException("-1", "两密码不一致");
		}*/
		AccountVO av = accountMapper.findAccountByCode(requestVo.getAccountCode());
		av.setPwd(newPwdConfirm);
		Account account = new Account();
		try {
			BeanUtils.copyProperties(account, av);
		} catch (Exception e) {
			throw new BizException("-1", "发生了错误！");
		}

		accountMapper.update(account);
	}
}
