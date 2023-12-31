package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class SpringController {
	@RequestMapping("/test")
	@ResponseBody
	public String yes() {
		return "test";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringController.class, args);
	}
}
