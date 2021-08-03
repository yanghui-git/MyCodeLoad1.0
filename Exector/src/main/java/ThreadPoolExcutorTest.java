import org.junit.Test;

import java.net.InetAddress;
import java.util.concurrent.*;

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

    /**
     * https://zhuanlan.zhihu.com/p/214205558
     *
     * 线程池四种 拒绝策略：
     * 若线程池中的核心线程数被用完且阻塞队列已排满，则此时线程池的资源已耗尽，线程池将没有足够的线程资源执行新的任务。为了保证操作系统的安全，线程池将通过拒绝策略处理新添加的线程任务。
     */
    @Test
    public void testRejectedExecutionHandler() {
        new ThreadPoolExecutor(5, 20, 3000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()
                //默认策略 抛出RejectedExecutionException RuntimeException
                //   new ThreadPoolExecutor.AbortPolicy()

                //默默的丢弃被处理的任务
                //第2种拒绝策略是 DiscardPolicy，这种拒绝策略正如它的名字所描述的一样，当新任务被提交后直接被丢弃掉，也不会给你任何的通知，相对而言存在一定的风险，因为我们提交的时候根本不知道这个任务会被丢弃，可能造成数据丢失。
                // new ThreadPoolExecutor.DiscardPolicy()

                // 第3种拒绝策略是 DiscardOldestPolicy，如果线程池没被关闭且没有能力执行，则会丢弃任务队列中的头结点，通常是存活时间最长的任务，这种策略与第二种不同之处在于它丢弃的不是最新提交的，而是队列中存活时间最长的，这样就可以腾出空间给新提交的任务，但同理它也存在一定的数据丢失风险。

                // 有点类似于LRU算法  第二种 丢弃最新  第三种 丢弃最老
                // new ThreadPoolExecutor.DiscardOldestPolicy()

//第一点新提交的任务不会被丢弃，这样也就不会造成业务损失。
//第二点好处是，由于谁提交任务谁就要负责执行任务，这样提交任务的线程就得负责执行任务，而执行任务又是比较耗时的，在这段期间，提交任务的线程被占用，也就不会再提交新的任务，减缓了任务提交的速度，相当于是一个负反馈。在此期间，线程池中的线程也可以充分利用这段时间来执行掉一部分任务，腾出一定的空间，相当于是给了线程池一定的缓冲期。
                // new ThreadPoolExecutor.CallerRunsPolicy()
        );
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

