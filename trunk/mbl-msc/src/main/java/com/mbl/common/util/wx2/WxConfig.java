package com.mbl.common.util.wx2;


/**
 * 微信支付工具类
 * @author anso
 *
 */
public class WxConfig {

	//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	private static String key = "TkzVydkhnrYOnU79aAhBKvFy71bemMBL";
								 
	//微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID = "wxb1d0b9b296be1eba";
	
	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = "1301717901";
	
	//受理模式下给子商户分配的子商户号
	private static String subMchID = "";

	//HTTPS证书的本地路径
	private static String certLocalPath = "";

	//HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword = "";

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;
	
	//接收微信支付异步通知回调地址
	private static String notifyUrl = "http://121.41.41.171:8080/mbl-msc/rest/order/wxPayAfterSuccess";
	
	//取值如下：JSAPI，NATIVE，APP，WAP,详细说明见参数规定
	private static String tradeType = "APP";
	
	// 固定写死
	private static String packageValue = "Sign=WXPay";
	
	//机器IP
	private static String ip = "";

	//以下是几个API的路径：
	//统一下单接口
	public static String UNIFIED_ORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	//1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	//2）被扫支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		WxConfig.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";

	public static String getKey() {
		return key;
	}

	public static String getAppID() {
		return appID;
	}

	public static String getMchID() {
		return mchID;
	}

	public static String getSubMchID() {
		return subMchID;
	}

	public static String getCertLocalPath() {
		return certLocalPath;
	}

	public static String getCertPassword() {
		return certPassword;
	}

	public static String getNotifyUrl() {
		return notifyUrl;
	}

	public static String getTradeType() {
		return tradeType;
	}

	public static String getPackageValue() {
		return packageValue;
	}

	public static String getIp() {
		return ip;
	}

	
	
}
