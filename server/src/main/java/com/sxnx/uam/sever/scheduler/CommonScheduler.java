package com.sxnx.uam.sever.scheduler;

import com.sxnx.uam.model.mapper.AuthTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 公司：普元金融—F2E
 * 文件名：CommonScheduler
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:29
 * 描述：通用的定时任务调度
 */
@Component
@EnableAsync
public class CommonScheduler {

    private static final Logger log = LoggerFactory.getLogger(CommonScheduler.class);

    @Autowired
    private AuthTokenMapper authTokenMapper;

    //剔除掉那些已经失效的token     cron=建议一个月一次
    @Scheduled(cron = "0 0 0 28 * ?") //每个月的 28 日 00:00:00 运行
    @Async("taskExecutor")
    public void deleteInvalidateToken() {
        try {
            log.info("--剔除掉那些已经失效的token--定时任务调度开启--");
            authTokenMapper.deleteUnactiveToken();

            //交给运维-自动抽取出那些失效的token，进行转移 (etl)
        } catch (Exception e) {
            log.error("--剔除掉那些已经失效的token--发生异常：", e.fillInStackTrace());
        }
    }


}















