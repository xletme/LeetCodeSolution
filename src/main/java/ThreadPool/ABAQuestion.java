package ThreadPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author maoXin
 * @Description
 * @Date 17:44 2021/7/22
 */
public class ABAQuestion {

    private static AtomicInteger atomicInt = new AtomicInteger(100);

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            atomicInt.compareAndSet(100, 101);
            atomicInt.compareAndSet(101, 100);
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           boolean c3 =  atomicInt.compareAndSet(100, 101);
            System.out.println(c3);
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Thread thread3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedRef.compareAndSet(100, 101,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            atomicStampedRef.compareAndSet(101, 100,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("hello");
        });

        Thread thread4 = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           boolean c3 =  atomicStampedRef.compareAndSet(100, 101,
                   stamp, stamp + 1);
            System.out.println(c3);
            System.out.println("world");
        });

        thread3.start();
        thread4.start();
    }
}
