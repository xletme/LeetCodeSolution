package Leetcode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/7 14:45
 **/
public class Duplicate {

    private static final Duplicate instance = new Duplicate();

    private static AtomicBoolean isSuccess = new AtomicBoolean(false);

    private static AtomicInteger tryCount = new AtomicInteger(0);

    public static boolean containsDuplicate(int[] nums) {
        //思路  计算原数组长度 和 去重后的数组长度，决定返回true或false
        int oldLength = nums.length;
        long count = Arrays.stream(nums).distinct().count();
        return oldLength != count;
    }

    public String testCount(int key) {
        if (tryCount.get() >= 3) {
            System.out.println("我们都失败了 + 失败" );
            throw new RuntimeException("error");
        }
        try {
            System.out.println("zzzz");
            int num = 5/0;
        } catch (Exception e) {
           tryCount.getAndIncrement();
            System.out.println("失败" + tryCount + "次");
            return testCount(key);
        } finally {
            tryCount.getAndDecrement();
            System.out.println("finally" + tryCount + "次");
            System.out.println("over");
        }
        return isSuccess.get() ? "https://" + key : "4";
    }

    public static void main(String[] args) {
        //System.out.println(instance.testCount(9));
        //System.out.println(tryCount);

        BigDecimal frozenAmount = new BigDecimal(200);
        BigDecimal thawAmount = new BigDecimal(0);
        BigDecimal payAmount = new BigDecimal(0);
        BigDecimal transAmount = new BigDecimal(0);
        BigDecimal totalToUse = thawAmount.add(payAmount).add(transAmount);
        if (totalToUse.compareTo(BigDecimal.ZERO) > 0 && frozenAmount.compareTo(totalToUse) < 0) {
            throw new RuntimeException("可用冻结金额不足");
        }
    }
}
