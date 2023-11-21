package com.example.demo.controller.deferredresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(()->{//开启一个线程，否则Spring容器阻塞，无法启动
            while(true) {
                if(!ObjectUtils.isEmpty(mockQueue.getCompleteOrder())) {
                    String orderNumber=mockQueue.getCompleteOrder();
                    System.out.println("返回订单处理结果："+orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
