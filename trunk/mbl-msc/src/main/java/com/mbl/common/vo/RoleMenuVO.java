package com.mbl.common.vo;

import com.mbl.common.framework.mapper.BaseEntity;

public class RoleMenuVO extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4787239326383022758L;

	/** 主键id */
    private String rmId;
    
    /** 角色id */
    private String roleId;
    
    /** 菜单id */
    private String menuId;
    
    private String roleName;
    
    private String menuName;
    
    private String parentMenuId;
    
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	/**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getRmId() {
        return this.rmId;
    }
     
    /**
     * 设置主键id
     * 
     * @param rmId
     *          主键id
     */
    public void setRmId(String rmId) {
        this.rmId = rmId;
    }
    
    /**
     * 获取角色id
     * 
     * @return 角色id
     */
    public String getRoleId() {
        return this.roleId;
    }
     
    /**
     * 设置角色id
     * 
     * @param roleId
     *          角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    /**
     * 获取菜单id
     * 
     * @return 菜单id
     */
    public String getMenuId() {
        return this.menuId;
    }
     
    /**
     * 设置菜单id
     * 
     * @param menuId
     *          菜单id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


}
