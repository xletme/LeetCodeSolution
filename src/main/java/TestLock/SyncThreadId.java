package TestLock;

import org.springframework.util.StopWatch;

/**
 * @Author maoXin
 * @Description
 * @Date 17:28 2021/6/22
 */
public class SyncThreadId implements Runnable{

    public synchronized void get(){
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set(){
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }
    public static void main(String[] args) {
        SyncThreadId ss=new SyncThreadId();
        StopWatch sw = new StopWatch();
        sw.start();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
        sw.stop();
        System.out.println(sw.prettyPrint());

    }
}
