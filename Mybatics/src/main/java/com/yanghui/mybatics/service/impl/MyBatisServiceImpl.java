package com.yanghui.mybatics.service.impl;

import com.yanghui.mybatics.mapper.StudentMapper;
import com.yanghui.mybatics.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyBatisServiceImpl implements MyBatisService {

    @Autowired
    private StudentMapper studentMapper;


    public String testOne(String name) {
        return "MyBaticsServiceImpl" + name;
    }

    public String testTwo(String name, String age) {
        return "MyBaticsServiceImpl" + name + age;
    }

    public int testFour(String name) {
        return studentMapper.deleteByName(name);
    }


}
