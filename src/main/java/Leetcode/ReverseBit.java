package Leetcode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/6 10:50
 **/
public class ReverseBit {
    public  int reverseSingleNum(int n){
        char[] chars = String.valueOf(n).toCharArray();
        char[] reverse = reverse(chars, 0, chars.length - 1);
        StringBuffer str = new StringBuffer();
        for (char c : reverse) {
            str.append(c);
        }
        return Integer.parseInt(str.toString());
    }

    private char[] reverse(char[] nums, int start, int end) {
        while (start < end) {
            char temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        ReverseBit reverseBit = new ReverseBit();
        int i = reverseBit.reverseSingleNum(12346678);
        System.out.println(i);
    }
}
