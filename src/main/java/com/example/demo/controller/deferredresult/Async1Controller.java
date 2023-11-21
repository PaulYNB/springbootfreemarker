package com.example.demo.controller.deferredresult;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class Async1Controller {    
    
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    
    @RequestMapping("/order")
    public DeferredResult<String> order() {
    	
        System.out.println("主线程开始");
        String orderNumber= "88";
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result=new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        System.out.println("主线程返回");
        return result;
    } 
}
