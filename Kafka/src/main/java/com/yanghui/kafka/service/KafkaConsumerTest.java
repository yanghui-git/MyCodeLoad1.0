package com.yanghui.kafka.service;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerTest {

    /**
     *  group id 表示 同group下 此消息只会被消费一次
     * @param consumerRecord
     */
    @KafkaListener(topics = "consumerTest", groupId = "one")
    public void consumer1(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("one" + consumerRecord.value());
    }

    @KafkaListener(topics = "consumerTest", groupId = "one")
    public void consumer4(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("one one" + consumerRecord.value());
    }

   /* @KafkaListener(topics = "consumerTest", groupId = "two")
    public void consumer2(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("two" + consumerRecord.value());
    }

    @KafkaListener(topics = "consumerTest", groupId = "three")
    public void consumer3(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("three" + consumerRecord.value());
    }*/
}
