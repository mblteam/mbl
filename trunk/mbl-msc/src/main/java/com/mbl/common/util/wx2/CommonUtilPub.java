package com.mbl.common.util.wx2;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mbl.common.util.MD5Util;

/**
 * 微信支付工具类
 * 
 * @author yufeng
 *
 */
public class CommonUtilPub {

	/**
	 * 是否微信支付签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @return boolean
	 */
	public boolean isWxSign(Map<String, Object> map, String sign) {

		String tenpaySign = getSign(map);

		return tenpaySign.equals(sign);
	}

	/**
	 * 签名算法
	 * 
	 * @param map
	 *            要参与签名的数据对象
	 * @return 签名
	 */
	public static String getSign(Map<String, Object> map) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + WxConfig.getKey();
		result = MD5Util.MD5Encode(result, "UTF-8").toUpperCase();
		return result;
	}

	/**
	 * 获取一定长度的随机字符串
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 将Map转化成xml
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String mapToXml(HashMap<String, Object> paramMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			if (entry.getValue() != "") {
				String key = entry.getKey();
				String value = String.valueOf(entry.getValue());
				sb.append("<").append(key).append(">").append(value)
						.append("</").append(key).append(">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取本机Ip
	 * 
	 * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。 获得符合
	 * <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String localIp() {
		String ip = null;
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface
						.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			// logger.warn("获取本机Ip失败:异常信息:"+e.getMessage());
			System.out.println("获取本机Ip失败:异常信息:" + e.getMessage());
		}
		return ip;
	}
}
