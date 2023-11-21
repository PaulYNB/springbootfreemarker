package com.example.demo.mongo.service;

import org.springframework.stereotype.Service;

import com.example.demo.mongo.pojo.AyUserAttachmentRel;

/*
* 用户附件服务层
* ye
* 2020.11.09
* */
@Service
public interface AyUserAttachmentRelService {
    AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel);
}

