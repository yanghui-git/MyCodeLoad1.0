package com.yanghui.config.test.annotation;


import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotationTest {

    String name() default "Annotation test";

    int age() default 20;

    boolean love() default false;

}
