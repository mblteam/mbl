package com.mbl.common.constant;

import java.util.List;

import com.mbl.common.bean.DictLine;

/**
 * 通用常量
 * 功能详细描述
 * @author zl
 * @create 2015年12月23日 上午1:26:57 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ConstantClass {

	public static final String ABLE = "1" ; //有效
	public static final String DISABLE = "0" ; //无效
	
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_CUSTOMER = "sessionCustomer";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";	//不对匹配该值的访问路径拦截（正则）
	
	/**
	 * 验证码类型
	 */
	public static final String VERIFY_CODE_REGISTER = "1"; //注册
	public static final String VERIFY_CODE_FORGETPASS = "2"; //找回密码
	public static final String VERIFY_CODE_UPDATETEL = "3"; //更换手机号
	
	/**
	 * 订单状态
	 */
	/**
	 * 失效
	 */
	public static final String ODER_STATUS_NO = "-1"; //失效
	/**
	 * 取消
	 */
	public static final String ODER_STATUS_ZERO = "0"; 
	/**
	 * 生效
	 */
	public static final String ODER_STATUS_ONE = "1"; 
	/**
	 * 待支付
	 */
	public static final String ODER_STATUS_TWO = "2"; 
	/**
	 * 已支付
	 */
	public static final String ODER_STATUS_THREE = "3"; //已支付
	
	/**
	 * 得到订单状态
	 * 功能详细描述
	 * @param orderStatus
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String getOrderStatusDesc(String orderStatus){
		if(ODER_STATUS_NO.equals(orderStatus)){
			return "失效";
		}else if(ODER_STATUS_ZERO.equals(orderStatus)){
			return "取消";
		}else if(ODER_STATUS_ONE.equals(orderStatus)){
			return "生效";
		}else if(ODER_STATUS_TWO.equals(orderStatus)){
			return "待支付";
		}else if(ODER_STATUS_THREE.equals(orderStatus)){
			return "已支付";
		}
		return "";
	}
	
	/**
	 * 字典类型code
	 */
	public static final String DICT_SHOP_TYPE = "shop_type";
	
	/**
	 * 通过字典code获取字典值
	 * 功能详细描述
	 * @param dicts
	 * @param dictCode
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static final String getDictValueByDictCode(List<DictLine> dicts,String dictCode){
		for (DictLine dl : dicts) {
			if(dl.getDictCode().equals(dictCode)){
				return dl.getDictValue();
			}
		}
		return null;
	}
}
