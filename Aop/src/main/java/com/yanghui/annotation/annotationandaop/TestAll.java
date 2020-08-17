package com.yanghui.annotation.annotationandaop;

import lombok.Data;
import org.junit.Test;
import org.springframework.stereotype.Service;


@Service
@Data
public class TestAll {

    String name;

    String age;

    public void sayHello(String name, int age) {
        System.out.println("this is all test" + name + age);
    }


    @AnnotationYh(name = "123")
    public void sayNB() {
        System.out.println("NB AAAAAAA");
    }


    @Test
    public void one() {
        Class cla = new TestAll().getClass();
        if (cla.isAnnotationPresent(AnnotationYh.class)) {
            AnnotationYh annotationYh = (AnnotationYh) cla.getAnnotation(AnnotationYh.class);
            System.out.println(annotationYh.age());
            System.out.println(annotationYh.name());
        }
    }
}
