package com.cs.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.dto.Message;

@ControllerAdvice
public class GlobalExceptionController {

	@ResponseBody
    @ExceptionHandler(Exception.class)
    public Message handleException(Exception e) {
      
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
        	return new Message(0, "", "服务器出错");
        }
		return new Message(0, "", msg);
        
    }


}
