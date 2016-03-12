package com.mbl.msc.auth.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.Menu;
import com.mbl.common.bean.RoleMenu;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.RoleMenuVO;
import com.mbl.common.vo.TreeNode;

public interface AuthRoleMenuBiz {
	/***
	 * 删除对应关系
	 * 
	 * @param roleVo
	 * @throws BizException
	 */
	public void deleteRoleMenuById(RoleMenu roleMenuVo) throws BizException;

	/***
	 * 查询结果
	 * 
	 * @param map
	 * @return
	 * @throws BizException
	 */
	public List<RoleMenuVO> findRoleMenuList(Map<String, Object> map) throws BizException;

	/***
	 * 批量新增
	 * 
	 * @param roleMenuVo
	 */
	public void batchSaveRoleMenu(String roleId, List<Menu> menuVoList);

	/**
	 * 通过角色id查询角色菜单树
	 * 功能详细描述
	 * @param roleId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<TreeNode> findRoleMenuTreeByRoleId(String roleId);

}
