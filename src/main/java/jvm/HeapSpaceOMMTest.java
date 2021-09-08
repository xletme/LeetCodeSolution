package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author maoXin
 * @Description
 * @Date 16:59 2021/9/7
 */
public class HeapSpaceOMMTest {

    private static int index = 0;

    public static void main(String[] args) {
        List<byte[]> lists = new ArrayList<>();

        while (true) {
            try {
                index++;
                lists.add(new byte[1024*1024]);
            } catch (Throwable e) {
                System.out.println("distribute count : " + index);
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }
    }


}
