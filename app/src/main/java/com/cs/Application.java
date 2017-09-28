package com.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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


	
}
