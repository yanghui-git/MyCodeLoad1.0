package com.yanghui.mybatics.service.impl;

import com.yanghui.mybatics.dao.DataSet;
import com.yanghui.mybatics.mapper.DataSetMapper;
import com.yanghui.mybatics.mapper.StudentMapper;
import com.yanghui.mybatics.service.MyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBatisServiceImpl implements MyBatisService {

    @Autowired
    private DataSetMapper dataSetMapper;

    @Autowired
    private StudentMapper studentMapper;


    public String testOne(String name) {
        return "MyBaticsServiceImpl" + name;
    }

    public String testTwo(String name, String age) {
        return "MyBaticsServiceImpl" + name + age;
    }

    public List<DataSet> listAll(String tenantId) {
        System.out.println("111");
        return dataSetMapper.listAll(tenantId);
    }

    public int testFour(String name) {
        return studentMapper.deleteByName(name);
    }


}
