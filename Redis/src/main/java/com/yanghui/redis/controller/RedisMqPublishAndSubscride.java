package com.yanghui.redis.controller;

import com.yanghui.redis.util.RedisUtil;
import redis.clients.jedis.JedisPubSub;

/**
 * Redis 发布订阅模式 备注： 先订阅再发布
 */
public class RedisMqPublishAndSubscride {


    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                subscride("订阅发布");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publish();
            }
        }.start();
    }

    //简单发布一个消息
    public static void publish() {
        RedisUtil.getRedisTwo().publish("订阅发布", "这是test");
        System.out.println("发布了一条消息aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }


    //订阅者
    public static void subscride(final String chanleTest) {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                //   super.onMessage(channel, message);
                if (chanleTest.equals(channel)) {
                    System.out.println("收到订阅66666666666666666666666666666666" + message);
                } else {
                    System.out.println("错误订阅tttttt");
                }
            }

            //初始化订阅时候的处理
            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.print("onSubscribe:初始化订阅时候的处理   ");
                System.out.println(channel + "=" + subscribedChannels);
            }

        };
        RedisUtil.getRedisTwo().subscribe(jedisPubSub, chanleTest);

    }

}



