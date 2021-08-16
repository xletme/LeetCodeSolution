package Leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/4 16:36
 **/
public class LargerNumber {

    /**
     *
     */
    private static class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        //这一步是精髓
        Arrays.sort(str, new LargerNumberComparator());

        if(str[0].equals("0")){
            return "0";
        }

        StringBuilder largerNumber = new StringBuilder();
        for (String s : str) {
            largerNumber.append(s);
        }
        return largerNumber.toString();
    }

    public static void main(String[] args) {
        LargerNumber largerNumber = new LargerNumber();
        String s = largerNumber.largestNumber(new int[]{3, 30, 34, 5, 9});
        System.out.println(s);
    }
}
