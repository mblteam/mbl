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

package com.mbl.msc.auth.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.User;
import com.mbl.common.mapper.AccountMapper;
import com.mbl.common.mapper.UserMapper;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.auth.biz.AuthLoginBiz;

/**
 * 登录业务层
 * @author zl
 * @create 2015年11月30日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "authLoginBiz")
@Transactional
public class AuthLoginBizImpl implements AuthLoginBiz {
	
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private UserMapper userMapper;
	
	// 通过用户id查询账号
	@Override
	public AccountVO getAccountByUserId(String userId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		List<AccountVO> avList = accountMapper.findAllListByParams(params);
		return null!=avList&&avList.size()>0?avList.get(0):null;
	}
	
	// 通过账号id查询账号
	@Override
	public AccountVO getAccountByAccountId(String accountId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("accountId", accountId);
		List<AccountVO> avList = accountMapper.findAllListByParams(params);
		return null!=avList&&avList.size()>0?avList.get(0):null;
	}
	
	// 通过userId查询用户
	@Override
	public User getUserByUserId(String userId){
		return userMapper.getById(userId);
	}
}
