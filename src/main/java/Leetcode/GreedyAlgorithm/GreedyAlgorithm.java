package Leetcode.GreedyAlgorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/10/27 14:32
 **/
public class GreedyAlgorithm {

    private static final GreedyAlgorithm instance = new GreedyAlgorithm();

    /***
     *@描述
     * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
     *
     * -2：向左转 90 度
     * -1：向右转 90 度
     * 1 <= x <= 9：向前移动 x 个单位长度
     * 在网格上有一些格子被视为障碍物。
     *
     * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
     *
     * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
     *
     * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。
     * 思路：
     * 每走一步判断是否经过 障碍点  ，然后在这里进行相应的逻辑处理 注意obstacles 的值 ，这里的值可能有多个
     * 要把 obstacles 存到hashMap中
     * case： -1 向右转   -2 像左转  得找个标志来定位方向  根据方向来判定 逻辑处理  flag : y x
     * 直到遍历完整的 commands  得到对应的坐标  进行欧式平方运算
     * 数据结构：数组  map   方向确定 和行走 障碍物确定 有问题，搞不出来
     *
     * 官方思路：
     * 定义一个  directions[][] 二维数组来表示 方向 东南西北，{[0,1],[1,0],[0,-1],[-1,0]}
     * -1 右转 索引 +1   -2 左转 索引 +3       %4
     * obstacles数组中的元素使用set进行存放  obstacles[0]+" "+obstacles[1]
     * 行走的时候，一步一步走   x y 表示横纵坐标  依次加 direction[0] direction[1]
     * 直到  走完 command  或者 遇到障碍物为止 停下，最后 返回  x*x + y*y
     *  maxDistSquare = Math.max(maxDistSquare, x * x + y * y); 官方的这里没看懂
     *@创建时间 2020/10/27
     */
    public int robotSim(int[] commands, int[][] obstacles) {

        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0]+" "+obstacle[1]);
        }
        int x = 0;
        int y = 0;
        int direction = 0;
        int max = 0;
        for (int command : commands) {
           if(command == -1){
               direction = (direction+1) % 4;
           }else if(command == -2){
               direction = (direction+3) % 4;
           }else {
               int step = 0;
               int [] tmp = directions[direction];
               while (step < command && (!set.contains((x+tmp[0])+" "+(y+tmp[1])))){
                   x += tmp[0];
                   y += tmp[1];
                   step++;
               }
           }
            max = Math.max(max,x*x+y*y);
        }
        return max;
    }

    /***
     *@描述
     * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
     *
     * The operation of drinking a full water bottle turns it into an empty bottle.
     *
     * Return the maximum number of water bottles you can drink.
     *
     * 思路；贪心算法
     * 一步一步算，先把所有酒喝完，一瓶一瓶的去换
     * numBottles  numExchange    emptyBottles = numBottles --
     * 换一瓶  numBottles++，直到emptyBottles < numExchange 为止 返回 numBottles
     * 数据结构 : no use  时间复杂度 O(N)  空间复杂度 O(1)
     *@创建时间 2020/10/28
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int emptyBottles = numBottles;
        while(emptyBottles >= numExchange){
            emptyBottles = emptyBottles - numExchange+1;
            numBottles++;
        }
        return numBottles;
    }

    /***
     *@描述
     * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly
     * alternate between positive and negative. The first difference (if one exists) may be either positive or negative.
     * A sequence with fewer than two elements is trivially a wiggle sequence.
     *
     * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are
     * alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences,
     * the first because its first two differences are positive and the second because its last difference is zero.
     *
     * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
     * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
     * leaving the remaining elements in their original order.
     *
     * 思路：贪心算法
     * 下一个数字是  小于 那就找连续最少的，没有连续的就取当前值
     * 直到 遍历完 nums 数组
     * 使用stack 来存储 ,连续增加的值 取最大，连续减少的值取最小
     * 最后返回  长度  stack.size(),最后在考虑特殊情况
     * 数据结构 stack   数组
     *@创建时间 2020/10/28
     */
   /* public int wiggleMaxLength(int[] nums) {
        if(nums.length <= 1){
            return nums.length;
        }
        if(nums.length == 2){
            if(nums[0] != nums[1]) {
                return 2;
            }else {
                return 1;
            }
        }
        boolean flag = false;
        int k = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
           if(nums[i] != nums[0]){
               stack.push(nums[i]);
               k = i;
               flag = nums[i] -nums[0] > 0;
               break;
           }
        }
        for (int i = k; i < nums.length;i++ ) {
            if(nums[i] != stack.peek()){
                if((nums[i] > stack.peek() && flag) || (nums[i] < stack.peek() && !flag)){
                    stack.pop();
                }else {
                    flag = !flag;
                }
                stack.push(nums[i]);
            }
        }
        return stack.size();
    }*/
   //官方思路 ： 大体思路和我差不多  没有使用数据结构  使用count int来进行统计 ,上面那个损耗有可能就是stack的原因
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                count++;
                prevdiff = diff;
            }
        }
        return count;
    }

    /***
     *@描述
     * Assume you are an awesome parent and want to give your children some cookies.
     * But, you should give each child at most one cookie.
     *
     * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
     * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i,
     * and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
     *
     * 思路：贪心算法
     * g[i] s[j]  遍历g[i] 把 s[j]中最小的 给 g[i],直到遍历完 g[i] ,每给出一个 s[j]移除 该元素 定义一个res  res++ 最后返回 res
     * 最后处理特殊情况
     * 数据结构 :array linkedList PriorityQueue 来实现  遍历莫法按照从小到大的顺序来，底层是二叉树实现   是按照树形结构来遍历的
     * 时间复杂度 ： O(N2) 空间复杂度：O(N)
     *
     * 对两个 g s进行排序，然后 思路和我一样
     *@创建时间 2020/10/29
     */
    public int findContentChildren(int[] g, int[] s) {
        if(s.length == 0){
            return 0;
        }
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i : g) {
            for (int j = 0; j < s.length; j++) {
                if(s[j] >= i){
                    s[j] = 0;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    /***
     *@描述
     * You have an initial power of P, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).
     *
     * Your goal is to maximize your total score by potentially playing each token in one of two ways:
     *
     * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
     * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
     * Each token may be played at most once and in any order. You do not have to play all the tokens.
     *
     * Return the largest possible score you can achieve after playing any number of tokens.
     *
     * 思路：贪心算法
     * 能量用来 翻 令牌组最小的值 获取分,  分用来翻最大的能量的牌 来获取能量     直到 分数为0 && 能量翻不了令牌 返回得分(其中的最大值)
     * 数据结构 ： array
     *@创建时间 2020/10/29
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        int res = 0;
        int M = 0;
        int j = tokens.length;
        if(j==0){
            return 0;
        }
        Arrays.sort(tokens);
        int min = tokens[0];
        int max = tokens[tokens.length-1];
        j--;
        int k = 0;
        if(P < min){
            return 0;
        }
        while (true){
            if(P >= min) {
                P -= min;
                tokens[k] = 0;
                res++;
                k++;
                if(k > tokens.length - 1){
                    return res;
                }
                min = tokens[k];
            }else if(res > 0){
                res--;
                P += max;
                tokens[j] = 0;
                j--;
                max = tokens[j];
            }
            M = Math.max(res,M);
            if(res == 0 && P < min){
                break;
            }
            if(min == 0 || max ==0){
                break;
            }
        }
        return M;
    }

    /***
     *@描述
     *  Balanced strings are those who have equal quantity of 'L' and 'R' characters.
     *
     * Given a balanced string s split it in the maximum amount of balanced strings.
     *
     * Return the maximum amount of splitted balanced strings.
     *
     *  
     *
     * Example 1:
     *
     * Input: s = "RLRRLLRLRL"
     * Output: 4
     * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
     *
     * solution :
     *  use a flag to calculate  and write a method to split the sequence
     *  end condition : all character walk one by one to the end
     *  special : two c   both diff  and equal in nums
     *  use greedy   O(N) O(1)
     *@创建时间 2020/11/20
     */
    public int balancedStringSplit(String s) {
        int res = 0;
        int l = 1, r = 0;//计数器
        char[] chars = s.toCharArray();
        char a = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == a) {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                //find one  l ,r  init  res++
               res++;
               l = 0;
               r = 0;
            }
        }
        return res;
    }

    /***
     *@描述
     * Given the array nums, obtain a subsequence of the array whose sum of elements is strictly greater than the sum of the non included elements in such subsequence. 
     *
     * If there are multiple solutions, return the subsequence with minimum size and if there still exist multiple solutions, return the subsequence with the maximum total sum of all its elements. A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array. 
     *
     * Note that the solution with the given constraints is guaranteed to be unique. Also return the answer sorted in non-increasing order.
     *
     *  
     *
     * Example 1:
     *
     * Input: nums = [4,3,10,9,8]
     * Output: [10,9]
     * Explanation: The subsequences [10,9] and [10,8] are minimal such that the sum of their elements is strictly greater than the sum of elements not included, however, the subsequence [10,9] has the maximum total sum of its elements. 
     *  solution:
     *   1. sort   sum   traverse return result   O(N log(N))  O(N)
     *   2. sum  find max one by one , return result
     *@创建时间 2020/11/20
     */
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> res = new LinkedList<>();
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int tmp = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            tmp += nums[i];
            sum -= nums[i];
            res.add(nums[i]);
            if(tmp > sum){
                break;
            }
        }
        return res;
    }

    /***
     *@描述
     * At a lemonade stand, each lemonade costs $5. 
     *
     * Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
     *
     * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.
     *
     * Note that you don't have any change in hand at first.
     *
     * Return true if and only if you can provide every customer with correct change.
     *
     * Example 1:
     *
     * Input: [5,5,5,10,20]
     * Output: true
     * Explanation:
     * From the first 3 customers, we collect three $5 bills in order.
     * From the fourth customer, we collect a $10 bill and give back a $5.
     * From the fifth customer, we give a $10 bill and a $5 bill.
     * Since all customers got correct change, we output true.
     *
     * solution： calculate  a b  a: the count of 5  b: the count of 10
     * traver the array once and determine the result  O(N)  O(1)
     *
     *@创建时间 2020/11/23
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills.length == 0) {
            return true;
        }
        int a = 0, b = 0;
        for (int bill : bills) {
            if (bill == 5) {
                a++;
            }else if (bill == 10) {
                if (a > 0) {
                    a--;
                    b++;
                } else {
                    return false;
                }
            } else {
                if (a > 0) {
                    a--;
                    if (b > 0) {
                        b--;
                    } else if (a > 1) {
                        a--;
                        a--;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     *@描述
     * We are given an array A of N lowercase letter strings, all of the same length.
     *
     * Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
     *
     * For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"], and the remaining columns of A are ["b","v"], ["e","y"], and ["f","z"].  (Formally, the c-th column is [A[0][c], A[1][c], ..., A[A.length-1][c]]).
     *
     * Suppose we chose a set of deletion indices D such that after deletions, each remaining column in A is in non-decreasing sorted order.
     *
     * Return the minimum possible value of D.length.
     *
     * Example 1:
     *
     * Input: A = ["cba","daf","ghi"]
     * Output: 1
     * Explanation:
     * After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
     * If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
     *
     * solution:  A.length  == 1 return 0
     * traver the each string of array,contract the same index  determine if they are increasing ,yes do nothing  or res++;  return res
     * O(N2) O(1)
     *@创建时间 2020/11/23
     */
    public int minDeletionSize(String[] A) {
        if(A.length == 1){
            return 0;
        }
        int res = 0;
        int sl = A[0].length();
        for (int i = 0; i < sl; i++) {
            for (int j = 0; j < A.length - 1; j++) {
                String s0 = A[j];
                String s1 = A[j+1];
                int c1 = s0.charAt(i);
                int c2 = s1.charAt(i);
                if (c1 > c2) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    /***
     *@描述
     * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
     *
     * Return the largest possible sum of the array after modifying it in this way.
     *
     *  
     *
     * Example 1:
     *
     * Input: A = [4,2,3], K = 1
     * Output: 5
     * Explanation: Choose indices (1,) and A becomes [4,-2,3].
     *Note:
     * 1 <= A.length <= 10000
     * 1 <= K <= 10000
     * -100 <= A[i] <= 100
     * solution :  traver the array ,sort the array    record the negative counts and  K
     * if (K =< counts ) change one by one by the order
     * K  > counts  change counts nums one by one by the order  determine if there is a zero ,yes then return the sum
     * else  change the min positive num  if odd  -  else  +
     *  n log (N)   O(1)
     *@创建时间 2020/11/23
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int res = 0;
        int counts = 0;
        boolean flag = false;
        int min = Integer.MAX_VALUE;
        int var = 0;
        for (int i : A) {
            if (i == 0) {
                flag = true;
                break;
            }
        }
        for (int i : A){
            if (i < 0){
                counts++;
            }
            if (Math.abs(i) < min) {
                min = Math.abs(i);
            }
        }

        if (K <= counts) {
            for (int i = 0; i < K; i++) {
                A[i] = -A[i];
            }
        } else {
            for (int i = 0; i < counts; i++) {
                A[i] = -A[i];
            }
            if (flag) {
                //do nothing
            } else {
                if ((K - counts) % 2 == 0) {
                    //do nothing
                } else {
                    var = -min * 2;
                }
            }
        }
        for (int i : A) {
           res +=i;
        }
        return res +var;
    }

    /***
     *@描述
     *  We have n chips, where the position of the ith chip is position[i].
     *
     * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
     *
     * position[i] + 2 or position[i] - 2 with cost = 0.
     * position[i] + 1 or position[i] - 1 with cost = 1.
     * Return the minimum cost needed to move all the chips to the same position.
     *
     * solution: let all the nums join the odd and  even number,then contract the counts each others  the smaller move to another
     * return the smaller counts
     * odd and even could be  0 ,1   actually ,calculate the nums of odd number and even number
     *@创建时间 2020/11/24
     */
    public int minCostToMoveChips(int[] position) {
        int oddNum = 0,evenNum = 0;
        for (int i : position) {
            if (i % 2 == 0) {
                evenNum++;
            } else {
                oddNum++;
            }
        }
        return Math.min(oddNum,evenNum);
    }

    /***
     *@描述
     *  Say you have an array prices for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     *
     * solution: greedy find the pre n day's max profit
     * one day to one day to add ,then return the res
     * key: compare today and tomorrow  O(N) O(1)
     *@创建时间 2020/11/24
     */
    public int maxProfit(int[] prices) {
        int res  = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                res += prices[i + 1] - prices[i];
            }
        }
        return res;
    }

    @Test
    public void testMinCostToMoveChips(){
        int[] arr = new int[]{1,2,3,4,5};
        Assert.assertEquals(4,instance.maxProfit(arr));
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        str.append("我已经活了");
        Calendar c = Calendar.getInstance();
        c.set(1994,Calendar.JULY,24,15,0);
        long ms = System.currentTimeMillis() - c.getTime().getTime();
        str.append(ms / 1000).append("秒");
        System.out.println(str);
    }
}
