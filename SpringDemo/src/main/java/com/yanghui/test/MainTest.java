package com.yanghui.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanghui.test")
@ServletComponentScan(basePackages = "com.yanghui.test.filter")
@ImportResource({"classpath:/spring.xml"})
public class MainTest {
    public static void main(String[] args){
        SpringApplication.run(MainTest.class);
    }
}
