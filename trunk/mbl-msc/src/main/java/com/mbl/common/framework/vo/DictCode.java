package com.mbl.common.framework.vo;

/**
 * 字典类型
 *
 * @author SongHe
 * @create 2015年5月27日 下午4:04:30
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class DictCode {

	/**
	 * 订单类型
	 */
	public static final String SET_THE_ORDER = "1";//套餐订单
	
	public static final String NEWS_CENTER_ORDER = "2";//商城产品订单
	
	public static final String VEHICLE_MAINTENANCE_ORDER = "3";//车辆维修订单

	/**
	 * 状态
	 */
	public static final String VALID_STATUS = "1";// 有效
	
	public static final String INVALID_STATUS = "0";//无效
	
	/**
	 * 是否使用积分
	 */
	public static final String USE_POINT = "1";// 使用
	
	public static final String UN_USE_POINT = "0";//未使用
	
	/**
	 * 是否使用优惠券
	 */
	public static final String USE_COUPON = "1";// 使用
	
	public static final String UN_USE_COUPON = "0";//未使用
	
	/**
	 * 支付方式
	 */
	public static final String WECHAT_PAYMENT = "1";//微信支付
	
	public static final String ALIPAY_PAYMENT = "2";//支付宝支付
	
	
	/**
	 * 支付方式
	 */
	public static final String ALREADY_PAID = "1";//已支付
	
	public static final String NON_PAYMENT = "0";//未支付
	
	/**
	 * 是否结算
	 */
	public static final String IS_SETTLE = "1";//已结算
	
	public static final String NOT_SETTLE = "0";//未结算
	
	/**
	 * 套餐类型
	 */
	public static final String MONTH_CARD = "1";//月卡
	
	public static final String ANNUAL_CARD = "2";//年卡
	
	public static final String COUNT_CARD = "3";//次数卡
	
	public static final String MAINTENANCE = "4";//维修
	
	public static final String NOT_MAINTENANCE = "5";//非维修
	
	
	/**
	 * 用户类型 
	 */
	public static final String USER_PLATFORM = "3"; //平台用户
	
	public static final String USER_CAR_OWNER = "1"; //车主
	
	public static final String USER_MERCHANT = "2"; //商户
	
}
