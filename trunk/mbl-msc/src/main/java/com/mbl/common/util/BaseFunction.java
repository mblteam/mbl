/**
 * 工程: gwnisdemo <br/>
 * 作者: txz <br>
 * 时间: 2014-08-08 15:36:57 <br>
 * Hunan GoalWisdom Technologies Co.,Ltd
 * All rights reserved.
 */

package com.mbl.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.portable.ApplicationException;

import com.mbl.rest.pkg.vo.UserPackageVo;

/**
 * 类: BaseFunction <br>
 * 描述: TODO <br>
 * 作者: songkeyu <br>
 * 时间: 2014-8-5 上午10:34:53
 */
public class BaseFunction {
	
	
	/**
	 * 
	 * 方法: stringToDate <br>
	 * 描述: 字符串转换到时间格式<br>
	 * 作者: liuliwen <br>
	 * 时间: 2015-5-19 上午11:46:06
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return
	 */
	public static Date stringToDate(String dateStr, String formatStr) throws Exception{
		if(dateStr==null||"".equals(dateStr))return null;
		if(formatStr==null||"".equals(formatStr))formatStr="yyyy-MM-dd";;
		DateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.parse(dateStr);
	}
	
	/**
	 * 方法: replaceString <br>
	 * 描述: 处理字符串替换其中的标志字符，替换为值 <br>
	 * 作者: liuliwen <br>
	 * 时间: 2015-5-19 下午7:36:47
	 * @param str
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String replaceString(String str, Map<String, String> map)
			throws Exception {
		if (str == null)
			return null;
		// 用于匹配的正则表达式 查询类似"name}"的字符串
		// Pattern pattern = Pattern.compile("^\\$\\{[a-zA-Z]*\\}$");
		// Matcher m = pattern.matcher(str);
		// String strs=pattern.
		// 进行字符匹配并且替换
		int i = 0;
		int sta = str.indexOf("${");
		if (sta > 0) {
			char[] strTo = str.toCharArray();
			StringBuffer sb = new StringBuffer();
			while (str.indexOf("${", i) > 0) {
				int end = str.indexOf("}", sta);
				// 将不需要替换的部分拼接到要返回的字符串里面
				sb.append(strTo, i, sta - i);
				// 拿出要替换的部分，从其值所在的map中获取到值
				String key = str.substring(sta + 2, end);
				sb.append(map.get(key)==null?"":map.get(key));
				i = end + 1;
				sta = str.indexOf("${", i);
			}
			sb.append(strTo, i, strTo.length - i);
			return sb.toString();
		}
		return str;
	}
    /**
     * 方法: dateToString <br>
     * 描述: 时间转换为字符串 <br>
     * 作者: liuliwen <br>
     * 时间: 2015-5-19 上午11:45:47
     * @param date
     * @param formatStr
     * @return
     */
	public static String dateToString(Date date,String formatStr){
		String dateStr="";
		if(date!=null){
			if(formatStr==null||"".equals(formatStr))formatStr="yyyy-MM-dd";
			DateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
			dateStr=simpleDateFormat.format(date);
		}
		return dateStr;
	}
	/**
	 * 
	 * 方法: getDesignationDate <br>
	 * 描述: 获得指定的日期字符串，例如-1是获取前一天，1是后一天 <br> <br>
	 * 作者: liuliwen <br>
	 * 时间: 2015-9-9 上午10:52:30
	 * @param i
	 */
	public static String getDesignationDate(int i){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, i);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
		return(dateToString(dBefore, ""));
	}
	/**
	 * 
	 * 方法: ifNumber <br>
	 * 描述: 验证是否纯数字 <br>
	 * 作者: liuliwen <br>
	 * 时间: 2015-7-30 上午11:03:04
	 * @param str
	 */
	public static boolean ifNumber(String str){
		if(str==null)return false;
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence)str);
		return matcher.matches();
	}

	/**
	 * liuliwen
	 * 计算年龄,返回年龄字符串，如果小于2岁，会加上月份，比如25岁，1岁4个月,大于2岁的默认超过6个月算加一岁，即四舍五入，小于28天为新生儿
	 * @param bornTime
	 * @return
	 */
	public static String computingAge(Date bornTime){
		if(bornTime==null){
			return "";
		}
		Calendar now = Calendar.getInstance();  //获取当前时间
        if(now.getTime().before(bornTime)){
        	//出生日期在当前日期之后，错误
			return "出生日期大于当前日期";
		}
		Calendar born = Calendar.getInstance();
		born.setTime(bornTime);
		StringBuffer sb=new StringBuffer();
		//相差天数
		int ageDay = new Long((now.getTime().getTime()-bornTime.getTime())/(1000*60*60*24)).intValue();
		if(ageDay<28){//如果小于28天，新生儿
			return "新生儿";
		}
		//年份差
		int bornYear=born.get(Calendar.YEAR);
		int nowYear=now.get(Calendar.YEAR);
		int ageYear=nowYear-bornYear;
		//月份差
		int bornMonth=born.get(Calendar.MONTH);
		int nowMonth=now.get(Calendar.MONTH);
		int ageMonth=nowMonth-bornMonth;
		if(nowMonth<bornMonth){
			ageYear-=1;
			ageMonth+=12;
		}
		//如果年龄大于2，月份超过6个月，年龄加1
		if(ageYear>2&&ageMonth>6){
			ageYear+=1;
		}
		if(ageYear>0)sb.append(ageYear+"岁");
		if(ageYear<2&&ageMonth>=1)sb.append(ageMonth+"个月");
		return sb.toString();
	}
	/**
	 * 方法: intToChina <br>
	 * 描述: 数字转中文表示,只处理100以内的数 <br>
	 * 作者: liuliwen <br>
	 * 时间: 2015-7-8 上午9:48:04
	 */
	 public static String intToChina(int d) {
       String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" ,"十"};
       //0到10直接处理
       if(d<11)return str[d];
       //20特殊处理
       if(d==20)return "二十";
       //十一到十九特殊处理
       if(d>10&&d<20){
    	   return "十"+str[d-10];
       }
       String s = String.valueOf(d);
       StringBuffer sb = new StringBuffer();
       for (int i = 0; i < s.length(); i++) {
           String index = String.valueOf(s.charAt(i));
           sb = sb.append(str[Integer.parseInt(index)]);
       }
       //这里的数字都是大于20的，需要在中间插入一个十
       sb=sb.insert(1, "十");
       return sb.toString();
   }
	/**liuliwen
	 * 冒泡排序
	 * @param r
	 * @return
	 */
	public static int[] sequence(int[] r) {
		int n = r.length;
		int j, i, max, min;
		int low = 0;
		int high = n - 1;
		while (low < high) {
			for (j = low; j < high; j++) {// 正向找到最大值
				if (r[j] > r[j + 1]) {// 第一个大于第二个,交换位置
					max = r[j];
					r[j] = r[j + 1];
					r[j + 1] = max;
				}
			}
			for (i = high; i > low; i--) {// 反向找到最小值
				if (r[i] < r[i - 1]) {// 最后一个小于他之前的一个，交换到前面
					min = r[i];
					r[i] = r[i - 1];
					r[i - 1] = min;
				}
			}
			low++;
			high--;
		}
		return r;
	}

	 /**
     * 方法: ifSameDay <br>
     * 描述: 判断两个日期是否为医院护理上的同一天  早上8点到第二天早上8点<br>
     * 作者: liuliwen <br>
     * 时间: 2015-7-7 下午2:10:44
     */
	public static boolean ifCareSameDay(Date a,Date b){
		long twentyfourHour=24*60*60*1000;
		//如果时间差大于24小时，就不用继续进行判断
		if(a.after(b)){
			if(a.getTime()-b.getTime()>twentyfourHour)
				return false;
		}else{
			if(b.getTime()-a.getTime()>twentyfourHour)
				return false;
		}
		Calendar dateA = Calendar.getInstance();
		dateA.setTime(a);
		Calendar dateB = Calendar.getInstance();
		dateB.setTime(b);
		int dayA=dateA.get(Calendar.DAY_OF_YEAR);
		int dayB=dateB.get(Calendar.DAY_OF_YEAR);
		int hourA=dateA.get(Calendar.HOUR_OF_DAY);
		int hourB=dateB.get(Calendar.HOUR_OF_DAY);
		if(dayA==dayB){//如果是同一天，必须是同时大于早8点或小于早8点，
			if((hourA>7&&hourB>7)||(hourA<8||hourB<8))return true;
		}else{//如果不是同一天，必须是一个大于早8点，一个小于早8点
			if((hourA>7&&hourB<8)||(hourA<8&&hourB>7))return true;
		}
		return false;
	}

	/**
	 * 将字符串按照给定的长度拆分成多行
	 * @author txz
	 */
	public static void splitDisease(List<String> str, String disease, int rowLength) throws Exception {
		byte[] b = disease.getBytes("UTF-8");
		if(b.length<=rowLength){
			str.add(disease);
			return;
		}
		//判断截取的字符有没有半个中文字符的情况
		int count=0;
		for(int i=0;i<rowLength;i++){
			if(b[i]<0)
				count+=1;
		}
		String split="";
		String rest="";
		if(count%3==0){
			//被3整除   代表没有不完整的中文字符
			split = new String(b,0,rowLength, "UTF-8");
			rest = new String(b,rowLength,b.length-rowLength, "UTF-8");
		}else if(count%3==1){
			//余数为1   代表有1/3个中文字符
			split = new String(b,0,rowLength-1, "UTF-8");
			rest = new String(b,rowLength-1,b.length-(rowLength-1), "UTF-8");
		}else if(count%3==2){
			//余数为2   代表有2/3个中文字符
			split = new String(b,0,rowLength+1, "UTF-8");
			rest = new String(b,rowLength+1,b.length-(rowLength+1), "UTF-8");
		}
        if(split.length()>11){
            String temp = split;
            split = temp.substring(0,11);
            rest = temp.substring(11)+rest;
        }
		str.add(split);
		splitDisease(str, rest, rowLength);
	}

	/**
	 * 发送短信
	 * @param phoneNo 电话号码
	 * @param messge 信息
	 * @throws Exception
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static void sendSMS(String phoneNo,String messge) throws Exception {
		System.out.println("phoneNo");
		StringBuffer content = new StringBuffer();
		content.append(messge);
		// 发送内容
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://api.duanxin.cm/?");

		// 向StringBuffer追加用户名
		sb.append("action=send&username=70206487");

		// 向StringBuffer追加密码（密码采用MD5 32位 小写）
		sb.append("&password=0342daeedb4b91c6ea4020b31a34e677");

		// 向StringBuffer追加手机号码
		sb.append("&phone=");
		sb.append(phoneNo);
		sb.append("&encode=utf8");

		//sb.append("&content=" + URLEncoder.encode(content.toString(),""));
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content=" + URLEncoder.encode(content.toString(),"UTF-8"));
		// 创建url对象
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		in.close();
	}

	/**
	 * 把 VO 中所有属性为 null 的转为 ""
	 * 
	 * @throws ApplicationException
	 */
	public static void nullConverNullString(Object obj) {
		if (obj != null) {

			Class classz = obj.getClass();
			// 获取所有该对象的属性值
			Field fields[] = classz.getDeclaredFields();

			// 遍历属性值，取得所有属性为 null 值的
			for (Field field : fields) {
				try {
					Type t = field.getGenericType();
					if (!t.toString().equals("boolean")&&!"serialVersionUID".equals(field.getName())) {
						Method m = classz.getMethod("get"
								+ change(field.getName()));
						Object name = m.invoke(obj);// 调用该字段的get方法
						if (name == null) {
							Method mtd = classz.getMethod(
									"set" + change(field.getName()),
									new Class[] { String.class });// 取得所需类的方法对象
							mtd.invoke(obj, new Object[] { "" });// 执行相应赋值方法
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	* @param src
	*            源字符串
	* @return 字符串，将src的第一个字母转换为大写，src为空时返回null
	*/
	public static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}
	
	/** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */ 
    public static Map convertBean(Object bean) 
            throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
        Class type = bean.getClass(); 
        Map returnMap = new HashMap(); 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); 

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(bean, new Object[0]); 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                } else { 
                    returnMap.put(propertyName, ""); 
                } 
            } 
        } 
        return returnMap; 
    } 

}
