package Leetcode;

import java.util.concurrent.CountDownLatch;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/11 17:15
 **/
public class Foo {

    private CountDownLatch second = new CountDownLatch(1);
    private CountDownLatch third = new CountDownLatch(1);
    private static Foo foo = new Foo();

    public Foo() {}

    public void first() throws InterruptedException {

        System.out.println("first");
        // mark the first job as done, by increasing its count.
        second.countDown();
    }

    public void second() throws InterruptedException {
        second.await();
        System.out.println("second");
        // mark the second as done, by increasing its count.
        third.countDown();
    }

    public void third() throws InterruptedException {
        third.await();
        System.out.println("third");
        //wait others task are completed, to invoke third.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                foo.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
