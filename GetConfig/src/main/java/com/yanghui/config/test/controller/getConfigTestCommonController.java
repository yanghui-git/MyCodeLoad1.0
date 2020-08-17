package com.yanghui.config.test.controller;

import com.yanghui.config.test.dao.Student;
import com.yanghui.config.test.dao.StudentCommon;
import com.yanghui.config.test.dao.StudentTeacher;
import com.yanghui.config.test.dao.TeacherCommon;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * SpringBoot读取配置文件的4种方式
 * https://blog.csdn.net/m0_37735176/article/details/99614824?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight
 */
@RestController
@RequestMapping("yh/config/common")
public class getConfigTestCommonController {


    /**
     * @Value  @PropertySource("classpath:/common.properties") 倒入配置文件
     */

    @Autowired
    StudentCommon studentCommon;

    @RequestMapping(value = "/test/one", method = RequestMethod.GET)
    public void testOne() {
       System.out.println(studentCommon.toString());
    }

@Test
    public void test(){
     testOne();
    }

    /**
     *  environment note 可以直接獲取common.properties
     */

    @Autowired
    Environment environment;

    @RequestMapping(value = "/test/two", method = RequestMethod.GET)
    public void testTwo() {
        System.out.println(environment.getProperty("student.common.name")+environment.getProperty("student.common.age")+environment.getProperty("test.env"));
    }



    /**
     * 使用@ConfigurationProperties(prefix=“xxx”)读取 獲取   1 優先獲取application.yml 配置  2必須要有 set方法
     */
    @Autowired
    private TeacherCommon teacherCommon;

    @RequestMapping(value = "/test/three", method = RequestMethod.GET)
    public void testThree() {
        System.out.println(teacherCommon.toString());
    }


    /**
     * 綜合測試 兩個配置文件結合
     */
    @Autowired
    private StudentTeacher studentTeacher;

    @RequestMapping(value = "/test/four", method = RequestMethod.GET)
    public void testFour() {
        System.out.println(studentTeacher.toString());
    }



    /**
     * java.util.properties 獲取
     */

    @Test
    public void tttt(){
        try {
            Properties properties =new Properties();
            properties.load(new FileInputStream(new File("D:\\桌面\\YangTest\\GetConfigTest\\src\\main\\resources\\common.properties")));
            System.out.println(properties.getProperty("com.teacher.name"));
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
    }
}
