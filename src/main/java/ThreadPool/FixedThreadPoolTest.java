package ThreadPool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author maoXin
 * @Description
 * @Date 15:51 2021/4/27
 */
public class FixedThreadPoolTest {

    @Test
    public void testFixedThreadPool() {
        /*ExecutorService executorService = new ThreadPoolExecutor(4,4,60L,
                TimeUnit.MILLISECONDS,new SynchronousQueue<>(),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());*/
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0 ; i < 6; i++) {
            executorService.submit(new Task("thread " + i));
        }
        //关闭线程池
        executorService.shutdown();
    }

    static class Task implements Runnable{

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("start task" + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("end task" + name);
        }
    }
}
