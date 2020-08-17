package com.yanghui.quartz;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * https://blog.csdn.net/weixin_42817518/article/details/90692296 Quartz入门Demo
 */
public class QuartzTest {


    /**
     * 目的：设置一个定时器，每两秒钟输出一次“Hello Quartz”
     */

//    public static void main(String[] args) {
//        try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            JobDetail job = JobBuilder.newJob(HelloJob.class)
//                    .withIdentity("yh", "yh")
//                    .build();
//
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("yh", "yh")
//                    .startNow()
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(2)
//                            .repeatForever())
//                    .build();
//
//            scheduler.scheduleJob(job, trigger);
//            scheduler.start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * job传入自定义参数
     *
     *  https://blog.csdn.net/weixin_42151222/article/details/85260780
     *
     * @throws Exception
     */
//      public static void main(String[] args) throws Exception{
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        scheduler.start();
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("1", "2").build();
//
//        jobDetail.getJobDataMap().put("test","222222");
//
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("1", "2").startNow().withSchedule(simpleSchedule()
//                .withIntervalInSeconds(2)
//                .repeatForever())
//                .build();
//
//        scheduler.scheduleJob(jobDetail,trigger);
//        scheduler.start();
//    }

    /**
     * job传入自定义参数
     * <p>
     *  https://blog.csdn.net/weixin_42151222/article/details/85260780
     *
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        scheduler.start();
//
//        // jobDetail.getJobDataMap().put("test","222222");
//        JobDataMap jobDataMap = new JobDataMap();
//        jobDataMap.put("test", "33333");
//
//
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("1", "2").usingJobData(jobDataMap).build();
//
//        // jobDetail.getJobDataMap().put("test","222222");
//
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("1", "2").startNow().withSchedule(simpleSchedule()
//                .withIntervalInSeconds(2)
//                .repeatForever())
//                .build();
//
//        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.start();
//    }


    /**
     * quartz 获取任务状态
     *
     * https://blog.csdn.net/qq_18730505/article/details/79282686
     *
     */
    public static void main(String[] args) throws Exception {
        final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        ExecutorService executorService=Executors.newFixedThreadPool(3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // jobDetail.getJobDataMap().put("test","222222");
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("test", "33333");

                JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("1", "2").usingJobData(jobDataMap).build();

                // jobDetail.getJobDataMap().put("test","222222");

                Trigger trigger = TriggerBuilder.newTrigger().withIdentity("1", "2").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                        .build();

                try {
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * NONE: 不存在
         * NORMAL: 正常
         * PAUSED: 暂停
         * COMPLETE:完成
         * ERROR : 错误
         * BLOCKED : 阻塞
         */
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(3000L);
                        TriggerKey triggerKey=TriggerKey.triggerKey("1","2");
                        Trigger.TriggerState status =scheduler.getTriggerState(triggerKey);
                        System.out.println("任务状态监测"+status);
                        System.out.println(status);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
