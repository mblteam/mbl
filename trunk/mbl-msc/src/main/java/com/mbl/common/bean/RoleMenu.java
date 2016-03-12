package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 角色菜单对应关系(M_ROLE_MENU)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class RoleMenu extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -5359232675812323170L;
    
    /** 主键id */
    private String rmId;
    
    /** 角色id */
    private String roleId;
    
    /** 菜单id */
    private String menuId;
    
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