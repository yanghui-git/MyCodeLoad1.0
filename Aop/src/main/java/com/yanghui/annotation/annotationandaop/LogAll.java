package com.yanghui.annotation.annotationandaop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
@Aspect
public class LogAll {


    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("execution(* com.yanghui.annotation.annotationandaop.TestAll.sayHello(*,*))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("1111");
        /**
         * 打印方法参数  s是指原方法的参数
         */
        for (Object object : proceedingJoinPoint.getArgs()) {
            System.out.println(object);
        }
        proceedingJoinPoint.proceed();

        System.out.println("2222");
    }


//    @Around("execution(* com.yanghui.annotation.annotationandaop.TestAll.sayNB())")
//    public void aroundTwo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//        /**
//         * AOP中获取自定义注解的属性值
//         *
//         *  https://blog.csdn.net/wushucheng18/article/details/80582034?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-6.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-6.edu_weight
//         *
//         */
//        Signature signature = proceedingJoinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//
//        AnnotationYh annotationYh = method.getAnnotation(AnnotationYh.class);
//        if (annotationYh != null) {
//            System.out.println(annotationYh.name());
//            System.out.println(annotationYh.age());
//        }
//
//
//        proceedingJoinPoint.proceed();
//        System.out.println("iiiii");
//
//    }




    @Around("@annotation(com.yanghui.annotation.annotationandaop.AnnotationYh)")
    public void aroundThree(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        /**
         * AOP中获取自定义注解的属性值
         *
         *  https://blog.csdn.net/wushucheng18/article/details/80582034?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-6.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-6.edu_weight
         *
         */
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        AnnotationYh annotationYh = method.getAnnotation(AnnotationYh.class);
        if (annotationYh != null) {
            System.out.println(annotationYh.name());
            System.out.println(annotationYh.age());
        }


        proceedingJoinPoint.proceed();
        System.out.println("iiiii");
    }

}
