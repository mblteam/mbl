package com.mbl.common.util.wx2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

    //表示请求器是否已经做了初始化工作
    private static boolean hasInit = false;

    //连接超时时间，默认10秒
    private static int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private static int connectTimeout = 30000;

    //请求器的配置
    private static RequestConfig requestConfig;

    //HTTP请求器
    private static CloseableHttpClient httpClient ;

    private static void init() throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

    	httpClient = HttpClients.custom().build();
    	
        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

        hasInit = true;
    }
	
    /**
     * 通过Https往API post xml数据
     *
     * @param url    API地址
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     */
	public static String postData(String url,String xmlObj){
		 if (!hasInit) {
	          try {
				init();
			} catch (Exception e) {
				System.out.println("初始化HttpUtils异常"+e.getMessage());
			}
	      }
		
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		
		//解决XStream对出现双下划线的bug
//        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        
        //将要提交给API的数据对象转换成XML格式数据Post给API
//        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(xmlObj, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        
        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        }  catch (Exception e) {
          System.out.println("网络访问异常"+e.getMessage());

        } finally {
            httpPost.abort();
        }
        
        return result;
        
	}
	
	public static String readResult(InputStream inputStream) throws IOException{
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		String line=null;
		while((line=br.readLine())!=null){
			sb.append(line);
			line=null;
		}
		br.close();
		inputStream.close();
		if(sb.length()>0){
			return sb.toString();
		}
		
		return null;
	}
	
	
	
	
	
	
	
}
