package com.yanghui.mybatics.test;


import com.yanghui.mybatics.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("yh/mybatis")
@Transactional
public class MybatisTestService {

    @Autowired
    private MyBatisService myBatisService;


    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String testOne(@RequestParam(value = "name", required = false) String name) {
        System.out.println(name);
        return name + "    666";
    }

    @RequestMapping(value = "/two", method = RequestMethod.POST)
    public String testTwo(@RequestParam(value = "name", required = false) String name) {
        return myBatisService.testOne("6666");
    }

    @RequestMapping(value = "/three", method = RequestMethod.POST)
    public Object testThree(@RequestParam(value = "tenantId", required = false) String tenantId) {
        // System.out.println(JSON.toJSONString(myBatisService.listAll(tenantId)));
        System.out.println("222222");
        return 666;

    }

    @RequestMapping(value = "/four", method = RequestMethod.POST)
    public Object testFour(@RequestParam(value = "name", required = true) String name) {
        // System.out.println(JSON.toJSONString(myBatisService.listAll(tenantId)));
        System.out.println("222222");
        int a = myBatisService.testFour(name);
        a = 1 / 0;
        return a;
    }

}
