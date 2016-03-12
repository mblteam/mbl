package com.mbl.common.util.wx;

import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mbl.common.vo.OrderVO;

public class WxSignUtil {
	private static Logger log = LoggerFactory.getLogger(WxSignUtil.class);

	/**
	 * 生成 签名
	 * 
	 * @param json
	 * @return
	 * @return String
	 * @author Pippo
	 */
	public static String getSign(OrderVO orderVO) {
		String url = "";
		try {
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", ConfigUtil.APPID);// 公众账号ID
			parameters.put("body", ConfigUtil.order_name);
			parameters.put("mch_id", ConfigUtil.MCH_ID);// 商户号
			parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());// 随机字符串
			parameters.put("notify_url", ConfigUtil.notify_url);
			parameters.put("out_trade_no", orderVO.getOrderId());
			parameters.put("spbill_create_ip", ConfigUtil.ip);
			parameters.put("total_fee", orderVO.getPaid());//金额
			parameters.put("trade_type", ConfigUtil.trade_type);
			String sign = PayCommonUtil.createSign("UTF-8", parameters);// 生成sign签名
			url = //GlobalConfig.getPropertyValue("common.weixin.QRcodeUrl")+"?"+
			   PayCommonUtil.createSign2("UTF-8", parameters) + "sign=" + sign;
		} catch (Exception e) {
			log.error("生成订单二维码URL失败", e);
		}
		return url;
	}
}
