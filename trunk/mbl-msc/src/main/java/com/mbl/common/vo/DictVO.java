package com.mbl.common.vo;

import com.mbl.common.framework.mapper.BaseEntity;

public class DictVO extends BaseEntity implements java.io.Serializable{
	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = -3877205473000052820L;

	/** 字典id */
    private String dictId;
    
    /** 字典名称 */
    private String dictName;
    
    /** 字典头编码 */
    private String headCode;
    
    /** 字典状态1-有效 0-失效 */
    private String dictStatus;
    
    /** 字典行id */
    private String dictLineId;
    
    /** 字典code */
    private String dictCode;
    
    /** 字典值 */
    private String dictValue;
    
    /** 行状态1-有效 0-失效 */
    private String lineStatus;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getHeadCode() {
		return headCode;
	}

	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}

	public String getDictStatus() {
		return dictStatus;
	}

	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}

	public String getDictLineId() {
		return dictLineId;
	}

	public void setDictLineId(String dictLineId) {
		this.dictLineId = dictLineId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
    
}
