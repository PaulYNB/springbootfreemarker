package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AsyncService;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;
//    @Autowired
//    private TaskExecutor myThreadPoolTaskExecutor;

    /**
     * 测试 异步注解：@Async
     * AsyncConfigurer中配置了AsyncUncaughtExceptionHandler，该handler
     * 会处理async业务层一路抛出来的Exception
     * @return
     */
    @RequestMapping("/asyncT")
    @ResponseBody
    public String thread() throws Exception {
        //调用service层的任务
        asyncService.executeAsync();
        System.out.println("当前运行的处理线程名称：" + Thread.currentThread().getName());
        return "async test success";
    }
}

