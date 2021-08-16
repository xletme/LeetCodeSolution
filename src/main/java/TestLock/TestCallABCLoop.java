package TestLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCallABCLoop {

    public static void main(String[] args) {
       final PrintDemo demo = new PrintDemo();


            new Thread(new Runnable() {
                public void run() {
                    for (int i = 1; i <=10 ; i++) {
                        demo.LoopA(i);
                    }
                }
            },"线程A").start();

            new Thread(new Runnable() {
                public void run() {
                    for (int i = 1; i <=10 ; i++) {
                        demo.LoopB(i);
                    }
                }
            },"线程B").start();

            new Thread(new Runnable() {
                public void run() {
                    for (int i = 1; i <=10 ; i++) {
                        demo.LoopC(i);
                    }
                }
            },"线程C").start();


    }

}

class PrintDemo{

    private int number = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void LoopA(int loop){
        lock.lock();
        try{
            if(number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                for (int i = 0; i < 1; i++) {
                    System.out.print("A"+"\t"+loop+"\n");
                }
                number = 2;
                condition2.signal();
        }finally {
            lock.unlock();
        }

    }

    public void LoopB(int loop){
        lock.lock();
        try{
            if(number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                for (int i = 0; i < 1; i++) {
                    System.out.print("B"+"\t"+loop+"\n");
                }
                number = 3;
                condition3.signal();

        }finally {
            lock.unlock();
        }

    }

    public void LoopC(int loop){
        lock.lock();
        try{
            if(number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                for (int i = 0; i < 1; i++) {
                    System.out.print("C"+"\t"+loop+"\n");
                }
                number = 1;
                condition1.signal();
        }finally {
            lock.unlock();
        }

    }

}
