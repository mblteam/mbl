package com.mbl.common.util.wx2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.util.JsonUtil;

public class WxPayUtils {

	
	/**
	 * 
	 * @param SpbillCreateIp 终端Id
	 * @param outTradeNo 商户自己的订单号
	 * @param totalFee 	 总金额 单位为分
	 * @param body		商品描述
	 * @param detail	商品详情
	 */
	public static SignModel getSign(String SpbillCreateIp, String outTradeNo, int totalFee, String body, String detail, String plat) {
		UnifiedOrder unifiedOrder = new UnifiedOrder();
		unifiedOrder.setBody(body);
		unifiedOrder.setDetail(detail);
		unifiedOrder.setOutTradeNo(outTradeNo);
		unifiedOrder.setTotalFee(totalFee);
		
		unifiedOrder.setAppid(WxConfig.getAppID());
		unifiedOrder.setMchId(WxConfig.getMchID());
		unifiedOrder.setNonceStr(CommonUtilPub.getRandomStringByLength(32));
		unifiedOrder.setSpbillCreateIp(SpbillCreateIp);
		unifiedOrder.setNotifyUrl(WxConfig.getNotifyUrl());
		unifiedOrder.setTradeType(WxConfig.getTradeType());
		String prepayId = getPrepayId(unifiedOrder);
		
		String nonceStr = CommonUtilPub.getRandomStringByLength(32);//随机串
        long timeStamp = (new Date()).getTime() / 1000;        //时间戳，自1970年以来的秒数
        Map<String, Object> params = new HashMap<String, Object>();
       
        params.put("appid", WxConfig.getAppID());
        params.put("partnerid", WxConfig.getMchID());
        params.put("prepayid", prepayId);
        params.put("package", WxConfig.getPackageValue());
        params.put("noncestr", nonceStr);
        params.put("timestamp", timeStamp);
        
        String sign = CommonUtilPub.getSign(params);
        
        SignModel sm= new SignModel();
        sm.setNonceStr(nonceStr);
        sm.setTimeStamp(timeStamp);
        sm.setPrepayId(prepayId);
        sm.setAppId(WxConfig.getAppID());
        sm.setPackageValue(WxConfig.getPackageValue());
        sm.setPartnerId(WxConfig.getMchID());
        
        if("IOS".equalsIgnoreCase(plat)){
        	params = new HashMap<String, Object>();
        	params.put("appid", sm.getAppId());
        	params.put("partnerid", sm.getPartnerId());
        	params.put("prepayid", sm.getPrepayId());
        	params.put("package", sm.getPackageValue());
        	params.put("noncestr", sm.getNonceStr());
        	params.put("timestamp", sm.getTimeStamp());
        	sm.setSign(CommonUtilPub.getSign(params));
		}else{
			sm.setSign(sign);
		}
        return sm;
	}

	/**
	 * 统一下单接口 获得预支付Id
	 * 
	 * @return
	 * @throws BizException 
	 */
	private static String getPrepayId(UnifiedOrder unifiedOrder) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		// 公众账号ID 是 String(32)
		// wxd678efh567hg6787
		// 微信分配的公众账号ID（企业号corpid即为此appId）
		paramMap.put("appid", unifiedOrder.getAppid()); 
		// 商户号 是 String(32)
		// 1230000109
		// 微信支付分配的商户号
		paramMap.put("mch_id", unifiedOrder.getMchId()); 
		// 随机字符串 是
		// String(32)
		// 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
		// 随机字符串，不长于32位。推荐随机数生成算法
		paramMap.put("nonce_str", unifiedOrder.getNonceStr()); 
		
		// 商品描述 是 String(128) Ipad
		// mini 16G 白色
		// 商品或支付单简要描述
		paramMap.put("body", unifiedOrder.getBody()); 
		// 商品详情 否 String(8192)
		// Ipad mini 16G 白色
		// 商品名称明细列表
		String detail = unifiedOrder.getDetail();
		if(StringUtils.isNotEmpty(detail)){
			paramMap.put("detail", detail);
		}
		// 商户订单号 是
		// String(32)
		// 20150806125346
		// 商户系统内部的订单号,32个字符内、可包含字母,
		// 其他说明见商户订单号
		paramMap.put("out_trade_no", unifiedOrder.getOutTradeNo()); 
		// 总金额 是 Int 888
		// 订单总金额，单位为分，详见支付金额
		paramMap.put("total_fee", unifiedOrder.getTotalFee()); 
		// 通知地址 是
		// String(256)
		// http://www.weixin.qq.com/wxpay/pay.php
		// 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
		paramMap.put("notify_url", unifiedOrder.getNotifyUrl()); 
		// 交易类型 是
		// String(16)
		// JSAPI
		// 取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
		paramMap.put("trade_type", unifiedOrder.getTradeType()); 

