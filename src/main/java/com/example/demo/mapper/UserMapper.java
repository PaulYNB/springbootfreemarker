package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.User;

@Mapper
//@Repository
public interface UserMapper {
	/**
	 * 查询所有用户
	 */
	public List<User> findAll();
}
