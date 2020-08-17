package com.yanghui.activemq.Service;

import com.yanghui.activemq.jms.util.Message;
import com.yanghui.activemq.jms.util.MessageTwo;
import com.yanghui.activemq.jms.util.MqProduce;
import javafx.beans.binding.ObjectExpression;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/yanghui/mq")
public class ActiveMqTestService {

    @Autowired
    private MqProduce mqProduce;

    @RequestMapping(value = "/test/one")
    public String testOne() {
        return "test success";
    }

    @RequestMapping(value = "/test/two", method = RequestMethod.GET)
    public void testTwo() {
        Message message = new Message();
        message.setAge("20");
        message.setName("yh");
        mqProduce.sendMsgOne(message);
    }


    @RequestMapping(value = "/test/three", method = RequestMethod.GET)
    public void testThree() {
        MessageTwo messageTwo = new MessageTwo();
        messageTwo.setMsg("22222");
        mqProduce.sendMsgTwo(messageTwo);
    }

}
