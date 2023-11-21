package com.example.demo.controller;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class TestMessageConvertController {
	
	//定义消息转换器
	//SpringBoot默认配置了消息转换器
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter stringHttpMessageConverter =new StringHttpMessageConverter(Charset.forName("ISO8859-1"));
		return stringHttpMessageConverter;
	}
	
	@RequestMapping("/testMessageConvert")
	@ResponseBody
	public String testMessageConvert() {
		return "你好！！！";
	}
	public static void main(String[] args) {
		SpringApplication.run(TestMessageConvertController.class, args);
	}
}
