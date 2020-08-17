package com.yanghui.config.test.annotation;

import lombok.Data;

/**
 * java 自定义注解
 * https://blog.csdn.net/zt15732625878/article/details/100061528?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.edu_weight
 */
@Data
@MyAnnotationTest(age = 222222, love = false)
public class AnnotationTest {

    private String name;

    private int age;

    private boolean love;

    @Override
    public String toString() {
        return "AnnotationTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", love=" + love +
                '}';
    }

}
