package jvm;

/**
 * @Author maoXin
 * @Description
 * @Date 16:51 2021/9/7
 */
public class StackOverFlowTest {

    private static int index = 0;

    private void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackOverFlowTest test = new StackOverFlowTest();
        try {
            test.call();
        } catch (Throwable e) {
            System.out.println("stack depth : " + index);
            e.printStackTrace();
        }
    }
}
