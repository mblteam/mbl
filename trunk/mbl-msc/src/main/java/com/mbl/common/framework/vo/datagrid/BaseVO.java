package com.mbl.common.framework.vo.datagrid;

import java.io.Serializable;
import java.util.Date;

public class BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 创建人
	 */
	public String creater;
	
	/**
	 * 创建时间
	 */
	public Date createTime;
	
	/**
	 * 创建起始时间
	 */
	public Date start_createTime;
	
	/**
	 * 创建结束时间
	 */
	public Date end_createTime;
	
	/**
	 * 修改人
	 */
	public String  updator;
	
	/**
	 * 修改时间
	 */
	public Date updateTime;
	
	/**
	 * 修改起始时间
	 */
	public Date start_updateTime;
	
	/**
	 * 修改结束时间
	 */
	public Date end_updateTime;

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStart_createTime() {
		return start_createTime;
	}

	public void setStart_createTime(Date start_createTime) {
		this.start_createTime = start_createTime;
	}

	public Date getEnd_createTime() {
		return end_createTime;
	}

	public void setEnd_createTime(Date end_createTime) {
		this.end_createTime = end_createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getStart_updateTime() {
		return start_updateTime;
	}

	public void setStart_updateTime(Date start_updateTime) {
		this.start_updateTime = start_updateTime;
	}

	public Date getEnd_updateTime() {
		return end_updateTime;
	}

	public void setEnd_updateTime(Date end_updateTime) {
		this.end_updateTime = end_updateTime;
	}	
	
}
