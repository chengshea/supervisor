package com.cs.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.cs.pojo.WXUserInfo;
import com.cs.util.OAuth;
import com.cs.service.WXService;

@Service
public class WXServiceImpl implements WXService{

	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name="stringRedisTemplate")
    ValueOperations<String,String> valOpsStr;
	
	/**
	 * 描述：缓存全局access_token
	 * 最近修改时间: 2017年11月21日 上午10:49:24 <br>
	 */
	@Override
	public String token() {
		String gobal_access_token = valOpsStr.get("gobal_access_token");
       if(StringUtils.isBlank(gobal_access_token)) {
           gobal_access_token = OAuth.getAccessToken();
           valOpsStr.set("gobal_access_token",gobal_access_token , 7200, TimeUnit.SECONDS);
       }
	    return gobal_access_token;
	}

	@Override
	public WXUserInfo getUserInfo(String openID) {
		 String token = token();
		return OAuth.getUserInfo(token, openID);
	}

	
	@Override
	public String address() {
		
		return null;
	}

}
