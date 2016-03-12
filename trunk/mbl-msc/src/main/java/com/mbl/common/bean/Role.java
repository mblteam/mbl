package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 角色(M_ROLE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Role extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 7758484111336197429L;
    
    /** 角色id */
    private String roleId;
    
    /** 角色编号 */
    private String roleCode;
    
    /** 角色名称 */
    private String roleName;
    
    /** 角色状态1-有效 0-失效 */
    private String roleStatus;
    
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
     * 获取角色名称
     * 
     * @return 角色名称
     */
    public String getRoleName() {
        return this.roleName;
    }
     
    /**
     * 设置角色名称
     * 
     * @param roleName
     *          角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * 获取角色状态1-有效 0-失效
     * 
     * @return 角色状态1-有效 0-失效
     */
    public String getRoleStatus() {
        return this.roleStatus;
    }
     
    /**
     * 设置角色状态1-有效 0-失效
     * 
     * @param roleStatus
     *          角色状态1-有效 0-失效
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
    
}