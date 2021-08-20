package ThreadPool;

import java.util.concurrent.Semaphore;

/**
 * @Author maoXin
 * @Description semaphore test
 * @Date 15:41 2021/8/20
 */
public class SemaphoreTest {

    //信号量测试 停车场例子  5辆车,3个车位

    static class Parking {

        private Semaphore semaphore;

        Parking(int count) {
            semaphore = new Semaphore(count);
        }

        public void park() {
            try {
                //获取信号量
                semaphore.acquire();
                long time = (long) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒..." );
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }


    static class Car extends Thread {

        private Parking parking;

        public Car(Parking parking) {
            this.parking = parking;
        }

        public void run() {
            parking.park();
        }
    }

    public static void main(String[] args) {

        Parking parking = new Parking(3);

        for (int i = 0; i < 5; i++) {
            new Car(parking).start();
        }
    }
}
