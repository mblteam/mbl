package com.mbl.msc.account.accountRole.biz;

import java.util.List;

import com.mbl.common.bean.AccountRole;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.AccountRoleVO;

/**
 * 账户角色关联业务逻辑
 * @author fangxiaowei
 * @create 2015年12月24日 下午5:17:05 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AccountRoleBiz {
	/**
	 * 通过主键id获取账户角色信息
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public AccountRole getById(String id);
	/**
	 * 保存账户角色
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param accountRoles
	 * @return
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void saveByAccountId(AccountRoleVO accountRoleVO) throws BizException;
	/**
	 * 通过账户id获取账户角色信息
	 * @param accountId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<AccountRole> getByAccountId(String accountId);
	/**
	 * 通过账户id删除相关账户角色信息
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param accountId
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void deleteByAccountId(String accountId);
}
