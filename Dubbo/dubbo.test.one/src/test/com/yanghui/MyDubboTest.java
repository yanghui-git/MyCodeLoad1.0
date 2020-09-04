package com.yanghui;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.yanghui.dubboone.service.DemoDubboOneService;
import org.junit.Test;

public class MyDubboTest {

    @Test
    public void test(){
        //dubbo自定义配置
        ReferenceConfig referenceConfig =new ReferenceConfig();

        //应用信息
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("dubbo-test-one");
        referenceConfig.setApplication(applicationConfig);

        //注册中心
        RegistryConfig registryConfig =new RegistryConfig();
        registryConfig.setAddress("zookeeper://10.1.200.204:2181");
        registryConfig.setTimeout(3000);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setCheck(false);

        //调用接口
        referenceConfig.setInterface(DemoDubboOneService.class);


        //直接调用
        DemoDubboOneService demoDubboOneService= (DemoDubboOneService) referenceConfig.get();
        String str=demoDubboOneService.testOne();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(str);

    }
}
