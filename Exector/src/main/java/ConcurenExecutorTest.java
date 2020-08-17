import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurenExecutorTest {

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    public void one() {
        Set<Callable<String>> futureList = new HashSet<>();
        for(int i=0;i<10;i++){
            futureList.add(
                    new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            String time=String.format("当前时间 %s" ,new Date());
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

    @Test
    public void two(){


    }


}
