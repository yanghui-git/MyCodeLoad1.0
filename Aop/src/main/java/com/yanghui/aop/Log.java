package com.yanghui.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class Log {


    @Before("execution(* com.yanghui.aop.TestAop.testOne())")
    public void before(){
        System.out.println("方法执行前");
    }


    @After("execution(* com.yanghui.aop.TestAop.testOne())")
    public void after(){
        System.out.println("方法执行后");
    }


    /**
     * 环绕通知
     * @param pjp
     */
    @Around("execution(* com.yanghui.aop.TestAop.testOne())")
    public void around(ProceedingJoinPoint pjp){

       System.out.println("1111111");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        System.out.println("222");
    }

}
