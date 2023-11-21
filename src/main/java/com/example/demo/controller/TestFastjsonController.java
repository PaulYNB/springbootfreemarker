package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.pojo.User;

@Controller
public class TestFastjsonController {
	@ResponseBody
	@RequestMapping("/testFastJson")
	public Object show() {
		
		User user =new User();
		user.setId(1);
		user.setUsername("霸道");
		user.setPassword("流氓气质");
		user.setDate(new Date());
		
		return user;
	}
}
