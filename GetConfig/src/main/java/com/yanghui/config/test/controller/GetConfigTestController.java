package com.yanghui.config.test.controller;

import com.yanghui.config.test.dao.Student;
import com.yanghui.config.test.dao.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot读取配置文件的4种方式
 * https://blog.csdn.net/m0_37735176/article/details/99614824?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight
 */
@RestController
@RequestMapping("yh/config")
public class GetConfigTestController {

    /**
     * @Value 獲取
     */
    @Value("${student.name}")
    private String name;

    @Value("${student.age}")
    private String age;

    @Autowired
    private Teacher teacher;

    @RequestMapping(value = "/test/one", method = RequestMethod.GET)
    public void testOne() {
        System.out.println(name + age);
        System.out.println(teacher.toString());
    }


    /**
     * Environment 獲取
     */
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/test/two", method = RequestMethod.GET)
    public void testTwo() {
        System.out.println(environment.getProperty("student.name") + environment.getProperty("student.age"));

    }


    /**
     * 使用@ConfigurationProperties(prefix=“xxx”)读取 獲取
     */

    @Autowired
    private Student student;

    @RequestMapping(value = "/test/three", method = RequestMethod.GET)
    public void testThree() {
        System.out.println(student.toString());
    }


}
