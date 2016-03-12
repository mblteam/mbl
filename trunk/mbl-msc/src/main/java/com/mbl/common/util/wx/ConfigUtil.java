package com.mbl.common.util.wx;


public class ConfigUtil {
	/**
	 * 服务号相关信息
	 */
	public final static String APPID = "wxb1d0b9b296be1eba";// 服务号的应用号
	public final static String APP_SECRECT = "0c4b0ea80c92e4a788ae8ff4ecd643ba";// 服务号的应用密码
	public final static String TOKEN = "chengfengzhineng123";// 服务号的配置token
	public final static String MCH_ID = "1277259901";// 商户号
	public final static String API_KEY = "Yn0JKWnM8BT4OFQqgBmIKET1ohk5Srxe";// API密钥
	public final static String SIGN_TYPE = "MD5";// 签名加密方式
	public final static String CERT_PATH = "http://hncf.f3322.net/orange/WeiXin/cert/apiclient_cert.p12";// 微信支付证书存放路径地址
	public final static String SSLCERT_PATH = "http://hncf.f3322.net/orange/WeiXin/cert/apiclient_cert.pem";// 微信支付证书存放路径地址
	public final static String SSLKEY_PATH = "http://hncf.f3322.net/orange/WeiXin/cert/apiclient_key.pem";// 微信支付证书存放路径地址
	// 微信支付统一接口的回调action
//	public final static String NOTIFY_URL = "http://nefile.go724.cn/orange/31150.shopService";
	public final static String NOTIFY_URL = "http://hncf56.f3322.net:58888/cf-finanplatform/wxordersreturnurl.wxpay";
	// 微信支付成功支付后跳转的地址
	public final static String SUCCESS_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc4b057c5b5ffb7ec&redirect_uri=http://hncf.ne56erp.com/orange/wechat/openid.jsp?action=viewtest&response_type=code&scope=snsapi_base&state=13#wechat_redirect";
	/**
	 * 微信基础接口地址s
	 */
	//获取jsapi_ticket
	public static String ticket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 菜单创建（POST） 限100（次/天）  
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	// 获取token接口(GET)
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// oauth2授权接口(GET)
	public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 刷新access_token接口（GET）
	public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 获取用户基本信息
	public final static String Jpb_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**
	 * 微信支付接口地址
	 */
	// 微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// 退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	
	public static String order_name = "微信支付订单结算";
	
	public static String notify_url = "http://121.41.41.171:8080/mbl-msc/rest/order/wxPayAfterSuccess";
	
	public static String ip = "121.41.41.171";
	
	public static String trade_type = "APP";

}
