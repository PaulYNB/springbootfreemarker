package com.example.demo.controller.deferredresult.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class DeferredResultJMSController {

    //@Autowired  //to be open
    private JmsTemplate jmsTemplate;

    //@Autowired  //to be open
    private Destination responseQueue;

    @GetMapping("/request/{id}")
    public DeferredResult<String> processRequest(
    		@PathVariable("id") String requestId) throws JMSException {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        // 发送JMS消息到MQ队列
        jmsTemplate.send("requestQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(requestId);
                message.setJMSReplyTo(responseQueue);
                return message;
            }
        });

        // 创建一个消息消费者来接收MQ的响应
        MessageConsumer consumer = jmsTemplate.getConnectionFactory().createConnection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE)
                .createConsumer(responseQueue);

        // 使用DeferredResult的回调函数来处理MQ的响应
        deferredResult.onTimeout(() -> {
            // 请求超时时的处理
            deferredResult.setErrorResult("Request timeout");
            try {
                consumer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        deferredResult.onCompletion(() -> {
            // 请求完成时的处理
            try {
                consumer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        // 启动一个新线程来等待MQ的响应并设置结果给DeferredResult
        new Thread(() -> {
            try {
                // 等待MQ的响应
                Message response = consumer.receive();

                // 处理MQ的响应
                if (response instanceof TextMessage) {
                    String result = ((TextMessage) response).getText();
                    deferredResult.setResult(result);
                } else {
                    deferredResult.setErrorResult("Invalid response type");
                }
            } catch (JMSException e) {
                deferredResult.setErrorResult("Error while waiting for response");
            }
        }).start();

        return deferredResult;
    }
}
