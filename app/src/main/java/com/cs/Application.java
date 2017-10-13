package com.cs;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Controller
@EnableScheduling
@EnableSwagger2
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
	
}
