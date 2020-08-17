package com.yanghui.config.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/common.properties")
@ComponentScan(basePackages = "com.yanghui.config.test")
public class GetConfigMain {

    public static void main(String[] args){
        SpringApplication.run(GetConfigMain.class);
    }
}
