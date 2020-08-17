package com.yanghui.activemq.queue;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.concurrent.*;

public class ThreadTestTwo {


    class TaskTest implements Callable<Integer> {
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        }
    }

    @Test
    public void testOne() {
        TaskTest taskTest = new TaskTest();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(taskTest);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("主线程在执行任务");
        try {
            System.out.println("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

    @Test
    public void testTwo() {
        TaskTest taskTest = new TaskTest();
        FutureTask futureTask = new FutureTask(taskTest);
        System.out.println("主线程开始运行");
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println("规定时间内返回结果" + futureTask.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("所以现场执行完毕");

    }

    @Test
    public void testThree() throws Exception {
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                return testFour();
            }
        };
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        Thread.sleep(1000);
        if (futureTask.isDone()) {
            System.out.println("111");
            System.out.println(futureTask.get());
        }
        System.out.println("222");
    }

    public String testFour() throws Exception {
        //  Thread.sleep(1000L);
        return "6666";
    }

    public static void testFive() {

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    System.out.println("2222222");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("111");
        System.out.println("122");
        System.out.println("133");
    }

    @Test
    public void testSix() {
        testFive();
    }

    public static void main(String[] args) {
        testFive();
    }
}




