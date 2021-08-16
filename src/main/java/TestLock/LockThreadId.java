package TestLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author maoXin
 * @Description
 * @Date 17:29 2021/6/22
 */
public class LockThreadId implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        LockThreadId ss = new LockThreadId();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}
