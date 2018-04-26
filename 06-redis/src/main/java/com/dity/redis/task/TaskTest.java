package com.dity.redis.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
@Component
public class TaskTest {

    //@Scheduled(fixedDelay = 1000*1)
    public void test(){
        try {
            Thread.sleep(3000);
            System.out.println("当前时间:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