		// 设备号 否 String(32) 013467007045764
		// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		String deviceInfo = unifiedOrder.getDeviceInfo();
		if (StringUtils.isNotEmpty(deviceInfo)) {
			paramMap.put("device_info", unifiedOrder.getDeviceInfo());
		}
		// 附加数据 否 String(127) 深圳分店 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
		String attach = unifiedOrder.getAttach();
		if (StringUtils.isNotEmpty(attach)) {
			paramMap.put("attach", attach);
		}
		// 货币类型 否 String(16) CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
		String feeType = unifiedOrder.getFeeType();
		if (StringUtils.isNotEmpty(feeType)) {
			paramMap.put("fee_type", feeType);
		}
		// 终端IP 是 String(16) 123.12.12.123
		// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		String spbillCreateIp = unifiedOrder.getSpbillCreateIp();
		if (StringUtils.isNotEmpty(spbillCreateIp)) {
			paramMap.put("spbill_create_ip", spbillCreateIp);
		}
		// 交易起始时间 否 String(14) 20091225091010
		// 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
		String timeStart = unifiedOrder.getTimeStart();
		if (StringUtils.isNotEmpty(timeStart)) {
			paramMap.put("time_start", timeStart);
		}
		// 交易结束时间 否 String(14) 20091227091010
		// 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
		// 注意：最短失效时间间隔必须大于5分钟
		String timeExpire = unifiedOrder.getTimeExpire();
		if (StringUtils.isNotEmpty(timeExpire)) {
			paramMap.put("time_expire", timeExpire);
		}
		// 商品标记 否 String(32) WXG 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
		String goodsTag = unifiedOrder.getGoodsTag();
		if (StringUtils.isNotEmpty(goodsTag)) {
			paramMap.put("goods_tag", goodsTag);
		}
		// 商品ID 否 String(32) 12235413214070356458058
		// trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
		String productId = unifiedOrder.getProductId();
		if (StringUtils.isNotEmpty(productId)) {
			paramMap.put("product_id", productId);
		}
		// 指定支付方式 否 String(32) no_credit no_credit--指定不能使用信用卡支付
		String limitPay = unifiedOrder.getLimitPay();
		if (StringUtils.isNotEmpty(limitPay)) {
			paramMap.put("limit_pay", limitPay);
		}
		// 用户标识 否 String(128) oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
		// trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
		String openid = unifiedOrder.getOpenid();
		if (StringUtils.isNotEmpty(openid)) {
			paramMap.put("openid", openid);
		}
		
		System.out.println("签名前参数：\n"+JsonUtil.javaObjToJson(paramMap));
		// 签名 是 String(32)
		// C380BEC2BFD727A4B6845133519F3AD6
		// 签名，详见签名生成算法
		paramMap.put("sign", CommonUtilPub.getSign(paramMap)); 
		System.out.println("签名后参数：\n"+JsonUtil.javaObjToJson(paramMap));
		//先调用微信的统一下单接口，生成预交易单。（参数传递与接收都是XML 数据格式。）
		String xmlData = CommonUtilPub.mapToXml(paramMap);// 把参数转换成XML数据格式
		String resXml = HttpUtils.postData(WxConfig.UNIFIED_ORDER_API, xmlData);
		
		System.out.println("下单返回结果:\n"+resXml);
		
		Document dd = null;
		String prepayId = null;
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			throw new BizException("10", "订单生成失败");
		}
		if (dd != null) {
			Element root = dd.getRootElement();
			if (root == null) {
				throw new BizException("10", "订单生成失败");
			}
			String returnCode = root.element("return_code").getTextTrim();
			if ("SUCCESS".equalsIgnoreCase(returnCode)) {
				String resultCode = root.element("result_code").getTextTrim();
				if ("SUCCESS".equalsIgnoreCase(resultCode)) {
					prepayId = root.element("prepay_id").getText(); // 解析 xml 获得
				}else{
					String errCodeDes = root.element("err_code_des").getTextTrim();
					throw new BizException(resultCode, errCodeDes);
				}
			} else {
				String returnMsg = root.element("return_msg").getTextTrim();
				throw new BizException(returnCode, "订单生成失败:"+returnMsg);
			}
		}
		System.out.println("获得预支付ID："+prepayId);
		return prepayId;
	}
}
