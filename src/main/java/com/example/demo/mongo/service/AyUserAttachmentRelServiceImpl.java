package com.example.demo.mongo.service;

import org.springframework.stereotype.Service;

import com.example.demo.mongo.dao.AyUserAttachmentRelRepository;
import com.example.demo.mongo.pojo.AyUserAttachmentRel;

import javax.annotation.Resource;

/*
* MongoDB接口实现
* ye
* 2020.11.09
* */
@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {
    @Resource
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel) {
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}

