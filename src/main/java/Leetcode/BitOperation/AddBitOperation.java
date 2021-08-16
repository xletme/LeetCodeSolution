package Leetcode.BitOperation;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/16 14:23
 **/
public class AddBitOperation {

    private static AddBitOperation instance = new AddBitOperation();

    public int add(int a, int b) {
      int sum = 0;
      int c = 0;
      while (b != 0){
          sum = a ^ b;
          c = (a & b) << 1;
          a = sum;
          b = c;
      }
      return a;
    }

    //二进制手表思路: 输入num 按规则输出显示可能的时间  0<n<=10  hour:minute  0<=hour<=11  0<=minute<=59
    // a.把num拆分成 上下两部分  上面4个 下面6个  比如 6  4 2,3 3,2 4,1 5,0 6;
    // b.依次按顺序 列出可能的场景  比如 4 2  15 > 11 直接忽略    3 3  C（6 3）  20种情况 7：。。。。。。 hour > 11忽略 minute > 59 忽略
    // 双List来存储a    2个List来存储 上下LED灯值  可能的值存到对应List 特殊处理个位数

    //Integer.bitCount 返回整数 转换为二进制 1的数量
    public List<String> readBinaryWatch(int num) {
        List<String> res = new LinkedList<>();
        for(int h = 0;h < 12; h++) {
            for(int m = 0; m <60; m++) {
                // the key is here
                if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                    res.add(String.format("%d:%02d",h,m));
                }
            }
        }
        return res;
    }

    //思路:^ 运算计算差别的值  Integer.bitCount 计算 1 位数
    public int convertInteger(int A, int B) {
        return Integer.bitCount(A ^ B);
    }

    //思路:移位操作 右移4位  ，与 0xf做 & 运算，每次得最后一位 最后在reverse   完全不用考虑负数
    public String toHex(int num) {
        StringBuilder str = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        String[] hexChar = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
        while (num != 0) {
            str.append(hexChar[(num & 0xf)]);
            num = num >>> 4;
        }
        return str.reverse().toString();
    }

    /***
     *@描述  思路:  与 3 做 & 运算，每次带符号右移一位   只要结果等于 3的 直接返回false  默认为true   ==0 也返回false  双 0 前面是双 1
     *@参数 [n]
     *@返回值 boolean
     */
    public boolean hasAlternatingBits(int n) {
        while (n != 0) {
            if ((n & 3) == 3) {
                return false;
            }else if((n & 3) == 0){
                return false;
            }
            n = n >>> 1;
        }
        return true;
    }

    /***
     *@描述   question description:exchange the num bits  odd and even
     *@参数 [num]
     *@返回值 int
     *  solution:  没读懂题目  借鉴的java回答的  odd calculate the odd 1,even calculate the same even  odd move one step in the left
     * even move one step in the right     return odd | even  ,the key is solution  get the odd and even one by one ,then handle  finally merge
     *@创建时间 2020/12/8
     */
    public int exchangeBits(int num) {
        int odd = num & 0x55555555;
        int even = num & 0xaaaaaaaa;
        odd = odd << 1;
        even = even >>> 1;
        return odd | even;
        //180.

    }

    //思路:暴力 把数组中每个值 和 对应的个数放入hashMap,如果有对应的值大于 1/2 nums.length 则返回该元素值 否则返回-1
   /* public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i]) + 1);
            }else {
                map.put(nums[i],1);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if(next.getValue() > nums.length /2){
                return next.getKey();
            }
        }
        return -1;
    }*/

    //思路:s t convert to charArray  sum  sumT - sumS  -> change to char
    public char findTheDifference(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chars1 = t.toCharArray();
        int sumT = 0;
        int sumS = 0;
        for (char aChar : chars) {
            sumS += aChar;
        }
        for (char c : chars1) {
            sumT += c;
        }
        return (char) (sumT - sumS);
    }

    public int insertBits(int N, int M, int i, int j) {
        int sum = 0;
        for(int k = i; k <= j; k++){
            if(k >= 31){
                break;
            }
            sum += Math.pow(2,k);
        }
        int S = N & sum;
        M = M << i;
        return N-S+M;
    }

    //思路:由于异或运算（XOR）满足结合律，并且对一个数进行两次完全相同的异或运算会得到原来的数，因此我们可以通过异或运算找到缺失的数字。
    // 把 数组中所有元素进行 ^ 运算, 这才是官方真正的巧妙  上面那个太传统 效率感觉太低 写法太low 了
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i ^ nums[i] ;
        }
        return res;
    }

    public int findComplement(int num) {
        int c = 0;
        int b = num;
        while(num != 0){
            num = num >> 1;
            c = (c << 1) +1;
        }
        return c ^ b;
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }


    /***
     *@描述
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * solution: 1.使用桶计数  输出 value为1 的索引 （数组size多大好呢？）   hash表  求和sum   集合
     * 2.位运算  所有元素做异或运算 得到最后的值就是 出现一次的元素 ，牛逼啊  官方思路
     *@创建时间 2020/12/1
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /***
     *@描述 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * solution:bit operation <<   left move  if n == 1
     *@创建时间 2020/12/1
     */
    public boolean isPowerOfTwo(int n) {
        int count = 0;
        n = Math.abs(n);
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>=1;
        }
        return count == 1;
    }

    /***
     *@描述
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
     *
     * solution:  类似 2的幂   小于0 return false  否者 traver n  , << 2
     * 1.0xaaaaaaaa & num == 0 确保  偶数位上没有1
     * 2.num > 0 确保没有负数
     * 3.num & (num-1) == 0 确保二进制位上只有一个1
     *@创建时间 2020/12/1
     */
    public boolean isPowerOfFour(int num) {
        return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
        //return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
    }

    /***
     *@描述 calculate two Integer + - ,not use operator
     *@创建时间 2020/12/2
     */
    public int getSum(int a, int b) {
        int c = 0;
        while (b != 0) {
            c = (a & b) << 1;
            a = a ^ b;//获取不进位的
            b = c;
        }
        return a;
    }

    /***
     *@描述
     * You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1s you could create.
     *
     * Example 1:
     *
     * Input: num = 1775(11011101111)2
     * Output: 8
     * Example 2:
     *
     * Input: num = 7(0111)2
     * Output: 4
     *
     * 基础知识： 整数的范围  -2的31次方   到 2的31次方减1  第32位是符号位 1表示负数  0 表示正数
     *
     * solution: calculate the effective value,and traver to add 1 in the zero position one by one .  request the longest sequence of 1s
     * 1.找到最高位的数  15 最高位1就是 8    8 * 2 * 2 = 32 - 1  = 31 最高位 i==32 就不管 31二进制1的个数 n = 5
     * 2.遍历  1 到 5  ,num & 2的n次方   如果为 0 就相加，计算连续1个个数  max 存入  直到结束
     * 我这思路不严谨，能解决大部分问题 特殊的还处理不了 尤其是负数  和   2的31房左右的树
     *@创建时间 2020/12/9
     */
    /*public int reverseBits(int num) {
        if (num == 0) {
            return 1;
        }
        int i = Integer.highestOneBit(num);
        int n = 0;
        int max = 0;
        if (i * 4 == 0xffffffff + 1) {
            n = 32;
            max = countBitOne(num + (int)Math.pow(2,30));
        } else {
            i = i * 4 - 1;
            n = Integer.bitCount(i);
        }
        for (int j = 0; j <= n; j++) {
            int k = (int)Math.pow(2,j);
            if ((num & k) == 0) {
                max = Math.max(max,countBitOne(num + k));
            }
        }
        return max;
    }

    private int countBitOne(int num) {
        int count = 0;
        int max = 0;
        num = Math.abs(num);
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            } else {
                max = Math.max(count,max);
                count = 0;
            }
            num = num >> 1;
        }
        return Math.max(max,count);
    }*/
    public int reverseBits(int num) {
        String s = Integer.toBinaryString(num);
        String[] arr = s.split("0");
        if (num == -1) {
            return arr[0].length();
        }
        if(arr.length<1 ) {
            return arr.length+1;
        }
        int max = arr[0].length();
        int res = max+1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].length() + arr[i].length() > max) {
                max = arr[i - 1].length() + arr[i].length();
                res = max + 1;
            }
        }
        return res;
    }


    /***
     *@描述
     * \Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set bits in their binary representation.
     *
     * (Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)
     *
     * Example 1:
     *
     * Input: L = 6, R = 10
     * Output: 4
     * Explanation:
     * 6 -> 110 (2 set bits, 2 is prime)
     * 7 -> 111 (3 set bits, 3 is prime)
     * 9 -> 1001 (2 set bits , 2 is prime)
     * 10->1010 (2 set bits , 2 is prime)
     * solution: prime ：质数   1 2 3 5 7
     * 1.traver  (R,L) all nums  ,find require the request  number   O(N)2 O(1)
     * 2.
     *@创建时间 2020/12/9
     */
    public int countPrimeSetBits(int L, int R) {
        int res = 0;
        int count = 0;
        for (int i = L; i <= R; i++) {
            count = Integer.bitCount(i);
            if (isPrime(count)) {
                res++;
            }
        }
        return res;
    }

    public boolean isPrime(int n){
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i < n-1; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }


    /*public static boolean isPrime(int num){
        boolean isprime = true;
        int len = num / 2;
        for (int i = 2; i < len; i++) {
            if (num % i == 0) {
                isprime = false;
                break;
            }
        }
        return isprime;
    }*/


    /***
     *@描述
     * Given a non-negative integer num, return the number of steps to reduce it to zero. If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
     *
     *  
     *
     * Example 1:
     *
     * Input: num = 14
     * Output: 6
     * Explanation: 
     * Step 1) 14 is even; divide by 2 and obtain 7. 
     * Step 2) 7 is odd; subtract 1 and obtain 6.
     * Step 3) 6 is even; divide by 2 and obtain 3. 
     * Step 4) 3 is odd; subtract 1 and obtain 2. 
     * Step 5) 2 is even; divide by 2 and obtain 1. 
     * Step 6) 1 is odd; subtract 1 and obtain 0.
     *
     * solution: follow the given request as up stage
     *@创建时间 2020/12/9
     */
    public int numberOfSteps (int num) {
        int res = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num >>= 1;
            } else {
                num -= 1;
            }
            res++;
        }
        return res;
    }

    /***
     *@描述
     * Given an integer n and an integer start.
     *
     * Define an array nums where nums[i] = start + 2*i (0-indexed) and n == nums.length.
     *
     * Return the bitwise XOR of all elements of nums.
     *
     *  
     *
     * Example 1:
     *
     * Input: n = 5, start = 0
     * Output: 8
     * Explanation: Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
     * Where "^" corresponds to bitwise XOR operator.
     * solution: directly to write the code O(N) O(N)
     *@创建时间 2020/12/9
     */
    public int xorOperation(int n, int start) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= start + 2 * i;
        }
        return res;
    }

    /***
     *@描述 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     *
     *  
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * 输出: 2
     *
     * solution: find the max element appear most in the array ,compare to the length of array nums
     * hashMap 统计  ++   if(value > nums.length()/2 ) return key
     * O(N) O(N)
     *@创建时间 2020/12/10
     */
   /* public int majorityElement(int[] nums) {
        int len = nums.length / 2;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num,1);
            } else {
                map.put(num,map.get(num) + 1);
            }
            if (map.get(num) > len) {
                return num;
            }
        }
        return -1;
    }*/
   //投票法   mathematics derivation :  众数    思路：  众数 1 非众数 -1   求sum  > 1  , n为数组长度   前a个 sum = 0 --》  剩余的 n-a个  sum > 0 且众数在后面
    // 当 sum = 0 假设当前 num为众数  最后返回该  num    特殊情况，没有众数  遍历 统计众数的个数， 如果count < len / 2  return -1
    public int majorityElement(int[] nums) {
        int votes = 0, x = 0;
        int count = 0;
        int tmp = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
            }
            tmp = (num == x ? 1 : -1);
            votes += tmp;
        }

        for (int num : nums) {
            if (num == x) count++;
        }
        return count < (nums.length / 2) ? -1 : x;
    }


    @Test
    public void singleNumberTest(){
        System.out.println(0x100);
        Assert.assertEquals(8,instance.xorOperation(5,0));
    }
    

    public static void main(String[] args) {
        System.out.println((int)Math.pow(2,31));
        System.out.println(Integer.toBinaryString(-(int)Math.pow(2,31)));
    }
}
