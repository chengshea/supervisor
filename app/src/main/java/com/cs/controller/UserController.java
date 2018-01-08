package com.cs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.cs.dto.Message;
import com.cs.model.UserInfo;
import com.cs.service.UserInfoService;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"manager"},description="manager")
@Controller
@RequestMapping("/manager")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private  UserInfoService   u;

	
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView listUsers(HttpServletRequest request){
		Integer size=10;
		logger.info("========users  redirect:==========");
		ModelAndView mv=new ModelAndView();
		Page<UserInfo> page = u.getpage(0, size);
		mv.setViewName("/admin/users");
		mv.addObject("users", page);
		return mv;
		
	}
	 
   
    
	@RequestMapping("/userList")
    @ResponseBody
    public Message users(Map<String,Object> map) {
		logger.info("===============");
    	Integer size = 10;

		Page<UserInfo> page = u.getpage(0, size);
		List<UserInfo> lui = new ArrayList<UserInfo>();
		for (UserInfo userInfo : page) {
			logger.info(new Gson().toJson(userInfo));
			lui.add(userInfo);
		}
//		return new Message(0, lui);
				return new Message(200, lui, "返回成功 ", page.getNumberOfElements());

    }
}