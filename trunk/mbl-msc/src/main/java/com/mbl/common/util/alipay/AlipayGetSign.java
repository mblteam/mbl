package com.mbl.common.util.alipay;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.codec.CharEncoding;
import com.mbl.common.vo.OrderVO;

public class AlipayGetSign {

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AlipayGetSign.class);

	/****
	 * 支付宝数字签名
	 * 
	 * @param orderVO
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getSign(OrderVO orderVO) throws UnsupportedEncodingException {
		if (orderVO == null) {
			return null;
		}
		String totalPrice = orderVO.getPaid().toString();
		LOGGER.debug("totalPrice-----------------" + totalPrice);
				
		StringBuffer returnStr = new StringBuffer();
		returnStr.append("partner=\"").append(PayConstants.partner).append("\"");
		returnStr.append("&seller_id=\"").append(PayConstants.seller_id).append("\"");
		returnStr.append("&out_trade_no=\"").append(orderVO.getOrderNo()).append("\"");
		returnStr.append("&subject=\"").append(PayConstants.order_name).append("\"");
		returnStr.append("&body=\"").append(PayConstants.order_name).append("\"");
		//测试修改  
		// TODO
		returnStr.append("&total_fee=\"").append("0.01").append("\"");
		returnStr.append("&notify_url=\"").append(PayConstants.responseUrl).append("\"");
		returnStr.append("&service=\"").append(PayConstants.service).append("\"");
		returnStr.append("&payment_type=\"").append(PayConstants.payment_type).append("\"");
		returnStr.append("&_input_charset=\"").append(PayConstants.input_charset).append("\"");
		returnStr.append("&it_b_pay=\"").append(PayConstants.it_b_pay).append("\"");
		returnStr.append("&return_url=\"").append(PayConstants.returnUrl).append("\"");	
		String info = RSA.sign(returnStr.toString(), PayConstants.private_key, PayConstants.input_charset);
		returnStr.append("&sign=\"");
		returnStr.append(URLEncoder.encode(info, CharEncoding.UTF_8)).append("\"");
		returnStr.append("&sign_type=\"").append(PayConstants.sign_type).append("\"");

		LOGGER.debug(returnStr);

		return returnStr.toString();
	}
}
