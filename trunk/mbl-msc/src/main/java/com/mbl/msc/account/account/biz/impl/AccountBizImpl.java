package com.mbl.msc.account.account.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Account;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.AccountMapper;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.account.AccountConstant;
import com.mbl.msc.account.account.biz.AccountBiz;

@Service(value = "accountBiz")
@Transactional
public class AccountBizImpl implements AccountBiz {
	@Resource
	private AccountMapper accountMapper;
	
	public Account getById(String id){
		return accountMapper.getById(id);
	}
	
	public Long countAccountList(Map<String, Object> query) {
		return accountMapper.getCountByParams(query);
	}
	
	public List<AccountVO> findAccountList(Map<String, Object> query,Integer page,Integer pageSize) {
		return accountMapper.findAllListByParams(query, RowBounsUtil.getRowBounds(page, pageSize));
	}
	
	/***
	 * 保存账户
	 * @param accountVO
	 * @param account
	 * @throws BizException
	 */
	public Account saveAccount(AccountVO accountVO) throws BizException {
		try {
			Account account = new Account();
			BeanUtils.copyProperties(accountVO, account);
			if(StringUtils.isEmpty(accountVO.getAccountId())){
				account.setAccountId(UUID.randomUUID().toString());
				accountMapper.save(account);
			} else {
				accountMapper.update(account);
			}
			return account;
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	/***
	 * 删除账户
	 * @param accountVo
	 * @throws BizException
	 */
	public void deleteAccount(String id) throws BizException {
		accountMapper.delById(id);
	}
	/**
	 * 通过用户id删除账户
	 * @param userId  
	 * @see com.mbl.msc.account.account.biz.AccountBiz#deleteAccountByUserId(java.lang.String)
	 */
	@Override
	public void deleteAccountByUserId(String userId) {
		accountMapper.deleteAccountByUserId(userId);
	}
	/**
	 * 通过密码查找账户
	 * @param oldAccountPsw
	 * @return  
	 * @throws Exception 
	 * @see com.mbl.msc.account.account.biz.AccountBiz#findByOldPsw(java.lang.String)
	 */
	@Override
	public Long findByOldPsw(Map<String, Object> query) throws Exception {
		String oldPassword = CommonUtils.md5Encode(query.get("oldAccountPsw").toString());
		query.put("oldAccountPsw", oldPassword);
		return accountMapper.findByOldPsw(query);
	}
	/**
	 * 修改密码
	 * @param query
	 * @return
	 * @throws Exception  
	 * @see com.mbl.msc.account.account.biz.AccountBiz#updatePsw(java.util.Map)
	 */
	@Override
	public String updatePsw(Map<String, Object> query) throws Exception {
		String accountId = query.get("accountId").toString();
		String oldAccountPsw = query.get("oldAccountPsw").toString();
		if (StringUtils.isNotBlank(accountId)&&StringUtils.isNotBlank(oldAccountPsw)) {
			String oldPassword = CommonUtils.md5Encode(query.get("oldAccountPsw").toString());
			query.put("oldAccountPsw", oldPassword);
			Long count = accountMapper.findByOldPsw(query);
			if(count<=0){
				return "-1";
			}else{
				Account account = accountMapper.getById(accountId);
				String newAccountPsw = query.get("newAccountPsw").toString();
				if(StringUtils.isNotEmpty(newAccountPsw)){
					account.setPwd(CommonUtils.md5Encode(newAccountPsw));
				}
				accountMapper.update(account);
			}
		}
		return null;
	}
	/**
	 * 通过账户编号查找账户
	 * @param accountCode
	 * @return  
	 * @see com.mbl.msc.account.account.biz.AccountBiz#findAccountByCode(java.lang.String)
	 */
	@Override
	public AccountVO findAccountByCode(String accountCode) {
		return accountMapper.findAccountByCode(accountCode);
	}
	/**
	 * 重置密码
	 * @param accountId
	 * @return  
	 * @throws Exception 
	 * @see com.mbl.msc.account.account.biz.AccountBiz#reSetPsw(java.lang.String)
	 */
	@Override
	public String reSetPsw(String accountId) throws Exception {
		Account account = accountMapper.getById(accountId);
		account.setPwd(CommonUtils.md5Encode(AccountConstant.ACCOUNT_ORIGINAL_CODE));
		accountMapper.update(account);
		return null;
	}
}
