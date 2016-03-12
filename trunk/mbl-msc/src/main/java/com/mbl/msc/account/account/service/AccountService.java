package com.mbl.msc.account.account.service;

import com.mbl.common.framework.exception.excepts.BizException;

/**
 * 向用户提供接口
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月23日 下午2:21:44 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AccountService {
	/**
	 * 保存用户
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param userId
	 * @param accountCode
	 * @throws BizException 
	 * @throws Exception 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void saveAccout(String userId,String accountCode) throws BizException, Exception;
	/**
	 * 删除客户
	 * @param id
	 * @throws BizException 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void deleteAccountByUserId(String userId) throws BizException;
}
