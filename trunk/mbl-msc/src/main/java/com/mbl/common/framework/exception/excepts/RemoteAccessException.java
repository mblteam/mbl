package com.mbl.common.framework.exception.excepts;


/**
 * 远程接口访问异常
 * @author Administrator
 *
 */
public class RemoteAccessException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;

	// 错误码
	private String code;

	public RemoteAccessException() {
		super();
	}

	public RemoteAccessException(String code) {
		this.code = code;
	}

	public RemoteAccessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public RemoteAccessException(Throwable cause) {
		super(cause);
	}

	public RemoteAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public RemoteAccessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
