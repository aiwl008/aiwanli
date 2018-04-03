package com.aiwl.common.exception;

/**
 * Õ®”√“Ï≥£
 * 
 * @author dohko
 * 
 */
public class Zw56Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6206609505458736421L;

	private String message;
	private int code = -1;
	private Object data;

	public Zw56Exception() {
	}

	public Zw56Exception(String message) {
		this.message = message;
	}

	public Zw56Exception(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public Zw56Exception(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
