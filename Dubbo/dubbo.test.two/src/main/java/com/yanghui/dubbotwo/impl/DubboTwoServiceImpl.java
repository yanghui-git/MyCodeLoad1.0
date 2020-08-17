package com.yanghui.dubbotwo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanghui.dubbotwo.service.DubboTwoService;


@Service
public class DubboTwoServiceImpl implements DubboTwoService {

    public String demoOne() {
        return "dubbo two service";
    }


    public String demoTwo(String a, String b) {
        return "dubbo two " + a + b;
    }
}
