package com.cs;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;


import com.cs.config.WXConfig;



@SpringBootApplication
public class Application  {


	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	 @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        factory.setMaxFileSize("3MB"); //KB,MB  
        factory.setMaxRequestSize("102400KB");  
        return factory.createMultipartConfig();  
    }  
	
	 
	 @Bean
	 public  WXConfig   initConfig(){
		 return new WXConfig();
	 }
	 
	 
	 
	 
}
