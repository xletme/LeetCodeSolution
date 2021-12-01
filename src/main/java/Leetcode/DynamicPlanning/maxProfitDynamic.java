package Leetcode.DynamicPlanning;

import java.util.Arrays;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/10/9 11:44
 **/
public class maxProfitDynamic {

    private static final maxProfitDynamic instance = new maxProfitDynamic();

    /***
     *
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     *  规则 :股票买入和卖出 只能一次  int[] prices 第i个元素表示每天的价格 先买再卖
     * 思路:穷举法  列出所有的可能 max求最大值，大于0 则输出 max，否则输出0 时间复杂度  O(N2) 空间复杂度 O(N)
     * 找出最小值 和 最大值 ,如果在最小值在最大值之前，返回 max-min  否则 找第二大的值
     * 依次算出第i天的最大利润值，不放list 用int存储 每次比较max Math.max();
     * 依次遍历到最后一天
     * 7 1 5 3 6 4
     * 7 6 4 3 1
     *@创建时间 2020/10/9
     */
    /*public int maxProfit(int[] prices) {
        int res = 0;
        int max = 0;
        if(prices.length <= 1){
            return 0;
        }
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i];
            for(int j = 0 ; j < i; j++ ){
                if(prices[j] < tmp){
                   res = tmp - prices[j];
                   max = Math.max(res,max);
                }

            }
        }

        return Math.max(max, 0);
    }*/

    /***
     *@描述 min 关键，表示前面n个数的最小值,依次求出第i天的利润 取其最大值 定义初始 max = 0 min = princes[0]
     * O(N) O(1)
     *@创建时间 2020/10/9
     */
    public int maxProfit(int[] prices) {
        if(prices.length <= 1)
            return 0;
        int min = prices[0], max = 0;
        for(int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    /***
     *@描述 题目：
     * 爬楼梯，一共有多少种方案
     * 参数n 表示一共n阶梯   一次能爬 1 、2 阶
     * 思路:
     * 7
     * 1 1 1  1 1 1  1
     * 1 2 1  1  1 1
     * 2 1 1 1  1 1 1
     *
     * 1 1 1 1 1 1 1
     * 2 的 （n-1）次方
     *
     * 1 1
     * 2 2
     * 3 3
     * 4 5           1 3   2 2      1 1 2   1 2 1  2 2    2+1+2 = 5
     * 1 1 1 1
     * 1 2 1
     * 1 1 2
     * 2 1 1
     * 2 2
     *              5  1 4  2  3   5+3=8
     *              6  1 5  2 4    1 1 4  1 2 3   2 4     5+3+5 = 13     1 2 3 5 8 13  伏波拉契数列 递归
     *              f(n) = f(n-1)+f(n-2)   空间换时间,使用map来存放  f(n)
     *@创建时间 2020/10/10
     */
    public int climbStairs(int n) {
        if(n==0){
            return 0;
        }
        int [] arr = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for(int i=2;i<=n;i++){
            arr[i] = arr[i-1]+arr[i-2];
           // arr[i] %=1000000007;
        }
        return arr[n];
    }

    /***
     *@描述 求整数数组 nums[]  总和最大的连续数列 返回总和
     * [-2,1,-3,4,-1,2,1,-5,4]
     * dynamic planning
     * 连续   初始化 max = nums[0]  sum = 0最前面2数相加和第二个数比较，大于等于 第二个数 sum = 和  否则  sum = 第二个数    max = Math.max(sum,max);
     * nums.length  = 0 直接返回 sum
     *@创建时间 2020/10/10
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(sum + nums[i] >= nums[i]){
                sum += nums[i];
            }else {
                sum = nums[i];
            }
            max = Math.max(sum,max);
        }
        return max;
    }

    /***
     *@描述 给定一数组 Nums 相邻的两个不能选 , 可以求和的最大值
     * 1 7 8 6 3   1+8+3   7+6 13
     * 1 7        7
     * 1 7 8      9
     * 1 7 8 9    7+9 16   16 9
     * 1 7 8 9 3  7+9 16   16 12
     * 1 8 3  12
     * 1 9    10
     * 7 9    16
     * 7 3    10
     * 官方思路：
     * 1间房  max = nums[0]
     * 2间房  max = Math.max(nums[0],nums[1])
     * k间房
     * a.偷第k间房  max = k-2最大金额 + 第k间房的金额
     * b.偷第k-1间房  max = k-1 最大金额
     * 比较 a b两种的最大值 max(a,b)
     *@创建时间 2020/10/12
     */
    public int rob(int[] nums) {
        int res = 0;
        int pre2 = 0;
        int pre1 = 0;
        int k1 = 0 ;
        int k2 = 0;
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        pre2 = nums[0];
        pre1 = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            k1 = pre2 + nums[i];
            k2 = pre1;
            pre2 = pre1;
            res = Math.max(k1,k2);
            pre1 = res;
        }
        return res;
    }

    /***
     *@描述  判断 s 是否为 t的子序列
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串
     * t : axybtx
     * s: axt true  s:atx true
     * 思路：
     * 依次判断 s的 组合是否包含在 t中
     * s : axt   a ax axt 3种  a x t
     * 在t中找第一个与 字符一致的位置,截取  cut     contains  subString for  
     *@创建时间 2020/10/12
     */
    public boolean isSubsequence(String s, String t) {
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if(t.contains(String.valueOf(aChar))){
                int firstIndex = t.indexOf(String.valueOf(aChar));
                t = t.substring(firstIndex+1);
            }else {
                return false;
            }
        }
        return true;
    }


