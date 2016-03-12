package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;

import java.util.Date;

/**
 * 用户车辆信息(M_USER_CAR)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class UserCar extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7101032515289610169L;
    
    /** 主键id */
    private String carId;
    
    /** 品牌id */
    private String brand;
    
    /** 品牌名称 */
    private String brandName;
    
    /** 车辆型号id */
    private String carType;
    
    /** 车辆型号名称  */
    private String seriesName;
    
    /** 车牌号 */
	private String carPlateNo;
    
    /** 生产年月 */
    private String productYear;
    
    /** 里程 */
    private Integer mileage;
    
    /** 上路日期 */
    private Date onroadTime;
    
    /** 排量 */
    private String displacement;
    
    /** 图片地址 */
    private String photo;
    
    /** 状态 1-有效 0-失效 */
    private String carStatus;
    
    /** 账户id */
    private String accountId;
    
    /** 预约标示 */
    private String appointmentFlag;
    
    /**
     * 获取主键id
     * 
     * @return 主键id
     */
    public String getCarId() {
        return this.carId;
    }
     
    /**
     * 设置主键id
     * 
     * @param carId
     *          主键id
     */
    public void setCarId(String carId) {
        this.carId = carId;
    }
    
    /**
     * 获取车辆型号
     * 
     * @return 车辆型号
     */
    public String getCarType() {
        return this.carType;
    }
     
    /**
     * 设置车辆型号
     * 
     * @param carType
     *          车辆型号
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }
    
    /**
     * 获取品牌
     * 
     * @return 品牌
     */
    public String getBrand() {
        return this.brand;
    }
     
    /**
     * 设置品牌
     * 
     * @param brand
     *          品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    /**
     * 获取生产年月
     * 
     * @return 生产年月
     */
    public String getProductYear() {
        return this.productYear;
    }
     
    /**
     * 设置生产年月
     * 
     * @param productYear
     *          生产年月
     */
    public void setProductYear(String productYear) {
        this.productYear = productYear;
    }
    
    /**
     * 获取里程
     * 
     * @return 里程
     */
    public Integer getMileage() {
        return this.mileage;
    }
     
    /**
     * 设置里程
     * 
     * @param mileage
     *          里程
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
    
    /**
     * 获取上路日期
     * 
     * @return 上路日期
     */
    public Date getOnroadTime() {
        return this.onroadTime;
    }
     
    /**
     * 设置上路日期
     * 
     * @param onroadTime
     *          上路日期
     */
    public void setOnroadTime(Date onroadTime) {
        this.onroadTime = onroadTime;
    }
    
    /**
     * 获取排量
     * 
     * @return 排量
     */
    public String getDisplacement() {
        return this.displacement;
    }
     
    /**
     * 设置排量
     * 
     * @param displacement
     *          排量
     */
    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }
    
    /**
     * 获取图片地址
     * 
     * @return 图片地址
     */
    public String getPhoto() {
        return this.photo;
    }
     
    /**
     * 设置图片地址
     * 
     * @param photo
     *          图片地址
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    /**
     * 获取状态 1-有效 0-失效
     * 
     * @return 状态 1-有效 0-失效
     */
    public String getCarStatus() {
        return this.carStatus;
    }
     
    /**
     * 设置状态 1-有效 0-失效
     * 
     * @param carStatus
     *          状态 1-有效 0-失效
     */
    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
    
    /**
     * 获取账户id
     * 
     * @return 账户id
     */
    public String getAccountId() {
        return this.accountId;
    }
     
    /**
     * 设置账户id
     * 
     * @param accountId
     *          账户id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

	public String getAppointmentFlag() {
		return appointmentFlag;
	}

	public void setAppointmentFlag(String appointmentFlag) {
		this.appointmentFlag = appointmentFlag;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getCarPlateNo() {
		return carPlateNo;
	}

	public void setCarPlateNo(String carPlateNo) {
		this.carPlateNo = carPlateNo;
	}
	
}