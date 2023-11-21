package com.example.demo.mongo.pojo;

import org.springframework.data.annotation.Id;
/*
 * 用户头像关联表
 * ye
 * 2020.11.09
 * */
public class AyUserAttachmentRel {
    @Id
    private String id;
    private String userId;
    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}




