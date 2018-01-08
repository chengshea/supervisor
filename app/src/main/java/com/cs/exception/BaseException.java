package com.cs.exception;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Object obj;

	

	public void setObj(Object obj) {
		this.obj = obj;
	}



	public BaseException(Object obj) {
		super();
		this.obj = obj;
	}
	

	
}
