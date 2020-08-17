package com.yanghui.config.test.dao;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentCommon {

    @Value("${student.common.name}")
    private String name;

    @Value("${student.common.age}")
    private String age;

    @Override
    public String toString() {
        return "StudentCommon{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
