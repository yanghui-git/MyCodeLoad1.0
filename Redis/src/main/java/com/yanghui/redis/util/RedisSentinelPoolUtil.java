//package com.yanghui.redis.util;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisSentinelPool;
//
//import java.util.HashSet;
//import java.util.Set;
//
///*
//    JedisSentinelPool  哨兵模式
// */
//public class RedisSentinelPoolUtil {
//    public static JedisSentinelPool jedisSentinelPool;
//
//    public static Set<String> sentine = new HashSet<String>();
//
//    public static Jedis getRedis() {
//        sentine.add("10.1.200.204:6379");
//
//        if (jedisSentinelPool == null) {
//            jedisSentinelPool = new JedisSentinelPool("10.1.200.204", sentine, new GenericObjectPoolConfig(), 3000, "Root_123", 14
//            );
//        }
//        return jedisSentinelPool.getResource();
//    }
//
//
//}
