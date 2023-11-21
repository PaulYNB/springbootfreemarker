package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池配置类，用于处理异步请求/调用。
 * 注解@EnableAsync是开启异步，开启异步后才能完美使用@Asynz进行异步调用；
 * 注解@Bean是将线程池对象交给spring容器管理，在创建WebAsyncTask（包装了callable）对象的时候可传入一个TaskExecutor
 * tasks ：每秒的任务数
 * taskcost：每个任务花费时间，假设为0.1s
 * responsetime：系统允许容忍的最大响应时间，假设为1s
 *
 */
@Configuration
//@EnableAsync // 开启异步
public class ThreadPoolConfig {

    /**
     *   每秒需要多少个线程处理?
     */
    private int corePoolSize = 3;

    /**
     * 线程池维护线程的最大数量
     */
    private int maxPoolSize = 3;

    /**
     * 缓存队列
     */
    private int queueCapacity = 10;

    /**
     * 允许的空闲时间
     * 默认为60
     */
    private int keepAlive = 100;

    @Bean(name = "myThreadPoolTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(queueCapacity);
        // 设置允许的空闲时间（秒）
		// executor.setKeepAliveSeconds(keepAlive);
        // 设置默认线程名称
        executor.setThreadNamePrefix("paulyangthread-");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}

