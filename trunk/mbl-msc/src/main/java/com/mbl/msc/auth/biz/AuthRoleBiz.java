package com.mbl.msc.auth.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.Role;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.TreeNode;

/**
 * 角色业务逻辑
 * @author fangxiaowei
 * @create 2015年12月10日 下午5:18:26 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AuthRoleBiz {
	
	/***
	 * 根据id查角色
	 * @param id
	 * @return
	 */
	public Role getById(String id);
	/***
	 * 统计总数
	 * @param query
	 * @return
	 */
	public Long countRoleList(Map<String, Object> query) ;
	
	/***
	 * 保存角色
	 * 
	 * @param roleVo
	 * @throws BizException
	 */
	public Role saveRole(Role roleVo) throws BizException;

	/***
	 * 删除角色
	 * 
	 * @param roleVo
	 * @throws BizException
	 */
	public void deleteRole(String id) throws BizException;

	/****
	 * 查询
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Role> findRoleList(Map<String, Object> query,Integer page,Integer pageSize);

	/**
	 * 通过角色id查询角色菜单权限数据
	 * 功能详细描述
	 * @param roleId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	TreeNode getMenuRightByAccountId(String accountId);
}
