package com.yanghui.dubbotwo.service;

import com.alibaba.dubbo.config.annotation.Service;

public interface DubboTwoService {
    String demoOne();

    String demoTwo(String a, String b);
}
