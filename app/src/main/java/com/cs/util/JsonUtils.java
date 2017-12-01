package com.cs.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.cs.pojo.WXUserInfo;
import com.google.gson.Gson;



//import net.sf.json.JSONObject;





public class JsonUtils {
	 /**
     * 防止实例化
     */
    private JsonUtils(){
    }

    /**
     * 字符串转换为map
     * @param result
     * @return
     */
    public static Map getMap(String jsonString) {
        return new Gson().fromJson(jsonString, HashMap.class);
    } 
    
    public static WXUserInfo getUserInfo(String jsonString) {
    	WXUserInfo info = new Gson().fromJson(jsonString, WXUserInfo.class);
        return info;
    }
    
    
    public static void main(String[] args) {
		String str=" {\"subscribe\":1,\"openid\":\"oSEsE0lCdMY3BxDykycdwu9eGmhs\",\"nickname\":\"人生就像旅行\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"武汉\",\"province\":\"湖北\",\"country\":\"中国\",\"headimgurl\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/iaRMPmMmL0WmVg3X2VK2KmUQo4m16bIhghaVuYGL9I8pmhSia7icXRXqsXH9oiaJEdA6WK7PEES6ohUTaPCXqVCIcbZs2g80hiaLt\\/0\",\"subscribe_time\":1511152659,\"remark\":\"\",\"groupid\":0,\"tagid_list\":[]}";
		WXUserInfo info = getUserInfo(str);
		System.err.println(info);
	}
}