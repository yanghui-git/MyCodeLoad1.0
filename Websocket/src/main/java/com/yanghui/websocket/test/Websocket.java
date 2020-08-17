package com.yanghui.websocket.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Component
@ServerEndpoint(value = "/yanghui/test")
public class Websocket {

    @OnOpen
    public void onOpen() {
        System.out.println("建立连接");
    }

    @OnClose
    public void onClose() {
        System.out.println("关闭连接");
    }

    /**
     * ScheduleExecutorService定时任务推送消息
     */
//
//    @OnMessage
//    public void onMessage(final String message, final Session session) {
//        sendMessage(message, session);
//        ExecutorService executorService = Executors.newScheduledThreadPool(3);
//        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                sendMessage(message + new Date(), session);
//            }
//        }, 2000L, 3000L, TimeUnit.MILLISECONDS);
//    }

    /**
     * 整合quartz 進行消息推送
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(final String message, final Session session) throws Exception {
        sendMessage(message, session);
    }


    /**
     * 发送消息
     */
    public void sendMessage(Object message, Session session) throws Exception { ;
        addTimeJob(message, session);
    }


    /**
     * 建立quartz 任务 推送数据
     */
    public static void addTimeJob(Object message, Session session) throws Exception {

        WebsocketData websocketData = new WebsocketData();
        websocketData.setData(message);
        websocketData.setSession(session);
        //建立schedule
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        //建立job
        JobDetail jobDetail = JobBuilder.newJob(WebsocketJob.class).withIdentity("yh", "yh").build();
        //存入数据
        jobDetail.getJobDataMap().put("param", websocketData);

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("yh", "yh").startNow().withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever())
                .build();

        //移除老任务
        removeJob("yh", "yh", scheduler);
        //启动任务
        scheduler.scheduleJob(jobDetail, trigger);
    }


    /**
     * 移除任务
     *
     * @param jobName
     * @param groupName
     */
    public static void removeJob(String jobName, String groupName, Scheduler scheduler) {
        try {
            JobKey jkey = new JobKey(jobName, groupName);
            if (!scheduler.checkExists(jkey)) {
                return;
            }
            TriggerKey key = new TriggerKey(jobName, groupName);
            scheduler.pauseTrigger(key);
            scheduler.unscheduleJob(key);
            scheduler.deleteJob(new JobKey(jobName, groupName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
