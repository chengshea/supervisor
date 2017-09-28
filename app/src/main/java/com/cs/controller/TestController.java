package com.cs.controller;



import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.dto.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(description = "测试")
@Controller
@RequestMapping("/api")
public class TestController {



	@ApiOperation(value = "登录", notes = "测试登陆")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Message login(@ApiParam(  value="帐号",required = true)
	         @RequestParam(value = "user",defaultValue="123") String user,
	         @ApiParam(value="密码",required=true)@RequestParam(value = "pwd",defaultValue="456") String pwd
	       ) {
	    boolean flag =false;
		if(user.equals("123") && pwd.equals("456"))
			flag=true;
		
		
    
	
		
		if (flag==true) {
			String uiId = UUID.randomUUID().toString().replaceAll("-", "");
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("uiId", uiId);
			return new Message(1,m,"登陆成功");

		} else {
			return new Message(0,"","帐号或密码错误");
		}
		
		
	}
	

}
