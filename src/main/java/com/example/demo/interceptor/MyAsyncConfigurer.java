package com.example.demo.interceptor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling. concurrent.ThreadPoolTaskExecutor;

/*
 * 检查@async的时候该配置是否发生作用。
 */
@Configuration  
public class MyAsyncConfigurer implements AsyncConfigurer 
{
    @Resource
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;
    
//    @Bean("kingAsyncExecutor")  
//    public ThreadPoolTaskExecutor executor() {  
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();  
//        int corePoolSize = 10;  
//        executor.setCorePoolSize(corePoolSize);  
//        int maxPoolSize = 50;  
//        executor.setMaxPoolSize(maxPoolSize);  
//        int queueCapacity = 10;  
//        executor.setQueueCapacity(queueCapacity);  
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
//        String threadNamePrefix = "kingDeeAsyncExecutor-";  
//        executor.setThreadNamePrefix(threadNamePrefix);  
//        executor.setWaitForTasksToCompleteOnShutdown(true);  
//        // 使用自定义的跨线程的请求级别线程工厂类19         int awaitTerminationSeconds = 5;  
//        executor.setAwaitTerminationSeconds(5);  
//        executor.initialize();  
//        return executor;  
//    }  
//  
//    @Override  
//    public Executor getAsyncExecutor() {  
//        return executor();  
//    }  
  
	  //@Override  
	  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		  
//	      return (ex, method, params)
//	    		  -> ErrorLogger.getInstance().log(String.format("执行异步任务'%s'", method), ex);  
		  return new CustomAsyncUncaughtExceptionHandler();
	  }
	
	  //@Override  
	  public Executor getAsyncExecutor() {
		  System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	      return myThreadPoolTaskExecutor;  
	  }
	  
	  /**
	   * 自定义异步异常处理器
	   */
	  static class CustomAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
	
	      @Override
	      public void handleUncaughtException(Throwable ex, Method method, 
	    		  Object... params) {
	          //打印日志等，实际项目以具体业务需求来处理
	          System.out.println("AsyncUncaughtExceptionHandler.handleUncaughtException...........");
	      }
	  }

	
}

