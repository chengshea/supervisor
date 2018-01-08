package com.cs.util;

import java.util.Map;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.config.WXConfig;
import com.cs.exception.BaseException;
import com.cs.pojo.WXUserInfo;


public class OAuth {
	private static Logger logger = LoggerFactory.getLogger(OAuth.class);
	/**
	 * 根据id和密钥 获取全局唯一 access_token
	 * @变更记录 2017年11月17日 下午3:23:00 cs创建
	 */
	
	public static String getAccessToken(){
        String token = "";
        //利用code获取code
        StringBuffer access_token_url = new StringBuffer();
        access_token_url.append("https://api.weixin.qq.com/cgi-bin/token?")
          .append("grant_type=client_credential")
          .append("&appid=").append(WXConfig.AppId)
          .append("&secret=").append(WXConfig.Appsecret);
             
        try{
            Map tokenMap = Send.back(access_token_url.toString());
            logger.info("\n tokenMap:"+tokenMap);
            if(tokenMap != null && tokenMap.containsKey("access_token")){
            	token = (String)tokenMap.get("access_token");
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
    
         return token;
    }
	
	
	/**
	 * 
	 * 描述： 用户点击链接回调返回code
	 * @param  int  状态1 静默授权， 其它 用户允许授权回调才返回code
	 * @变更记录 2017年11月20日 下午2:57:56 cs创建
	 */
	public static String getCode(int status){
        //获取code
        StringBuffer code_url = new StringBuffer();
        try{                  
        	String url = java.net.URLEncoder.encode(WXConfig.RedirectUri, "utf-8");
        	code_url.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
        	.append("appid=").append(WXConfig.AppId)
        	.append("&redirect_uri=").append(url)
        	.append("&response_type=code");
        	if(status==1){//静默
        		code_url.append("&scope=snsapi_base");
        	}else{
        		code_url.append("&scope=snsapi_userinfo");
        	}
        	code_url.append("&state=STATE#wechat_redirect");
             
            logger.info("\n url："+code_url.toString());
         
        }catch(Exception e){
        	e.printStackTrace();
        }
    
         return code_url.toString();
    }
	
	
	
	/**
	 * 
	 * 描述： 用户授权，利用回调code，获取   
	 * @param  String 
	 * @变更记录 2017年11月20日 下午2:42:02 cs创建
	 */
	public static String getOpenIdByCode( String code){
        String openID = "";
        //利用code获取openID
        StringBuffer access_token_url = new StringBuffer();
        access_token_url.append("https://api.weixin.qq.com/sns/oauth2/access_token?")
          .append("appid=").append(WXConfig.AppId)
          .append("&secret=").append(WXConfig.Appsecret)
          .append("&code=").append(code)
          .append("&grant_type=authorization_code");
             
        try{
           
            Map tokenMap = Send.back(access_token_url.toString());
            logger.info("\n tokenMap:"+tokenMap);
            if(tokenMap != null && tokenMap.containsKey("openid")){
                openID = (String)tokenMap.get("openid");
                //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
                if(tokenMap.containsKey("unionid")){
                	logger.info("\n unionid:"+tokenMap.get("unionid"));
                	 
                }
            }
        }catch(Exception e){
        }
        
        return openID;
    }
	
	
	/**
	 * 
	 * 描述： 关注了该公众号    1关注  0未关注
	 * @param  String 
	 * @变更记录 2017年11月20日 下午3:46:23 cs创建
	 */
	public static Integer getInfo(WXUserInfo info){
		Integer  status=0;
		if(info != null ){
              status = info.getSubscribe();
            if(status==0){//用户未关注该公众号
          	  
          	  
            }
       }
		return status;
	}
	
	/**
	 * 
	 * 描述： 通过OpenID,access_token 来获取用户基本信息
	 * @param  String 
	 * @变更记录 2017年11月20日 下午3:45:33 cs创建
	 */
	public static WXUserInfo getUserInfo(String access_token,String openID){
		WXUserInfo info = null;
        StringBuffer access_token_url = new StringBuffer();
        access_token_url.append("https://api.weixin.qq.com/cgi-bin/user/info?")
          .append("access_token=").append(access_token)
          .append("&openid=").append(openID)
          .append("&lang=zh_CN");
             
        try{
        	String request = Send.request(access_token_url.toString(),null,"GET");
        	 info = JsonUtils.getUserInfo(request);
            logger.info("\n getInfo :"+info);
        }catch(Exception e){
        	throw new BaseException("通过OpenID,access_token 获取用户信息出错 ："+e.getMessage());
        }
        
        return info;
    }
}
