package com.cs.service;


import com.cs.pojo.WXUserInfo;

public interface WXService {
      
	  public  String  token();
	  
	  public WXUserInfo   getUserInfo(String openID);
	  
	  public  String  address();
}
