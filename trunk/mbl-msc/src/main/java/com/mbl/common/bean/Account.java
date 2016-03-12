package com.mbl.common.bean;

import java.util.Date;

import com.alibaba.druid.support.json.JSONUtils;
import com.mbl.common.framework.mapper.BaseEntity;
import com.mbl.common.util.JsonUtil;

/**
 * 账户信息(M_ACCOUNT)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Account extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3640195220283820745L;
    
    /** 主键id */
    private String accountId;
    
    /** 用户账号 */
    private String accountCode;
    
    /** 密码 */
    private String pwd;
    
    /** 账户类型 1-普通用户 2-店铺用户 3-平台管理用户 */
    private String accountType;
    
    /** 关联用户id */
    private String userId;
    
    /** 状态 1-有效 0-失效 */
    private String status;
    
    /** 关联店铺id */
    private String shopId;
    
    /** 失效日期 */
    private Date disabledDate;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getAccountId() {
        return this.accountId;
    }
     
    /**
     * 设置主键id
     * 
     * @param accountId
     *          主键id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    /**
     * 获取用户账号
     * 
     * @return 用户账号
     */
    public String getAccountCode() {
        return this.accountCode;
    }
     
    /**
     * 设置用户账号
     * 
     * @param accountCode
     *          用户账号
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
    
    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getPwd() {
        return this.pwd;
    }
     
    /**
     * 设置密码
     * 
     * @param pwd
     *          密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    /**
     * 获取账户类型 1-普通用户 2-店铺用户 3-平台管理用户
     * 
     * @return 账户类型 1-普通用户 2-店铺用户 3-平台管理用户
     */
    public String getAccountType() {
        return this.accountType;
    }
     
    /**
     * 设置账户类型 1-普通用户 2-店铺用户 3-平台管理用户
     * 
     * @param accountType
     *          账户类型 1-普通用户 2-店铺用户 3-平台管理用户
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    /**
     * 获取关联用户id
     * 
     * @return 关联用户id
     */
    public String getUserId() {
        return this.userId;
    }
     
    /**
     * 设置关联用户id
     * 
     * @param userId
     *          关联用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取状态 1-有效 0-失效
     * 
     * @return 状态 1-有效 0-失效
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态 1-有效 0-失效
     * 
     * @param status
     *          状态 1-有效 0-失效
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取关联店铺id
     * 
     * @return 关联店铺id
     */
    public String getShopId() {
        return this.shopId;
    }
     
    /**
     * 设置关联店铺id
     * 
     * @param shopId
     *          关联店铺id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    
    /**
     * 获取失效日期
     * 
     * @return 失效日期
     */
    public Date getDisabledDate() {
        return this.disabledDate;
    }
     
    /**
     * 设置失效日期
     * 
     * @param disabledDate
     *          失效日期
     */
    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }
}