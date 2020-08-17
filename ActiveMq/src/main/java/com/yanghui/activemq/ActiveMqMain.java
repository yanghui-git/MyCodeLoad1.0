package com.yanghui.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanghui.activemq")
@ImportResource({"classpath:/spring.xml"})
public class ActiveMqMain {
    public static void main(String[] args){
        System.out.println("启动ActiveMq");
        SpringApplication.run(ActiveMqMain.class);
    }
}
