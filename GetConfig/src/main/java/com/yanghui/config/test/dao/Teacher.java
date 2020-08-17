package com.yanghui.config.test.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Teacher {


    @Value("${teacher.name}")
    private String name;

    @Value("${teacher.age}")
    private String age;


    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
