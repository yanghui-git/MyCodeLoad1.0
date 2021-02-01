import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

/**
 * https://blog.csdn.net/CookSjg/article/details/60881800
 * https://blog.csdn.net/suifeng3051/article/details/49444177
 * https://blog.csdn.net/suifeng3051/article/details/49443835
 */
public class ExcutorServiceTest {

/**
 * 1. newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 2. newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 3. newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * 4. newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *
 */
    /**
     * 简单使用  execute(Runnable)  异步调用
     */

    public static void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ExecutorService Test");
            }
        });

         System.out.println("999");
         System.out.println("100");
    }



    /**
     * submit(Runnable)
     */
  //  @Test
    public  void testTwo() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("666");
            }
        });
        try {
            //此方法会产生阻塞
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("777");
        executorService.shutdown();
    }


    /**
     * submit(Callable)和submit(Runnable)类似，也会返回一个Future对象，但是除此之外，submit(Callable)接收的是一个Callable的实现，Callable接口中的call()方法有一个返回值，可以返回任务的执行结果，而Runnable接口中的run()方法是void的，没有返回值。请看下面实例：
     */
  //  @Test
    public static void testThree() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future future = executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("1212");
                return "submit Callable";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("1313");


        /**
         * 当我们使用完成ExecutorService之后应该关闭它，否则它里面的线程会一直处于运行状态。
         *
         * 举个例子，如果的应用程序是通过main()方法启动的，在这个main()退出之后，如果应用程序中的ExecutorService没有关闭，这个应用将一直运行。之所以会出现这种情况，是因为ExecutorService中运行的线程会阻止JVM关闭。
         *
         * 如果要关闭ExecutorService中执行的线程，我们可以调用ExecutorService.shutdown()方法。在调用shutdown()方法之后，ExecutorService不会立即关闭，但是它不再接收新的任务，直到当前所有线程执行完成才会关闭，所有在shutdown()执行之前提交的任务都会被执行。
         *
         * 如果我们想立即关闭ExecutorService，我们可以调用ExecutorService.shutdownNow()方法。这个动作将跳过所有正在执行的任务和被提交还没有执行的任务。但是它并不对正在执行的任务做任何保证，有可能它们都会停止，也有可能执行完成。
         */
       // executorService.shutdown();
    }


    /**
     * invokeAny(...)方法接收的是一个Callable的集合，执行这个方法不会返回Future，但是会返回所有Callable任务中其中一个任务的执行结果。这个方法也无法保证返回的是哪个任务的执行结果，反正是其中的某一个。请看下面实例：
     */
    public static void testFour() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        String result = null;
        try {
            result = executorService.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        executorService.shutdown();

    }


    /**
     * invokeAll(...)与 invokeAny(...)类似也是接收一个Callable集合，但是前者执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象。情况下面这个实例：
     */
    public static void testFive() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        List<Future<String>> futures = executorService.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();

    }

    public static void main(String[] args) {
        //这个方法有个问题，就是没有办法获知task的执行结果。如果我们想获得task的执行结果，我们可以传入一个Callable的实例（下面会介绍
        //test();
        //testTwo();
          //testThree();
         testFour();
//        try {
//            testFive();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }



}
