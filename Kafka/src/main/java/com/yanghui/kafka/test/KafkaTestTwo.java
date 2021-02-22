package com.yanghui.kafka.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;


/**
 *  https://www.cnblogs.com/xingyunblog/p/10536984.html kafka入门demo
 */
public class KafkaTestTwo {
    static String brokerList = "10.20.178.137:9092";
    static String groupId = "yh";
    static String groupIdTwo = "yh-Two";
    static String topic = "yh.kafka.test";

    public static void main(String[] args) {
        //发送消息 定时任务
        ExecutorService serviceProduce=Executors.newScheduledThreadPool(3);
        ((ScheduledExecutorService) serviceProduce).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",new Date());
                KafkaProduceTest.Create();
            }
        }, 3000L,3000L, TimeUnit.MILLISECONDS);


        //消费
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Set<Callable<String>> calables = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            calables.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    KafkaConsumerTest.Receive();
                    return null;
                }
            });
        }

        try {
            executorService.invokeAll(calables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class KafkaProduceTest {
        //配置生产者客户端参数
        //将配置序列化
        public static void Create() {
            Properties properties = new Properties();
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("bootstrap.servers", brokerList);
            //创建kafka示例
            KafkaProducer kafkaProducer = new KafkaProducer(properties);
            //构建待发送的消息
            ProducerRecord producerRecord = new ProducerRecord(topic, "this is test");
            //发送消息
            kafkaProducer.send(producerRecord);
            System.out.println("kafakaProduce send message success");
            //关闭生产者客户端实例
          //  kafkaProducer.close();
        }
    }


    static class KafkaConsumerTest {
        public static void Receive() throws Exception {
            //设置消费者名称
            //将属性值反序列化
            Properties properties = new Properties();
            properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("bootstrap.servers", brokerList);
            properties.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.StickyAssignor");
            properties.put("group.id", groupId);
            //创建一个消费者客户端实例
            org.apache.kafka.clients.consumer.KafkaConsumer kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer(properties);
            //订阅主题
            kafkaConsumer.subscribe(Collections.singleton(topic));
            //循环消费消息
            try {
                while (true) {
                    ConsumerRecords<Object, Object> consumerRecords = (ConsumerRecords) kafkaConsumer.poll(Duration.ofSeconds(2).getSeconds());

                    for (ConsumerRecord<Object, Object> record : consumerRecords)
                        System.out.println("kafka consumer value" + record.value().toString() + " " + groupId);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}