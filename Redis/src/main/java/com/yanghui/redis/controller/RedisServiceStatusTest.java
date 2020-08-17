package com.yanghui.redis.controller;

import com.yanghui.redis.util.RedisPoolUtil;

import com.yanghui.redis.util.RedisUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("yh/redis")
public class RedisServiceStatusTest {

    @RequestMapping(value = "/test/one", method = RequestMethod.GET)
    public void testOne() {
        System.out.println("redis test one success");
    }

    @RequestMapping(value = "/test/two/{name}", method = RequestMethod.GET)
    public void testTwo(@PathVariable("name") String name) {
        System.out.println("redis test two success" + name);
    }

    @RequestMapping(value = "/test/three", method = RequestMethod.GET)
    public void testThree() {
        String ss = RedisUtil.set("test1", "yhhhh");
        String sss = RedisUtil.set("test2", "yhhhh222");
        //  RedisUtil.del("test");
        String result = RedisUtil.get("test");
        System.out.println(ss + result);
    }

    @RequestMapping(value = "/test/four", method = RequestMethod.GET)
    public void testFour() {
        String ss = RedisPoolUtil.getRedis().set("test1", "yhhhh");
        String sss = RedisPoolUtil.getRedis().set("test2", "yhhhh222");
        //  RedisUtil.del("test");
        String result = RedisPoolUtil.getRedis().get("test");
        System.out.println(ss + result);
    }

//    @RequestMapping(value = "/test/five", method = RequestMethod.GET)
//    public void testFive() {
//        String ss = RedisSentinelPoolUtil.getRedis().set("test1", "yhhhh");
//        String sss = RedisSentinelPoolUtil.getRedis().set("test2", "yhhhh222");
//        //  RedisUtil.del("test");
//        String result = RedisSentinelPoolUtil.getRedis().get("test");
//        System.out.println(ss + result);
//    }
}
