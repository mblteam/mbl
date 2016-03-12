/**    
 * Copyright (C),Kingmed
 * @FileName: JsonUtil.java  
 * @Package: com.kingmed.lb.modules.utils  
 * @Description: //模块目的、功能描述  
 * @Author wangbozheng
 * @Date 2014年11月19日 下午4:17:13  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.mbl.common.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JsonUtil工具
 * 
 * @author wangbozheng
 * @create 2014年11月19日 下午4:17:13
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class JsonUtil {
	/**
	 * Logger。
	 */
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 实用类构造函数定义为私有。
	 */
	private JsonUtil() {

	}

	/**
	 * JSON对象转换为JSON字符串。
	 * 
	 * @param obj
	 *            待转化的对象
	 * @param dateFormat
	 *            默认日期格式
	 * 
	 * @return String JSON字符串
	 */
	public static String javaObjToJson(Object obj, DateFormat dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		String jsonStr = "";
		try {
			if (null != dateFormat) {
				mapper.setDateFormat(dateFormat);
			}
			jsonStr = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("", e);
		}
		return jsonStr;
	}

	/**
	 * JSON对象转换为JSON字符串。
	 * 
	 * @param obj
	 *            待转化的对象
	 * 
	 * @return String JSON字符串
	 */
	static public String javaObjToJson(Object obj) {
		return javaObjToJson(obj, null);
	}

	/**
	 * json字符串转换为jsonNode对象。
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @return JsonNode json
	 */
	public static JsonNode strTojsonNode(String jsonStr) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		JsonNode json = null;
		try {
			json = mapper.readValue(jsonStr, JsonNode.class);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
		}
		return json;
	}

	/**
	 * json字符串转换为jsonNode对象。
	 * 
	 * @param jsonStr
	 * @param t
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static <T> T jsonStrToObj(String jsonStr, Class<T> t) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		try {
			return mapper.readValue(jsonStr, t);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * json字符串转换为jsonNode对象。
	 * 
	 * @param jsonStr
	 * @param t
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static <T> T jsonStrToObj(String jsonStr, Class<T> t, DateFormat df) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		try {
			mapper.setDateFormat(df);
			return mapper.readValue(jsonStr, t);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
			throw new RuntimeException(e);
		}
	}

	public static <T> T jsonObjToObj(Object obj, Class<T> t) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		try {
			String jsonStr = javaObjToJson(obj);
			return mapper.readValue(jsonStr, t);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
			throw new RuntimeException(e);
		}
	}

	public static List<?> jsonStrToList(String jsonStr, Class<?> t) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		try {
			JavaType javaType = getCollectionType(ArrayList.class, t);
			return mapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> jsonObjToList(Object obj, Class<T> t) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		try {
			String jsonStr = javaObjToJson(obj);
			JavaType javaType = getCollectionType(ArrayList.class, t);
			return mapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
			throw new RuntimeException(e);
		}
	}

	public static <T> T jsonObjToParametricType(Object obj,
			Class<?> parametrized, Class<?>... parameterClasses) {
		ObjectMapper mapper = getIgnoreUnknowPropFailMapper();
		try {
			String jsonStr = javaObjToJson(obj);
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(parametrized, parameterClasses);
			return mapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			log.error("json字符串转换成json对象出错！", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 *
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	public static ObjectMapper getIgnoreUnknowPropFailMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);// 设置忽略不识别的属性
		return mapper;
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

}
