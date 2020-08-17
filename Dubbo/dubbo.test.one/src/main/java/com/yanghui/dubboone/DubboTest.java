package com.yanghui.dubboone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = "com.yanghui.dubboone")
@ImportResource(locations = {"classpath:spring.xml"})
public class DubboTest {
  public static void main(String[] args){
      SpringApplication.run(DubboTest.class);
  }
}
