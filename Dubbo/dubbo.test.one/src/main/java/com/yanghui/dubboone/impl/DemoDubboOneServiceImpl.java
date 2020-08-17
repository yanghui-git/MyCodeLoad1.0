package com.yanghui.dubboone.impl;

import com.yanghui.dubboone.service.DemoDubboOneService;
import org.springframework.stereotype.Service;

@Service
public class DemoDubboOneServiceImpl implements DemoDubboOneService {
    public String testOne() {
        return "dubbo test one ";
    }

    public String testTwo(String a, String b) {
        return "dubbo test two" + a + b;
    }
}
