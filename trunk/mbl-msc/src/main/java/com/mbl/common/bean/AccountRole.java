package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 账号角色对应关系(M_ACCOUNT_ROLE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class AccountRole extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -4000571434750984397L;
    
    /** 主键id */
    private String urId;
    
    /** 账号id */
    private String accountId;
    
    /** 角色id */
    private String roleId;
    
    /** 状态1-有效 0-失效 */
    private String status;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getUrId() {
        return this.urId;
    }
     
    /**
     * 设置主键id
     * 
     * @param urId
     *          主键id
     */
    public void setUrId(String urId) {
        this.urId = urId;
    }
    
    /**
     * 获取账号id
     * 
     * @return 账号id
     */
    public String getAccountId() {
        return this.accountId;
    }
     
    /**
     * 设置账号id
     * 
     * @param accountId
     *          账号id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
     * 获取状态1-有效 0-失效
     * 
     * @return 状态1-有效 0-失效
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态1-有效 0-失效
     * 
     * @param status
     *          状态1-有效 0-失效
     */
    public void setStatus(String status) {
        this.status = status;
    }
}