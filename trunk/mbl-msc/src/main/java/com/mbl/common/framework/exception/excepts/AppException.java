package com.mbl.common.framework.exception.excepts;

import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 异常处理基础类
 * 
 * @author Administrator
 *
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static Properties config = new Properties();
	
	private final static Logger LOGGER = Logger.getLogger(AppException.class);
	static {
		try {
			config.load(new InputStreamReader(AppException.class
					.getClassLoader()
					.getResourceAsStream("exceptmsg.properties"), "UTF-8"));
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	// 错误码
	private String code;

	public AppException() {
		super();
	}

	public AppException(String code) {
		this.code = code;
	}

	public AppException(String code, String message) {
		super(message);
		this.code = code;
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static String getExceptMsg(String msgCode) {
		return config.getProperty(msgCode);
	}

}
