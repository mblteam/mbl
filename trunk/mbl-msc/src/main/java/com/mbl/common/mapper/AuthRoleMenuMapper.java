package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.RoleMenu;
import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.RoleMenuVO;

public interface AuthRoleMenuMapper  extends BaseMapper<RoleMenu>{
	/***
	 * 根据roleId删除
	 * @param map
	 */
	public void delByRoleId(Map<String, Object> map);
	
	public List<RoleMenuVO> findRoleMenuListByParams(Map<String, Object> map);
	
	public RoleMenuVO getRoleMenuById(Map<String, Object> map);
}
