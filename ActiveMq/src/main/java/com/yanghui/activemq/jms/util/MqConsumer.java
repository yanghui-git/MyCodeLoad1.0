package com.yanghui.activemq.jms.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class MqConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (!(message instanceof ObjectMessage)) {
            return;
        }

        try {
            Object object = ((ObjectMessage) message).getObject();
            System.out.println(object.toString());
        } catch (JMSException e) {
            System.out.println(e.getMessage());
        }
    }

}
