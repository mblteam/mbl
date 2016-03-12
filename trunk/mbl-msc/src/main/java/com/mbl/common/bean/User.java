package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 用户信息表(M_USER)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class User extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 8862390308538365031L;
    
    /** 主键id */
    private String userId;
    
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
    /**
     * 账户类型
     */
    private String accountType;
    
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
    /**
     * 获取账户类型
     *〈一句话功能简述〉 
     * 功能详细描述
     * @return
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * 设置账户类型
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param accountType
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
    
}