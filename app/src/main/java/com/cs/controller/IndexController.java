package com.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	
	     @RequestMapping("/")
	    public String index() {
	        return "forward:/swagger-ui.html";
	    }
}
