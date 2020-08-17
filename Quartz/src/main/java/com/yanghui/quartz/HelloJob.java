package com.yanghui.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        //获取自定义参数
        if (jobDataMap != null && jobDataMap.containsKey("test")) {
            //System.out.println(jobDataMap.get("test"));
        }

        System.out.println("Hello Quartz");
    }
}