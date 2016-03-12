package com.mbl.msc.auth.biz;

import java.util.List;

import com.mbl.common.bean.Menu;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.TreeNode;
import com.mbl.msc.auth.vo.MenuTreeVO;

/**
 * 权限菜单业务逻辑接口
 * @author zl
 * @create 2015年12月1日 上午12:33:37 
 * @version 1.0.0
 */
public interface AuthMenuBiz {

	/**
	 * 是否根节点
	 * @return
	 */
	boolean isHasRootMenu();

	/**
	 * 是否存在当前菜单
	 * @param menuVO
	 * @return
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	String isExistMenu(Menu menuVO) throws BizException;

	/**
	 * 通过菜单id找子菜单
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	List<MenuTreeVO> findChildById(String id);

	/**
	 * 添加菜单
	 * @param menuVO
	 * @return
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	Menu addMenu(Menu menuVO) throws BizException;

	/**
	 * 删除菜单
	 * @param menuId
	 * @return
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	int delMenu(String menuId) throws BizException;

	/**
	 * 查询菜单
	 * @param menuId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	Menu getMenuById(String menuId);

	/**
	 * 加载所有菜单
	 * 功能详细描述
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	List<TreeNode> loadAllMenu();
}
