package com.cs.controller;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import com.cs.dto.Message;
import com.cs.tool.Files;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = "测试")
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
	   
		if (user.equals("123") && pwd.equals("456")) {
			String uiId = UUID.randomUUID().toString().replaceAll("-", "");
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("uiId", uiId);
			return new Message(1,m,"登陆成功");

		} else {
			return new Message(0,"","帐号或密码错误");
		}
		
		
	}
	
	
	@ApiOperation(value = "文件", notes = "传")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Message file(@ApiParam( value="文件",required = true)
	          MultipartFile file
	       ) {
		String path = null;
		
		String filename = file.getOriginalFilename();
		if(filename!=null){
			String root = RequestContext.class.getResource("/").getFile();
			try {
				path = new File(root).getParentFile().getParentFile().getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String,Object> m=new HashMap<String,Object>();
		
		StringBuilder sb = new StringBuilder().append(path).append(File.separator).append("upload").append(File.separator).append(filename);
		File url= new File(sb.toString());
		if(!url.getParentFile().exists())
			url.getParentFile().mkdirs();
		try {
			file.transferTo(url);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		    m.put("path", url);
			return new Message(1, m, "上传成功");
		
	}
	
	@ApiOperation(value = "多文件", notes = "少jar包测试")
	@RequestMapping(value = "/uploads", method = RequestMethod.POST)
	@ResponseBody
	public Message files(@ApiParam( value="文件",required = true)
	          MultipartFile file,@ApiParam( value="文件",required = true)
             @RequestParam("file1") MultipartFile file1,HttpServletRequest request
	       ) {
		Map<String,Object> m=new HashMap<String,Object>();
		String load = Files.load(request);
		   m.put("path", load);
			return new Message(1, m, "上传成功");
		
	}

}
