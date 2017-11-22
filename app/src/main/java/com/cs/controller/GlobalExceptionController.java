package com.cs.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.dto.Message;

@ControllerAdvice
public class GlobalExceptionController {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ResponseBody
    @ExceptionHandler(Exception.class)
    public Message handleException(Exception e) {
		LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        logger.error(e.getMessage());
        Map<String,Object> m=new HashMap<String,Object>();
		m.put("time", ""+time);
		return new Message(0, m, e.getMessage());
        
    }


}
