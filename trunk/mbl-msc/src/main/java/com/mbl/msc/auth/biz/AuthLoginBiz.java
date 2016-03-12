package com.mbl.msc.auth.biz;

import com.mbl.common.bean.User;
import com.mbl.common.vo.AccountVO;

/**
 * 登录
 * 功能详细描述
 * @author zl
 * @create 2015年12月27日 下午3:09:03 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AuthLoginBiz {

	/**
	 * 通过用户id查询账号
	 * 功能详细描述
	 * @param userId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	AccountVO getAccountByUserId(String userId);

	/**
	 * 通过userId查询用户
	 * 功能详细描述
	 * @param userId
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	User getUserByUserId(String userId);

	/**
	 * 通过账号id查询账号
	 * 功能详细描述
	 * @param accountId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	AccountVO getAccountByAccountId(String accountId);
	
}
