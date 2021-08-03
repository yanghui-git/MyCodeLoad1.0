package com.yanghui.mybatics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ClassUtils;

@SpringBootApplication
public class MainTest {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class);
        System.out.println(ClassUtils.isPresent("com.yanghui.mybatics.config.MybatisConfig", null));
    }
}
