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

package com.mbl.rest.pkg.vo;

import java.math.BigDecimal;
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

public class UserPackageVo {
	
	 /** 主键id */
    private String upId;
    
    /** 用户编号 */
    private String userId;
    
    /** 套餐编号 */
    private String pkgId;
    
    /** 该套餐使用开始时间 */
    private Timestamp startDate;
    
    private String startDateStr;
    
    /** 改套餐使用结束时间 */
    private Timestamp endDate;
    
    private String endDateStr;
    
    /** 对应店铺id */
    private String shopId;
    
    private String shopName;
    
    public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/** 套餐类型（月卡、年卡、一次性） */
    private String pkgType;
    
    private String pkgTypeName;
    
    /** 套餐次数 （针对次卡用） */
    private Integer pkgNum;
    
    /** 已用次数（针对次卡使用） */
    private Integer useNum;
    
    /** 剩余次数（针对次卡使用） */
    private Integer leftNum;
    
    /** 支付的订单id */
    private String orderId;
    
    /** 状态 */
    private String status;
    
    /** 套餐简称 */
    private String pkgName;
    
    /** 价格 */
    private BigDecimal price;
    
    /** 套餐内容 */
    private String pkgContent;

    /**
     * 获取套餐简称
     * 
     * @return 套餐简称
     */
    public String getPkgName() {
        return this.pkgName;
    }
     
    /**
     * 设置套餐简称
     * 
     * @param pkgName
     *          套餐简称
     */
    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
    
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getUpId() {
        return this.upId;
    }
     
    /**
     * 设置主键id
     * 
     * @param upId
     *          主键id
     */
    public void setUpId(String upId) {
        this.upId = upId;
    }
    
    /**
     * 获取用户编号
     * 
     * @return 用户编号
     */
    public String getUserId() {
        return this.userId;
    }
     
    /**
     * 设置用户编号
     * 
     * @param userId
     *          用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取套餐编号
     * 
     * @return 套餐编号
     */
    public String getPkgId() {
        return this.pkgId;
    }
     
    /**
     * 设置套餐编号
     * 
     * @param pkgId
     *          套餐编号
     */
    public void setPkgId(String pkgId) {
        this.pkgId = pkgId;
    }
    
    /**
     * 获取该套餐使用开始时间
     * 
     * @return 该套餐使用开始时间
     */
    public Timestamp getStartDate() {
        return this.startDate;
    }
     
    /**
     * 设置该套餐使用开始时间
     * 
     * @param startDate
     *          该套餐使用开始时间
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    
    /**
     * 获取改套餐使用结束时间
     * 
     * @return 改套餐使用结束时间
     */
    public Timestamp getEndDate() {
        return this.endDate;
    }
     
    /**
     * 设置改套餐使用结束时间
     * 
     * @param endDate
     *          改套餐使用结束时间
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    
    /**
     * 获取对应店铺id
     * 
     * @return 对应店铺id
     */
    public String getShopId() {
        return this.shopId;
    }
     
    /**
     * 设置对应店铺id
     * 
     * @param shopId
     *          对应店铺id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    
    /**
     * 获取套餐类型（月卡、年卡、一次性）
     * 
     * @return 套餐类型（月卡、年卡、一次性）
     */
    public String getPkgType() {
        return this.pkgType;
    }
     
    /**
     * 设置套餐类型（月卡、年卡、一次性）
     * 
     * @param pkgType
     *          套餐类型（月卡、年卡、一次性）
     */
    public void setPkgType(String pkgType) {
        this.pkgType = pkgType;
    }
    
    /**
     * 获取套餐次数 （针对次卡用）
     * 
     * @return 套餐次数 （针对次卡用）
     */
    public Integer getPkgNum() {
        return this.pkgNum;
    }
     
    /**
     * 设置套餐次数 （针对次卡用）
     * 
     * @param pkgNum
     *          套餐次数 （针对次卡用）
     */
    public void setPkgNum(Integer pkgNum) {
        this.pkgNum = pkgNum;
    }
    
    /**
     * 获取已用次数（针对次卡使用）
     * 
     * @return 已用次数（针对次卡使用）
     */
    public Integer getUseNum() {
        return this.useNum;
    }
     
    /**
     * 设置已用次数（针对次卡使用）
     * 
     * @param useNum
     *          已用次数（针对次卡使用）
     */
    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }
    
    /**
     * 获取剩余次数（针对次卡使用）
     * 
     * @return 剩余次数（针对次卡使用）
     */
    public Integer getLeftNum() {
        return this.leftNum;
    }
     
    /**
     * 设置剩余次数（针对次卡使用）
     * 
     * @param leftNum
     *          剩余次数（针对次卡使用）
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }
    
    /**
     * 获取支付的订单id
     * 
     * @return 支付的订单id
     */
    public String getOrderId() {
        return this.orderId;
    }
     
    /**
     * 设置支付的订单id
     * 
     * @param orderId
     *          支付的订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    /**
     * 获取状态
     * 
     * @return 状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态
     * 
     * @param status
     *          状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPkgContent() {
		return pkgContent;
	}

	public void setPkgContent(String pkgContent) {
		this.pkgContent = pkgContent;
	}

	public String getPkgTypeName() {
		return pkgTypeName;
	}

	public void setPkgTypeName(String pkgTypeName) {
		this.pkgTypeName = pkgTypeName;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
}
