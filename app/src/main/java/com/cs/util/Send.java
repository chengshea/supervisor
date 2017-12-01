package com.cs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.exception.BaseException;

/**
 * 
* @Title: Send.java 
* @Description: 请求类
* @author cs 
* @date 2017年11月22日 下午9:43:12 
* @version V1.0
 */
public class Send {
	 private static Logger logger = LoggerFactory.getLogger(Send.class);
	 
	 public static String request(String url, String content, String method) throws IOException {
	        URL sendUrl = new URL(url);
	        StringBuilder sbf = new StringBuilder();
	        HttpURLConnection http = (HttpURLConnection)sendUrl.openConnection();
	        //设置请求方式
	        http.setRequestMethod(method);
	        //url连接可以用于输入输出
	        http.setDoInput(true);
	        http.setDoOutput(true);
	        http.setRequestProperty("Content-Type", "application/json");
	 
	        if("POST".equals(method)){
	            //设置包体
	            http.getOutputStream().write(content.getBytes("utf-8"));
	            http.getOutputStream().flush();
	            http.getOutputStream().close();
	        }
	        //取得返回值
	        InputStream in = http.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
	        String line = null;
	        while((line = reader.readLine()) != null){
	            sbf.append(line);
	        }
	        return sbf.toString();
	    }
	 
	 
	   public static Map back(String request){
		   logger.info(request);
		   String result = "";
	        try{
	            result = request(request,null,"GET");
	        }catch(Exception e){
	        	throw new BaseException(e.getMessage()+": \n url :"+request);
	        }
		   Map tokenMap = null;
           try{
           	    tokenMap = JsonUtils.getMap(result);
           }catch(Exception e){
           	     throw new BaseException(e.getMessage()+": \n url :"+result);
           }
           if(tokenMap == null || tokenMap.get("errcode") != null){
	        	logger.error("request get 请求 方法出错");
	        	return null;
	        }
		   return tokenMap;
	   }
}
