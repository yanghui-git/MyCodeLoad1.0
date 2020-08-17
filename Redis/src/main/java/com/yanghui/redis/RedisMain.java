package com.yanghui.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanghui.redis")
public class RedisMain {
    public static void main(String[] args){
        SpringApplication.run(RedisMain.class);

        try {
            Thread.currentThread().sleep(30000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
