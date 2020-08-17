package com.yanghui.websocket;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanghui.websocket")
public class WebsocketMain {
    public static void main(String[] args){

        SpringApplication.run(WebsocketMain.class);
    }

}
