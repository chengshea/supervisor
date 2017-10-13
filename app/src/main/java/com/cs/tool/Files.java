package com.cs.tool;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class Files {
	

	public static String load(HttpServletRequest request){
		String url=null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
		// 判断请求 enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
		        // 将request变成多部分request
		        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		        Iterator<String> iter = multiRequest.getFileNames();
		    if(iter.hasNext()==true)
		        while (iter.hasNext()) {
		                // 一次遍历所有文件
		                MultipartFile file = multiRequest.getFile(iter.next()
		                                .toString());
		                if (file != null) {
		                        String filename = file.getOriginalFilename();
		                         
		                        StringBuffer abs= new StringBuffer()
		                        		.append(request.getSession().getServletContext().getRealPath("/"))
		                                .append("upload").append("/").append(filename);
		                        
		                        File f = new File(abs.toString());
		                       
		                        File parent = f.getParentFile();
								if (!parent.exists()) {
									parent.mkdirs();
									
								try {
									file.transferTo(f);
								} catch (IllegalStateException | IOException e) {
									e.printStackTrace();
								}
								
		                     if (url == null) {// 第一个文件
                                    url=abs.toString();
                             } else {// 不是第一个文件
                            	  url += ","+abs.toString() ;
                             }
		                }
		                        
		                }
		                        
	         }
	   }
		return url;
	}
}
