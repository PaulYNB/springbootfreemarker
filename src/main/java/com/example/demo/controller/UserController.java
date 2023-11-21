package com.example.demo.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;


/**
 * @author badao
 * @Description:测试
 * @Time:2019年3月6日 下午11:20:19
 */
@RestController
public class UserController {
	 
	
	//注入Service服务对象
	@Autowired
	private UserService userService;
	
	/**
	 * 整合SSM
	 */
	@RequestMapping("ssm")
	public List<User> findAll(){
		List<User> list = userService.findAll();
		return list;
	}
	/**
	 * 返回基本类型json数据
	 * @return
	 */
	@RequestMapping("hello")
	public String showhello() {
		return "hello world,badao Spring Boot";
	}
	
	/**
	 * 返回pojo对象
	 * @return
	 */
	@RequestMapping("pojo")
	public User showUser() {
		User user = new User();
		user.setId(1);
		user.setUsername("霸道");
		user.setPassword("123");
		
		return user;
		
	}
	
	/**
	 * 返回Map集合对象
	 * @return
	 */
	@RequestMapping("maps")
	public HashMap<String, Object> showMaps() {
		HashMap<String, Object> maps = new HashMap<String,Object>();
		maps.put("username","霸道");
		maps.put("password", "123");
		maps.put("mapkey", "mapvalue");
		return maps;
		
	}
	
	/**
	 * 返回List集合对象
	 * @return
	 */
	@RequestMapping("list")
	public List<User> showList() {
		List<User> list =new ArrayList<User>();
		User user1 =new User();
		user1.setId(1);
		user1.setUsername("badao");
		user1.setPassword("123");
		User user2 =new User();
		user2.setId(1);
		user2.setUsername("badao");
		user2.setPassword("123");
		list.add(user1);
		list.add(user2);
		return list;
		
	}
}
