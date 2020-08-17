package com.yanghui.test.controller;

import com.yanghui.test.filter.ThreadLoacalUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/yh/test")
public class ThreadLocalControllerTest {


    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public void testOne() {
      System.out.println(ThreadLoacalUtil.get().getAge());
      System.out.println(ThreadLoacalUtil.get().getName());
      System.out.println(ThreadLoacalUtil.get().getDate());
    }
}
