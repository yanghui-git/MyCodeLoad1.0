package com.yanghui.aop.controller;

import com.yanghui.annotation.annotationandaop.AnnotationYh;
import com.yanghui.annotation.annotationandaop.TestAll;
import com.yanghui.aop.TestAop;
import com.yanghui.aop.TestAopTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aop/yh")
public class AopTestController {

    @Autowired
    TestAop testAop;

    @Autowired
    TestAopTwo testAopTwo;

    @RequestMapping(value = "/test/one", method = RequestMethod.GET)
    public void one() {
        testAop.testOne();
    }


    @RequestMapping(value = "/test/two", method = RequestMethod.GET)
    public void two() {
        testAopTwo.testOne();
    }


    /**
     * 综合测试 注解+aop
     */
    @Autowired
    TestAll testAll;

    @RequestMapping(value = "/test/three", method = RequestMethod.GET)
    public void three() {
        testAll.sayHello("testttttttt", 22222);
    }


    @RequestMapping(value = "/test/four", method = RequestMethod.GET)
    public void four() {
        testAll.sayNB();
    }
}
