package Leetcode;

import java.util.Arrays;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/6 10:00
 **/
public class MoveArry {
    /*输入: [1,2,3,4,5,6,7] 和 k = 3
    输出: [5,6,7,1,2,3,4]*/
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int step = k % n;
        reverse(nums,0,n-1);
        reverse(nums,step,n-1);
        reverse(nums,0,step-1);
    }

    private int[] reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        int [] nums = new int[] {1,2,3,4,5,6,7,8};
        MoveArry moveArry = new MoveArry();
        moveArry.rotate(nums,3);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
