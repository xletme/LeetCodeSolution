package Leetcode.Sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/25 10:31
 **/
public class SortArithmetic {

    private final static SortArithmetic instance = new SortArithmetic();

    /***
     *@描述
     * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
     *
     * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
     *
     * You may return any answer array that satisfies this condition.
     *
     *  
     *
     * Example 1:
     *
     * Input: [4,2,5,7]
     * Output: [4,5,2,7]
     * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
     *
     * solution: 1.build two array ,one odd array ,one even array   add both two join the new array   O(2N)  O(2N)
     * 2.traver the array ,swap the element one by one until be suit to the condition
     *@创建时间 2020/11/25
     */
    /*public int[] sortArrayByParityII(int[] A) {
        int[] arr1 = new int[A.length / 2]; //even
        int[] arr2 = new int[A.length / 2]; //odd
        int[] res = new int[A.length];
        int index1 = 0,index2 = 0;

        for (int i : A) {
            if (i % 2 == 0) {
                arr1[index1++] = i;
            } else {
                arr2[index2++] = i;
            }
        }

        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = arr1[i / 2];
            } else {
                res[i] = arr2[(i - 1) / 2];
            }
        }
        return res;
    }*/
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length - 1; i = i + 2) {
            if ((A[i] & 1) != 0) {
                while ((A[j] & 1) != 0) {
                    j = j + 2;
                }
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }

    /***
     *@描述
     * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
     *
     * If it is impossible to form any triangle of non-zero area, return 0.
     *
     *  
     *
     * Example 1:
     *
     * Input: [2,1,2]
     * Output: 5
     *
     * solution:
     * 1.method of exhaustion(穷举法) maxP  condition, a + b > c   make the p biggest
     * 2. 题友的思路   先排序   a > b > c > d   abd     b + d > a  because  c > d  so  b + c > a  so abc is the max
     *
     *@创建时间 2020/11/25
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i > 2; i--) {
            int a = A[i];
            int b = A[i - 1];
            int c = A[i - 2];
            if (a < b + c) {
                return a + b + c;
            }
        }
        return 0;
    }

    /***
     *@描述
     * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
     *
     * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
     *
     *  
     *
     * Example 1:
     *
     * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * Output: [2,2,2,1,4,3,3,9,6,7,19]
     *
     * solution : 1.element of arr2 put in a hashMap   check arr1 element ,if exist in hashMap  key(array2)--value(counts)
     * else put in another list ,  list.sort  + hashMap.get
     *
     *@创建时间 2020/11/26
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i : arr2) {
            map.put(i,0);
        }

        for (int i : arr1) {
            if (map.containsKey(i)) {
                map.put(i,map.get(i)+1);
            } else {
               list.add(i);
            }
        }

        Collections.sort(list);
        int index = 0;

        for (int value : arr2) {
            Integer tmp = map.get(value);
            for (int j = 0; j < tmp; j++) {
                arr1[index++] = value;
            }
        }

        for (Integer i : list) {
            arr1[index++] = i;
        }

        return arr1;
    }

    /***
     *@描述
     * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
     *
     * Return the sorted array.
     *
     *  
     *
     * Example 1:
     *
     * Input: arr = [0,1,2,3,4,5,6,7,8]
     * Output: [0,1,2,4,8,3,5,6,7]
     * Explantion: [0] is the only integer with 0 bits.
     * [1,2,4,8] all have 1 bit.
     * [3,5,6] have 2 bits.
     * [7] has 3 bits.
     * The sorted array by bits is [0,1,2,4,8,3,5,6,7]
     *
     * solution :
     * a 1. find 10000's binary    1111111
     * 2.all the elements  do & operation with the 111111 ,and split many array
     * 3.sort all the array and put all in the arr ,finally return
     * b put the array  maxValue in the rule , delete the element not in array
     * c first sort the arr ,then dived into groups    add to the new array   O(n logN) o(n)
     *@创建时间 2020/11/26
     */
    /*public int[] sortByBits(int[] arr) {
        int[] res = new int[arr.length];
        int index = 0;
        Arrays.sort(arr);
        for (int i = 0; i < 14; i++) {
            for (int j : arr) {
                if (calculateCountOfDecimal(j) == i) {
                    res[index++] = j;
                }
            }
        }
        return res;
    }*/

    //精选  思路 ：先找出位数最大的  乘以 100000  + arr[i]  ，再排序   后面在还原数组  这真的是鬼才  O(n logN) O(N)
    public int[] sortByBits(int[] arr) {
        int[] res = new int[arr.length];
        int index = 0;
        for (int i : arr) {
            res[index++] = Integer.bitCount(i) * 100000 + i;
        }
        Arrays.sort(res);
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i] % 100000;
        }
        return res;
    }
    public int calculateCountOfDecimal(int d) {
        int c = 0;
        while (d > 0) {
            if ((d & 1) == 1) {
                c++;
            }
            d = d >> 1;
        }
        return c;
    }

    /***
     *@描述
     * Given a string s. You should re-order the string using the following algorithm:
     *
     * Pick the smallest character from s and append it to the result.
     * Pick the smallest character from s which is greater than the last appended character to the result and append it.
     * Repeat step 2 until you cannot pick more characters.
     * Pick the largest character from s and append it to the result.
     * Pick the largest character from s which is smaller than the last appended character to the result and append it.
     * Repeat step 5 until you cannot pick more characters.
     * Repeat the steps from 1 to 6 until you pick all characters from s.
     * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.
     *
     * Return the result string after sorting s with this algorithm.
     *
     *  
     *
     * Example 1:
     *
     * Input: s = "aaaabbbbcccc"
     * Output: "abccbaabccba"
     * Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
     * After steps 4, 5 and 6 of the first iteration, result = "abccba"
     * First iteration is done. Now s = "aabbcc" and we go back to step 1
     * After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
     * After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
     * solution: bucket sort   use bucket to record the count of each character ,then from low to high,from high to low  traver the array
     * until the res.length not below the length of give's s   官方解答
     *@创建时间 2020/11/30
     */
    public String sortString(String s) {
        StringBuffer resStr = new StringBuffer();
        int[] num = new int[26];
        for (char c : s.toCharArray()) {
            num[c - 'a']++;
        }
        while (resStr.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    resStr.append((char)(i + 'a'));//add element to the resStr then reduce 1
                    num[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (num[i] > 0) {
                    resStr.append((char)(i + 'a'));//add element to the resStr then reduce 1
                    num[i]--;
                }
            }
        }
        return resStr.toString();
    }

    /***
     *@描述
     * Given an array of unique integers salary where salary[i] is the salary of the employee i.
     *
     * Return the average salary of employees excluding the minimum and maximum salary.
     *
     * solution:  1.find the max and min value,remove  then req the average
     *@创建时间 2020/11/30
     */
    public double average(int[] salary) {
        int max = salary[0];
        int min = salary[0];
        int sum = 0;
        for (int i : salary) {
            if (min > i) {
                min = i;
            }
            if (max < i ) {
                max = i;
            }
            sum += i;
        }
        return (double) (sum - min - max) / (salary.length - 2);
    }

    /***
     *@描述
     * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
     *
     * Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
     *
     *  
     * solution : sort the array,then check the difference of two consecutive elements in the same  O(N logN) + O(N)  O(1)
     *@创建时间 2020/11/30
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }
        Arrays.sort(arr);
        for (int i = 2; i < arr.length; i++) {
            if((arr[i] - arr[i-1]) != (arr[1] - arr[0])) {
                return false;
            }
        }
        return true;
    }

    /***
     *@描述
     * Given a string s and an integer array indices of the same length.
     *
     * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
     *
     * Return the shuffled string.
     *
     *  
     *
     * Example 1:
     *
     *
     * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
     * Output: "leetcode"
     * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
     *
     * solution : sort the array - indices ,then traver the array and add the elements to the stringBuffer ,finally convert to string to return
     * O(N log(N) + N)  O(N)
     *@创建时间 2020/11/30
     */
    public String restoreString(String s, int[] indices) {
        StringBuffer res = new StringBuffer();
        char[] c = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            c[indices[i]] = s.charAt(i);
        }

        return new String(c);
    }

    /***
     *@描述
     * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
     *
     * Return the sorted array.
     *
     *  
     *
     * Example 1:
     *
     * Input: nums = [1,1,2,2,2,3]
     * Output: [3,1,1,2,2,2]
     * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
     * Example 2:
     *
     * solution: bucket sort  then reverse to input to implements the req of decreasing order
     *@创建时间 2020/11/30
     */
   /* public int[] frequencySort(int[] nums) {
        int[] arr = new int[201];
        int index = 0;
        for (int num : nums) {
            arr[num + 100]++;
        }//bucket sort
        int max = 0;
        for (int i : arr) {
           if (i > max) {
               max = i;
           }
        }//find the max,confirm the count of next loop
        for (int i = 1; i <= max ; i++) {
            for (int j = 200; j >= 0; j--) {//reverse to handle the same frequency num
                if (arr[j] == i) {
                    for (int k = 0; k < arr[j]; k++) {
                        nums[index++] = j - 100;
                    }
                }
            }
        }
        return Arrays.copyOfRange(nums,0,nums.length);//arrayCopy get effective array
    }*/
   //官方答案
    public int[] frequencySort(int[] nums) {
        int[] cnts = new int[201];
        for (int n : nums){//第一步 1
            cnts[n + 100] ++;
        }
        for (int i = 0; i < nums.length; i ++){//第二步 按照规则 放大
            nums[i] = 10000 * cnts[nums[i] + 100] - nums[i] + 100;
        }
        Arrays.sort(nums);// 1+2 +排序实现  按频率 频率相同数字降序

        for (int i = 0; i < nums.length; i ++){//数据还原
            nums[i] = 100 - nums[i] % 10000 ;
        }

        return nums;
    }

    /***
     *@描述
     * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
     *
     * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：arr = [85], pieces = [[85]]
     * 输出：true
     * solution:  1.构造HashMap  key pieces[i][0]  value piece  好强的技巧性在里面  2.遍历检查值是否存在
     *@创建时间 2020/11/30
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer,int[]> map = new HashMap<>();
        for (int[] piece : pieces) {//构造hashMap
            map.put(piece[0],piece);
        }
        for (int i = 0; i < arr.length; ) {
            if (map.containsKey(arr[i])) {
                int[] piece = map.get(arr[i]);
                for (int k : piece) {
                    if (arr[i] == k) {
                        i++;//自增条件
                    } else {
                        return false;//unique elements  not equals ,return false
                    }
                }
            } else {
                return false;//not contains key,return false
            }
        }
        return true;//meet all the demand that the question
    }

    @Test
    public void testZero() {
        int[] arr = new int[]{10,100,1000,10000};
        Assert.assertEquals(1000,instance.sortByBits(arr)[3]);
    }

    public static void main(String[] args) {
       int[] arr = new int[]{1,2,3,4,5};
       int[][] pieces = new int[][]{{1,2,3,4,5}};
        System.out.println(instance.canFormArray(arr,pieces));
    }

}
