package com.mbl.common.framework.exception.excepts;


/**
 * 数据访问层异常
 * @author Administrator
 *
 */
public class DataAccessException extends AppException {

	private static final long serialVersionUID = 1L;

	// 错误码
	private String code;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String code) {
		this.code = code;
	}

	public DataAccessException(String code, String message) {
		super(code,message);
		this.code = code;
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String code, String message, Throwable cause) {
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
