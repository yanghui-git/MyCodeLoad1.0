package com.yanghui.aop;

import org.springframework.stereotype.Service;

@Service
public class TestAop {


    public void testOne(){
        System.out.println("这是一条测试方法");
    }
}