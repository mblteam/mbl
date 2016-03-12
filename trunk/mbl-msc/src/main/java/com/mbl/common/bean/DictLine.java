package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 字典表行表(M_DICT_LINE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class DictLine extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7853611326457192107L;
    
    /** 字典行id */
    private String dictLineId;
    
    /** 字典头id */
    private String dictHeadId;
    
    /** 字典code */
    private String dictCode;
    
    /** 字典值 */
    private String dictValue;
    
    /** 行状态1-有效 0-失效 */
    private String lineStatus;
    
    /**
     * 获取字典行id
     * 
     * @return 字典行id
     */
    public String getDictLineId() {
        return this.dictLineId;
    }
     
    /**
     * 设置字典行id
     * 
     * @param dictLineId
     *          字典行id
     */
    public void setDictLineId(String dictLineId) {
        this.dictLineId = dictLineId;
    }
    
    /**
     * 获取字典头id
     * 
     * @return 字典头id
     */
    public String getDictHeadId() {
        return this.dictHeadId;
    }
     
    /**
     * 设置字典头id
     * 
     * @param dictHeadId
     *          字典头id
     */
    public void setDictHeadId(String dictHeadId) {
        this.dictHeadId = dictHeadId;
    }
    
    /**
     * 获取字典code
     * 
     * @return 字典code
     */
    public String getDictCode() {
        return this.dictCode;
    }
     
    /**
     * 设置字典code
     * 
     * @param dictCode
     *          字典code
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
    
    /**
     * 获取字典值
     * 
     * @return 字典值
     */
    public String getDictValue() {
        return this.dictValue;
    }
     
    /**
     * 设置字典值
     * 
     * @param dictValue
     *          字典值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
    
    /**
     * 获取行状态1-有效 0-失效
     * 
     * @return 行状态1-有效 0-失效
     */
    public String getLineStatus() {
        return this.lineStatus;
    }
     
    /**
     * 设置行状态1-有效 0-失效
     * 
     * @param lineStatus
     *          行状态1-有效 0-失效
     */
    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }
}