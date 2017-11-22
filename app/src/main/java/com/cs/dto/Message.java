package com.cs.dto;


public class Message {
     
   private int status;	
   private 	Object  data;
   private String msg;
   
   
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
		this.status = status;
		this.data = data;
		this.msg = msg;
	}
	public Message(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
   
   
}
