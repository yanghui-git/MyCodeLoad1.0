package com.yanghui.annotation;

public class Mytest {

    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        Class classss = annotationTest.getClass();

        //判断该类是否配置了 MyAnnotationTest 注解
        System.out.println(classss.isAnnotationPresent(MyAnnotationTest.class));

        ////返回所有的注解
        System.out.println(classss.getAnnotations());

        if (classss.isAnnotationPresent(MyAnnotationTest.class)) {
            //获取指定的注解
            MyAnnotationTest myAnnotationTest = (MyAnnotationTest) classss.getAnnotation(MyAnnotationTest.class);
            System.out.println(myAnnotationTest.age() + myAnnotationTest.name() + myAnnotationTest.love());
        }

    }
}
