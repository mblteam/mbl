package com.mbl.common.util.wx2;

/**
 * 微信统一下单接口参数
 * @author Aaron
 *
 */
public class UnifiedOrder {
	
	private String appid;		// 公众账号ID 	是 	String(32) 	wxd678efh567hg6787 	微信分配的公众账号ID（企业号corpid即为此appId）
	private String mchId;		// 商户号		是 	String(32) 	1230000109 			微信支付分配的商户号
	private String nonceStr;	// 随机字符串 	是 	String(32) 	5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	随机字符串，不长于32位。推荐随机数生成算法
	private String body;		// 商品描述 	是 	String(128) 	Ipad mini  16G  白色 	商品或支付单简要描述
	private String outTradeNo;	// 商户订单号 	是 	String(32) 	20150806125346 	商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	private int totalFee;		// 总金额 		是 	Int 	888 	订单总金额，单位为分，详见支付金额
	private String spbillCreateIp;//终端IP	是 	String(16) 	123.12.12.123 	APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	private String notifyUrl;	// 通知地址 	是 	String(256) 	http://www.weixin.qq.com/wxpay/pay.php 	接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	private String tradeType;	// 交易类型 	是 	String(16) 	JSAPI 	取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
	
	private String deviceInfo;	// 设备号 		否 	String(32) 	013467007045764 	终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String detail;		// 商品详情 	否 	String(8192) 	Ipad mini  16G  白色 	商品名称明细列表
	private String attach;		// 附加数据 	否 	String(127) 	深圳分店 	附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	private String feeType;		// 货币类型 	否 	String(16) 	CNY 	符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String timeStart;	// 交易起始时间 	否 	String(14) 	20091225091010 	订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	private String timeExpire;	// 交易结束时间 	否 	String(14) 	20091227091010 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则 注意：最短失效时间间隔必须大于5分钟
	private String goodsTag;	// 商品标记 	否 	String(32) 	WXG 	商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	private String productId;	// 商品ID 	否 	String(32) 	12235413214070356458058 	trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	private String limitPay;	// 指定支付方式 	否 	String(32) 	no_credit 	no_credit--指定不能使用信用卡支付
	private String openid;		// 用户标识 	否 	String(128) 	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 	trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换

//根据以上参数生成
//	private String sign;		// 签名 		是 	String(32) 	C380BEC2BFD727A4B6845133519F3AD6 	签名，详见签名生成算法
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
