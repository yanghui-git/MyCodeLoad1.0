package com.yanghui.activemq.jms;

public class MqConfig {

    /**
     * jms 整合mq 配置
     */

    public static String brokerUrl = "failover:(tcp://10.1.61.174:61616)";

    public static String mqName="yh.test.mq";

}
