package com.mbl.common.util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 工具类：提供公用方法
 * @author xjs
 * @create 2015年12月02日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommonUtils {

	private static final  double EARTH_RADIUS = 6378137;//赤道半径
	
	/**
	 * 判断电话号码是否有效
	 * @author xjs
	 * @param tel
	 * @return
	 */
	public static boolean isTel(String tel){
		String regExp = "^[1]([3|5|7|8][0-9]{1})[0-9]{8}$";  
		Pattern p = Pattern.compile(regExp);  
		Matcher m = p.matcher(tel);  
		return m.find();  
	}
	
	/** 
     * 转化为弧度(rad) 
     * */  
    private static double rad(String d)  
    {  
       
       return Double.valueOf(d) * Math.PI / 180.0;  
    }  
    
    /** 
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下 
     * @param lon1 第一点的经度 
     * @param lat1 第一点的纬度 
     * @param lon2 第二点的经度 
     * @param lat3 第二点的纬度 
     * @return 返回的距离，单位km 
     * */  
    public static double getDistance(String lon1,String lat1,String lon2, String lat2)  
    {  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lon1) - rad(lon2);  
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
       s = s * EARTH_RADIUS;  
       
       return s;  
    }
    
    /**
     * MD5加密算法
     * @param inStr
     * @return
     * @throws Exception
     */
    public static String md5Encode(String inStr) throws Exception {
    	MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
        
    } 
    
    /**
     * 生成预约单号
     * 如：A 20151216 00000001
     * @return
     * @throws Exception
     */
    public static String generatorNo(String maxAppNo) {
    	
    	String[] letters={"A","B","C","D","E","F","G","H","I","J","K","L","N",
    			          "M","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    	String letter = letters[RandomUtils.nextInt(25)];
    	
    	DateFormat format=new SimpleDateFormat("yyyyMMdd");
    	String date=format.format(new Date());
    	
    	StringBuffer appNo=new StringBuffer(letter+date);
    	appNo.append(String.format("%06d", Integer.valueOf(maxAppNo)+1));
    	
    	return appNo.toString();
    } 
    
}
