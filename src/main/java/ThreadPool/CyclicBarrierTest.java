package ThreadPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author maoXin
 * @Description  cyclicBarrier test
 * @Date 14:46 2021/8/20
 */
public class CyclicBarrierTest {

    //场景 8个人到了就开席  强调,要等人到齐了 才能开,不然只能等着

    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(8, new Runnable() {
            @Override
            public void run() {
                System.out.println("8个人都到齐了, 我们开席,哪个坐小孩那桌");
            }
        });

        for (int i = 0; i < 8; i++) {
            new CyclicBarrierThread().start();
        }
    }
}
