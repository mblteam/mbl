package com.mbl.common.framework.exception.excepts;



/**
 * 服务层异常
 * @author Administrator
 *
 */
public class ServiceException extends AppException {

	private static final long serialVersionUID = 1L;

	// 错误码
	private String code;

	public ServiceException() {
		super();
	}

	public ServiceException(String code) {
		this.code = code;
	}

	public ServiceException(String code, String message) {
		super(code);
		this.code = code;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String code, String message, Throwable cause) {
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
