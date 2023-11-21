package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestHandlerController {
	@RequestMapping("/testHandler")
	@ResponseBody
	public String show() 
	{
		int a=5/0;
		return "testHandler";
	}
}
