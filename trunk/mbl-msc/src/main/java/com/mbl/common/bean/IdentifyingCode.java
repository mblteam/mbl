package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
import java.sql.Timestamp;

/**
 * 验证码(M_IDENTIFYING_CODE)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class IdentifyingCode extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -8374970537380184539L;
    
    /** 主键id */
    private String codeId;
    
    /** 验证码 */
    private String sendCode;
    
    /** 发送手机号码 */
    private String tel;
    
    /** 验证码类型 */
    private String flag;
    
    /** 状态1-有效 0-失效 */
    private String codeStatus;
    
    /** 失效时间 */
    private Timestamp disabledDate;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getCodeId() {
        return this.codeId;
    }
     
    /**
     * 设置主键id
     * 
     * @param codeId
     *          主键id
     */
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }
    
    /**
     * 获取验证码
     * 
     * @return 验证码
     */
    public String getSendCode() {
        return this.sendCode;
    }
     
    /**
     * 设置验证码
     * 
     * @param sendCode
     *          验证码
     */
    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }
    
    /**
     * 获取发送手机号码
     * 
     * @return 发送手机号码
     */
    public String getTel() {
        return this.tel;
    }
     
    /**
     * 设置发送手机号码
     * 
     * @param tel
     *          发送手机号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /**
     * 获取状态1-有效 0-失效
     * 
     * @return 状态1-有效 0-失效
     */
    public String getCodeStatus() {
        return this.codeStatus;
    }
     
    /**
     * 设置状态1-有效 0-失效
     * 
     * @param codeStatus
     *          状态1-有效 0-失效
     */
    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }
    
    /**
     * 获取失效时间
     * 
     * @return 失效时间
     */
    public Timestamp getDisabledDate() {
        return this.disabledDate;
    }
     
    /**
     * 设置失效时间
     * 
     * @param disabledDate
     *          失效时间
     */
    public void setDisabledDate(Timestamp disabledDate) {
        this.disabledDate = disabledDate;
    }

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
}