    /***
     *@描述 使用最小花费爬楼梯 可以选择 0 1索引阶梯为起始点     一次可以爬到  1 2 层
     *输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出: 6
     * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
     * 思路：
     * leetCode评论解答  最后只有两种结果，要么是 sum(n-1) 要么是 sum(n-2)  取倒数第一和第二的最小值
     * res[1] = cost[1]这个很关键
     * 因为后面会用到这个值
     *@创建时间 2020/10/19
     */
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 0){
            return 0;
        }
        if(cost.length == 1){
            return cost[0];
        }
        int sumSt = 0;
        int sumDd = 0;
        int[] res = new int[cost.length];
        res[0] = cost[0];
        res[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            sumSt = res[i-1] ;
            sumDd = res[i-2];
            res[i] = Math.min(sumDd,sumSt) + cost[i];
        }
        return Math.min(res[cost.length - 1 ],res[cost.length - 2]);
    }

    /***
     *@描述 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，
     * 因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数
     *
     * 思路：
     *  累加   数组长度为n    n天的 最多时间   Math.max(sum(n-1),sum(n-2)+n)
     *  n = 1 nums[0]
     *  n = 2 math.max(nums[0],nums[1])
     *
     *  找一个数组来存储   数组长度为 n 的最大值，最后返回对应的下标就OK了
     *
     *  任何东西都可以使用穷举法  列出所有的可能，获取最大值
     *
     *@创建时间 2020/10/22
     */
    public int massage(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        int[] res = new int[nums.length];
        res[0] = nums[0];
        res[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res[i] = Math.max(res[i-1],res[i-2]+nums[i]);
        }
        return res[nums.length - 1];
    }


    /***
     *@描述 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，
     * 计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     * 思路:
     * 1.穷举法  列出小孩的所有可能
     * 2.
     * 1 1
     * 2 2
     * 3 4
     * 4 7
     * 5 13
     * 1 2 3 特殊处理
     *
     * 后续的  等于前面三个加起来
     * 和伏波拉契数列类似
     *
     * 定义 数组来存储    定义三个变量  返回  res[n]
     *@创建时间 2020/10/22
     */
    public int waysToStep(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(n == 3){
            return 4;
        }
        int[] res = new int[n];
        res[0] = 1;
        res[1] = 2;
        res[2] = 4;
        for(int i = 3;i < n;i++){
            res[i] = (res[i-1]+res[i-2])%1000000007+res[i-3];
            res[i] %= 1000000007;
        }
        return res[n-1];
    }

    /***
     *@描述 编写一个程序，找出第 n 个丑数  丑数就是质因数只包含 2, 3, 5 的正整数。
     * 示例:
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     * 1 是丑数。
     * n 不超过1690
     * 思路：
     * 初始化 dp[0] = 1
     * 每次 乘以 2 3 5 如果大于  dp[i-1] 就存入数组
     * 分别 乘以 2 3 5 存入数值，置换其中的最小值  比较的对象就是 dp[i] dp[j] *2  *3 *5    找最小可能的值
     * 时间复杂度  o(N2)  空间复杂度 O(N) dp[n] 数组的长度
     *@创建时间 2020/10/23
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j=0;j<i;j++){
                if(dp[j] * 2 >dp[i-1]){
                    dp[i] = Math.min(dp[j]*2,dp[i]);
                    continue;
                }
                if(dp[j] * 3 >dp[i-1]){
                    dp[i] = Math.min(dp[j]*3,dp[i]);
                    continue;
                }
                if(dp[j] * 5 >dp[i-1]){
                    dp[i] = Math.min(dp[j]*5,dp[i]);
                }
            }
        }
        return dp[n-1];
    }

    /***
     *@描述 Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     *
     * 思路：bst 二叉搜索树   root.left < root.val < root.right
     * 1.按照这样的规则去构造，看下一共有多少种情况 直接返回   需要用到root tree
     * 2.找出其中的规律，写算法来解答
     *
     * 官方思路：动态规划  f(i,n) : 以i为根，以长度为n的序列有多少种不同的bst  G(n)：数字n总共有 多少种bst
     * 最后的公式： G(n) = ∑ (n i = 1)G(i-1).G(n-i)
     *
     * 1....n  BST
     * 1 2 3 4 5 6 7  f(3,7) = G(2).G(4)  f(i,n) = G(i-1).G(n-i)
     * G(n) = ∑(n i =1)f(i,n) = ∑(n i=1)G(i-1).G(n-i)
     * G(0) = 0 G(1)= 1
     */
    public int numTrees(int n) {
        int [] res = new int[n+1];
        res[0]=1; //0是空树  1是只有根 所以都为1
        res[1]=1;
        for(int i=2;i<=n;i++){
            int sum = 0;//sum用来表示 f(i,n)
            for(int j=1;j<=i;j++){
                sum += res[j-1]*res[i-j];
            }
            res[i] = sum;
        }
        return res[n];
    }

    /***
     *@描述
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * How many possible unique paths are there?
     *
     * Input: m = 3, n = 7
     * Output: 28
     *
     * 思路：
     * 最终的公式：G(m,n) = G(m-1,n)+G(m,n-1)
     * G(1,n) = 1 G(n,1) =1
     *
     */
    public int uniquePaths(int m, int n) {
        int[][] G = new int[m+1][n+1];
        for(int i=1;i<m+1;i++){
            G[i][0] = 0;
            G[i][1] = 1;
        }
        for(int i=1;i<n+1;i++){
            G[0][i] = 0;
            G[1][i] = 1;
        }
        for(int i=2;i<m+1;i++){
            for(int j=2;j<n+1;j++){
                G[i][j] = G[i-1][j] +G[i][j-1];
            }
        }
        return G[m][n];
    }
   /* 官方思路：这是个杨辉三角形，每个位置的路径 = 该位置左边的路径 + 该位置上边的路径
    public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }*/

    /**
     * @Description:
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     *
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     *
     * solution:
     * 1.使用bigCount来解决
     * 2.找规律
     * @Date: 2021/6/18 10:11
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,4,5,3,1,1,3};
        Arrays.stream(instance.countBits(5)).forEach(System.out::println);
    }
}


