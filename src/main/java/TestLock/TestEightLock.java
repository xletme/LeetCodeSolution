package TestLock;

public class TestEightLock {

    /**
     * @param args
     * 非静态方法锁默认为this  静态方法锁默认是Class对象实例
     */
    public static void main(String[] args) {

        final EightLockDemo demo = new EightLockDemo();
        final EightLockDemo demo2 = new EightLockDemo();

        new Thread(new Runnable() {
            public void run() {
                demo.printOne();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                demo2.printTwo();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                demo.printThree();
            }
        }).start();
    }

}

class EightLockDemo{
    public static synchronized void printOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("One");
    }

    public static synchronized void printTwo(){
        System.out.println("Two");
    }

    public void printThree(){
        System.out.println("Three");
    }
}
