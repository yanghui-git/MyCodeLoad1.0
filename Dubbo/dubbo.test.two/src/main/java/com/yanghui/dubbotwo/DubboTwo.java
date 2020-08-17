package com.yanghui.dubbotwo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboTwo {
    public static void main(String[] args) {
        System.out.println("开始启动dubbo two 服务");

        ClassPathXmlApplicationContext classXml = new ClassPathXmlApplicationContext("classpath:/dubbo-two.xml");

        classXml.start();

        System.out.println("dubbo two服务已启动，按任意键退出");

        try {
            System.in.read(); // 按任意键退出
            System.out.println("结束启动 dubbo  two服务");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
