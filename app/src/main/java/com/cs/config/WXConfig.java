package com.cs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WXConfig {

public static  String  AppId;
	
	public  static String  Appsecret;
	
	public  static String  Token;
	
	public  static String RedirectUri;

	
	@Value("${wx.appId}")
	public  void setAppId(String appId) {
		AppId = appId;
	}
	@Value("${wx.appsecret}")
	public  void setAppsecret(String appsecret) {
		Appsecret = appsecret;
	}
	@Value("${wx.token}")
	public  void setToken(String token) {
		Token = token;
	}
	@Value("${wx.redirect_uri}")
	public  void setRedirectUri(String redirectUri) {
		RedirectUri = redirectUri;
	}
}
