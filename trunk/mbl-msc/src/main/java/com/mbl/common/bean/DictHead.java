package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

/**
 * 字典表头表(M_DICT_HEAD)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class DictHead extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -8228394142545303692L;
    
    /** 字典id */
    private String dictId;
    
    /** 字典名称 */
    private String dictName;
    
    /** 字典头编码 */
    private String headCode;
    
    /** 字典状态1-有效 0-失效 */
    private String dictStatus;
    
    /**
     * 获取字典id
     * 
     * @return 字典id
     */
    public String getDictId() {
        return this.dictId;
    }
     
    /**
     * 设置字典id
     * 
     * @param dictId
     *          字典id
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }
    
    /**
     * 获取字典名称
     * 
     * @return 字典名称
     */
    public String getDictName() {
        return this.dictName;
    }
     
    /**
     * 设置字典名称
     * 
     * @param dictName
     *          字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    
    /**
     * 获取字典状态1-有效 0-失效
     * 
     * @return 字典状态1-有效 0-失效
     */
    public String getDictStatus() {
        return this.dictStatus;
    }
     
    /**
     * 设置字典状态1-有效 0-失效
     * 
     * @param dictStatus
     *          字典状态1-有效 0-失效
     */
    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus;
    }

	public String getHeadCode() {
		return headCode;
	}

	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
}