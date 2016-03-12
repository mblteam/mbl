package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.Menu;
import com.mbl.common.framework.mapper.BaseMapper;


public interface AuthMenuMapper extends BaseMapper<Menu> {
	/**
	 * 查找根菜单
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	List<Menu> findRootMenu();

	/**
	 * 根据条件查询菜单数量除了当前菜单
	 * @param params
	 * @param menuId
	 * @return
	 */
	Long getCountByParamsNotMenuId(Map<String, Object> params);
	
}
