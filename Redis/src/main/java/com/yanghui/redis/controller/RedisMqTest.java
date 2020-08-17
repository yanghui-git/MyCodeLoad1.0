package com.yanghui.redis.controller;

import com.yanghui.redis.util.RedisUtil;


/**
 * Redis 做缓存队列demo
 */
public class RedisMqTest {

    public static void main(String[] args) {

       // new Produce().start();
        new Consumer("消费者1").start();
        new Consumer("消费者2").start();
    }

}

/**
 * 生产端
 */
class Produce extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000L);
                String ss = String.valueOf(System.currentTimeMillis());
                System.out.println("向redis插入数据中。。。。。。" + ss);
                RedisUtil.lpush("测试队列", ss);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    String consumerName;

    public Consumer(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000L);
                System.out.println(consumerName+"redis消费数据中" + RedisUtil.rpop("测试队列"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
