import org.junit.Test;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorTest {


    /**
     * - corePoolSize：线程池维护线程的最少数量
     * - maximumPoolSize：线程池维护线程的最大数量
     * - keepAliveTime： 线程池维护线程所允许的空闲时间
     * - unit： 线程池维护线程所允许的空闲时间的单位
     * - workQueue： 线程池所使用的缓冲队列
     * - handler： 线程池对拒绝任务的处理策略
     */

    //创建
    @Test
    public void testThreadPoolExecutorCreate() {
        ExecutorService executorService = new ThreadPoolExecutor(5, 20, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    }

    ;


    @Test
    public void test() {
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            String localip = ia.getHostAddress();
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

