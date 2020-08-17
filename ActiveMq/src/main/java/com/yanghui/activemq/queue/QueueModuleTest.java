package com.yanghui.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Queue 点对点
 */
public class QueueModuleTest {
    public static String userName = "admin";
    public static String userPassword = "Uyun_123";
    public static String mqUrl = "failover:(tcp://10.1.200.204:61616)";

    //public static String mqUrl = "http://10.1.200.204:8161";
    public static void main(String[] args) {

        try {
            QueueProduceTest();
            System.out.println("test success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            QueueConsumerTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * queue 点对点模式 生产者
     */
    public static void QueueProduceTest() throws Exception {
        //1、创建一个连接工厂对象，需要指定服务的ip及端口。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, userPassword, mqUrl);
        //2、使用工厂对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        //3、开启连接，调用Connection对象的start方法
        connection.start();
        //4、创建一个Session对象。
        //第一个参数：是否开启事务。如果true开启事务，第二个参数无意义。一般不开启事务false。
        //第二个参数：应答模式。自动应答或者手动应答。一般自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用queue
        Queue queue = session.createQueue("activemq test 6666");
        //6、使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(queue);
        //7、创建一个Message对象，可以使用TextMessage。
		/*TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("hello Activemq");*/
        TextMessage textMessage = session.createTextMessage("hello world");
        //8、发送消息
        producer.send(textMessage);
        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * queue点对点 消费者
     */
    public static void QueueConsumerTest() throws Exception {
        //创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, userPassword, mqUrl);
        //创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个Destination对象。queue对象
        Queue queue = session.createQueue("activemq test 6666");
        //使用Session对象创建一个消费者对象。
        MessageConsumer consumer = session.createConsumer(queue);
        //接收消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //打印结果
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待接收消息
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
