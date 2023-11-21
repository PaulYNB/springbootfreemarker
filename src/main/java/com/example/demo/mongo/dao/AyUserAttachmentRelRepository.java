package com.example.demo.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongo.pojo.AyUserAttachmentRel;


/*
* 用户附件Repository
* 用jpa实现MongoDB和java的连接
* 2020.11.09
* */
public interface AyUserAttachmentRelRepository extends 
	MongoRepository<AyUserAttachmentRel, String> {

}

