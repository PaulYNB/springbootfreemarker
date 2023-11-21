package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
@Service("userServiceImpl2")
public class UserServiceImpl2 implements UserService {
	
	//注入mapper接口代理对象
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> list = userMapper.findAll();
		return list;
	}

}
