package com.yanghui.test.demo;

import java.time.Duration;

public class StaticTest {

    static {
        System.out.println("666");
    }

    public StaticTest() {
        System.out.println("888");
    }

    {
        System.out.println("777");
    }

    public static void main(String[] args){
        StaticTest staticTest=new StaticTest();
        System.out.println(Duration.ofSeconds(2));
    }
}
