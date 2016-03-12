package com.mbl.common.framework.exception.excepts;


/**
 * 不能处理的异常
 * @author Administrator
 *
 */
public class UnCheckedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// 错误码
	private String code;

	public UnCheckedException() {
		super();
	}

	public UnCheckedException(String code) {
		this.code = code;
	}

	public UnCheckedException(String code, String message) {
		super(message);
		this.code = code;
	}

	public UnCheckedException(Throwable cause) {
		super(cause);
	}

	public UnCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnCheckedException(String code, String message, Throwable cause) {
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
