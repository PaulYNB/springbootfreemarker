package com.example.demo.controller;

import javax.sound.midi.MidiDevice.Info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableAutoConfiguration
@Controller
//configed in application.properties
@ConfigurationProperties(prefix="book")
public class BookController {
	
	//@Value("${book.author}")
	private String author;
	
	//@Value("${book.name}")
	private String name;
	
	private String price;

	private String time;
	
	private String other;
	
	private String qizhi;
	
	@RequestMapping("/bookInfo")
	@ResponseBody
	public String showInfo() {
		
		return author+":"+name+":"+price+":"+other+":"+qizhi;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getQizhi() {
		return qizhi;
	}

	public void setQizhi(String qizhi) {
		this.qizhi = qizhi;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookController.class, args);
	}
	
}
