package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void executeAsync() throws Exception {
    	System.out.println("start executeAsync");
        try {
            Thread.sleep(5000);
            System.out.println("当前运行的异步线程名称：" + Thread.currentThread().getName());
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println("end executeAsync");
        //throw new Exception("AsyncServiceImpl.executeAsync() exception......");
    }
}

