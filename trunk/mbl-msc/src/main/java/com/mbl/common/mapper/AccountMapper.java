package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.Account;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.AccountVO;

public interface AccountMapper extends BaseMapper<Account>{

	AccountVO findAccountByParams(Map<String, Object> map);
	
	Long getCountByParams(Map<String, Object> map);
	
	AccountVO findAccountByCode(String accountCode);
	
	List<AccountVO> findAllListByParams(Map<String, Object> map,RowBounds rowBounds);
	
	List<AccountVO> findAllListByParams(Map<String, Object> map);

	void deleteAccountByUserId(String userId);

	Long findByOldPsw(Map<String, Object> map);

}