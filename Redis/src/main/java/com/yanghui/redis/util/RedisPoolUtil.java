package com.yanghui.redis.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * JedisPool
 */
public class RedisPoolUtil {
    public static JedisPool jedisPool;

    public static Jedis getRedis(){
          if(jedisPool==null){
             jedisPool=new JedisPool(new GenericObjectPoolConfig(),"10.1.200.204",6379,3000,"Root_123",15);
          }
          return jedisPool.getResource();
    }
}
