package com.example.demo.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	private Integer id;
	private String username;
	private String password;
	@JSONField(format="yyyy-MM-dd")
	private Date date;
	public Integer getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
