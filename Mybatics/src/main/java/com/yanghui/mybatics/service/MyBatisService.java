package com.yanghui.mybatics.service;

import com.yanghui.mybatics.dao.DataSet;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface MyBatisService {

    String testOne(String name);

    String testTwo(String name, String age);

    List<DataSet> listAll(String tenantId);

    int testFour(String name);
}
