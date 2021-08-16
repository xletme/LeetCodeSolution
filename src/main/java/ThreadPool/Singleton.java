package ThreadPool;

/**
 * @Author maoXin
 * @Description
 * @Date 17:20 2021/8/4
 */
public class Singleton {

    private static volatile Singleton singleton;

    private static volatile int index;

    private Singleton() {}

    private static  Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                final Singleton instance = Singleton.getInstance();
                System.out.println(instance + " " + index++ );
            }).start();
        }
    }
}
