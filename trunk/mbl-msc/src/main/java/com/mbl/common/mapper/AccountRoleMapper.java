package com.mbl.common.mapper;

import java.util.List;

import com.mbl.common.bean.AccountRole;
import com.mbl.common.framework.mapper.BaseMapper;

public interface AccountRoleMapper extends BaseMapper<AccountRole>{
	
	List<AccountRole> getByAccountId(String accountId);
	
	void deleteByAccountId(String accountId);
}