package com.yanghui.websocket.test;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.websocket.Session;
import java.util.Date;

public class WebsocketJob implements Job {


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取携带的参数
        System.out.println(22222);

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        if (jobDataMap == null || jobDataMap.size() == 0 || !jobDataMap.containsKey("param")) {
            System.out.println("no params");
            return;
        }

        WebsocketData websocketData = (WebsocketData) jobDataMap.get("param");
        if (websocketData.getSession() == null) {
            System.out.println("no session");
            return;
        }
        sendMessage(websocketData.getData(), websocketData.getSession());
    }


    /**
     * 发送消息
     */
    public void sendMessage(Object message, Session session) {
        session.getAsyncRemote().sendText("websocket get message success " + message+new Date());
    }


}
