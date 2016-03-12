package com.mbl.msc.auth.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Menu;
import com.mbl.common.bean.RoleMenu;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.mapper.AuthMenuMapper;
import com.mbl.common.mapper.AuthRoleMenuMapper;
import com.mbl.common.util.MenuTreeUtil;
import com.mbl.common.vo.RoleMenuVO;
import com.mbl.common.vo.TreeNode;
import com.mbl.msc.auth.biz.AuthRoleMenuBiz;

@Service(value = "authRoleMenuBiz")
@Transactional
public class AuthRoleMenuBizImpl implements AuthRoleMenuBiz {
	@Resource
	private AuthRoleMenuMapper authRoleMenuMapper;

	@Resource
	private AuthMenuMapper authMenuMapper;

	/***
	 * 删除对应关系
	 * @param roleVo
	 * @throws BizException
	 */
	public void deleteRoleMenuById(RoleMenu roleMenuVo) throws BizException {
		authRoleMenuMapper.delById(roleMenuVo.getRmId());
	}
	
	/***
	 * 查询结果
	 * @param map
	 * @return
	 * @throws BizException
	 */
	public List<RoleMenuVO> findRoleMenuList(Map<String, Object> map) throws BizException {
		return authRoleMenuMapper.findRoleMenuListByParams(map);
	}
	
	/***
	 * 批量新增
	 * @param roleMenuVo
	 */
	public void batchSaveRoleMenu(String roleId,List<Menu> menuVoList){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		authRoleMenuMapper.delByRoleId(map);
		
		for(Menu menu : menuVoList) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menu.getMenuId());
			roleMenu.setRmId(UUID.randomUUID().toString());
			authRoleMenuMapper.save(roleMenu);
		}
	}
	
	/**
	 * 通过角色id查询角色菜单树
	 * @param roleId
	 * @return  
	 * @see com.mbl.msc.auth.biz.AuthRoleMenuBiz#findRoleMenuTreeByRoleId(java.lang.String)
	 */
	@Override
	public List<TreeNode> findRoleMenuTreeByRoleId(String roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		List<RoleMenuVO> roleMenuList = authRoleMenuMapper.findRoleMenuListByParams(map);
		Map<String,Object> menuExistMap = new HashMap<String,Object>();
		for (RoleMenuVO roleMenu : roleMenuList) {
			menuExistMap.put(roleMenu.getMenuId(), true);
		}
		List<Menu> roots = authMenuMapper.findRootMenu();
		if(null==roots||roots.size()==0){
			return new ArrayList<TreeNode>();
		}
		Menu root = roots.get(0);
		TreeNode tn = new TreeNode();
		tn.setId(root.getMenuId());
		tn.setCode(root.getMenuCode());
		tn.setText(root.getMenuName());
		if(menuExistMap.containsKey(tn.getId())){
			tn.setState(MenuTreeUtil.STATE_SELETED);
		}
		tn.setParentId("#");
		fetchMenu(tn,menuExistMap);
		List<TreeNode> tnns = new ArrayList<TreeNode>();
		tnns.add(tn);
		return tnns;
	}
	
	private void fetchMenu(TreeNode node,Map<String,Object> menuExistMap){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentMenuId", node.getId());
		List<Menu> menus = authMenuMapper.findListByParams(params);
		if(null!=menus&&menus.size()>0){
			List<TreeNode> tnChildList = new ArrayList<TreeNode>();
			for (Menu menu : menus) {
				TreeNode tnChild = new TreeNode();
				tnChild.setId(menu.getMenuId());
				tnChild.setCode(menu.getMenuCode());
				tnChild.setText(menu.getMenuName());
				if(menuExistMap.containsKey(tnChild.getId())){
					tnChild.setState(MenuTreeUtil.STATE_SELETED);
				}
				tnChild.setParentId(node.getId());
				fetchMenu(tnChild,menuExistMap);
				tnChildList.add(tnChild);
			}
			node.setChildren(tnChildList);
		}
	}
	
	public AuthMenuMapper getAuthMenuMapper() {
		return authMenuMapper;
	}

	public void setAuthMenuMapper(AuthMenuMapper authMenuMapper) {
		this.authMenuMapper = authMenuMapper;
	}

	public AuthRoleMenuMapper getAuthRoleMenuMapper() {
		return authRoleMenuMapper;
	}

	public void setAuthRoleMenuMapper(AuthRoleMenuMapper authRoleMenuMapper) {
		this.authRoleMenuMapper = authRoleMenuMapper;
	}

}
