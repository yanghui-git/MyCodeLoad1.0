package com.yanghui.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Service;
import org.junit.Test;

import javax.jms.*;

/**
 * topic模式
 */
public class TopicModuleTest {
    public static String userName = "admin";
    public static String userPassword = "Uyun_123";
    public static String mqUrl = "failover:(tcp://10.1.200.204:61616)";

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                try {
                    TopicProduceTest();
                } catch (Exception e) {
                }
            }

            ;
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TopicConsumerTestOne();
                } catch (Exception e) {
                }
            }

            ;
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TopicConsumerTestTwo();
                } catch (Exception e) {
                }
            }

            ;
        }.start();
    }

    /**
     * topic 模式生产者
     */
    public static void TopicProduceTest() throws Exception {
//1、创建一个连接工厂对象，需要指定服务的ip及端口。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, userPassword, mqUrl);
        //2、使用工厂对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        //3、开启连接，调用Connection对象的start方法
        connection.start();
        //4、创建一个Session对象。
        //第一个参数：是否开启事务。如果true开启事务，第二个参数无意义。一般不开启事务false。
        //第二个参数：应答模式。自动应答或者手动应答。一般自动应答。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用topic
        Topic topic = session.createTopic("test-topic-666");
        //6、使用Session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(topic);
        //7、创建一个Message对象，可以使用TextMessage。
		/*TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("hello Activemq");*/
        TextMessage textMessage = session.createTextMessage("hello world---topic");
        //8、发送消息
        producer.send(textMessage);
        //9、关闭资源
        producer.close();
        session.close();
        connection.close();

    }

    /**
     * topic 消费者01
     */
    public static void TopicConsumerTestOne() throws Exception {
        //创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, userPassword, mqUrl);
        //创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个Destination对象。topic对象
        Topic topic = session.createTopic("test-topic-666");
        //使用Session对象创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //接收消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String result = textMessage.getText() == null ? "666" : textMessage.getText();
                    System.out.println(result + "01");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//等待接收消息
        System.out.println("topic的消费端01。。。。。");
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * topic 消费者02
     */
    public static void TopicConsumerTestTwo() throws Exception {
        //创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, userPassword, mqUrl);
        //创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个Destination对象。topic对象
        Topic topic = session.createTopic("test-topic-666");
        //使用Session对象创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(topic);
        //接收消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String result = textMessage.getText() == null ? "666" : textMessage.getText();
                    System.out.println(result + "02");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//等待接收消息
        System.out.println("topic的消费端02。。。。。");
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

}
