package com.example.blogcode.javabasic.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * packageName    : com.example.blogcode.javabasic.async.config
 * fileName       : WebConfig
 * author         : tkdwk567@naver.com
 * date           : 2022/07/31
 */
@Configuration
public class WebConfig {

    @Bean("async-thread")
    public Executor asyncThread() {
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        poolExecutor.setMaxPoolSize(100);
        poolExecutor.setCorePoolSize(10);
        poolExecutor.setQueueCapacity(10);
        poolExecutor.setThreadNamePrefix("Async-");
        return poolExecutor;
    }
}
