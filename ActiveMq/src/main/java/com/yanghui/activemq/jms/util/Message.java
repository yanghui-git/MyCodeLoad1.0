package com.yanghui.activemq.jms.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {


    String name;

    String age;
}
