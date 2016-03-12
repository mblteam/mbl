package com.mbl.msc.account.accountRole.biz.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.AccountRole;
import com.mbl.common.bean.Role;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.mapper.AccountRoleMapper;
import com.mbl.common.vo.AccountRoleVO;
import com.mbl.msc.account.accountRole.AccountRoleConstant;
import com.mbl.msc.account.accountRole.biz.AccountRoleBiz;

@Service(value = "accountRoleBiz")
@Transactional
public class AccountRoleBizImpl implements AccountRoleBiz {
	@Resource
	private AccountRoleMapper accountRoleMapper;
	
	@Override
	public AccountRole getById(String id){
		return accountRoleMapper.getById(id);
	}
	@Override
	public void saveByAccountId(AccountRoleVO accountRoleVO) throws BizException{
		try {
			accountRoleMapper.deleteByAccountId(accountRoleVO.getAccountId());
			AccountRole accountRole = null;
			for (Role role : accountRoleVO.getRoles()) {
				accountRole = new AccountRole();
				accountRole.setUrId(UUID.randomUUID().toString());
				accountRole.setAccountId(accountRoleVO.getAccountId());
				accountRole.setRoleId(role.getRoleId());
				accountRole.setStatus(AccountRoleConstant.ACCOUNT_ROLE_EFFECTIVE);
				accountRoleMapper.save(accountRole);
			}
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	@Override
	public List<AccountRole> getByAccountId(String accountId) {
		return accountRoleMapper.getByAccountId(accountId);
	}
	@Override
	public void deleteByAccountId(String accountId) {
		accountRoleMapper.deleteByAccountId(accountId);
	}
}
