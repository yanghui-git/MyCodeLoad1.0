package com.yanghui.mybatics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanghui.mybatics")
@ImportResource(locations = {"classpath:spring-context.xml"})
@EnableTransactionManagement
public class MainTest {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class);
    }
}
