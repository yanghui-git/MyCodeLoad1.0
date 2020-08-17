package com.yanghui.dubboone.controller;

import com.yanghui.dubboone.service.DemoDubboOneService;
import com.yanghui.dubboone.service.DemoDubboTwoService;
import com.yanghui.dubbotwo.service.DubboTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("dubbo/yanghui")
public class DubboTestController {

    @Autowired
    private DemoDubboOneService demoDubboOneService;

    @Autowired
    private DubboTwoService dubboTwoService;

    @Autowired
    private DemoDubboTwoService demoDubboTwoService;

    @RequestMapping(value = "/test/one", method = RequestMethod.GET)
    public void testOne() {
        System.out.println(demoDubboOneService.testOne());
        System.out.println(demoDubboOneService.testTwo("111", "222"));
    }

    @RequestMapping(value = "/test/really", method = RequestMethod.GET)
    public void testTwo() {
        System.out.println(dubboTwoService.demoOne());
        System.out.println(dubboTwoService.demoTwo("333", "444"));
    }


    @RequestMapping(value = "/test/six", method = RequestMethod.GET)
    public void testSix() {
        System.out.println(demoDubboTwoService.speak());
        System.out.println(demoDubboTwoService.write());
    }

}
