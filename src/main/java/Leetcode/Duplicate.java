package Leetcode;

import java.util.Arrays;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/7 14:45
 **/
public class Duplicate {

    public static boolean containsDuplicate(int[] nums) {
        //思路  计算原数组长度 和 去重后的数组长度，决定返回true或false
        int oldLength = nums.length;
        long count = Arrays.stream(nums).distinct().count();
        return oldLength != count;
    }

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1,2,3,4,1,2,3}));
    }
}
