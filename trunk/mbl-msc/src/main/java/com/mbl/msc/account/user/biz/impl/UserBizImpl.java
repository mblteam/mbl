package com.mbl.msc.account.user.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.User;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.UserMapper;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.account.service.AccountService;
import com.mbl.msc.account.user.biz.UserBiz;



/**
 * 用户业务逻辑层
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月19日 下午3:21:26 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "userBiz")
@Transactional
public class UserBizImpl implements UserBiz {

	@Resource
	private UserMapper userMapper;
	@Resource
	private AccountService accountService;
	
	public Long getCountByParams(Map<String, Object> map) {
		return userMapper.getCountByParams(map);
	}
	
	public User getById(String userId) {
		return userMapper.getById(userId);
	}
	/***
	 * 保存
	 * @throws Exception 
	 * @throws BizException 
	 */
	public int saveUser(AccountVO accountVo) throws BizException, Exception {
		if(accountVo!= null) {
			 User user = new User();
			BeanUtils.copyProperties(accountVo, user);
			if(StringUtils.isNotEmpty(user.getUserId())) {
				return userMapper.update(user);
			} else {
				user.setUserId(UUID.randomUUID().toString());
				accountService.saveAccout(user.getUserId(),accountVo.getAccountCode());
				return userMapper.save(user);
			}
		}
		return 0;
	}

	@Override
	public int delById(String userId) throws BizException {
		accountService.deleteAccountByUserId(userId);
		return userMapper.delById(userId);
	}

	@Override
	public List<User> findList(Map<String, Object> query, Integer page,
			Integer pageSize) {
		return userMapper.findListByParams(query, RowBounsUtil.getRowBounds(page, pageSize));
	}

}
