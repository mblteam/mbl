package com.mbl.msc.account.account.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.account.AccountConstant;
import com.mbl.msc.account.account.biz.AccountBiz;
import com.mbl.msc.account.account.service.AccountService;
/**
 * 向用户提供接口
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月23日 下午2:23:52 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Transactional
@Service(value="accountService")
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountBiz accountBiz;
	/**
	 * 保存用户
	 * @param userId
	 * @param accountCode  
	 * @throws Exception 
	 * @see com.mbl.msc.account.account.service.AccountService#saveAccout(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveAccout(String userId, String accountCode) throws Exception {
		AccountVO accountVo = new AccountVO();
		accountVo.setUserId(userId);
		accountVo.setAccountCode(accountCode);
		accountVo.setPwd(CommonUtils.md5Encode(AccountConstant.ACCOUNT_ORIGINAL_CODE));
		accountVo.setStatus(AccountConstant.ACCOUNT_EFFECTIVE);
		accountVo.setAccountType(AccountConstant.ENQUIRE_TYPE_PLAT);
		accountBiz.saveAccount(accountVo);
	}
	@Override
	public void deleteAccountByUserId(String userId) throws BizException {
		accountBiz.deleteAccountByUserId(userId);
	}
}
