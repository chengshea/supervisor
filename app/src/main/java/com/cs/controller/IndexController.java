package com.cs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.cs.util.CheckoutUtil;


@Controller
@RequestMapping("/api")
public class IndexController {
   
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	    @RequestMapping("/")
	    public String index() {
	        return "forward:/swagger-ui.html";
	    }
	    
	    @RequestMapping(value="/redirect",method={RequestMethod.GET,RequestMethod.POST })
	    public void redirect(HttpServletRequest request,HttpServletResponse response){
	    	boolean isGet = request.getMethod().toLowerCase().equals("get");    
	        if (isGet) {    
		        // 微信加密签名
		        String signature = request.getParameter("signature");
		        // 时间戳
		        String timestamp = request.getParameter("timestamp");
		        // 随机数
		        String nonce = request.getParameter("nonce");
		        // 随机字符串
		        String echostr = request.getParameter("echostr");
		        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		        if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
		                   //应答服务器信息
		                try {
							response.getWriter().write(echostr);
						} catch (IOException e) {
							e.printStackTrace();
						}
		             
		        }
	        }else{
	        	
	        }
	       
	    } 
	    
	    
	    @RequestMapping(value="/register",method=RequestMethod.GET)
		 public void register(HttpServletRequest request) throws IOException {
			 String code = request.getParameter("code");
			 if(code!=null && !"".equals(code)){
		        	
		           
		            logger.info(" \n code : "+code);
		    }
		
		 }
	    
}
