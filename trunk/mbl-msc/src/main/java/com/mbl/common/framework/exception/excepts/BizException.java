package com.mbl.common.framework.exception.excepts;


/**
 * 业务逻辑异常
 * @author Administrator
 *
 */
public class BizException extends AppException {

	private static final long serialVersionUID = 1L;

	// 错误码
	private String code;

	public BizException() {
		super();
	}

	public BizException(String code) {
		this.code = code;
	}

	public BizException(String code, String message) {
		super(code,message);
		this.code = code;
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String code, String message, Throwable cause) {
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
