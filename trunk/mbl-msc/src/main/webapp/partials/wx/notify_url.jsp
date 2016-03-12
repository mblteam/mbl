<%

%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mbl.common.util.alipay.*"%>
<%@ page import="java.net.*"%>
<%@ page import="org.apache.commons.codec.CharEncoding"%>
<%@ page import="org.apache.log4j.*"%>

<%
	final Logger log=Logger.getLogger(Object.class);
	//获取微信POST过来反馈信息
	Map<String,String>params=new HashMap<String,String>();
	Map requestParams=request.getParameterMap();
	for(Iterator iter=requestParams.keySet().iterator();iter.hasNext();){
		String name=(String)iter.next();
		String[]values=(String[])requestParams.get(name);
		String valueStr="";
		for(int i=0;i<values.length;i++){
			valueStr=(i==values.length-1)?valueStr+values[i]
					:valueStr+values[i]+",";
		}
		log.error(name+"==="+valueStr);
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//valueStr=newString(valueStr.getBytes("ISO-8859-1"),"gbk");
		params.put(name,valueStr);
	}
	
	//获取微信的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	//商户订单号
	String out_trade_no=new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	log.error("out_trade_no==="+out_trade_no);
	
	//微信交易号
	String transaction_id=new String(request.getParameter("transaction_id").getBytes("ISO-8859-1"),"UTF-8");
	log.error("transaction_id==="+transaction_id);
	//交易状态
	String return_code=new String(request.getParameter("return_code").getBytes("ISO-8859-1"),"UTF-8");
	log.error("return_code==="+return_code);
	
	//获取微信的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

	if(return_code.equals("SUCCESS")){
		//交易成功
	}else{
		//交易失败
		out.print("FAIL");
	}
%>
