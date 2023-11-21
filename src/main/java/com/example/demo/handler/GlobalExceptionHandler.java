package com.example.demo.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
//全局异常处理器
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String,Object> handlerExcception(Exception e) {
		Map<String,Object> map = new HashMap<>();
		map.put("code",500);
		map.put("message",e.toString());
		return map;
	}
}
