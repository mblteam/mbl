package com.mbl.common.vo;

import java.util.Date;

public class AccountVO {

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

    /** 用户姓名 */
    private String userName;
    
    /** 电话号码 */
    private String tel;
    
    /** 地址 */
    private String address;
    
    /** 性别 */
    private String sex;
    
    /** 积分 */
    private Integer point;
    
    /** 用户状态1-有效 0-失效 */
    private String userStatus;
    
    /** 其他联系方式 */
    private String tel2;
    
    /** token */
	private String token;
	/**
	 * 门店编码
	 */
	private String shopCode;
	/**
	 * 创建人
	 */
	private String creationBy;
	/**
	 * 创建时间
	 */
	private String creationDate;
	/**
	 * 最后修改人
	 */
	private String lastUpdateBy;
	/**
	 * 最后修改时间
	 */
	private String lastUpdateDate;
	 
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getUserId() {
        return this.userId;
    }
     
    /**
     * 设置主键id
     * 
     * @param userId
     *          主键id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取用户姓名
     * 
     * @return 用户姓名
     */
    public String getUserName() {
        return this.userName;
    }
     
    /**
     * 设置用户姓名
     * 
     * @param userName
     *          用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * 获取电话号码
     * 
     * @return 电话号码
     */
    public String getTel() {
        return this.tel;
    }
     
    /**
     * 设置电话号码
     * 
     * @param tel
     *          电话号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /**
     * 获取地址
     * 
     * @return 地址
     */
    public String getAddress() {
        return this.address;
    }
     
    /**
     * 设置地址
     * 
     * @param address
     *          地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 获取性别
     * 
     * @return 性别
     */
    public String getSex() {
        return this.sex;
    }
     
    /**
     * 设置性别
     * 
     * @param sex
     *          性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * 获取积分
     * 
     * @return 积分
     */
    public Integer getPoint() {
        return this.point;
    }
     
    /**
     * 设置积分
     * 
     * @param point
     *          积分
     */
    public void setPoint(Integer point) {
        this.point = point;
    }
    
    /**
     * 获取用户状态1-有效 0-失效
     * 
     * @return 用户状态1-有效 0-失效
     */
    public String getUserStatus() {
        return this.userStatus;
    }
     
    /**
     * 设置用户状态1-有效 0-失效
     * 
     * @param userStatus
     *          用户状态1-有效 0-失效
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    
    /**
     * 获取其他联系方式
     * 
     * @return 其他联系方式
     */
    public String getTel2() {
        return this.tel2;
    }
     
    /**
     * 设置其他联系方式
     * 
     * @param tel2
     *          其他联系方式
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
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
    
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
