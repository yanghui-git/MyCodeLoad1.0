package com.yanghui.annotation.annotationandaop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AnnotationYh {

    String name() default "yyhh";

    int age() default 20;
}
