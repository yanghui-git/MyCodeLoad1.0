package com.yanghui.config.test.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentTeacher {

    @Value("${student.teacher.name}")
    String teacherName;

    @Override
    public String toString() {
        return "StudentTeacher{" +
                "teacherName='" + teacherName + '\'' +
                '}';
    }
}
