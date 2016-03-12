package com.mbl.rest.shop.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mbl.common.vo.ShopPackageVO;

/**
 * 
 * 店铺详情VO
 * @author xjs
 * @create 2015年12月02日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class ShopDetailVo{
	
	/** 主键id */
	private String shopId;

	/** 店铺介绍 */
	private String introduce;

	/** 店铺地址 */
	private String address;

	/** 经度 */
	private BigDecimal longitude;

	/** 纬度 */
	private BigDecimal latitude;

	/** 合同折扣上限 */
	private BigDecimal discountUpperLimit;

	/** 合同折扣下限 */
	private BigDecimal discountLowerLimit;

	/** 合作状态 */
	private String cooperateStatus;

	/** 合作开始时间 */
	private Date cooperateStartDate;

	/** 合作结束时间 */
	private Date cooperateEndDate;

	/** 店铺状态1-有效 0-失效 */
	private String shopStatus;

	/** 结算方式 */
	private String balanceType;

	/** 推荐等级 */
	private String level;
	/** 店铺名称 **/
	private String shopName;
	/** 店铺编号 **/
	private String shopCode;
	/** 联系电话 **/
	private String tel;
	/** 店铺类型 **/
	private String shopType;
	/** 店铺类型 **/
	private String shopTypeStr;
	/** 图片地址 **/
	private String url;
    
    /** 店铺套餐信息 */
    private List<ShopPackageVO> packages;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getShopId() {
        return this.shopId;
    }
     
    /**
     * 设置主键id
     * 
     * @param shopId
     *          主键id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    
    /**
     * 获取店铺介绍
     * 
     * @return 店铺介绍
     */
    public String getIntroduce() {
        return this.introduce;
    }
     
    /**
     * 设置店铺介绍
     * 
     * @param introduce
     *          店铺介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
    /**
     * 获取店铺地址
     * 
     * @return 店铺地址
     */
    public String getAddress() {
        return this.address;
    }
     
    /**
     * 设置店铺地址
     * 
     * @param address
     *          店铺地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 获取经度
     * 
     * @return 经度
     */
    public BigDecimal getLongitude() {
        return this.longitude;
    }
     
    /**
     * 设置经度
     * 
     * @param longitude
     *          经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    /**
     * 获取纬度
     * 
     * @return 纬度
     */
    public BigDecimal getLatitude() {
        return this.latitude;
    }
     
    /**
     * 设置纬度
     * 
     * @param latitude
     *          纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    /**
     * 获取合同折扣上限
     * 
     * @return 合同折扣上限
     */
    public BigDecimal getDiscountUpperLimit() {
        return this.discountUpperLimit;
    }
     
    /**
     * 设置合同折扣上限
     * 
     * @param discountUpperLimit
     *          合同折扣上限
     */
    public void setDiscountUpperLimit(BigDecimal discountUpperLimit) {
        this.discountUpperLimit = discountUpperLimit;
    }
    
    /**
     * 获取合同折扣下限
     * 
     * @return 合同折扣下限
     */
    public BigDecimal getDiscountLowerLimit() {
        return this.discountLowerLimit;
    }
     
    /**
     * 设置合同折扣下限
     * 
     * @param discountLowerLimit
     *          合同折扣下限
     */
    public void setDiscountLowerLimit(BigDecimal discountLowerLimit) {
        this.discountLowerLimit = discountLowerLimit;
    }
    
    /**
     * 获取合作状态
     * 
     * @return 合作状态
     */
    public String getCooperateStatus() {
        return this.cooperateStatus;
    }
     
    /**
     * 设置合作状态
     * 
     * @param cooperateStatus
     *          合作状态
     */
    public void setCooperateStatus(String cooperateStatus) {
        this.cooperateStatus = cooperateStatus;
    }
    
    /**
     * 获取合作开始时间
     * 
     * @return 合作开始时间
     */
    public Date getCooperateStartDate() {
        return this.cooperateStartDate;
    }
     
    /**
     * 设置合作开始时间
     * 
     * @param cooperateStartDate
     *          合作开始时间
     */
    public void setCooperateStartDate(Date cooperateStartDate) {
        this.cooperateStartDate = cooperateStartDate;
    }
    
    /**
     * 获取合作结束时间
     * 
     * @return 合作结束时间
     */
    public Date getCooperateEndDate() {
        return this.cooperateEndDate;
    }
     
    /**
     * 设置合作结束时间
     * 
     * @param cooperateEndDate
     *          合作结束时间
     */
    public void setCooperateEndDate(Date cooperateEndDate) {
        this.cooperateEndDate = cooperateEndDate;
    }
    
    /**
     * 获取店铺状态1-有效 0-失效
     * 
     * @return 店铺状态1-有效 0-失效
     */
    public String getShopStatus() {
        return this.shopStatus;
    }
     
    /**
     * 设置店铺状态1-有效 0-失效
     * 
     * @param shopStatus
     *          店铺状态1-有效 0-失效
     */
    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }
    
    /**
     * 获取结算方式
     * 
     * @return 结算方式
     */
    public String getBalanceType() {
        return this.balanceType;
    }
     
    /**
     * 设置结算方式
     * 
     * @param balanceType
     *          结算方式
     */
    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }
    
    /**
     * 获取推荐等级
     * 
     * @return 推荐等级
     */
    public String getLevel() {
        return this.level;
    }
     
    /**
     * 设置推荐等级
     * 
     * @param level
     *          推荐等级
     */
    public void setLevel(String level) {
        this.level = level;
    }

	public List<ShopPackageVO> getPackages() {
		return packages;
	}

	public void setPackages(List<ShopPackageVO> packages) {
		this.packages = packages;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShopTypeStr() {
		return shopTypeStr;
	}

	public void setShopTypeStr(String shopTypeStr) {
		this.shopTypeStr = shopTypeStr;
	}
	
}
