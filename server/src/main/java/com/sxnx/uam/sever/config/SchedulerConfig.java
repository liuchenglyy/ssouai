package com.sxnx.uam.sever.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
* 公司：普元金融—F2E
* 文件名：SchedulerConfig
* 作者：duanwenhong(段文红)
* 邮件：duanwh@primeton.com
* 时间：2020-2-10 10:45
* 描述：定时任务配置-线程池的方式配置
*/
@Configuration
public class SchedulerConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(4);
        //最大核心线程数
        executor.setMaxPoolSize(10);
        //设置队列中等待被调度处理的任务的数量
        executor.setQueueCapacity(8);
        executor.initialize();
        return executor;
    }

}