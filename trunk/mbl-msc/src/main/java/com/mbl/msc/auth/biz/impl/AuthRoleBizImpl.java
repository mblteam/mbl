package com.mbl.msc.auth.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.AccountRole;
import com.mbl.common.bean.Menu;
import com.mbl.common.bean.Role;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.AccountRoleMapper;
import com.mbl.common.mapper.AuthMenuMapper;
import com.mbl.common.mapper.AuthRoleMapper;
import com.mbl.common.mapper.AuthRoleMenuMapper;
import com.mbl.common.util.MenuTreeUtil;
import com.mbl.common.vo.RoleMenuVO;
import com.mbl.common.vo.TreeNode;
import com.mbl.msc.auth.biz.AuthRoleBiz;

@Service(value = "authRoleBiz")
@Transactional
public class AuthRoleBizImpl implements AuthRoleBiz {
	@Resource
	private AuthRoleMapper authRoleMapper;
	
	@Resource
	private AuthMenuMapper authMenuMapper;
	
	@Resource
	private AuthRoleMenuMapper authRoleMenuMapper;
	
	@Resource
	private AccountRoleMapper accountRoleMapper;
	
	public Role getById(String id){
		return authRoleMapper.getById(id);
	}
	
	public Long countRoleList(Map<String, Object> query) {
		return authRoleMapper.getCountByParams(query);
	}
	
	public List<Role> findRoleList(Map<String, Object> query,Integer page,Integer pageSize) {
		return authRoleMapper.findListByParams(query, RowBounsUtil.getRowBounds(page, pageSize));
	}
	
	/***
	 * 保存角色
	 * @param roleVo
	 * @param role
	 * @throws BizException
	 */
	public Role saveRole(Role roleVo) throws BizException {
		try {
			Role role = new Role();
			BeanUtils.copyProperties(roleVo, role);
			if(StringUtils.isEmpty(roleVo.getRoleId())){
				role.setRoleId(UUID.randomUUID().toString());
				authRoleMapper.save(role);
			} else {
				authRoleMapper.update(role);
			}
			return role;
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	/***
	 * 删除角色
	 * @param roleVo
	 * @throws BizException
	 */
	public void deleteRole(String id) throws BizException {
		authRoleMapper.delById(id);
	}
	
	/**
	 * 通过账号id查询角色菜单权限数据
	 * @param accountId
	 * @return  
	 */
	@Override
	public TreeNode getMenuRightByAccountId(String accountId) {
		List<AccountRole> roles = accountRoleMapper.getByAccountId(accountId);
		if(CollectionUtils.isEmpty(roles)){
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roles.get(0).getRoleId());
		
		Map<String,Menu> menuExistMap = new HashMap<String,Menu>();
		
		List<Menu> roots = authMenuMapper.findRootMenu();
		if(null==roots||roots.size()==0){
			return null;
		}
		Menu root = roots.get(0);
		TreeNode tn = new TreeNode();
		tn.setId(root.getMenuId());
		tn.setCode(root.getMenuCode());
		tn.setText(root.getMenuName());
		tn.setParentId("#");
		
		menuExistMap.put(tn.getId(), root);
		
		List<RoleMenuVO> roleMenuList = authRoleMenuMapper.findRoleMenuListByParams(map);
		for (RoleMenuVO roleMenu : roleMenuList) {
			Menu menu = null==menuExistMap.get(roleMenu.getMenuId())
					?authMenuMapper.getById(roleMenu.getMenuId()):menuExistMap.get(roleMenu.getMenuId());
			if(null!=menu){
				menuExistMap.put(roleMenu.getMenuId(), menu);
				fetchMenu(menu,menuExistMap);
			}
		}
		return MenuTreeUtil.buildByPerms1(tn.getCode(), menuExistMap.values());
	}
	
	private void fetchMenu(Menu menu,Map<String,Menu> menuExistMap){
		if(null!=menu.getParentMenuId()&&!menuExistMap.containsKey(menu.getParentMenuId())){
			Menu menu1 = authMenuMapper.getById(menu.getParentMenuId());
		    menuExistMap.put(menu1.getMenuId(), menu1);
		    fetchMenu(menu,menuExistMap);
		}else{
			return;
		}
	}
}
