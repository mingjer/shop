package net.realme.framework.util.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	private String message;

	private Object[] params;

	public BusinessException() {
		super();
	}

	public BusinessException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BusinessException(int code, String message, Object[] params) {
		super();
		this.code = code;
		this.message = message;
		this.params = params;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
