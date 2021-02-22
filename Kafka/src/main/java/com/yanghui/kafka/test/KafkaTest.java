package com.yanghui.kafka.test;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;

/**
 * kafka produce
 */
public class KafkaTest {

    public static String url = "10.20.178.137:9092";

    public static void main(String[] args) {
        produceTest();
        consumerTest();

    }

    public static void produceTest() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", url);
        // 将ProducerRecord中的key和value转换成字节
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        producer.send(new ProducerRecord<String, String>("KafkaTest", "hello kafka"));
        producer.close();

    }

    public static void consumerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", url);
        props.put("group.id", "test");
        // 将ProducerRecord中的key和value转换成字节
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 设置topic，注意需要跟生产者发送消息的topic保持一致
        consumer.subscribe(Collections.singleton("KafkaTest"));
        while (true) {
            ConsumerRecords<String, String> records = (ConsumerRecords<String, String>) consumer.poll(1000);
            System.out.println(JSON.toJSONString(records));
        }

    }

    @Test
    public void test() throws Exception {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", url);
        // 将ProducerRecord中的key和value转换成字节
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        boolean send = true;
     //   while (send) {
            Thread.sleep(3000L);
            producer.send(new ProducerRecord<String, String>("consumerTest", "hello kafka"));
    //    }
        producer.close();
    }
}
