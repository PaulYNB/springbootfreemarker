package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;
@Service
public interface UserService {
	
	public List<User> findAll();
	
}
