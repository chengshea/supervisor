package com.cs.util;

import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;





/**
 * 
 * 名称：GetLocation.java<br>
 * 描述：获取地址
 * 最近修改时间: 2017年11月22日 下午2:17:07 <br>
 * @since  2017年11月22日
 * @authour cs
 */
public class GetLocation {

	/**
	 * 
	 * 描述： 维度，经度
	 * @param  String 
	 * @变更记录 2017年11月22日 上午9:55:46 cs创建
	 */
	 private static String getAdd(String log, String lat ){    
	        //lat 小  log  大    
	        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)   
	        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";    
	        String res = "";       
	        try {       
	            URL url = new URL(urlString);      
	            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();      
	            conn.setDoOutput(true);      
	            conn.setRequestMethod("POST");      
	            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));      
	            String line;      
	           while ((line = in.readLine()) != null) {      
	               res += line+"\n";      
	         }      
	            in.close();      
	        } catch (Exception e) {      
	            System.out.println("error in wapaction,and e is " + e.getMessage());      
	        }     
	     
	        return res;      
	    }    
	 
	 /**
	  *eg :{"queryLocation":[22.581738,113.854706],"addrList":[{"type":"poi","status":1,"name":"深圳市碧海湾高尔夫俱乐部有限公司","id":"ANB02F38QLJJ","admCode":"440306","admName":"广东省,深圳市,宝安区,","addr":"西乡镇西乡大道新湾路","nearestPoint":[113.85469,22.58167],"distance":6.083}]}
	  * 描述： 获取 省，市，区
	  * @param  String "113.854698", "22.581762"
	  * @变更记录 2017年11月22日 下午2:22:44 cs创建
	  */
	 public  static String admName(String log, String lat){
		 String add = getAdd(log, lat);
		 JsonElement parse = new JsonParser().parse(add);
		 JsonElement jsonObj = parse.getAsJsonObject().get("addrList");
		 JsonElement jsonArr = jsonObj.getAsJsonArray().get(0);
		 String str = jsonArr.getAsJsonObject().get("admName").getAsString();
		return str;
	        
	 }

	 
}
