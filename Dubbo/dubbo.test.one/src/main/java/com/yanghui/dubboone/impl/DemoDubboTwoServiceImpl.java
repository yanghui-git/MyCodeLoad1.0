package com.yanghui.dubboone.impl;

import com.yanghui.dubboone.service.DemoDubboTwoService;
import org.springframework.stereotype.Service;

@com.alibaba.dubbo.config.annotation.Service
public class DemoDubboTwoServiceImpl implements DemoDubboTwoService {
    public String speak() {
        return "speak hello";
    }

    public String write() {
        return "write hello";
    }
}
