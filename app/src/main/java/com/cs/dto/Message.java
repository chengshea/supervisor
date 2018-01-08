package com.cs.dto;


public class Message {
     
   private int code;	
   private 	Object  data;
   private String msg;
   private int count;
   
   
   
	public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Message(int status, Object data, String msg) {
		super();
		this.code = status;
		this.data = data;
		this.msg = msg;
	}
	public Message(int status, String msg) {
		this.code = status;
		this.msg = msg;
	}
	public Message(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	
	
	public Message(int status, Object data, String msg, int count) {
		super();
		this.code = status;
		this.data = data;
		this.msg = msg;
		this.count = count;
	}
	
	
   
   
}
