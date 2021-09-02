import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ConcurenExecutorTest {

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    public void one() {
        Set<Callable<String>> futureList = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            futureList.add(
                    new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            String time = String.format("当前时间 %s", new Date());
                            System.out.println(time);
                            System.out.println();
                            return time;
                        }
                    }

            );
        }
        try {
            // List<Future<String>> futureRes= executorService.invokeAll(futureList);
            //System.out.println("2222");
            executorService.invokeAny(futureList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO  为什么阿里巴巴禁止使用 Executors 创建线程池？  https://blog.csdn.net/singwhatiwanna/article/details/111026784

    @Test
    public void study() {
        //两个方法允许请求的最大队列长度是 Integer.MAX_VALUE ，可能会出现任务堆积，出现OOM
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(5);
//允许创建的线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，导致发生OOM
        Executors.newCachedThreadPool();
    }


}
