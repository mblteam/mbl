/**    
 * Copyright (C),Kingmed
 * @FileName: LoginRequestVo.java  
 * @Package: com.kingmed.lb.authc.vo  
 * @Description: //模块目的、功能描述  
 * @Author HuangSiwei  
 * @Date 2015年4月7日 下午6:20:16  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.mbl.msc.account.user.vo;

import java.sql.Timestamp;




/**
 * 
 * 用户已支付套餐请求参数
 * @author xjs
 * @create 2015年12月05日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class UserVO {
	
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
    
    /**  */
	private Timestamp creationDate;

	/**  */
	private String creationBy;

	/**  */
	private Timestamp lastUpdateDate;

	/**  */
	private String lastUpdateBy;
    /**
     * 账户类型
     */
    private String accountType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getAccountType() {
		return accountType;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
