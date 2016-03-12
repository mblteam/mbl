package com.mbl.common.util.alipay;

public class PayConstants {
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088911777623092";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMqnDUrcRHskXgQB79AlU2Q2snTsjKICLpM1ufWk1VgKBZ3S/NBRpAkecl/EEWHFacqsdgw5bdvUyqAIrJ4DfWOk/LzYhCfq3MyEgfwbvPtAt/XovZTnYUmMCYVhBY1N2/XIMSBzrXiuctCnNysWK/OJ+CG9osKAc7czFjGp+jqXAgMBAAECgYB3FUkjV2JKdmlks3qndneVaOre+G3/eYbC7IxVA8J2VJVOU8BJ7d+VmDWffsi0ClyLQ2Ku9kRPLLDQ76f3xpc0HcMVw94vZQ1Z8omHJnVq1JyfCeF3l+sYv6kq0uNCYqKpiqktCK/53jrDleREm2eaVh76hu/fqB5vAdVTm6W0uQJBAO0/8328hzBCiYsmQO6wm+ND+3h5NMiAgnpZema2pFoPVYQTPUDIx5tjIuhAWv6ckCy40Il/VRyQ+HpqASYMMlsCQQDaqyCdszSDc0tUpQV5gOg1lCCvRK2YFvhIIzM6S8pePO1Jtz1VHvkNO99HRWVNiuw6/2CxWpBgMh8TDJTOPVV1AkBygc40npylF0gpZ/IpAgaLPxGNSPrH1vaitLMWBgr3CLWehGao8P8eKU/8xqrkfo47d4v3AQUEP5geBTG+ul5HAkAl+8PWoOJmUwiq0194E6RCalCjIVLmMFdAcJzda3zjkcHGJEZLGu44FkZaovtN1Ovj86hgJlMd4pbzQCQ5Dv5hAkEA0hetSVcT8uSwdSebsm34I6pK9wnBuKKSHPGUxYMScQ0cwSavJRTLly45VpPPb9wxWTyx30W7lh7tX9NwrjWtoQ==";

	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
	public static String service = "mobile.securitypay.pay";
	
	public static String payment_type = "1";
	
	public static String seller_id = "1951029094@qq.com";
	
	public static String it_b_pay = "30m";
	
//	public static String requestUrl = "http://notify.java.jpxx.org/index.jsp?";
	public static String requestUrl = "https://mapi.alipay.com/gateway.do";
//	public static String requestUrl = "https://www.alipay.com/cooperate/gateway.do?";
//	public static String responseUrl = "http://121.41.41.171:8080/mbl-msc/partials/alipay/notify_url.jsp";
	public static String responseUrl = "http://121.41.41.171:8080/mbl-msc/rest/order/payAfterSuccess";
	public static String returnUrl = "m.alipay.com";
	
	public static String order_name = "订单结算";
	
}
