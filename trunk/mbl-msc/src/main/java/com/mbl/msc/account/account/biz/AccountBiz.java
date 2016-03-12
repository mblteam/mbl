package com.mbl.msc.account.account.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.Account;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.AccountVO;

/****
 * 账户业务逻辑
 * 
 * @author jiangjj
 *
 */
public interface AccountBiz {
	
	/***
	 * 根据id查账户
	 * @param id
	 * @return
	 */
	public Account getById(String id);
	/***
	 * 统计总数
	 * @param query
	 * @return
	 */
	public Long countAccountList(Map<String, Object> query) ;
	
	/***
	 * 保存账户
	 * 
	 * @param accountVo
	 * @throws BizException
	 */
	public Account saveAccount(AccountVO accountVo) throws BizException;

	/***
	 * 删除账户
	 * 
	 * @param accountVo
	 * @throws BizException
	 */
	public void deleteAccount(String id) throws BizException;

	/****
	 * 查询
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<AccountVO> findAccountList(Map<String, Object> query,Integer page,Integer pageSize);
	/**
	 * 通过用户id删除账户
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param userId
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void deleteAccountByUserId(String userId);
	/**
	 * 通过密码查找账户
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param oldAccountPsw
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public Long findByOldPsw(Map<String, Object> query)  throws Exception;
	/**
	 * 修改密码
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param query
	 * @return
	 * @throws Exception 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public String updatePsw(Map<String, Object> query) throws Exception;
	/**
	 * 通过账户编码查找账户信息
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param accountCode
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	AccountVO findAccountByCode(String accountCode);
	/**
	 * 重置密码
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param accountId
	 * @return
	 * @throws Exception 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public String reSetPsw(String accountId) throws Exception;
}
