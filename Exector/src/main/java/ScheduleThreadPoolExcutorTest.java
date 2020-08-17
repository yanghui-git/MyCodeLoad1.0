import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过Executors创建定时任务 https://blog.csdn.net/john_cena/article/details/84601672
 */
public class ScheduleThreadPoolExcutorTest {

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

    public static ScheduledExecutorService getSche() {
        if (scheduledExecutorService == null) {
            return Executors.newScheduledThreadPool(3);
        }
        return scheduledExecutorService;
    }

    //创建
    @Test
    public void testScheduleThreadPoolExecutorCreate() {

        ScheduledExecutorService scheduledExecutorService;

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    }

    /**
     * //延迟多久时间后自动执行任务，只执行一次
     */
    @Test
    public static void testOne() {
        getSche().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", new Date());
            }
        }, 3000L, TimeUnit.MILLISECONDS);

        getSche().shutdown();
    }


    /**
     * 延迟多久开始执行，之后每隔多久执行一次
     */
    public static void testTwo() {
        getSche().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("666");
            }
        }, 3000L, 3000L, TimeUnit.MILLISECONDS);
    }


    /**
     * 延迟多长时间后，自动执行此任务，待到此线程结束任务，再过多久时间定时调度此任务
     */
    public static void testThree() {
        getSche().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("77");
            }
        }, 1000L, 2000L, TimeUnit.MILLISECONDS);

    }


    public static void main(String[] args) {
        // testOne();
        // testTwo();
        testThree();
    }

}
