
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 */
public class TestExecutor {

    public static void main(String[] args) {
        methodExecutor();
        methodConcurrent();
    }

    /**
     * Executor模式
     */
    public static void methodExecutor() {
        //定长线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Set<Callable<String>> setList = new HashSet<Callable<String>>();
        //15个并发
        for (int i = 0; i < 10; i++) {
            final int result = i;
            setList.add(new Callable() {
                public Object call() throws Exception {
                    Thread.sleep(300l);
                    System.out.println("当前线程: " + result);
                    return result;
                }
            });
        }
        try {
            executorService.invokeAny(setList);    //执行部分
            //    executorService.invokeAll(setList);   //全部执行
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * concurrent Semaphore 信号量 （允许同时最大多少线程访问）
     * 信号量Semaphore https://www.cnblogs.com/whgw/archive/2011/09/29/2195555.html
     */
    public static void methodConcurrent() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //设置只能3个线程同时访问  即最大3人同时蹲坑
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 获取许可
                    try {
                        semaphore.acquire();
                        System.out.println("第" + index + "号" + "正在蹲坑");
                        Thread.sleep((long) (Math.random() * 10000));
                        //访问完成后，释放
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //  System.out.println("-----------------" + semaphore.availablePermits());
                    System.out.println("-----------------第" + index + "号" + "蹲坑结束");
                }
            };
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }

}
