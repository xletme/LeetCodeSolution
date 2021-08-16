package Leetcode.DynamicPlanning;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/10/22 13:48
 **/
public class NumArray {

    private int[] res;

    /***
     *@描述 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     * 思路：
     *
     * 传入 nums 初始化数组
     * sumRange 按照 i到j进行求和
     * 对 i j 的值进行判断
     *
     *@创建时间 2020/10/22
     */
    public NumArray(int[] nums) {
       this.res = new int[nums.length];
       if(nums.length > 0) {
           res[0] = nums[0];
           for (int i = 1; i < nums.length; i++) {
               res[i] = res[i - 1] + nums[i];
           }
       }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? res[j] : res[j] - res[i-1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        int param_1 = obj.sumRange(2,5);
        System.out.println(param_1);
    }
}
