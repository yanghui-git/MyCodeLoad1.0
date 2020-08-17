package com.yanghui.redis.util;

import redis.clients.jedis.Jedis;

/**
 * 普通连接
 */
public class RedisUtil {

    public static Jedis jedis = null;

    public static synchronized Jedis getRedis() {
        if (jedis == null) {
            jedis = new Jedis("10.1.200.204", 6379, 3000);
            jedis.auth("Root_123");
            jedis.select(15);
        }
        return jedis;
    }

    public static synchronized Jedis getRedisTwo() {
        Jedis jedis = new Jedis("10.1.200.204", 6379, 3000);
        jedis.auth("Root_123");
        jedis.select(15);
        return jedis;
    }

    /**
     * 获取
     */
    public static String get(String key) {
        String result = getRedis().get(key);
        return result;
    }

    /**
     * 设置
     */
    public static String set(String key, String value) {
        String result = getRedis().set(key, value);
        return result;
    }

    /**
     * 删除
     */
    public static long del(String key) {
        Long result = getRedis().del(key);
        return result;
    }

    /**
     * 入队列
     */
    public static void lpush(String key, String value) {
        getRedis().lpush(key, value);
    }

    /**
     * 出队列
     */
    public static String rpop(String key) {
        return getRedis().rpop(key) == null ? "" : getRedis().rpop(key);
    }

    /**
     * 出队列
     */
    public static String lpop(String key) {
        return getRedis().lpop(key) == null ? "" : getRedis().lpop(key);
    }


    /**
     * 发布一个消息
     */
    public static void publish(String key, String value) {
        getRedis().publish(key, value);
    }
}
