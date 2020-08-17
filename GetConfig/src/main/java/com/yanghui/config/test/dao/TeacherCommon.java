package com.yanghui.config.test.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.teacher")
public class TeacherCommon {

    String name;

    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TeacherCommon{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
