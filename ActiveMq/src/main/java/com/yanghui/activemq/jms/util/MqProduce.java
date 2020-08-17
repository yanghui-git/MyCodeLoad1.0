package com.yanghui.activemq.jms.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Service
public class MqProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 序列化后的消息
     *
     * @param message
     */
    public void sendMsgOne(com.yanghui.activemq.jms.util.Message message) {

        jmsTemplate.send(jmsTemplate.getDefaultDestination(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(message);
            }
        });
    }


    public void sendMsgTwo(MessageTwo message) {
        jmsTemplate.convertAndSend(message);
    }


    @Test
    public void test(){

    }
}


