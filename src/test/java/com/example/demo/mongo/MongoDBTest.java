package com.example.demo.mongo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.HelloSpringBootApplication;
import com.example.demo.mongo.pojo.AyUserAttachmentRel;
import com.example.demo.mongo.service.AyUserAttachmentRelService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloSpringBootApplication.class)
public class MongoDBTest {
	
	@Resource
	AyUserAttachmentRelService ayUserAttachmentRelService;
	
	@Test
	public void testMongoDB() {
        AyUserAttachmentRel ayUserAttachmentRel = new AyUserAttachmentRel();
        ayUserAttachmentRel.setId("123456");
        ayUserAttachmentRel.setUserId("666");
        ayUserAttachmentRel.setFileName("个人简历.doc");
        AyUserAttachmentRel ayUserAttachmentRel1 = ayUserAttachmentRelService.save(ayUserAttachmentRel);
        System.out.println(ayUserAttachmentRel1 + "保存成功！");
    }

}
