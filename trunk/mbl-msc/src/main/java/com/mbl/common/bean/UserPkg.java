package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 已支付用户套餐关系表(M_USER_PKG)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class UserPkg extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2240231048229670246L;
    
    /** 主键id */
    private String upId;
    
    /** 用户编号 */
    private String userId;
    
    /** 套餐编号 */
    private String pkgId;
    
    /** 该套餐使用开始时间 */
    private Timestamp startDate;
    
    /** 改套餐使用结束时间 */
    private Timestamp endDate;
    
    /** 对应店铺id */
    private String shopId;
    
    /** 套餐类型（月卡、年卡、一次性） */
    private String pkgType;
    
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
    /**应付金额*/
    private BigDecimal price;
    /**折扣***/
    private BigDecimal discount;
    /**实付金额***/
    private BigDecimal paid;
    
    private String accountId;
    
    /*** 订单详情id **/
    private String orderDetailId;
    
    /*** 车id **/
    private String carId;
    
    public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}