package Leetcode.ArrayAlp;

import org.junit.Test;

import java.util.*;

import static java.lang.String.valueOf;

/**
 * @Author maoXin
 * @Description
 * @Date 9:57 2021/1/11
 */
public class ArrayAlp {

    private static final ArrayAlp instance = new ArrayAlp();


    /**
     * @Description: 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10或11)来表示。
     * <p>
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     * <p>
     * 示例1:
     * <p>
     * 输入:
     * bits = [1, 0, 0]           0 10 0 10 0 0 11 10 11 10 10 0 10 11 0        10结尾  00结尾   10 11
     * 00结尾 直接返回true  10 结尾  去掉0 在看剩下的字符串能否使用 10 11 0组合而得 可以就返回
     * 0 11 10  按照这个顺序，看下最后剩一个啥,剩0 就 返回true  0开头 && 下标不是size - 1
     * <p>
     * 输出: True
     * 解释:
     * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
     * <p>
     * solution: 官方解答 1和我一样  2 贪心法，归纳 0结尾都是比特币结束  最终答案取决于 最后一个0左边的1个数  偶数 true 奇数 false
     * @Date: 2021/1/11 10:00
     */
    public boolean isOneBitCharacter(int[] bits) {
        /*for (int i = 0; i < bits.length - 1;) {
            if (i == bits.length - 1) {
                return bits[i] == 0;
            }
            if (bits[i] == 0) {
                i = i + 1;
            } else if (bits[i] == 1 ) {
                i = i + 2;
            }
        }*/
        int count = 0;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 0) {
                break;
            }
            count++;
        }
        return count % 2 == 0;
    }

    /**
     * @Description: 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * <p>
     * 输入：matrix =[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     * 1  2  3  4
     * 5  6  7  8
     * 9 10 11 12
     * 矩阵 长 n  宽 m   n * m   int[n * m]
     * solution: 找规律 i =0  matrix[i][0] --- matrix[0][n] ---matrix[m][n] ---matrix[m][0]---matrix[i + 1][0]
     * m-- n-- 搞个计数的
     * l left r right t top b bottom
     * 初始值 0 (matrix[0].length - 1) 0 (matrix.length-1)
     * @Date: 2021/1/11 14:59
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;
    }

    /**
     * @Description:
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
     * 并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     * <p>
     * 
     * <p>
     * 示例 1:
     * <p>
     * 输入: [0,1,3]
     * 输出: 2
     * 示例2:
     * <p>
     * 输入: [0,1,2,3,4,5,6,7,9]
     * 输出: 8
     * <p>
     * solution: 折题没难度 啊  遍历嘛 nums[i] = i + 1;  false  return i + 1
     * @Date: 2021/1/11 16:06
     */
    public int missingNumber(int[] nums) {
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return i;
    }


    /**
     * @Description:
     * 对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。
     * 例如，如果X = 1231，那么其数组形式为[1,2,3,1]。
     * <p>
     * 给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：A = [1,2,0,0], K = 34
     * 输出：[1,2,3,4]
     * 解释：1200 + 34 = 1234
     * 示例 2：
     * <p>
     * 输入：A = [2,7,4], K = 181
     * 输出：[4,5,5]
     * 解释：274 + 181 = 455
     * <p>
     * solution: this is especially simple , like the desc of the demo
     * my wrong A.length between 1 and 10000   convert k into an array to add with A for each element
     * @Date: 2021/1/11 16:17
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        //first convert
        int[] tmp = convert(K);
        int tl = tmp.length;
        int al = A.length;
        int tmpInt = 0;
        A = reverse(A);
        //add
        if (al > tl) {
            for (int i = 0; i < A.length; i++) {
                tmpInt = i < tl ? tmp[i] : 0;
                res.add((A[i] + tmpInt) % 10);
                if (A[i] + tmpInt >= 10) {
                    if ((i + 1) < A.length) {
                        A[i + 1] += 1;
                    } else {
                        res.add(1);
                    }
                }
            }
        } else {
            for (int i = 0; i < tmp.length; i++) {
                tmpInt = i < al ? A[i] : 0;
                res.add((tmp[i] + tmpInt) % 10);
                if (tmp[i] + tmpInt >= 10) {
                    if ((i + 1) < tmp.length) {
                        tmp[i + 1] += 1;
                    } else {
                        res.add(1);
                    }
                }
            }
        }
        Collections.reverse(res);
        return res;
    }

    private int[] convert(int c) {
        List<Integer> arr = new LinkedList<>();
        int i = 1;
        while (!(c < Math.pow(10, i - 1))) {
            int v = (int) ((int) (c % Math.pow(10, i)) / Math.pow(10, i - 1));
            arr.add(v);
            i++;
        }
        return arr.stream().mapToInt(x -> x).toArray();
    }

    private int[] reverse(int[] a) {
        if (a == null) {
            return null;
        }

        int p1 = 0, p2 = a.length;
        int[] result = new int[p2];

        while (--p2 >= 0) {
            result[p2] = a[p1++];
        }

        return result;
    }

    /**
     * @Description:
     * 给定长度为2n的整数数组 nums ，你
     * 的任务是将这些数分成n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
     * 使得从 1 到n 的 min(ai, bi) 总和最大。
     * <p>
     * 返回该 最大总和 。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,4,3,2]
     * 输出：4
     * 解释：所有可能的分法（忽略元素顺序）为：
     * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * 所以最大总和为 4
     * <p>
     * solution: 1 2  2 3  4 5  6 7           1 2 4 6 = 13 sort,取奇数位相加
     * @Date: 2021/1/12 9:48
     */
    public int arrayPairSum(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i = i + 2) {
            res += nums[i];
        }
        return res;
    }

    /**
     * @Description:
     * 给定由若干0和1组成的数组 A。我们定义N_i：
     * 从A[0] 到A[i]的第 i个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     * <p>
     * 返回布尔值列表answer，只有当N_i可以被 5整除时，答案answer[i] 为true，否则为 false。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：[0,1,1]
     * 输出：[true,false,false]
     * 解释：
     * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
     * 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0   0  1  3  7  15  31  63 127 255 511 1021 2042 4084 8186 16335
     * <p>
     * this     question is probable to find the rule
     * solution: 1.int-->string-->binary-->decimal % 5 == 0 it nor reality cause A.length
     * <p>
     * 1   1   1   1    1   1    1    1   1   1   1    1     1     1
     * 1   2   4   8    16  32   64   128 256 512 1024 2048  4096  8192
     * <p>
     * 1.除开第一位，隔一位相加 就为 10的倍数
     * 2. 1 配一个4
     * false  false  false  true   false    false
     * 1      3      2      0      1        3
     * <p>
     * 官方解答：余数定理a=b%c=b%c%c;（a+b）%c=((a%c)+(b%c))%c,ab%c=((a%c)*(b%c))%c;
     * 求每次的余数    num = (num * 2 + A[i]) % 5 % 5 多次对5取余，结果不会变
     * O(N) O(N)
     * @Date: 2021/1/12 10:14
     */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < A.length; i++) {
            num = (num * 2 + A[i]) % 5;
            res.add(num == 0);
        }
        return res;
    }


    /**
     * @Description:
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
     * 可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * <p>
     * 给你一个整数数组flowerbed 表示花坛，由若干 0 和 1 组成，
     * 其中 0 表示没种植花，1 表示种植了花。另有一个数n ，
     * 能否在不打破种植规则的情况下种入n朵花？能则返回 true ，不能则返回 false。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：flowerbed = [1,0,0,0,1], n = 1
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：flowerbed = [1,0,0,0,1], n = 2
     * 输出：false
     * <p>
     * 1 <= flowerbed.length <= 2 * 104
     * flowerbed[i] 为 0 或 1
     * flowerbed 中不存在相邻的两朵花
     * 0 <= n <= flowerbed.length
     * <p>
     * solution: 2种思路 第一种：先处理数组 最多还能种多少花；第二种：遍历数组，直接种花，if n == 0  true ，遍历结束 n > 0 false
     * 明显第二种时间复杂度要简单些    1 0 0 0 0 1   2
     * 评论区有位道友牛逼啊，两边各加一个零 就不用考虑边界了
     * O(N) O(N)
     * @Date: 2021/1/13 10:06
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        int[] newFlowerBed = new int[flowerbed.length + 2];
        newFlowerBed[0] = 0;
        newFlowerBed[newFlowerBed.length - 1] = 0;
        for (int i = 1; i < newFlowerBed.length - 1; i++) {
            newFlowerBed[i] = flowerbed[i - 1];
        }
        for (int i = 0; i < newFlowerBed.length; i++) {
            if (i + 2 < newFlowerBed.length) {
                if (newFlowerBed[i] == 0 && newFlowerBed[i + 1] == 0 && newFlowerBed[i + 2] == 0) {
                    n--;
                    newFlowerBed[i + 1] = 1;
                }
                if (n == 0) {
                    return true;
                }
            }
        }
        return false;
    }
   /* public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if ( n == 0) {
            return true;
        }
        //先处理特殊情况
        if (flowerbed.length >= 2) {
            if (flowerbed[0] == 0){
                if (flowerbed[1] == 0) {
                    n--;
                    flowerbed[0] = 1;
                    if (n == 0) {
                        return true;
                    }
                }
            }
            if (flowerbed[flowerbed.length - 1] == 0) {
                if (flowerbed[flowerbed.length - 2] == 0) {
                    n--;
                    flowerbed[flowerbed.length - 1] = 1;
                    if (n == 0) {
                        return true;
                    }
                }
            }
        } else {
            if (flowerbed[0] == 0) {
                return n <= 1;
            } else {
                return n <= 0;
            }
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (i + 2 < flowerbed.length) {
                if (flowerbed[i] == 0 && flowerbed[i + 1] == 0 && flowerbed[i + 2] == 0) {
                    n--;
                    flowerbed[i + 1] = 1;
                }
                if (n == 0) {
                    return true;
                }
            }
        }
        return false;
    }*/


    /**
     * @Description:
     * 给你一个n行m列的矩阵，最开始的时候，每个单元格中的值都是 0。
     * <p>
     * 另有一个索引数组indices，indices[i] = [ri, ci]中的ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
     * <p>
     * 你需要将每对[ri, ci]指定的行和列上的所有单元格的值加 1。
     * <p>
     * 请你在执行完所有indices指定的增量操作后，返回矩阵中 「奇数值单元格」 的数目。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：n = 2, m = 3, indices = [[0,1],[1,1]]
     * 输出：6
     * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
     * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
     * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
     * <p>
     * 1 <= n <= 50
     * 1 <= m <= 50
     * 1 <= indices.length <= 100
     * 0 <= indices[i][0] <n
     * 0 <= indices[i][1] <m
     * <p>
     * solution:
     * first:follow the rule to handle the data indices[];
     * second:calculate the odd num of the indices finally return num
     * arr[0][indices[i][0]]  -- > arr[n][indices[i][0]]    arr[indices[i][1]][0]-->arr[indices[i][1]][m]
     * O(N*M) N是indices长度  M是Math.max(m,n)   O(N)
     * @Date: 2021/1/13 10:55
     */
    public int oddCells(int n, int m, int[][] indices) {
        int res = 0;
        int[][] arr = new int[n][m];
        for (int i = 0; i < indices.length; i++) {
            for (int t = 0; t < m; t++) {
                arr[indices[i][0]][t] += 1;
            }
            for (int k = 0; k < n; k++) {
                arr[k][indices[i][1]] += 1;
            }

        }

        for (int[] x : arr) {
            for (int i : x) {
                if (i % 2 == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    /** #todo since here
     * @Description:
     * 车按国际象棋中的规则移动。东，西，南，北四个基本方向任选其一，
     * 然后一直向选定的方向移动，直到满足下列四个条件之一：
     * <p>
     * 棋手选择主动停下来。
     * 棋子因到达棋盘的边缘而停下。
     * 棋子移动到某一方格来捕获位于该方格上敌方（黑色）的卒，停在该方格内。
     * 车不能进入/越过已经放有其他友方棋子（白色的象）的方格，停在友方棋子前。
     * 你现在可以控制车移动一次，请你统计有多少敌方的卒处于你的捕获范围内（即，可以被一步捕获的棋子数）。
     * <p>
     * solution:
     * 1.以车 R为中心，东南西北四个方位遍历，res计数 one(遇到卒停下来 res++) two(自家的象) three(边界)
     * 最后return res ; res初始化 0
     * R (x,y)  l w      (x,y)-->(x,0) y--  (x,y)-->(x,w) y++  (x,y)-->(0,y) x-- (x,y)-->(l,y) y++
     * judge if (board[x][y] == p)
     * O(M*N) M board矩阵的长度 N 寻找卒最远的长度   O(1)
     * @Date: 2021/2/4 15:23
     */
    public int numRookCaptures(char[][] board) {
        // 定义上下左右四个方向
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 找到白车所在的位置
                if (board[i][j] == 'R') {
                    // 分别判断白车的上、下、左、右四个方向
                    int res = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i, y = j;
                        while (true) {
                            x += dx[k];
                            y += dy[k];
                            if (x < 0 || x >= 8 || y < 0 || y >= 8 || board[x][y] == 'B') {
                                break;
                            }
                            if (board[x][y] == 'p') {
                                res++;
                                break;
                            }
                        }
                    }
                    return res;
                }
            }
        }
        return 0;
    }

    /**
     * @Description:
     * Given an array nums of 0s and 1s and an integer k,
     * return True if all 1's are at least k places away from each other, otherwise return False.
     * <p>
     * solution: 贪心法 寻找最近两个1的距离  与 k做比较,如果小于 k return false;
     * 考虑特殊情况  k = 0,return true    这种直接返回true  只有1一个1
     * Constraints:
     * <p>
     * 1 <= nums.length <= 105
     * 0 <= k <= nums.length
     * nums[i] is 0 or 1
     *
     * O(N) O(1) 贪心算法
     * @Date: 2021/2/4 16:07
     */
    public boolean kLengthApart(int[] nums, int k) {
        if (k == 0) {
            return true;
        }
        int start = -1;
        int change;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (start != -1) {
                    change = i - start;
                    if (change <= k) {
                        return false;
                    }
                }
                start = i;
            }
        }
        return true;
    }

    /**
     * @Description:
     * Given an array arr of integers,
     * check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).
     * <p>
     * More formally check if there existstwo indices i and j such that :
     * <p>
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     * <p>
     * Constraints:
     * <p>
     * 2 <= arr.length <= 500
     * -10^3 <= arr[i] <= 10^3
     * solution:
     * 奇数跳过，偶数除以二 找是否存在，存在返回true 否则 false  N*N
     * O(N2) O(1)
     * @Date: 2021/2/4 16:30
     */
    public boolean checkIfExist(int[] arr) {
        for (int i : arr) {
            if (i % 2 == 0 && i != 0) {
                int tmp = i / 2;
                for (int j : arr) {
                    if (j == tmp) {
                        return true;
                    }
                }
            }
            int count = 0;
            if (i == 0) {
                for (int j : arr) {
                    if (j == 0) {
                        count++;
                    }
                    if (count == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @Description:
     * Given two strings,write a method to decide if one is a permutation of the other.
     * <p>
     * Example 1:
     * <p>
     * Input: s1 = "abc", s2 = "bca"
     * Output: true
     * Example 2:
     * <p>
     * Input: s1 = "abc", s2 = "bad"
     * Output: false
     * <p>
     * solution: s1 s2 sort 之后看是否一致  O(logN) O(N)  限定字符类型  桶排序也是ok的
     * 总体思路 排序 + equals
     * @Date: 2021/2/5 13:53
     */
    public boolean CheckPermutation(String s1, String s2) {
        char[] charS1 = s1.toCharArray();
        char[] charS2 = s2.toCharArray();
        Arrays.sort(charS1);
        Arrays.sort(charS2);
        return Arrays.equals(charS1, charS2);
    }

    /**
     * @Description:
     * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，
     * 使得nums [i] = nums [j]，并且 i 和 j的差的 绝对值 至多为 k。
     * <p>
     * 
     * <p>
     * 示例1:
     * <p>
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     * <p>
     * solution: 1.存在一组索引 在距离最大的两个索引，求其绝对值 最小值  返回 abs <= k 2.不存在 返回false
     * @Date: 2021/2/5 14:05
     */
    //雕兄解答
    /*public boolean containsNearbyDuplicate(int[] nums, int k) {
        int size = nums.length;
        for(int i=0;i<size-1;i++){
            int len = Math.min(size,i+1+k);
            for(int j=i+1;j<len;j++){
                if(nums[i]==nums[j]){
                    return true;
                }
            }
        }
        return false;
    }*/
    //官方解答
    //滑动窗口解答,set size始终保持在 0-k个内,也只有set 内的元素和当前遍历元素相等,代表存在 返回true
    // O(N) N:数组长度 O(N) N:最多为K+1
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * @Description:
     * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
     * <p>
     * 如果nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
     * <p>
     * 源数组中可能存在 重复项 。
     * <p>
     * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,4,5,1,2]
     * 输出：true
     * 解释：[1,2,3,4,5] 为有序的源数组。
     * 可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
     * 示例 2：
     * <p>
     * 输入：nums = [2,1,3,4]
     * 输出：false
     * 解释：源数组无法经轮转得到 nums 。
     * <p>
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * solution: key the array have one discontinuous segment at most,
     * 1.get the flag
     * 2.compare nums[i] > nums[i+1]
     * O(N) O(1)
     * @Date: 2021/2/8 10:15
     */
    public boolean check(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        //confirm is asc  true indicate asc ;
        boolean res = (nums[0] < nums[nums.length - 1]);
        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] > nums[i + 1]) {
                //not asc first convert to asc ,
                if (res) {
                    return false;
                } else {
                    res = true;
                }
            }
        }
        return true;
    }

    /**
     * @Description:
     * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
     * <p>
     * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
     * <p>
     * 0 <= i < j < k <arr.length
     * |arr[i] - arr[j]| <= a
     * |arr[j] - arr[k]| <= b
     * |arr[i] - arr[k]| <= c
     * 其中 |x| 表示 x 的绝对值。
     * <p>
     * 返回 好三元组的数量 。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
     * 输出：4
     * 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
     * <p>
     * 提示：
     * <p>
     * 3 <= arr.length <= 100
     * 0 <= arr[i] <= 1000
     * 0 <= a, b, c <= 1000
     * solution:
     * 1.method of exhaustion i j k three cyclic ,add condition O(N3) O(1)
     * @Date: 2021/2/8 14:01
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * @Description:
     * 给你一个整数 n。
     * 请你先求出从 1到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），
     * 然后把数位和相等的数字放到同一个组中。
     * <p>
     * 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 13
     * 输出：4
     * 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
     * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
     * 示例 2：
     * <p>
     * 输入：n = 2
     * 输出：2
     * 解释：总共有 2 个大小为 1 的组 [1]，[2]。
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 10^4
     * solution:
     * 普通解法
     * 三步
     * 1.分组
     * 2.找出数字最多那组 返回个数
     * c 3.统计并列最多的 res
     * 用map 来实现吧
     * O(N+2M) N 遍历数1-n，计算十进制数位和耗时  M arr.length
     * O(N) arr的数组占用空间
     * @Date: 2021/2/8 14:25
     */
    public int countLargestGroup(int n) {
        int res = 0;
        int max = 0;
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int j = predictSum(i);
            arr[j]++;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        for (int j : arr) {
            if (j == max) {
                res++;
            }
        }
        return res;
    }

    /**
     * @Description: 官方map解答 存入hashMap的同时，记录max 最后遍历map求个数 返回  O(nlog(N)) O(N)
     * @Date: 2021/2/20 14:22
     *//*
    public int countLargestGroup(int n) {
        int res = 0;
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int key = predictSum(i);
            map.put(key,map.getOrDefault(key,0)+1);
            max = Math.max(max,map.get(key));
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                res++;
            }
        }
        return res;
    }*/

    /**
     * @Description:
     * add the sum of every position of t
     * @Date: 2021/2/20 13:43
     */
    private int predictSum(int t) {
        int res = 0;
        while (t > 0) {
            res += t % 10;
            t = t / 10;
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个m* n的矩阵grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。
     * <p>
     * 请你统计并返回grid中 负数 的数目。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     * 输出：8
     * 解释：矩阵中共有 8 个负数。
     * 示例 2：
     * <p>
     * 输入：grid = [[3,2],[1,0]]
     * 输出：0
     * 示例 3：
     * <p>
     * 输入：grid = [[1,-1],[-1,-1]]
     * 输出：3
     * 示例 4：
     * <p>
     * 输入：grid = [[-1]]
     * 输出：1
     * <p>
     * 约束：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -100 <= grid[i][j] <= 100
     * <p>
     * solution:
     * 直观的  遍历grid数组 统计负数个数 穷举法  O(N*M) O(1)
     * 优化版，非递增  每个二维数组 倒序统计负数个数  O(N*M) O(1)  利用数组的特性
     * 二分法 二分统计每一行负数个数
     * @Date: 2021/2/20 14:26
     */
    public int countNegatives(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] < 0) {
                res += (grid.length - i) * grid[i].length;
                break;
            }
            /*for (int j = grid[i].length - 1; j >= 0; j--) {
                if (grid[i][j] >= 0) {
                    break;
                }
                res++;
            }*/
            res += countNegativesForEachArr(grid[i]);
        }
        return res;
    }

    // 1 1 -1 -2
    public int countNegativesForEachArr(int[] grid) {
        int left = 0;
        int right = grid.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (grid[mid] < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return  grid.length - left;
    }

    /**
     * @Description:
     * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
     * <p>
     * 目标数组 target 最初为空。
     * 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
     * 重复上一步，直到在 nums 和 index 中都没有要读取的元素。
     * 请你返回目标数组。
     * <p>
     * 题目保证数字插入位置总是存在。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [0,1,2,3,4], index = [0,1,2,2,1]
     * 输出：[0,4,1,3,2]
     * 解释：
     * nums       index     target
     * 0            0        [0]
     * 1            1        [0,1]
     * 2            2        [0,1,2]
     * 3            2        [0,1,3,2]
     * 4            1        [0,4,1,3,2]
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4,0], index = [0,1,2,3,0]
     * 输出：[0,1,2,3,4]
     * 解释：
     * nums       index     target
     * 1            0        [1]
     * 2            1        [1,2]
     * 3            2        [1,2,3]
     * 4            3        [1,2,3,4]
     * 0            0        [0,1,2,3,4]
     * 示例 3：
     * <p>
     * 输入：nums = [1], index = [0]
     * 输出：[1]
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length, index.length <= 100
     * nums.length == index.length
     * 0 <= nums[i] <= 100
     * 0 <= index[i] <= i
     * <p>
     * solution: 按照题意
     * wo shabile 用list实现 有可用的api  但我不知道  list.add()在指定位置添加元素 元素后移
     * a.根据题意add元素  b.list赋值给target数组  O(N) O(N)
     * @Date: 2021/2/20 14:47
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] target = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        for (int i = 0; i < target.length; i++) {
            target[i] = list.get(i);
        }
        return target;
    }

    /**
     * @Description:
     * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
     * <p>
     * 输入为三个整数：day、month 和year，分别表示日、月、年。
     * <p>
     * 您返回的结果必须是这几个值中的一个
     * {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：day = 31, month = 8, year = 2019
     * 输出："Saturday"
     * 示例 2：
     * <p>
     * 输入：day = 18, month = 7, year = 1999
     * 输出："Sunday"
     * 示例 3：
     * <p>
     * 输入：day = 15, month = 8, year = 1993
     * 输出："Sunday"
     * <p>
     * 约束：给出的日期一定是在 1971 到 2100 年之间的有效日期。
     * solution: 魔法击败莫法  蔡勒公式
     * 1582年10月4日后：w = [c/4]-2c+y+[y/4]+[26*(m+1)/10]+d-1) mod 7 ; or w + 7;
     * 其中w为星期几，星期0为星期日，星期1为星期一，星期-1表示星期六，以此类推，
     * 所以最后算出来 mod 7的结果如果是负数 需要+7变为非负；
     * []表示取整；
     * c为[year/100] 即 年份的前两位数;
     * y为year%100 即 年份的后两位数；
     * m为月数，注意：如果是1、2月需要把它当成上一年的13、14月，例如1997年1月28日应写成1996年13月28日;
     * d为day；
     * O(1) O(1)
     * @Date: 2021/2/22 10:02
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] strArr = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int y = year, m = month;
        //1 2当成去年 13 14月处理
        if (month == 1 || month == 2) {
            --y;
            m += 12;
        }
        int cY = y / 100, yY = y - cY * 100;
        //关键公式
        int indexWeek = (cY / 4 - 2 * cY + yY + yY / 4 + 26 * (m + 1) / 10 + day - 1) % 7;
        //变负为正数
        if (indexWeek < 0)
            indexWeek += 7;
        return strArr[indexWeek];
    }


    /**
     * @Description:
     * 给你一个以行程长度编码压缩的整数列表nums。
     * <p>
     * 考虑每对相邻的两个元素
     * [freq, val] = [nums[2*i], nums[2*i+1]]（其中i >= 0），
     * 每一对都表示解压后子列表中有 freq个值为val的元素，
     * 你需要从左到右连接所有子列表以生成解压后的列表。
     * <p>
     * 请你返回解压后的列表。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：[2,4,4,4]
     * 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
     * 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
     * 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
     * 示例 2：
     * <p>
     * 输入：nums = [1,1,2,3]
     * 输出：[1,3,3]
     * <p>
     * solution: 以2为维度，遍历nums数组 添加 nums[n-1]个 nums[n]到list中 最后转为int[]
     * O(N*M) O(N)
     * @Date: 2021/2/22 10:56
     */
    /*public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < nums.length; i = i + 2) {
            for (int j = 0; j < nums[i - 1] ; j++) {
                list.add(nums[i]);
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }*/
    public int[] decompressRLElist(int[] nums) {
        int newLength = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i += 2) {
            newLength += nums[i];
        }
        int[] res = new int[newLength];
        for (int i = 1; i < nums.length; i = i + 2) {
            for (int j = 0; j < nums[i - 1]; j++) {
                res[index++] = nums[i];
            }
        }
        return res;
    }

    /**
     * @Description:
     * 有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为n的循环数组code以及一个密钥k。
     * <p>
     * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会同时被替换。
     * <p>
     * 如果k > 0，将第i个数字用 接下来k个数字之和替换。
     * 如果k < 0，将第i个数字用 之前k个数字之和替换。
     * 如果k == 0，将第i个数字用0替换。
     * 由于code是循环的，code[n-1]下一个元素是code[0]，且code[0]前一个元素是code[n-1]。
     * <p>
     * 给你 循环数组code和整数密钥k，请你返回解密后的结果来拆除炸弹！
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：code = [5,7,1,4], k = 3
     * 输出：[12,10,16,13]
     * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
     * 示例 2：
     * <p>
     * 输入：code = [1,2,3,4], k = 0
     * 输出：[0,0,0,0]
     * 解释：当 k 为 0 时，所有数字都被 0 替换。
     * 示例 3：
     * <p>
     * 输入：code = [2,4,9,3], k = -2
     * 输出：[12,5,6,13]
     * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
     * <p>
     * solution:
     * 1.分情况处理
     * k == 0
     * k < 0
     * k > 0
     * 2.替换对应的数字  O(N*K) N code数组长度   O(N) code.length
     * @Date: 2021/2/22 11:18
     */
    public int[] decrypt(int[] code, int k) {
        if (k == 0) {
            return new int[code.length];
        }
        int[] codeNew = new int[code.length];
        for (int i = 0; i < code.length; i++) {
            codeNew[i] = code[i];
        }
        if (k > 0) {
            for (int i = 0; i < code.length; i++) {
                code[i] = 0;
                for (int j = 1; j <= k; j++) {
                    code[i] += codeNew[(i + j + code.length) % code.length];
                }
            }
        }
        if (k < 0) {
            for (int i = 0; i < code.length; i++) {
                code[i] = 0;
                for (int j = 1; j <= -k; j++) {
                    code[i] += codeNew[(i - j + code.length) % code.length];
                }
            }
        }
        return code;
    }

    /**
     * @Description:
     * 给定一个非空且只包含非负数的整数数组nums，
     * 数组的度的定义是指数组里任一元素出现频数的最大值。
     * <p>
     * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2：
     * <p>
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     * <p>
     * 约束：
     * nums.length 在1到 50,000 区间范围内。
     * nums[i] 是一个在 0 到 49,999 范围内的整数。
     * solution: 1.找出出现次数最多的 2.1个就直接请求出现第一个到出现最后一个的距离 多个就依次求，然后取最小的 ×掉 这种方法不可取
     * 应该在统计最多个数的时候，就把该数字的最短长度记录下
     * Map<Integer,String> nums[i] count:length(记录start end位置)
     * 官方解答 Map<Integer,int[]> int[] 3个元素  count:start:end 每次更新刷新 count end
     * 可能用到map  O(N) O(N)
     * @Date: 2021/2/22 15:08
     */
    public int findShortestSubArray(int[] nums) {
        int res = 5000;
        Map<Integer, int[]> map = new HashMap<>();
        //Map<Integer,int[]> int[] 3个元素  count:start:end 每次更新刷新 count end
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        //找最大count 度
        int maxCount = map.values().stream().mapToInt(x -> x[0]).max().getAsInt();
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] value = entry.getValue();
            if (value[0] == maxCount) {
                //求最小距离
                res = Math.min(res, value[2] - value[1] + 1);
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个正整数数组 arr，请你找出一个长度为 m 且在数组中至少重复 k 次的模式。
     * <p>
     * 模式 是由一个或多个值组成的子数组（连续的子序列），连续 重复多次但 不重叠 。 模式由其长度和重复次数定义。
     * <p>
     * 如果数组中存在至少重复 k 次且长度为 m 的模式，则返回 true ，否则返回 false 。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [1,2,4,4,4,4], m = 1, k = 3
     * 输出：true
     * 解释：模式 (4) 的长度为 1 ，且连续重复 4 次。注意，模式可以重复 k 次或更多次，但不能少于 k 次。
     * 示例 2：
     * <p>
     * 输入：arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
     * 输出：true
     * 解释：模式 (1,2) 长度为 2 ，且连续重复 2 次。另一个符合题意的模式是 (2,1) ，同样重复 2 次。
     * 示例 3：
     * <p>
     * 输入：arr = [1,2,1,2,1,3], m = 2, k = 3
     * 输出：false
     * 解释：模式 (1,2) 长度为 2 ，但是只连续重复 2 次。不存在长度为 2 且至少重复 3 次的模式。
     * <p>
     * 约束：
     * 2 <= arr.length <= 100
     * 1 <= arr[i] <= 100
     * 1 <= m <= 100
     * 2 <= k <= 100
     * solution:
     * 1.以m为度，判断m * k长度的连续子数组是否存在 （滑动窗口)  有则返回true 默认false
     * 2.如果数组长度小于 m * k 返回false
     * 3.arr toString  tmp toString  O(N * M * K)  O(1)
     * @Date: 2021/2/23 15:12
     */
   /* public boolean containsPattern(int[] arr, int m, int k) {
        if (arr.length < m * k) {
            return false;
        }

        Map<Integer,int[]> map = new HashMap<>();
        StringBuffer arrBuffer = new StringBuffer();
        StringBuffer tmpBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            arrBuffer.append(arr[i]).append(":");
        }
        String arrStr = arrBuffer.toString();
        for (int i = 0; i < arr.length - m; i++) {
            int[] tmp = new int[m];
            int n = i;
            for (int j = 0; j < tmp.length; j++) {
                tmp[j] = arr[n];
                n++;
            }
            if (map.containsValue(tmp)) {
                continue;
            }
            map.put(i,tmp);
            for (int b = 0; b < k; b++) {
                for (int j : tmp) {
                    tmpBuffer.append(j).append(":");
                }
            }
            if (arrStr.contains(tmpBuffer.toString())) {
                return true;
            }
            tmpBuffer = new StringBuffer();
        }
        return false;
    }*/

    //评论区百分比的答案 等下好好看下 他们的思路  O(N*M) O(1) 滑动窗口
    public boolean containsPattern(int[] arr, int m, int k) {
        if (arr.length < m * k) {
            return false;
        }

        int left = 0;
        int right = m;
        int leftIndex = left;
        int rightIndex = right;
        int count = 0;
        int ans = 1;
        while (rightIndex < arr.length) {

            int rightNum = arr[rightIndex++];
            int leftNum = arr[leftIndex++];

            if (leftNum == rightNum) {
                count++;
            } else {
                leftIndex = ++left;
                rightIndex = ++right;
                count = 0;
                ans = 1;
            }

            if (count == m) {
                leftIndex = left;
                count = 0;
                ans++;
            }
            if (ans == k) {
                return true;
            }
        }

        return false;
    }

    /**
     * @Description:
     * 环形公交路线上有n个站，按次序从0到n - 1进行编号。
     * 我们已知每一对相邻公交站之间的距离，distance[i]表示编号为i的车站和编号为(i + 1) % n的车站之间的距离。
     * <p>
     * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
     * <p>
     * 返回乘客从出发点start到目的地destination之间的最短距离。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：distance = [1,2,3,4], start = 0, destination = 1
     * 输出：1
     * 解释：公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。
     * <p>
     * 约束：
     * 1 <= n<= 10^4
     * distance.length == n
     * 0 <= start, destination < n
     * 0 <= distance[i] <= 10^4
     * <p>
     * solution: compare the asc order sum and the desc order sum
     * [arr[start]---arr[destination])
     * [arr[destination] -- arr[start])  (n + 1) % n
     * has start > destination forever?
     * O(N) O(1)
     * @Date: 2021/2/25 10:45
     */
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int res;
        if (start > destination) {
            int tmp = start;
            start = destination;
            destination = tmp;
        }
        int asc = 0;
        int desc = 0;
        for (int i = start; i < destination; i++) {
            asc += distance[i];
        }
        for (int i = destination; i < distance.length; i++) {
            desc += distance[i];
        }
        for (int i = 0; i < start; i++) {
            desc += distance[i];
        }
        res = Math.min(asc, desc);
        return res;
    }

    /**
     * @Description:
     * 给你一个长度固定的整数数组arr，
     * 请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     * <p>
     * 注意：请不要在超过该数组长度的位置写入元素。
     * <p>
     * 要求：请对输入的数组就地进行上述修改，不要从函数返回任何东西。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,0,2,3,0,4,5,0]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
     * 示例 2：
     * <p>
     * 输入：[1,2,3]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
     * <p>
     * 约束：
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     * solution:
     * 1.当arr[i] == 0 arr[i+1] = 0 i+2--arr.length  arr[n] = arr[n-1]
     * record the len++ ,encounter 0 +2  len >= arr.length break;
     * 2.转list处理 在0处,调用add(index,0)处理 最后做remove 加 toArray操作 直接超时
     * 3.a.计算重复有效的0 possibleDup b.处理arr数组
     * @Date: 2021/2/25 11:02
     */
    /*public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                for (int j = arr.length - 1; j >= i + 2; j--) {
                    arr[j] = arr[j - 1];
                }
                if (i + 1 < arr.length) {
                    arr[i + 1] = 0;
                }
                i++;
            }
            if (i >= arr.length) {
                break;
            }
        }
    }*/

    /**
     * 1.统计0个数
     * 2.倒序处理数组
     * O(N) N arr.length O(1)
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int possibleDup = 0;
        int len = arr.length - 1;
        //1.计算0的个数
        for (int i = 0; i <= len - possibleDup; i++) {
            if (arr[i] == 0) {
                if (i == len - possibleDup) {
                    arr[len] = 0;
                    len -= 1;
                    break;
                }
                possibleDup++;
            }
        }
        //2.倒序处理数组
        int last = len - possibleDup;
        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + possibleDup] = 0;
                possibleDup--;
                arr[i + possibleDup] = 0;
            } else {
                arr[i + possibleDup] = arr[i];
            }
        }
    }

    /**
     * @Description:
     * 给你一个非递减的有序整数数组，
     * 已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
     * <p>
     * 请你找到并返回这个整数
     * <p>
     * 
     * <p>
     * 示例：
     * <p>
     * 输入：arr = [1,2,2,6,6,6,6,7,10]
     * 输出：6
     * 
     * <p>
     * 提示：
     * <p>
     * 1 <= arr.length <= 10^4
     * 0 <= arr[i] <= 10^5
     * <p>
     * solution: 和众数有点类似
     * 1。arr.length / 4  向上取整 special
     * 2.arr是有序的 asc ，使用count计数 某个数 == special 就返回
     *
     * asc 滑动窗口来解决
     *
     * @Date: 2021/2/25 15:48
     */
    /*public int findSpecialInteger(int[] arr) {
        double special = (double) arr.length / (double) 4;
        int count = 0;
        int tmp = arr[0];
        for (int j : arr) {
            if (j == tmp) {
                count++;
            } else {
                tmp = j;
                count = 1;
            }
            if (count > special) {
                return tmp;
            }
        }
        return 0;
    }*/

    /**
     *  asc 滑动窗口来解决
     *  1.arr.length / 4  向上取整  得到m
     *  2.（0，m）以m长度为区间截取数据 满足 arr[i] = arr[i + m] 直接返回 arr[i]
     *  O(N) O(1)
     * @param arr
     * @return
     */
    public int findSpecialInteger(int[] arr) {
        int m = (int) Math.ceil((double)arr.length / (double)4);
        if (arr.length % 4 == 0) {
            m = arr.length / 4 + 1;
        }
        int leftIndex = 0;
        int rightIndex = m - 1;
        while (rightIndex < arr.length) {
            if (arr[leftIndex] == arr[rightIndex++]) {
                return arr[leftIndex];
            }
            leftIndex++;
        }
        return 0;
    }

    /**
     * @Description:
     * Alice and Bob have candy bars of different sizes:
     * A[i] is the size of the i-th bar of candy that Alice has,
     * and B[j] is the size of the j-th bar of candy that Bob has.
     * <p>
     * Since they are friends,
     * they would like to exchange one candy bar each
     * so that after the exchange,
     * they both have the same totalamount of candy.
     * (The total amount of candya person has is the sum of the sizes of candybars they have.)
     * <p>
     * Return an integer array ans
     * where ans[0] is the size of the candy bar
     * that Alice must exchange,
     * and ans[1] is the size of the candy bar
     * that Bob must exchange.
     * <p>
     * If there are multiple answers,
     * you may return any one of them.
     * It is guaranteed an answer exists.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: A = [1,1], B = [2,2]  2  4 2
     * Output: [1,2]
     * Example 2:
     * <p>
     * Input: A = [1,2], B = [2,3]  3  5
     * Output: [1,2]
     * Example 3:
     * <p>
     * Input: A = [2], B = [1,3]  2 4  2     2  3
     * Output: [2,3]
     * Example 4:
     * <p>
     * Input: A = [1,2,5], B = [2,4]   8 - 6  =  2   5 -4 =1
     * Output: [5,4]
     * 
     * <p>
     * Note:
     * <p>
     * 1 <= A.length <= 10000
     * 1 <= B.length <= 10000
     * 1 <= A[i] <= 100000
     * 1 <= B[i] <= 100000
     * It is guaranteed that Alice and Bob have different total amounts ofcandy.
     * It is guaranteed there exists ananswer.
     * <p>
     * solution: 1.compare the k = sum(A)- sum(B)
     * 0 return int[];
     * >0 A[i] - B[i] = k-1;
     * <0 B[i] - A[i] = abs(k) - 1
     * O(N2) O(1)
     * @Date: 2021/2/26 10:53
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] res = new int[2];
        int sumA = 0;
        int sumB = 0;
        for (int i : A) {
            sumA += i;
        }
        for (int i : B) {
            sumB += i;
        }
        for (int value : A) {
            for (int i : B) {
                if (sumA - value + i == sumB - i + value) {
                    return new int[]{value, i};
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * The Fibonacci numbers,
     * commonly denoted F(n) form a sequence,
     * called the Fibonacci sequence,
     * such that each number is the sum of the two preceding ones,
     * starting from 0 and 1. That is,
     * <p>
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: n = 2
     * Output: 1
     * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
     * Example 2:
     * <p>
     * Input: n = 3
     * Output: 2
     * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
     * <p>
     * Constraints:
     * <p>
     * 0 <= n <= 30
     * solution:
     * fib 不能用递归，，要超时 空间换取时间
     * O(N) O(N)
     * @Date: 2021/2/26 11:38
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * @Description:
     * 给你一个数组prices，
     * 其中prices[i]是商店里第i件商品的价格。
     * <p>
     * 商店里正在进行促销活动，如果你要买第i件商品，
     * 那么你可以得到与 prices[j] 相等的折扣，
     *其中j是满足j > i且prices[j] <= prices[i]的最小下标，
     * 如果没有满足条件的j，你将没有任何折扣。
     * <p>
     * 请你返回一个数组，数组中第i个元素是折扣后你购买商品 i最终需要支付的价格。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：prices = [8,4,6,2,3]
     * 输出：[4,2,4,2,3]
     * 解释：
     * 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
     * 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
     * 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
     * 商品 3 和 4 都没有折扣。
     * 示例 2：
     * <p>
     * 输入：prices = [1,2,3,4,5] 1+2+3+4+5
     * 输出：[1,2,3,4,5]
     * 解释：在这个例子中，所有商品都没有折扣。
     * 示例 3：
     * <p>
     * 输入：prices = [10,1,1,6]  10+1+1-1+1+6
     * 输出：[9,0,1,6]
     * 
     * <p>
     * 提示：
     * <p>
     * 1 <= prices.length <= 500
     * 1 <= prices[i] <= 10^3
     * <p>
     * solution:
     * 1.正常思维 逆序就一直有折扣，顺序往后延  找到折扣为止
     * O(N2) O(1)
     * @Date: 2021/2/26 11:45
     */
    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    prices[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    /**
     * @Description:
     * 给定一个范围在 1 ≤ a[i] ≤ n (n = 数组大小 ) 的 整型数组，
     * 数组中的元素一些出现了两次，另一些只出现一次。
     * <p>
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * <p>
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * <p>
     * 输出:
     * [5,6]
     * <p>
     * solution:
     * 1.桶排序 要借助一个O(N)arr空间
     * 2.把小数放大  O(N) O(1)
     * @Date: 2021/2/26 13:39
     */
    /*public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }*/
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }



    /**
     * @Description:
     * Given an arrayA of strings made only from lowercase letters,
     * return a list of all characters that show up in all strings within the list
     * (including duplicates).For example,
     * if a character occurs 3 timesin all strings but not 4 times,
     * you need to include that character three timesin the final answer.
     * <p>
     * You may return the answer in any order.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: ["bella","label","roller"] b 1  2 1 l 2 a 1
     * Output: ["e","l","l"]
     * Example 2:
     * <p>
     * Input: ["cool","lock","cook"]
     * Output: ["c","o"]
     * 
     * <p>
     * Note:
     * <p>
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     * A[i][j] is a lowercase letter
     * <p>
     * solution: 以 A[0]为目标, cool 比如这种   用hashMap存储  c 1,o 2,l 1;往后一直检测 count数取最小的，最后在打印
     * 英文单词长度  M 数组长度 N  O(M * N) O(M2)
     * @Date: 2021/2/26 14:20
     */
    /*public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        if (A.length == 1) {
            char[] chars = A[0].toCharArray();
            for (char aChar : chars) {
                res.add(Character.toString(aChar));
            }
            return res;
        }
        char[] arr = A[0].toCharArray();
        for (char c : arr) {
            if (map.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 1; i < A.length; i++) {
            char[] charArray = A[i].toCharArray();
            Map<Character, Integer> tmpMap = new HashMap<>();
            for (char c : charArray) {
                if (tmpMap.containsKey(c)) {
                    tmpMap.put(c, tmpMap.getOrDefault(c, 0) + 1);
                } else {
                    tmpMap.put(c, 1);
                }
            }
            for (char c : arr) {
                if (map.containsKey(c) && !tmpMap.containsKey(c)) {
                    map.put(c, 0);
                } else {
                    map.put(c, Math.min(map.get(c), tmpMap.get(c)));
                }
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                res.add(Character.toString(entry.getKey()));
            }
        }
        return res;
    }*/

    //O(M*N*Q) N数组A长度 M第一个字符串长度 Q每个字符串长度 O(N)
   /* public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        //先放第一个进去
        String tmpStr = A[0];
        for (int i = 0; i < tmpStr.length(); i++) {
            char key = tmpStr.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            } else {
                map.put(key, 1);
            }
        }
        List<Character> charList = new ArrayList<>(map.keySet());
        List<Character> willRemoved = new ArrayList<>();

        //遍历A 开始更新 + 过滤
        if (A.length > 1) {
            for (int i = 1; i < A.length; i++) {
                String str = A[i];
                for (Character character : charList) {
                    if (!str.contains(character.toString())) {
                        map.remove(character);
                        continue;
                    }
                    map.put(character,
                                Math.min(calculateCharCount(str, character), map.get(character)));
                }
                charList.removeIf(character -> !str.contains(character.toString()));
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++) {
                res.add(key.toString());
            }
        }
        return res;
    }

    public int calculateCharCount(String str, Character c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) count++;
        }
        return count;
    }*/

        //拉姆达表达式求解  O(N*M) N A的长度  M每个字符串的长度 O(N)
        public List<String> commonChars(String[] A) {
           return Arrays.stream(A)
                   //转CharArr
                   .map(String::toCharArray)
                   //bucket sort
                   .map(chars -> {
                int[] flags = new int[26];
                for (char c : chars) {
                    flags[c - 'a']++;
                }
                return flags;
            }).reduce((f1,f2) -> {
                //筛选最小值
                       int[] arr = new int[26];
                for (int i = 0; i < 26; i++) {
                    arr[i] = Math.min(f1[i], f2[i]);
                }
                return arr;
            }).map(arr -> {
                //组装res
                List<String> res = new ArrayList<>();
                for (int i = 0; i < 26; i++) {
                    for (int j = 0; j < arr[i]; j++) {
                        res.add(String.valueOf((char)(i + 'a')));
                    }
                }
                return res;
            }).get();
        }

    /**
     * @Description:
     * Given an array of integers arr,
     * a lucky integer is an integer which has a frequency in the array equal to its value.
     * <p>
     * Return a lucky integerin the array.
     * If there are multiple lucky integers return the largest of them.
     * If there is no luckyinteger return -1.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: arr = [2,2,3,4]
     * Output: 2
     * Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
     * Example 2:
     * <p>
     * Input: arr = [1,2,2,3,3,3]
     * Output: 3
     * Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
     * Example 3:
     * <p>
     * Input: arr = [2,2,2,3,3]
     * Output: -1
     * Explanation: There are no lucky numbers in the array.
     * Example 4:
     * <p>
     * Input: arr = [5]
     * Output: -1
     * Example 5:
     * <p>
     * Input: arr = [7,7,7,7,7,7,7]
     * Output: 7
     * 
     * <p>
     * Constraints:
     * <p>
     * 1 <= arr.length <= 500
     * 1 <= arr[i] <= 500
     * <p>
     * solution:
     * 1.hashMap存储 key value 对比key和value是否相等，找最大值
     * 2.桶排序统计 最大值 O(N) O(N)
     * @Date: 2021/2/26 17:06
     */
    public int findLucky(int[] arr) {
        int res = -1;
        int[] bucket = new int[500];
        for (int i : arr) {
            bucket[i]++;
        }
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (i == bucket[i] && bucket[i] != 0) {
                return i;
            }
        }
        return res;
    }

    /**
     * @Description:
     * Given an integer n,
     * return any array containing n unique integers such that they add up to 0.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: n = 5
     * Output: [-7,-1,1,3,4]
     * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
     * Example 2:
     * <p>
     * Input: n = 3
     * Output: [-1,0,1]
     * Example 3:
     * <p>
     * Input: n = 1
     * Output: [0]
     * 
     * <p>
     * Constraints:
     * <p>
     * 1 <= n <= 1000
     * <p>
     * solution: 0 1 -1 2 -2 3 -3这样子取  n = 1 取0  n=2 -1 1
     * odd 0 然后对半分  even 直接对半分 O(N) O(N)
     * @Date: 2021/2/26 17:16
     */
    public int[] sumZero(int n) {
        if (n == 0) {
            return new int[]{0};
        }
        int[] res = new int[n];
        int index = 1;
        int newIndex = -1;
        int k = n / 2;
        if (n % 2 == 1) {
            res[n / 2] = 0;
            k += 1;
        }
        for (int i = 0; i < n / 2; i++) {
            res[i] = index++;
        }
        for (int i = k; i < n; i++) {
            res[i] = newIndex--;
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个整数数组nums，
     * 请你返回其中位数为偶数的数字的个数。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [12,345,2,6,7896]
     * 输出：2
     * 解释：
     * 12 是 2 位数字（位数为偶数）
     * 345 是 3 位数字（位数为奇数）
     * 2 是 1 位数字（位数为奇数）
     * 6 是 1 位数字 位数为奇数）
     * 7896 是 4 位数字（位数为偶数）
     * 因此只有 12 和 7896 是位数为偶数的数字
     * 示例 2：
     * <p>
     * 输入：nums = [555,901,482,1771]
     * 输出：1
     * 解释：
     * 只有 1771 是位数为偶数的数字。
     * 
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 10^5
     * <p>
     * solution: 判断奇偶数，计数count 最后return
     * @Date: 2021/2/26 17:33
     */
    public int findNumbers(int[] nums) {
        int res = 0;
        int count;
        for (int num : nums) {
            count = 1;
            while (num >= 10) {
                num /= 10;
                count++;
            }
            if (count % 2 == 0) {
                res++;
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个整数数组nums，
     * 请编写一个能够返回数组 “中心索引” 的方法。
     * <p>
     * 数组 中心索引 是数组的一个索引，
     * 其左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 如果数组不存在中心索引，返回 -1 。
     * 如果数组有多个中心索引，应该返回最靠近左边的那一个。
     * <p>
     * 注意：中心索引可能出现在数组的两端。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 中心索引是 3 。
     * 左侧数之和 (1 + 7 + 3 = 11)，
     * 右侧数之和 (5 + 6 = 11) ，二者相等。
     * 示例 2：
     * <p>
     * 输入：nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心索引。
     * 示例 3：
     * <p>
     * 输入：nums = [2, 1, -1]
     * 输出：0
     * 解释：
     * 中心索引是 0 。
     * 索引 0 左侧不存在元素，视作和为 0 ；
     * 右侧数之和为 1 + (-1) = 0 ，二者相等。
     * 
     * <p>
     * 提示：
     * <p>
     * nums 的长度范围为[0, 10000]。
     * 任何一个nums[i] 将会是一个范围在[-1000, 1000]的整数。
     * <p>
     * solution: res = arr[i] sum(nums) - res / 2 = sum[0...i-1]
     * @Date: 2021/2/26 17:44
     */
    public int pivotIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int sum = 0;
        int newSum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum - nums[i] == 2 * newSum) {
                return i;
            }
            newSum += nums[i];
        }
        return -1;
    }

    /**
     * @Description:
     * A 和B在一个3x3的网格上玩井字棋。
     * <p>
     * 井字棋游戏的规则如下：
     * <p>
     * 玩家轮流将棋子放在空方格 (" ") 上。
     * 第一个玩家 A 总是用"X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。
     * "X" 和 "O" 只能放在空方格中，而不能放在已经被占用的方格上。
     * 只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。
     * 如果所有方块都放满棋子（不为空），游戏也会结束。
     * 游戏结束后，棋子无法再进行任何移动。
     * 给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列），
     * 它按照 A 和 B 的行动顺序（先 A 后 B）记录了两人各自的棋子位置。
     * <p>
     * 如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；
     * 如果仍会有行动（游戏未结束），则返回 "Pending"。
     * <p>
     * 你可以假设moves都 有效（遵循井字棋规则），网格最初是空的，A 将先行动。
     * <p>
     * solution: 3字棋 和五子棋一样的规则，moves记录 A B的行动，A先行。 3点一线 斜率相同
     * 判断A B是否有，哪个有则返回哪个获胜 否则返回 Pending 或者 Draw(无法在走下去了)
     * moves.size <= 5 返回Pending
     * moves >= 6 分别统计A B
     * moves = even  A B 对半分
     * moves = odd A move.size/2 + 1 B moves/
     * 计算 A  B的情况
     * @Date: 2021/3/1 10:41
     */
    /*public String tictactoe(int[][] moves) {
        int aLen = 0;
        int bLen = 0;
        int[][] arr;
        int[][] brr;
        if (moves.length < 5) {
            return "Pending";
        }
        //确定  A B  size
        if (moves.length % 2 == 0) {
            aLen = moves.length / 2;
            bLen = aLen;
        } else {
            aLen = moves.length / 2 + 1;
            bLen = moves.length / 2;
        }
        arr = new int[aLen][2];
        brr = new int[bLen][2];
        int indexA = 0;
        int indexB = 0;
        //A B 填入值
        for (int i = 0; i <= moves.length - 1; i += 2) {
            arr[indexA++] = moves[i];
            if (moves.length % 2 == 1 && i == moves.length - 1) {
                break;
            }
            brr[indexB++] = moves[i + 1];
        }

        //A B 判断A B 是否完成
        if (judgeIsComplete(arr)) {
            return "A";
        }
        if (judgeIsComplete(brr)) {
            return "B";
        }
        if (moves.length == 9) {
            return "Draw";
        } else {
            return "Pending";
        }
    }

    public Boolean judgeIsComplete(int[][] arr) {
        if (arr.length < 3) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr.length; k++) {
                    if (i != j && j != k && i != k) {
                        if ((arr[j][0] - arr[i][0]) * (arr[k][1] - arr[j][1])
                                == (arr[k][0] - arr[j][0]) * (arr[j][1] - arr[i][1])) {
                            return true;
                        }
                        if ((arr[i][0] - arr[j][0]) * (arr[k][1] - arr[i][1])
                                == (arr[k][0] - arr[i][0]) * (arr[i][1] - arr[j][1])) {
                            return true;
                        }
                        if ((arr[k][0] - arr[i][0]) * (arr[j][1] - arr[k][1])
                                == (arr[j][0] - arr[k][0]) * (arr[k][1] - arr[i][1])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }*/

    /**
     * 思路：
     * 1.把九宫格 假设成9个二进制位  只有8种情况，可以取得游戏胜利 3横 3竖 2对角
     * 分别对应10进制 arr[] 7,448,56,73,146,292,84,273
     * 2.moves 0开始  偶数对应 A 奇数对应 B   每次与 a ^= 1<< (3x + y) 获取 1<< 3x+Y 获取所走的1 异或 保存走的 1
     * 3.a b 最后的值，与  arr相与  & 如果为 arr中的元素,则游戏结束
     * 4.特殊情况  == 9 ? Draw :  Pending
     * O(N) N 9 O(M) M 8
     * @param moves
     * @return
     */
    public String tictactoe(int[][] moves) {
        String res = "";
        int a = 0, b = 0;
        int[] arr = new int[] {7,448,56,73,146,292,84,273};
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                a ^= 1<<(3 * moves[i][0] + moves[i][1]);
            } else {
                b ^= 1<<(3 * moves[i][0] + moves[i][1]);
            }
        }

        for (int i : arr) {
            if ((i & a) == i) {
                return "A";
            }
            if ((i & b) == i) {
                return "B";
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    /**
     * @Description:
     * You are given an array of stringswordsand a stringchars.
     * <p>
     * A string is goodifit can be formed bycharacters from chars
     * (each charactercan only be used once).
     * <p>
     * Return the sum of lengths of all good strings in words.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: words = ["cat","bt","hat","tree"], chars = "atach"
     * Output: 6
     * Explanation:
     * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
     * Example 2:
     * <p>
     * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
     * Output: 10
     * Explanation:
     * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
     * 
     * <p>
     * Note:
     * <p>
     * 1 <= words.length <= 1000
     * 1 <= words[i].length, chars.length<= 100
     * All strings contain lowercase English letters only.
     * <p>
     * solution:
     * 1.check every word ,judge whether can be make up by the chars, sum+= result
     * 2.hashMap  each c num of word must <= c the num of chars
     * a little shit
     * @Date: 2021/3/1 14:04
     */
    /*public int countCharacters(String[] words, String chars) {
        int res = 0;
        for (String word : words) {
            res += judgeMakeUpWords(word, chars);
        }
        return res;
    }

    public int judgeMakeUpWords(String word,String chars) {
        if (word.length() > chars.length()) {
            return 0;
        }
        char[] chars1 = word.toCharArray();
        for (char c : chars1) {
            if (chars.contains(Character.toString(c))) {
                chars = chars.replaceFirst(Character.toString(c), "");
            } else {
                return 0;
            }
        }
        return word.length();
    }*/
    public int countCharacters(String[] words, String chars) {
        int res = 0;
        //handle chars
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        //calculate each word of the words array
        for (String word : words) {
            Map<Character, Integer> map2 = new HashMap<>();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                map2.put(c, map2.getOrDefault(c, 0) + 1);
            }
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (map2.getOrDefault(c, 0) > map1.getOrDefault(c, 0)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res += word.length();
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你两个整数数组arr1，arr2和一个整数d
     * ，请你返回两个数组之间的距离值。
     * <p>
     * 「距离值」定义为符合此距离要求的元素数目：对于元素arr1[i]
     * ，不存在任何元素arr2[j]满足 |arr1[i]-arr2[j]| <= d 。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
     * 输出：2
     * 解释：
     * 对于 arr1[0]=4 我们有：
     * |4-10|=6 > d=2
     * |4-9|=5 > d=2
     * |4-1|=3 > d=2
     * |4-8|=4 > d=2
     * 所以 arr1[0]=4 符合距离要求
     * <p>
     * 对于 arr1[1]=5 我们有：
     * |5-10|=5 > d=2
     * |5-9|=4 > d=2
     * |5-1|=4 > d=2
     * |5-8|=3 > d=2
     * 所以 arr1[1]=5 也符合距离要求
     * <p>
     * 对于 arr1[2]=8 我们有：
     * |8-10|=2 <= d=2
     * |8-9|=1 <= d=2
     * |8-1|=7 > d=2
     * |8-8|=0 <= d=2
     * 存在距离小于等于 2 的情况，不符合距离要求
     * <p>
     * 故而只有 arr1[0]=4 和 arr1[1]=5 两个符合距离要求，距离值为 2
     * <p>
     * 提示：
     * <p>
     * 1 <= arr1.length, arr2.length <= 500
     * -10^3 <= arr1[i], arr2[j] <= 10^3
     * 0 <= d <= 100
     * <p>
     * solution:
     * 1.穷举法 按照题意，arr1 每一个元素 和 arr2的每一个元素做abs运算，如果满足 res++ 最后返回res
     * O(M*N) O(1)
     * 2.arr2 排序 +二分查找  O(log(M) * N) O(1)
     * @Date: 2021/3/4 14:14
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int res = 0;
        boolean flag = true;
        for (int i : arr1) {
            for (int j : arr2) {
                if (Math.abs(i - j) <= d) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
            flag = true;
        }
        return res;
    }

    /**
     * @Description:
     * 有一个自行车手打算进行一场公路骑行，
     * 这条路线总共由n + 1个不同海拔的点组成。自行车手从海拔为 0的点0开始骑行。
     * <p>
     * 给你一个长度为 n的整数数组gain，其中 gain[i]是点 i和点 i + 1的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：gain = [-5,1,5,0,-7]
     * 输出：1
     * 解释：海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
     * 示例 2：
     * <p>
     * 输入：gain = [-4,-3,-2,-1,4,3,2]
     * 输出：0
     * 解释：海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
     * 
     * <p>
     * 提示：
     * <p>
     * n == gain.length
     * 1 <= n <= 100
     * -100 <= gain[i] <= 100
     * <p>
     * solution: gain数组做求和操作，寻找最大值 O(N) O(1)
     * @Date: 2021/3/4 14:44
     */
    public int largestAltitude(int[] gain) {
        int res = 0;
        int sum = 0;
        for (int i : gain) {
            sum += i;
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * @Description:
     * 给定一个二进制矩阵
     * A，我们想先水平翻转图像，然后反转图像并返回结果。
     * <p>
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转[1, 1, 0]的结果是[0, 1, 1]。
     * <p>
     * 反转图片的意思是图片中的0全部被1替换，1全部被0替换。例如，反转[0, 1, 1]的结果是[1, 0, 0]。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：[[1,1,0],[1,0,1],[0,0,0]]
     * 输出：[[1,0,0],[0,1,0],[1,1,1]]
     * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
     * 示例 2：
     * <p>
     * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * 
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j]<=1
     * <p>
     * solution:
     * 1.水平翻转  a[i][j] a[i][a[i].length - i]
     * 2.反向翻转 1--》0 0——》1
     * O(N) O(1)
     * @Date: 2021/3/4 14:53
     */
    public int[][] flipAndInvertImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length / 2; j++) {
                int tmp = image[i][j];
                image[i][j] = image[i][image[i].length - 1 - j];
                image[i][j] ^= 1;
                image[i][image[i].length - 1 - j] = tmp;
                image[i][image[i].length - 1 - j] ^= 1;
            }
        }
        return image;
    }

    /**
     * @Description:
     * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
     * <p>
     * nums[0] = 0
     * nums[1] = 1
     * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
     * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
     * 返回生成数组 nums 中的 最大 值。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 7
     * 输出：3
     * 解释：根据规则：
     * nums[0] = 0
     * nums[1] = 1
     * nums[(1 * 2) = 2] = nums[1] = 1
     * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
     * nums[(2 * 2) = 4] = nums[2] = 1
     * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
     * nums[(3 * 2) = 6] = nums[3] = 2
     * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
     * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
     * 示例 2：
     * <p>
     * 输入：n = 2
     * 输出：1
     * 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
     * 示例 3：
     * <p>
     * 输入：n = 3
     * 输出：2
     * 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
     * 
     * <p>
     * 提示：
     * <p>
     * 0 <= n <= 100
     * <p>
     * solution:
     * 1.根据题意，一次生成数 求最大值
     * 2.数学归纳法，现在没想出来 肯定有数学公式之类的
     * O(N) O(N)
     * @Date: 2021/3/4 15:54
     */
    public int getMaximumGenerated(int n) {
        int[] arr = new int[n + 1];
        if (n <= 1) {
            return n;
        }
        arr[0] = 0;
        arr[1] = 1;
        int max = 1;
        for (int i = 0; i < n + 1; i++) {
            if (i % 2 == 0) {
                arr[i] = arr[i / 2];
            } else {
                arr[i] = arr[i / 2] + arr[i / 2 + 1];
            }
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    /**
     * @Description:
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     * <p>
     * 请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。
     * <p>
     * 注意，当一组学生被选中时，他们之间可以以任何可能的方式重新排序，而未被选中的学生应该保持不动。
     * <p>
     * 
     * <p>
     * 示例：
     * <p>
     * 输入：heights =[1,1,4,2,1,3]
     * 输出：3
     * 解释：
     * 当前数组：[1,1,4,2,1,3]
     * 目标数组：[1,1,1,2,3,4]
     * 在下标 2 处（从 0 开始计数）出现 4 vs 1 ，所以我们必须移动这名学生。
     * 在下标 4 处（从 0 开始计数）出现 1 vs 3 ，所以我们必须移动这名学生。
     * 在下标 5 处（从 0 开始计数）出现 3 vs 4 ，所以我们必须移动这名学生。
     * 示例 2：
     * <p>
     * 输入：heights = [5,1,2,3,4]
     * 输出：5
     * 示例 3：
     * <p>
     * 输入：heights = [1,2,3,4,5]
     * 输出：0
     * 
     * <p>
     * 提示：
     * <p>
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     * <p>
     * solution:
     * 桶排序 与排序不同的 Count++，反正就是排序后找不同
     * O(N) N arr.length O(M) M 101
     * @Date: 2021/3/4 16:12
     */
    /*public int heightChecker(int[] heights) {
        int count = 0;
        int[] arr = Arrays.copyOf(heights, heights.length);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != heights[i]) {
                count++;
            }
        }
        return count;
    }*/
    public int heightChecker(int[] heights) {
        int count = 0;
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        for (int i = 0; i < heights.length; ) {
            for (int j = 0; j < arr.length; j++) {
                while (arr[j] > 0) {
                    if (j != heights[i]) {
                        count++;
                    }
                    i++;
                    arr[j]--;
                }
            }

        }
        return count;
    }

    /**
     * @Description:
     * 给你一个数组nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。
     * <p>
     * 换而言之，对于每个nums[i]你必须计算出有效的j的数量，
     * 其中 j 满足j != i 且 nums[j] < nums[i]。
     * <p>
     * 以数组形式返回答案。
     * <p>
     * 
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     * <p>
     * 提示：
     * <p>
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     * solution:
     * 1.思路比较清晰，traver the nums,and calculate each element to the new Array O(N2) O(N)
     * 2.排序完成后 在处理 HashMap 这个不得行  pass掉
     * 3.桶排序  个数为前面的 元素和
     * @Date: 2021/3/8 10:28
     */
    /*public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] > nums[j]) {
                   count++;
                }
            }
            res[i] = count;
        }
        return res;
    }*/
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] cnt = new int[101];
        int[] ret = new int[nums.length];
        int n = nums.length;
        for (int num : nums) {
            cnt[num]++;
        }
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ret;
    }

    /**
     * @Description: 示例 1：
     * <p>
     * 输入：x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
     * 输出：2
     * 解释：所有点中，[3,1]，[2,4] 和 [4,4] 是有效点。
     * 有效点中，[2,4] 和 [4,4] 距离你当前位置的曼哈顿距离最小，都为 1 。[2,4] 的下标最小，所以返回 2 。
     * 示例 2：
     * <p>
     * 输入：x = 3, y = 4, points = [[3,4]]
     * 输出：0
     * 提示：答案可以与你当前所在位置坐标相同。
     * <p>
     * solution:
     * 1.找到满足条件的元素
     * 2.求出距离 x y最近距离的元素集合
     * 3.找出下标最小的
     * 4.没有满足条件的返回 -1
     * O(N) o(1)
     * @Date: 2021/3/8 15:54
     */
    public int nearestValidPoint(int x, int y, int[][] points) {
        int res = Integer.MAX_VALUE;
        int minDis = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                flag = true;
                int dis = calculateDistance(x, y, points[i]);
                if (dis < minDis) {
                    minDis = dis;
                    res = i;
                }
                if (dis == minDis) {
                    res = Math.min(res, i);
                }
            }
        }
        if (!flag) {
            res = -1;
        }
        return res;
    }

    public int calculateDistance(int x, int y, int[] arr) {
        return Math.abs(x - arr[0]) + Math.abs(y - arr[1]);
    }

    /**
     * @Description:
     * 包含整数的二维矩阵 M 表示一个图片的灰度。
     * 你需要设计一个平滑器来让每一个单元的灰度成为平均灰度(向下舍入) ，
     * 平均灰度的计算是周围的8个单元和它本身的值求平均，
     * 如果周围的单元格不足八个，则尽可能多的利用它们。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [[1,1,1],
     * [1,0,1],
     * [1,1,1]]
     * 输出:
     * [[0, 0, 0],
     * [0, 0, 0],
     * [0, 0, 0]]
     * 解释:
     * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
     * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
     * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     * 注意:
     * <p>
     * 给定矩阵中的整数范围为 [0, 255]。
     * 矩阵的长和宽的范围均为[1, 150]。
     * <p>
     * solution:
     * 题目终于读懂了 我擦
     * 计算上下左右 以及斜对角的和
     * 和 个数  sum/count 向下取整  就是最后的值
     * O(N) N M数组的中长度 O(M) M是ans数组的空间
     * @Date: 2021/3/9 11:02
     */
    public int[][] imageSmoother(int[][] M) {
        int R = M.length, C = M[0].length;
        int[][] ans = new int[R][C];

        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r - 1; nr <= r + 1; ++nr) {
                    for (int nc = c - 1; nc <= c + 1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                }
                ans[r][c] /= count;
            }
        return ans;
    }

    /**
     * @Description:
     * Implement an algorithm to determine if a string has all unique characters.
     * What if you cannot use additional data structures?
     * <p>
     * Example 1:
     * <p>
     * Input: s = "leetcode"
     * Output: false
     * Example 2:
     * <p>
     * Input: s = "abc"
     * Output: true
     * 
     * <p>
     * Note:
     * <p>
     * 0 <= len(s) <= 100
     * <p>
     * solution:
     * 1.astr toArray
     * 2.Bucket sort (ascll码表)
     * 3.if element > 1 false else true
     * O(N) O(N)
     * @Date: 2021/3/9 13:51
     */
    public boolean isUnique(String astr) {
        char[] chars = astr.toCharArray();
        int[] tmp = new int[100];
        for (char c : chars) {
            tmp['z' - c]++;
        }
        for (int i : tmp) {
            if (i > 1) {
                return false;
            }
        }
        return true;
    }


    /**
     * @Description:
     * Given the array candies and the integer extraCandies,
     * where candies[i] represents the number of candies that the ith kid has.
     * <p>
     * For each kid check if there is a way to distribute extraCandies among the kids
     * such that he or she can have the greatest number of candies among them.
     * Notice that multiple kids can have the greatest number of candies.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: candies = [2,3,5,1,3], extraCandies = 3
     * Output: [true,true,true,false,true]
     * Explanation:
     * Kid 1 has 2 candies and if he or she receives all extra candies (3) will have 5 candies --- the greatest number of candies among the kids.
     * Kid 2 has 3 candies and if he or she receives at least 2 extra candies will have the greatest number of candies among the kids.
     * Kid 3 has 5 candies and this is already the greatest number of candies among the kids.
     * Kid 4 has 1 candy and even if he or she receives all extra candies will only have 4 candies.
     * Kid 5 has 3 candies and if he or she receives at least 2 extra candies will have the greatest number of candies among the kids.
     * <p>
     * Constraints:
     * <p>
     * 2 <= candies.length <= 100
     * 1 <= candies[i] <= 100
     * 1 <= extraCandies <= 50
     * <p>
     * solution:
     * 1.find greatest num
     * 2.each element + extraCandies compare with it
     * 3.return the array
     * O(N) O(1)
     * @Date: 2021/3/9 14:04
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        for (int candy : candies) {
            if ((candy + extraCandies) >= max) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    /**
     * @Description:
     * In a given integer array nums,
     * there is always exactly one largest element.
     * <p>
     * Find whether the largest element in the array
     * is at least twice as much as every other number
     * in the array.
     * <p>
     * If it is, return the index of the largest element,
     * otherwise return -1.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [3, 6, 1, 0]
     * Output: 1
     * Explanation: 6 is the largest integer, and for every other number in the array x,
     * 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
     * 
     * <p>
     * Example 2:
     * <p>
     * Input: nums = [1, 2, 3, 4]
     * Output: -1
     * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
     * 
     * <p>
     * Note:
     * <p>
     * nums will have a length in the range [1, 50].
     * Every nums[i] will be an integer in the range [0, 99].
     * <p>
     * solution:
     * 1.find the max num and record the index
     * 2. judge twice other element compare with max
     * 3.return index
     * O(N) O(1)
     * @Date: 2021/3/9 15:05
     */
    public int dominantIndex(int[] nums) {
        int res = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                res = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != res && nums[i] * 2 > max) {
                return -1;
            }
        }
        return res;
    }


    /**
     * @Description:
     * Given an unsorted array of integers nums,
     * return the length of the longest continuous increasing subsequence (i.e. subarray).
     * The subsequence must be strictly increasing.
     * <p>
     * A continuous increasing subsequence is defined by two indices l and r (l < r)
     * such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and
     * for each l <= i < r, nums[i] < nums[i + 1].
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,3,5,4,7]
     * Output: 3
     * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
     * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
     * 4.
     * Example 2:
     * <p>
     * Input: nums = [2,2,2,2,2]
     * Output: 1
     * Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
     * increasing.
     * 
     * <p>
     * Constraints:
     * <p>
     * 0 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * <p>
     * solution:
     * 1.traver the nums array ,record the count
     * 2.compare the max count ,finally return the count
     * O(N) O(1)
     * @Date: 2021/3/9 15:14
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int res = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 1;
            }
        }
        return res;
    }

    /**
     * @Description:
     * Given a m * n matrix of distinct numbers,
     * return all lucky numbers in thematrix in any order.
     * <p>
     * A lucky number is an element of the matrix
     * such that it is the minimum element in its row and
     * maximum in its column.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
     * Output: [15]
     * Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column
     * Example 2:
     * <p>
     * Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
     * Output: [12]
     * Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
     * <p>
     * solution:
     * 1.穷举法  遍历n个元素  行m 列j 依次检查  O(N*M) N 是matrix数组长度 M是该元素 row + column O(1)
     * 2.寻找每一行最小的数 和每一列最大的数，求并集 O(N) row column rowArr。size * columnArr的最大值 O(M) 新建数组空间
     * @Date: 2021/3/10 9:59
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int[] rowArr = new int[matrix.length];
        int[] columnArr = new int[matrix[0].length];
        int indexRow = 0;
        int indexColumn = 0;
        for (int[] ints : matrix) {
            int min = ints[0];
            for (int j = 0; j < matrix[0].length; j++) {
                min = Math.min(min, ints[j]);
            }
            rowArr[indexRow++] = min;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            int max = matrix[0][i];
            for (int j = 0; j < matrix.length; j++) {
                max = Math.max(max, matrix[j][i]);
            }
            columnArr[indexColumn++] = max;
        }
        for (int k : rowArr) {
            for (int value : columnArr) {
                if (k == value) {
                    res.add(k);
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * A magic index in an array A[0...n-1] is defined to be an index
     * such that A[i] = i. Given a sorted array ofintegers, write a method to find a magic index,
     * if one exists, in array A. If not, return -1.
     * If there are more than one magic index, return the smallest one.
     * <p>
     * Example1:
     * <p>
     * Input: nums = [0, 2, 3, 4, 5]
     * Output: 0
     * Example2:
     * <p>
     * Input: nums = [1, 1, 1]
     * Output: 1
     * Note:
     * <p>
     * 1 <= nums.length <= 1000000
     * This problem is thefollow-up of the original problem in the book, i.e.the values arenot distinct.
     * <p>
     * solution:
     * 1. traver the nums array,find the num equals the index
     * 2.if equals return if not exists return -1
     * O(N) O(1)
     * @Date: 2021/3/10 10:34
     */
    public int findMagicIndex(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }
        return res;
    }

    /**
     * @Description:
     * Given two integer arrays of equal length target and arr.
     * <p>
     * In one step, you can select any non-empty sub-array of arr and reverse it.
     * You are allowed to make any number of steps.
     * <p>
     * Return True if you can make arr equal to target,
     * or False otherwise.
     * <p>
     * 
     * <p>
     * Example 1:
     * <p>
     * Input: target = [1,2,3,4], arr = [2,4,1,3]
     * Output: true
     * Explanation: You can follow the next steps to convert arr to target:
     * 1- Reverse sub-array [2,4,1], arr becomes [1,4,2,3]
     * 2- Reverse sub-array [4,2], arr becomes [1,2,4,3]
     * 3- Reverse sub-array [4,3], arr becomes [1,2,3,4]
     * There are multiple ways to convert arr to target, this is not the only way to do so.
     * Example 2:
     * <p>
     * Constraints:
     * <p>
     * target.length == arr.length
     * 1 <= target.length <= 1000
     * 1 <= target[i] <= 1000
     * 1 <= arr[i] <= 1000
     * <p>
     * solution:
     * 1. sort two array
     * 2. compare to both array ,judge whether they are equals
     * O(N) O(1)
     * @Date: 2021/3/10 10:43
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description:
     * The Game of Master Mind is played as follows:
     * <p>
     * The computer has four slots, and each slot will contain a ball
     * that is red (R). yellow (Y). green (G) or blue (B).
     * For example, the computer might have
     * RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue).
     * <p>
     * You, the user, are trying to guess the solution.
     * You might, for example, guess YRGB.
     * <p>
     * When you guess the correct color for the correct slot,
     * you get a "hit:' If you guess a color that exists but is in the wrong slot,
     * you get a "pseudo-hit:' Note that a slot
     * that is a hit can never count as a pseudo-hit.
     * <p>
     * For example, if the actual solution is
     * RGBY and you guess GGRR, you have one hit and one pseudo-hit.
     * Write a method that, given a guess and a solution,
     * returns the number of hits and pseudo-hits.
     * <p>
     * Given a sequence of colors solution,
     * and a guess, write a method that return
     * the number of hits and pseudo-hit answer,
     * where answer[0] is the number of hits and
     * answer[1] is the number of pseudo-hit.
     * <p>
     * Example:
     * <p>
     * Input:  solution="RGBY",guess="GGRR"
     * Output:  [1,1]
     * Explanation:  hit once, pseudo-hit once.
     * Note:
     * <p>
     * len(solution) = len(guess) = 4
     * There are only "R","G","B","Y" in solutionandguess.
     * <p>
     * solution:
     * 用hashMap统计 R G B Y 的次数
     * 计算相同非0，最小次数和 为 total
     * 完全相同为 hit
     * pseudo-hit  = total - hit
     * O(N2) O(N) N==4
     * @Date: 2021/3/10 10:52
     */
    public int[] masterMind(String solution, String guess) {
        int[] res = new int[2];
        int total = 0;
        int hit = 0;
        char[] sArray = solution.toCharArray();
        char[] gArray = guess.toCharArray();
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> gMap = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            sMap.put(sArray[i], sMap.getOrDefault(sArray[i], 0) + 1);
            gMap.put(gArray[i], gMap.getOrDefault(gArray[i], 0) + 1);
            if (sArray[i] == gArray[i]) {
                hit++;
            }
        }
        for (Map.Entry<Character, Integer> sEntry : sMap.entrySet()) {
            for (Map.Entry<Character, Integer> gEntry : gMap.entrySet()) {
                if (sEntry.getKey().equals(gEntry.getKey())) {
                    total += Math.min(sEntry.getValue(), gEntry.getValue());
                }
            }
        }
        res[0] = hit;
        res[1] = total - hit;
        return res;
    }




    private int countNumber(int num) {
        if (num == 0) {
            return 1;
        }
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    /**
     * @Description:
     * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
     *
     * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
     *
     * 
     *
     * 示例 1：
     *
     *
     *
     * 输入：mat = [[1,2,3],
     *            [4,5,6],
     *            [7,8,9]]
     * 输出：25
     * 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
     * 请注意，元素 mat[1][1] = 5 只会被计算一次。
     *
     * solution:
     * 1.找出对角元素规律 相加 ： x==y || x+y = mat.length - 1
     * O(N) O(1)
     * @Date: 2021/3/11 11:28
     */
    public int diagonalSum(int[][] mat) {
        if (mat.length == 0) {
            return mat[0][0];
        }
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            sum += mat[i][i];
            sum += mat[i][mat.length - 1 - i];
        }
        if (mat.length % 2 == 1) {
            sum = sum - mat[(mat.length - 1)/2][(mat.length - 1)/2];
        }
        return sum ;
    }

    /**
     * @Description:
     * 给定一个二进制数组， 计算其中最大连续 1 的个数。
     *
     * 
     *
     * 示例：
     *
     * 输入：[1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     * 
     *
     * 提示：
     *
     * 输入的数组只包含0 和 1 。
     * 输入数组的长度是正整数，且不超过 10,000。
     *
     * solution:
     * 1.用 max来记录连续最大值，用count来记录连续值
     * 2.每次更新max  O(N) O(1)
     * @Date: 2021/3/11 13:52
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                max = Math.max(max,count);
            } else {
                count = 0;
            }
        }
        return max;
    }

    /**
     * @Description:
     * 给你一个数组 items ，其中items[i] = [typei, colori, namei] ，
     * 描述第 i 件物品的类型、颜色以及名称。
     *
     * 另给你一条由两个字符串ruleKey 和 ruleValue 表示的检索规则。
     *
     * 如果第 i 件物品能满足下述条件之一，则认为该物品与给定的检索规则 匹配 ：
     *
     * ruleKey == "type" 且 ruleValue == typei 。
     * ruleKey == "color" 且 ruleValue == colori 。
     * ruleKey == "name" 且 ruleValue == namei 。
     * 统计并返回 匹配检索规则的物品数量 。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：items = [["phone","blue","pixel"],
     * ["computer","silver","lenovo"],
     * ["phone","gold","iphone"]],
     * ruleKey = "color", ruleValue = "silver"
     * 输出：1
     * 解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
     * 示例 2：
     *
     * 输入：items = [["phone","blue","pixel"],
     * ["computer","silver","phone"],
     * ["phone","gold","iphone"]],
     * ruleKey = "type", ruleValue = "phone"
     * 输出：2
     * 解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。
     * 
     *
     * 提示：
     *
     * 1 <= items.length <= 104
     * 1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
     * ruleKey 等于 "type"、"color"
     *
     * solution:
     * 1.根据ruleKey看是查什么
     * 2.去对应的列查是否有该元素
     * 3.查到就 ++
     * O(N) O(1)
     * @Date: 2021/3/11 14:09
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int res = 0;
        String[] str = new String[]{"type","color","name"};
        int k = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(ruleKey)) {
                k = i;
                break;
            }
        }
        for (List<String> item : items) {
            if (item.get(k).equals(ruleValue)) {
                res++;
            }
        }
        return res;
    }

    /**
     * @Description:
     *给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     *
     * 
     *
     * 示例：
     *
     * 输入：[1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     * 
     *
     * 提示：
     *
     * 1 <= k <= n <= 30,000。
     * 所给数据范围 [-10,000，10,000]。
     *
     * solution:
     * 1.滑动窗口模型  求最大平均值
     * O(N) (1)
     * @Date: 2021/3/11 14:19  TODO 这里
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int averageSum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        averageSum = sum;
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[i - k]) {
                averageSum = Math.max(averageSum, sum + nums[i] - nums[i - k]);
            }
            sum = sum + nums[i] - nums[i - k];
        }
        return (double) averageSum / k;
    }

    /**
     * @Description:
     * 你在一家生产小球的玩具厂工作，有 n 个小球，
     * 编号从 lowLimit 开始，到 highLimit 结束（
     * 包括 lowLimit 和highLimit ，即n == highLimit - lowLimit + 1）。
     * 另有无限数量的盒子，编号从 1 到 infinity 。
     *
     * 你的工作是将每个小球放入盒子中，
     * 其中盒子的编号应当等于小球编号上每位数字的和。
     * 例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，
     * 而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
     *
     * 给你两个整数 lowLimit 和 highLimit ，
     * 返回放有最多小球的盒子中的小球数量。
     * 如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：lowLimit = 1, highLimit = 10
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
     * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
     * 编号 1 的盒子放有最多小球，小球数量为 2 。
     * 示例 2：
     *
     * 输入：lowLimit = 5, highLimit = 15
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
     * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
     * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
     * 示例 3：
     *
     * 输入：lowLimit = 19, highLimit = 28
     * 输出：2
     * 解释：
     * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
     * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
     * 编号 10 的盒子放有最多小球，小球数量为 2 。
     * 
     *
     * 提示：
     *
     * 1 <= lowLimit <= highLimit <= 10的5次方
     *
     * solution:
     * get the rule :
     * 1.calculate first num then ++continuous number   ,
     * to the highLimit numb
     * O(N) O(M) M 46
     * @Date: 2021/3/11 14:47
     */
    public int countBalls(int lowLimit, int highLimit) {
        int max = 0;
        int[] arr = new int[46];
        for (int i = lowLimit; i <= highLimit; i++) {
            arr[predictSum(i)]++;
        }
        for (int i : arr) {
            max = Math.max(i,max);
        }

        return max;
    }


    /**
     * @Description:
     * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：6
     * 示例 2：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：24
     * 示例 3：
     *
     * 输入：nums = [-1,-2,-3]
     * 输出：-6
     * 
     *
     * 提示：
     *
     * 3 <= nums.length <=104
     * -1000 <= nums[i] <= 1000
     *
     *
     *solution: 先排序
     * 1.数组排序后，分别求出三个最大正数的乘积，以及两个最小负数与最大正数的乘积，二者之间的最大值即为所求答案
     * 2.穷举法
     * O(logN) O(1)
     * @Date: 2021/3/12 10:37
     */
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /**
     * @Description:
     * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，
     * 使 (nums[i]-1)*(nums[j]-1) 取得最大值。
     *
     * 请你计算并返回该式的最大值。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [3,4,5,2]
     * 输出：12
     * 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
     *
     * solution:
     * 2最大数相乘 或者 2最小数相乘
     * O(N) O(1)
     * @Date: 2021/3/12 11:22
     */
    public int maxProduct(int[] nums) {
        int max =  Integer.MIN_VALUE;
        int maxIndex = 0;
        int secMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max && i != maxIndex) {
                secMax = nums[i];
            }
            if (nums[i] < max && nums[i] > secMax) {
                secMax = Math.max(nums[i],nums[i]);
            }
        }
        return (secMax - 1) * (max - 1);
    }

    /**
     * @Description:
     * 给你一个整数数组arr，请你删除最小5%的数字和最大 5%的数字后，
     * 剩余数字的平均值。
     *
     * 与 标准答案误差在10-5的结果都被视为正确结果。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
     * 输出：2.00000
     * 解释：删除数组中最大和最小的元素后，所有元素都等于 2，所以平均值为 2 。
     * 示例 2：
     *
     * 输入：arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
     * 输出：4.00000
     * 示例 3：
     *
     * 输入：arr = [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
     * 输出：4.77778
     * 示例 4：
     *
     * 输入：arr = [9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3]
     * 输出：5.27778
     *
     * 提示：
     *
     * 20 <= arr.length <= 1000
     * arr.length 是 20 的 倍数
     * 0 <= arr[i] <= 105
     *
     * solution:
     * 1.计算删除哪几个元素
     * 2. a.删除元素 求平均值 需要排序 (桶排序）
     * O(logN) O(1)
     * @Date: 2021/3/12 13:35
     */
    public double trimMean(int[] arr) {
        int sum = 0;
        int shouldBeDel = (int) (arr.length * 0.05);
        Arrays.sort(arr);
        for (int i = shouldBeDel; i < arr.length - shouldBeDel; i++) {
            sum += arr[i];
        }
        return (double) sum / (arr.length - 2 * shouldBeDel);
    }

    /**
     * @Description:
     * 给你个整数数组arr，其中每个元素都 不相同。
     *
     * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     *
     * 示例 1：
     *
     * 输入：arr = [4,2,1,3]
     * 输出：[[1,2],[2,3],[3,4]]
     * 示例 2：
     *
     * 输入：arr = [1,3,6,10,15]
     * 输出：[[1,3]]
     * 示例 3：
     *
     * 输入：arr = [3,8,-10,23,19,-4,-14,27]
     * 输出：[[-14,-10],[19,23],[23,27]]
     * 
     *
     * 提示：
     *
     * 2 <= arr.length <= 10^5
     * -10^6 <= arr[i] <= 10^6
     *
     * solution:
     * 1.排序
     * 2.找最小绝对差
     * 3.list 。add
     * O(logn) O(N)
     * @Date: 2021/3/12 13:50
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(arr);
        int minAbs = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            minAbs = Math.min(arr[i] - arr[i - 1],minAbs);
        }

        for (int i = 1; i < arr.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            if (arr[i] - arr[i - 1] == minAbs) {
                tmp.add(arr[i - 1]);
                tmp.add(arr[i]);
                res.add(tmp);
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。
     * 一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
     *
     * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。
     * 例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
     *
     * 返回使 s 变成 交替字符串 所需的 最少 操作数。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：s = "0100"
     * 输出：1
     * 解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
     * 示例 2：
     *
     * 输入：s = "10"
     * 输出：0
     * 解释：s 已经是交替字符串。
     * 示例 3：
     *
     * 输入：s = "1111"
     * 输出：2
     * 解释：需要 2 步操作得到 "0101" 或 "1010" 。
     * 
     *
     * 提示：
     *
     * 1 <= s.length <= 104
     * s[i] 是 '0' 或 '1'
     *
     * solution:
     * 1.写个算法排序  为0101 或1010   分别变成0101和1010看哪个变化的次数多
     * 2.对比排序前后的差异
     * 取1010 0101两种情况最小值
     * O(N) O(1)
     * @Date: 2021/3/12 14:05
     */
    public int minOperations(String s) {
        int clockwise = 0;//1010
        int anticlockwise = 0;//0101
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i = i +2) {
            if (chars[i] != '1') {
                clockwise++;
            }
            if (chars[i] !='0') {
                anticlockwise++;
            }
            if ((i + 1) < chars.length) {
                if (chars[i + 1] != '0') {
                    clockwise++;
                }
                if (chars[i + 1] != '1') {
                    anticlockwise++;
                }
            }
        }
        return Math.min(clockwise,anticlockwise);
    }


    /**
     * @Description:
     * 给你一个整数数组 nums。你可以选定任意的正数 startValue 作为初始值。
     *
     * 你需要从左到右遍历 nums数组，并将 startValue 依次累加上nums数组中的值。
     *
     * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的正数作为 startValue 。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [-3,2,-3,4,2]
     * 输出：5
     * 解释：如果你选择 startValue = 4，在第三次累加时，和小于 1 。
     *                 累加求和
     *                startValue = 4 | startValue = 5 | nums
     *                  (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
     *                  (1 +2 ) = 3  | (2 +2 ) = 4    |   2
     *                  (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
     *                  (0 +4 ) = 4  | (1 +4 ) = 5    |   4
     *                  (4 +2 ) = 6  | (5 +2 ) = 7    |   2
     *
     * 提示：
     *
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * solution：
     * 1.求 累加和最小值  负数 绝对值 + 1   非负数  1
     *  O(N) O(1)
     * @Date: 2021/3/15 9:54
     */
    public int minStartValue(int[] nums) {
        int res = 0;
        int min = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            min = Math.min(min,sum);
        }
        if (min < 0) {
            res = -min + 1;
        } else {
            res = 1;
        }
        return res;
    }

    /**
     * @Description:
     *如果数组是单调递增或单调递减的，那么它是单调的。
     *
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。
     * 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     *
     * 当给定的数组 A是单调数组时返回 true，否则返回 false。
     *
     * 提示：
     *
     * 1 <= A.length <= 50000
     * -100000 <= A[i] <= 100000
     * solution:
     * 遍历看 是否单调  ，定义一个flag
     * 特殊：长度2以下 都是单调的
     * O(N) O(1)
     * @Date: 2021/3/15 10:05
     */
    public boolean isMonotonic(int[] A) {
        if (A.length <= 2) {
            return true;
        }
        boolean isAsc = true;
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i - 1]) {
                isAsc = A[i] - A[i - 1] >= 0;
                break;
            }
        }
        for (int i = 2; i < A.length; i++) {
            //以递增或者递减特性作为判断条件
            if ((A[i] - A[i - 1] < 0 && isAsc) || (A[i] - A[i - 1] > 0) && !isAsc) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description:
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
     * 同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * solution:
     * 双指针
     * 左指针左边是有序数列
     * 右指针和左指针中间都是0
     * 每次替换右指针和左指针元素 直到 结束
     * 自己没写出来 跟随官方的回答
     *O(N) O(1)
     * @Date: 2021/3/15 10:18
     */
    public void moveZeroes(int[] nums) {
        int leftIndex = 0;
        int rightIndex = 0;
        int len = nums.length;
        while (rightIndex < len) {
            if (nums[rightIndex] != 0) {
                swap(nums,leftIndex,rightIndex);
                //每次交换左右指针元素都要增加leftIndex
                leftIndex++;
            }
            //无论是否移动元素，右指针都要右移
            rightIndex++;
        }
    }

    public void swap(int[] nums,int left ,int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /**
     * @Description:
     * 给你一个整数 n 和一个整数数组 rounds 。
     * 有一条圆形赛道由 n 个扇区组成，扇区编号从 1 到 n 。
     * 现将在这条赛道上举办一场马拉松比赛，该马拉松全程由 m 个阶段组成。
     * 其中，第 i 个阶段将会从扇区 rounds[i - 1] 开始，到扇区 rounds[i] 结束。
     * 举例来说，第 1 阶段从rounds[0]开始，到rounds[1]结束。
     *
     * 请你以数组形式返回经过次数最多的那几个扇区，按扇区编号 升序 排列。
     *
     * 注意，赛道按扇区编号升序逆时针形成一个圆（请参见第一个示例）。
     *
     * 输入：n = 4, rounds = [1,3,1,2]
     * 输出：[1,2]
     * 解释：本场马拉松比赛从扇区 1 开始。经过各个扇区的次序如下所示：
     * 1 --> 2 --> 3（阶段 1 结束）--> 4 --> 1（阶段 2 结束）--> 2（阶段 3 结束，即本场马拉松结束）
     * 其中，扇区 1 和 2 都经过了两次，它们是经过次数最多的两个扇区。扇区 3 和 4 都只经过了一次。
     * 示例 2：
     *
     * 输入：n = 2, rounds = [2,1,2,1,2,1,2,1,2]
     * 输出：[2]
     * 示例 3：
     *
     * 输入：n = 7, rounds = [1,3,5,7]
     * 输出：[1,2,3,4,5,6,7]
     * 
     *
     * 提示：
     *
     * 2 <= n <= 100
     * 1 <= m <= 100
     * rounds.length == m + 1
     * 1 <= rounds[i] <= n
     * rounds[i] != rounds[i + 1] ，其中 0 <= i < m
     *
     * solution:
     * 1.常规思路 按照题意跑完所有数组，然后统计最大值 的list element
     * 2.跑的时候，用hashMap(也可用桶排序 bucket)统计1-n出现的次数 记录最大值  最后遍历hashMap  添加到List
     * 3.只计算最后一圈，输出跑过的  倒序找到起点元素 ，依次添加元素
     * O(N*M) O(N)
     * @Date: 2021/3/15 11:12
     */
    /*public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int[] arr = new int[101];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rounds.length - 1; i++) {
            if (rounds[i + 1] > rounds[i]) {
                for (int j = rounds[i]; j < rounds[i + 1]; j++) {
                    arr[j]++;
                }
            } else {
                for (int j = rounds[i]; j <= n; j++) {
                    arr[j]++;
                }
                for(int j = 1; j < rounds[i + 1];j++) {
                    arr[j]++;
                }
            }
        }
        arr[rounds[rounds.length - 1]]++;
        for (int i : arr) {
            max = Math.max(i,max);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                res.add(i);
            }
        }
        return res;
    }
*/
    //直接取首尾元素,输出中间的 闭区间 O(N) O(1)
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int start = rounds[0];
        int end = rounds[rounds.length - 1];
        if (start == end) {
            res.add(start);
        } else if (start < end) {
            for (int i = start; i <= end; i++) {
                res.add(i);
            }
        } else {
            for (int i = start; i <= n; i++) {
                res.add(i);
            }
            for (int i = 1; i <= end; i++) {
                res.add(i);
            }
        }
        return res;
    }
    /**
     * @Description:
     * 给你一个长度为n的整数数组，
     * 请你判断在 最多 改变1 个元素的情况下，
     * 该数组能否变成一个非递减数列。
     *
     * 我们是这样定义一个非递减数列的：对于数组中任意的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
     *
     * 
     *
     * 示例 1:
     *
     * 输入: nums = [4,2,3]
     * 输出: true
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     * 示例 2:
     *
     * 输入: nums = [4,2,1]
     * 输出: false
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     * 
     *
     * 提示：
     *
     * 1 <= n <= 10 ^ 4
     * - 10 ^ 5<= nums[i] <= 10 ^ 5
     *
     * solution:
     * desc >= 2 false O(N) O(1)  换成前面和后面的数字
     * @Date: 2021/3/15 15:30
     */
    public boolean checkPossibility(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                //这里有一点没能够彻底理解
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }

    /**
     * @Description:
     * 给你一个由一些多米诺骨牌组成的列表dominoes。
     *
     * 如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     *
     * 形式上，dominoes[i] = [a, b]和dominoes[j] = [c, d]等价的前提是a==c且b==d，或是a==d 且b==c。
     *
     * 在0 <= i < j < dominoes.length的前提下，
     * 找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量。
     *
     * 
     *
     * 示例：
     *
     * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * 输出：1
     * 
     *
     * 提示：
     *
     * 1 <= dominoes.length <= 40000
     * 1 <= dominoes[i][j] <= 9
     *
     * solution:
     * 1.直接O(n2)遍历 找满足条件的对计数  穷举法  O(N2) O(1) 这种要超时
     * 2.用hashMap存储dominoes(经过排序处理) 遍历map,寻找 大于 等于2的 用数学公式排列组合求值 Cn2
     * @Date: 2021/3/16 9:59
     */
   /* public int numEquivDominoPairs(int[][] dominoes) {
        int count = 0;
        Map<String,Integer> map = new HashMap<>();
        for (int[] ints : dominoes) {
            swapSort(ints);
        }
        for (int[] dominoe : dominoes) {
            map.put(dominoe[0] + ":" + dominoe[1], map.getOrDefault(dominoe[0] + ":" + dominoe[1], 0) + 1);
        }
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                count += entry.getValue() * (entry.getValue() - 1) / 2;
            }
        }
        return count;
    }*/

    //题友解答 我的复杂度太高了  dominoes[i][j] 0---9   O(N) O(1) 数学变换 + 桶排序
    public int numEquivDominoPairs(int[][] dominoes) {
        int res = 0;
        int[] arr = new int[100];
        for (int i = 0; i < dominoes.length; i++) {
            int m = dominoes[i][0];
            int n = dominoes[i][1];
            //运用数学运算把 两种情况转化成100以内的同一个整数
            int k = m > n ? m * 10 + n : n * 10 + m;
            arr[k]++;
        }
        for (int i : arr) {
            if (i >= 2) {
                //运用排列组合只是，计算组合对数
                res += i * (i - 1) / 2;
            }
        }
        return res;
    }

    private void swapSort(int[] arr) {
        if (arr[0] > arr[1]) {
            int tmp = arr[0];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
    }

    /**
     * @Description:
     * 给你一个整数数组 nums 。
     *
     * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     *
     * 返回好数对的数目。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,1,1,3]
     * 输出：4
     * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1]
     * 输出：6
     * 解释：数组中的每组数字都是好数对
     * 示例 3：
     *
     * 输入：nums = [1,2,3]
     * 输出：0
     * 
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     *
     * solution:
     * 1.穷举法 两重循环遍历数组 寻找满足条件的 count++ 返回count
     * 2.数学计算  Cn2
     * 桶排序+排列
     * O(N) O(1)
     * @Date: 2021/3/16 11:15
     */
    public int numIdenticalPairs(int[] nums) {
        int res = 0;
        int[] arr = new int[101];
        for (int num : nums) {
           arr[num]++;
        }
        for (int i : arr) {
            if (i >= 2) {
                res += i * (i - 1) / 2;
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），
     * 并指定一个整数 queryTime 作为查询时间。
     *
     * 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
     *
     * 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，
     * 返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
     * 输出：1
     * 解释：一共有 3 名学生。
     * 第一名学生在时间 1 开始写作业，并于时间 3 完成作业，在时间 4 没有处于做作业的状态。
     * 第二名学生在时间 2 开始写作业，并于时间 2 完成作业，在时间 4 没有处于做作业的状态。
     * 第三名学生在时间 3 开始写作业，预计于时间 7 完成作业，这是是唯一一名在时间 4 时正在做作业的学生。
     * 示例 2：
     *
     * 输入：startTime = [4], endTime = [4], queryTime = 4
     * 输出：1
     * 解释：在查询时间只有一名学生在做作业。
     *
     * 提示：
     *
     * startTime.length == endTime.length
     * 1 <= startTime.length <= 100
     * 1 <= startTime[i] <= endTime[i] <= 1000
     * 1 <=queryTime <= 1000
     *
     * solution:
     * 1.start[i] <= query <= end[i] count++
     * O(N) O(1)
     * @Date: 2021/3/16 11:21
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        int len = startTime.length;
        for (int i = 0; i < len; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * @Description:
     * 学校的自助午餐提供圆形和方形的三明治，分别用数字0和1表示。
     * 所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。
     * 餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个栈里，每一轮：
     *
     * 如果队列最前面的学生喜欢栈顶的三明治，那么会拿走它并离开队列。
     * 否则，这名学生会放弃这个三明治并回到队列的尾部。
     * 这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。
     *
     * 给你两个整数数组students 和sandwiches，其中
     * sandwiches[i]是栈里面第i​​​​​​个三明治的类型（i = 0是栈的顶部），
     * students[j]是初始队列里第j​​​​​​名学生对三明治的喜好（j = 0是队列的最开始位置）。
     * 请你返回无法吃午餐的学生数量。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：students = [1,1,0,0], sandwiches = [0,1,0,1]
     * 输出：0
     * 解释：
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
     * 所以所有学生都有三明治吃。
     * 示例 2：
     *
     * 输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
     * 输出：3
     * 
     *
     * 提示：
     *
     * 1 <= students.length, sandwiches.length <= 100
     * students.length == sandwiches.length
     * sandwiches[i]要么是0，要么是1。
     * students[i]要么是0，要么是1。
     *
     * solution: 1.按照题意 遍历完一次 count++，再对比下 剩下的元素能否一次性 分完 ，能分完返回0 否则返回count
     * 1错了，退出循环 是以排队无法吃到三明治为止 队列中全是0 或者全是1  返回队列的size  也就是无论如何循环，返回的结果一样
     *
     * 看了官方解答 写两种思路
     * a.queue 直接翻译题目
     * b.思考后 统计学生喜欢 0 1的数量在遍历
     * O(N) O(1)
     * @Date: 2021/3/16 11:27
     */
    public int countStudents(int[] students, int[] sandwiches) {
       int[] like = new int[2];
        for (int student : students) {
            like[student]++;
        }
        for (int sandwich : sandwiches) {
            if (like[sandwich] == 0) {
                break;
            }
            like[sandwich]--;
        }
        return like[0] + like[1];
    }

    /*public int countStudents(int[] students, int[] sandwiches) {
        // 学生入队  循环遍历学生去拿走顶端的三明治， 设置flag  无人拿就break 返回 queue.size()
        Queue<Integer> queue = new LinkedList<>();
        for (int student : students) {
            queue.offer(student);
        }
        for (int sandwich : sandwiches) {
            if (queue.isEmpty()) {
                return 0;//表示拿完了
            }
            int size = queue.size();
            for (int i = 0; i < queue.size(); i++) {
                Integer poll = queue.poll();
                if (poll == sandwich) {
                    break;
                } else {
                    queue.offer(poll);
                }
            }
            if (queue.size() == size) {
                return queue.size();
            }
        }
        return 0;
    }*/

    /**
     * @Description:
     * 给你一个整数数组
     * A，只有可以将其划分为三个和相等的非空部分时才返回true，否则返回 false。
     *
     * 形式上，如果可以找出索引i+1 < j且满足
     * A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1]
     * == A[j] + A[j-1] + ... + A[A.length - 1]就可以将数组三等分。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：[0,2,1,-6,6,-7,9,1,2,0,1]
     * 输出：true
     * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
     * 示例 2：
     *
     * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
     * 输出：false
     * 示例 3：
     *
     * 输入：[3,3,6,5,-2,2,5,1,-9,4]
     * 输出：true
     * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
     * 
     *
     * 提示：
     *
     * 3 <= A.length <= 50000
     * -10^4<= A[i] <= 10^4
     *
     * solution:
     * 求和 sum  寻找 sum/3  O(N) O(1)
     * @Date: 2021/3/17 11:26
     */
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int third = sum / 3;
        int tmpSum = 0;
        int i = 0;
        for (; i < arr.length; i++) {
            tmpSum += arr[i];
            if (tmpSum == third) {
                i++;
                break;
            }
        }
        if (i == arr.length) {
            return false;
        }
        tmpSum = 0;
        int j = i;
        for (; j < arr.length; j++) {
            tmpSum += arr[j];
            if (tmpSum == third) {
                j++;
               break;
            }
        }
        if (j == arr.length) {
            return false;
        }
        return true;
    }

    /**
     * @Description:
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     *
     * solution:
     * 1.使用上一个行来生成下一个行 每一行n个元素
     * O(MN) M行数 N是每行个数 O(N)
     * @Date: 2021/3/17 14:04
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        res.add(list);
        if (numRows == 1) {
            return res;
        }
        for(int i = 2; i <= numRows; i++) {
            List<Integer> element = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    element.add(1);
                    continue;
                }
                if (j == i) {
                    element.add(1);
                    continue;
                }
                element.add(res.get(i - 2).get(j - 1) + res.get(i - 2).get(j - 2));
            }
            res.add(element);
        }
        return res;
    }


    /**
     * @Description:
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     * solution:
     * 和上题一样  return res.get(i);
     * O(MN) O(N)
     * @Date: 2021/3/17 14:39
     */
   /* public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        res.add(list);
        for(int i = 2; i <= rowIndex + 1; i++) {
            List<Integer> element = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    element.add(1);
                    continue;
                }
                if (j == i) {
                    element.add(1);
                    continue;
                }
                element.add(res.get(i - 2).get(j - 1) + res.get(i - 2).get(j - 2));
            }
            res.add(element);
        }
        return res.get(rowIndex);
    }*/
    //官方优化
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }


    /**
     * @Description:
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 
     *
     * 示例1：
     *
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * 示例2：
     *
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * 示例 3：
     *
     * 输入：digits = [0]
     * 输出：[1]
     * 
     *
     * 提示：
     *
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     *
     * solution:
     * 使用queue存储各位数字，然后添加到新数组
     * O(N) O(1)
     * @Date: 2021/3/17 16:24
     */
    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] <= 8) {
            digits[digits.length - 1] += 1;
            return digits;
        }
        Stack<Integer> stack = new Stack<>();
        digits[digits.length - 1] += 1;
        int i = digits.length - 1;
        for (; i >= 0; i--) {
            //非末尾 不进位 跳出循环
            if (i != digits.length - 1 && digits[i] != 10) {
                stack.push(digits[i]);
                break;
            }
            //i == 0  a。不进位 跳出循环 b。进位 push 0 1 跳出循环
            if (i == 0) {
                if (digits[i] != 10) {
                    stack.push(digits[i]);
                } else {
                    stack.push(0);
                    stack.push(1);
                }
                break;
            }
            //进位 前一位元素 + 1 push 0
            if (digits[i] == 10) {
                digits[i - 1] += 1;
                stack.push(0);
            }
        }
        i--; //弥补break i未加1
        for (int j = i; j >= 0; j--) {
            stack.push(digits[j]);
        }
        int[] res = new int[stack.size()];
        int index = 0;
        //stack 翻转到 res中
        for (int j = stack.size() - 1; j >= 0; j--) {
            res[index++] = stack.get(j);
        }
        return res;
    }

    /**
     * @Description:
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     *
     * 例如，在字符串 s = "abbxxxxzyy"中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     *
     * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。
     * 上例中的 "xxxx" 分组用区间表示为 [3,6] 。
     *
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     *
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     *
     * 
     *
     * 示例1：
     *
     * 输入：s = "abbxxxxzzy"
     * 输出：[[3,6]]
     * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
     * 示例 2：
     *
     * 输入：s = "abc"
     * 输出：[]
     * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
     * 示例 3：
     *
     * 输入：s = "abcdddeeeeaabbbcd"
     * 输出：[[3,5],[6,9],[12,14]]
     * 解释：较大分组为 "ddd", "eeee" 和 "bbb"
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 仅含小写英文字母
     *
     * solution: 找
     * 连续相同的字符 存map toCharArray （maxContinuous,List<List<Integer>>）
     * return map.getKey
     * 包含大于等于三 的成为较大分组 都要返回
     * O(N) O(N)
     * @Date: 2021/3/18 11:07
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> list = new ArrayList<>();
        if (s.length() == 1) {
            return list;
        }
        char[] charArray = s.toCharArray();
        int start = 0;
        int end = 0;
        boolean allSame = true;
        for (int i = 0; i < charArray.length - 1; i++) {
            if (charArray[i + 1] != charArray[i]) {
                allSame = false;
                if (end - start + 1 >= 3) {
                    list.add(Arrays.asList(start,end));
                }
                start = i + 1;
                end = i + 1;
                continue;
            }
            end++;
        }
        if (end - start + 1 >= 3) {
            list.add(Arrays.asList(start,end));
        }
        return list;
    }

    /**
     * @Description:
     * 给你一个整数数组arr ，请你将数组中的每个元素替换为它们排序后的序号。
     *
     * 序号代表了一个元素有多大。序号编号的规则如下：
     *
     * 序号从 1 开始编号。
     * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
     * 每个数字的序号都应该尽可能地小。
     * 
     *
     * 示例 1：
     *
     * 输入：arr = [40,10,20,30]
     * 输出：[4,1,2,3]
     * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
     * 示例 2：
     *
     * 输入：arr = [100,100,100]
     * 输出：[1,1,1]
     * 解释：所有元素有相同的序号。
     * 示例 3：
     *
     * 输入：arr = [37,12,28,9,100,56,80,5,12]
     * 输出：[5,3,4,2,8,6,7,1,3]
     *
     * 提示：
     *
     * 0 <= arr.length <= 105
     * -10的九次方 <= arr[i] <= 10次方
     *
     * solution:
     * 1.arr去重后排序  去重时记录 element个数  list
     * O(N2) O(N)
     * 2.找出最大最小值  桶排序，然后在统计具体位置
     * O(N) O(M)
     * @Date: 2021/3/18 11:43
     */
   /* public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);
        Map<Integer,Integer> map = new HashMap<>();
        int repeat = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] == arr[i]) {
                repeat++;
                continue;
            }
            map.put(arr[i],i - repeat);
        }
        map.put(arr[arr.length - 1],arr.length - 1 - repeat);
        for (int i = 0; i < copy.length; i++) {
            copy[i] = map.get(copy[i]) + 1;
        }
        return copy;
    }*/
    public int[] arrayRankTransform(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            min = Math.min(min,i);
            max = Math.max(max,i);
        }
        int[] array = new int[max - min + 1];
        for (int i : arr) {
            array[i - min]++;
        }
        int index = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                array[i] = index++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[arr[i] - min];
        }
        return arr;
    }

    /**
     * @Description:
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 
     *
     * 说明:
     *
     * 为什么返回数值是整数，但输出的答案是数组呢?
     *
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     * 你可以想象内部操作如下:
     *
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     *
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *   print(nums[i]);
     * }
     * 
     * 示例 1：
     *
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     *
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     * 
     *
     * 提示：
     *
     * 0 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     *
     * solution: 双指针移动 去除重复元素
     * O log(N) O(1)
     * @Date: 2021/3/18 14:51
     */
    /*public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i]) {
                nums[i] = Integer.MIN_VALUE;
            }
        }
        int start = 0;
        int end = 0;
        while (end < nums.length) {
            if (nums[end] != Integer.MIN_VALUE) {
                swap(nums,start,end);
                start++;
            }
            end++;
        }
        return start + 1;
    }*/
    //双指针优化
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != nums[left]) {
                left++;
                swap(nums,left,right);
            }
            right++;
        }
        return left + 1;
    }

    /**
     * @Description:
     * 给你一个数组arr，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用-1 替换。
     *
     * 完成所有替换操作后，请你返回这个数组。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：arr = [17,18,5,4,6,1]
     * 输出：[18,6,6,6,1,-1]
     * 解释：
     * - 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
     * - 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
     * - 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
     * - 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
     * - 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
     * - 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
     * 示例 2：
     *
     * 输入：arr = [400]
     * 输出：[-1]
     * 解释：下标 0 的元素右侧没有其他元素。
     * 
     *
     * 提示：
     *
     * 1 <= arr.length <= 104
     * 1 <= arr[i] <= 105
     *
     * solution:
     * 常规思路 求右边最大元素 放入 arr[i] 末尾元素用 -1
     * 2.反序   倒数第一 第二直接给出来（不够长特殊处理）
     * arr[i] = Math.max(arr[i+1],max);
     * O(N) O(1)
     *
     * @Date: 2021/3/18 15:35
     */
    public int[] replaceElements(int[] arr) {
        int max = arr[arr.length - 1];
        int[] tmp = new int[arr.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = arr[i];
        }
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            max = Math.max(max,tmp[i + 1]);
            arr[i] = max;
        }
        return arr;
    }

    /**
     * @Description:
     * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
     *
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     *
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     *
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     * 示例 1:
     *
     * 输入:
     * nums =
     * [[1,2],
     *  [3,4]]
     * r = 1, c = 4
     * 输出:
     * [[1,2,3,4]]
     * 解释:
     * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
     * 示例 2:
     *
     * 输入:
     * nums =
     * [[1,2],
     *  [3,4]]
     * r = 2, c = 4
     * 输出:
     * [[1,2],
     *  [3,4]]
     * 解释:
     * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
     * 注意：
     *
     * 给定矩阵的宽和高范围在 [1, 100]。
     * 给定的 r 和 c 都是正数。
     *
     * solution:
     * 先判断能否转换，不能直接返回  能根据 元素组 转换成 r c矩阵
     * O(N) O(N)
     * @Date: 2021/3/19 9:55
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length != r * c) {
            return nums;
        }
        int[][] res = new int[r][c];
        int k = 0;
        int index = 0;
        for (int[] num : nums) {
            for (int j = 0; j < num.length; j++) {
                res[k][index++] = num[j];
                if (index == c) {
                    index = 0;
                    k++;
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j]
     * 是第 i​​​​​​​​​​​​ 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
     *
     * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：accounts = [[1,2,3],[3,2,1]]
     * 输出：6
     * 解释：
     * 第 1 位客户的资产总量 = 1 + 2 + 3 = 6
     * 第 2 位客户的资产总量 = 3 + 2 + 1 = 6
     * 两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
     * 示例 2：
     *
     * 输入：accounts = [[1,5],[7,3],[3,5]]
     * 输出：10
     * 解释：
     * 第 1 位客户的资产总量 = 6
     * 第 2 位客户的资产总量 = 10
     * 第 3 位客户的资产总量 = 8
     * 第 2 位客户是最富有的，资产总量是 10
     * 示例 3：
     *
     * 输入：accounts = [[2,8,7],[7,1,3],[1,9,5]]
     * 输出：17
     * 
     *
     * 提示：
     *
     * m ==accounts.length
     * n ==accounts[i].length
     * 1 <= m, n <= 50
     * 1 <= accounts[i][j] <= 100
     *
     * solution:
     * 思路清晰 比较每一行的最大值 ，直接return
     * O(N) O(1)
     * @Date: 2021/3/19 10:12
     */
    public int maximumWealth(int[][] accounts) {
        int sum = 0;
        int max = 0;
        for (int[] account : accounts) {
            sum = 0;
            for (int j : account) {
                sum += j;
            }
            max = Math.max(max, sum);
        }
        return max;
    }


    /**
     * @Description:
     * 给你一个数组 nums 。数组「动态和」的计算公式为：
     * runningSum[i] = sum(nums[0]…nums[i]) 。
     *
     * 请返回 nums 的动态和。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1,1]
     * 输出：[1,2,3,4,5]
     * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
     * 示例 3：
     *
     * 输入：nums = [3,1,2,10,1]
     * 输出：[3,4,6,16,17]
     * 
     *
     * 提示：
     *
     * 1 <= nums.length <= 1000
     * -10^6<= nums[i] <=10^6
     *
     * solution:
     * nums[i] = nums[i-1] + nums[i]  nums[1] = nums[1]
     * 类似伏波拉契数列  用空间换取时间，只不过空间是参数传进来的 O(N) O(1)
     * @Date: 2021/3/19 10:17
     */
    public int[] runningSum(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }

    /**
     * @Description:
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例2:
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     *
     * 输入: [1,3,5,6], 0
     * 输出: 0
     *
     * solution: 这些题好简单啊  traver the array nums,
     * if (element == target) return index
     * if (nums[i] < target < nums[i+1]) return i + 1;
     * 直接二分查吧 不然好慢
     * Olog(N) O(1)
     * @Date: 2021/3/19 10:23
     */
    /*public int searchInsert(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        if (target == nums[nums.length - 1]) {
            return nums.length - 1;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == target) {
                return i;
            }
            if (nums[i] < target && nums[i + 1] >  target) {
                return i + 1;
            }
        }
        return nums.length;
    }*/

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) / 2) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * @Description:
     * 给你一个 m 行 n列的二维网格grid和一个整数k。
     * 你需要将grid迁移k次。
     *
     * 每次「迁移」操作将会引发下述活动：
     *
     * 位于 grid[i][j]的元素将会移动到grid[i][j + 1]。
     * 位于grid[i][n- 1] 的元素将会移动到grid[i + 1][0]。
     * 位于 grid[m- 1][n - 1]的元素将会移动到grid[0][0]。
     * 请你返回k 次迁移操作后最终得到的 二维网格。
     *
     * 
     *
     * 示例 1：
     *
     *
     *
     * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
     * 输出：[[9,1,2],[3,4,5],[6,7,8]]
     * 示例 2：
     *
     *
     *
     * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
     * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
     * 示例 3：
     *
     * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
     * 输出：[[1,2,3],[4,5,6],[7,8,9]]
     * 
     *
     * 提示：
     *
     * m ==grid.length
     * n ==grid[i].length
     * 1 <= m <= 50
     * 1 <= n <= 50
     * -1000 <= grid[i][j] <= 1000
     * 0 <= k <= 100
     *
     * solution:
     * 找规律 计算 横纵坐标值
     * y = (j + k) % n
     * x = (i + (j + k ) /n ) % m;
     * O(N) O(N)
     * @Date: 2021/3/19 11:23
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int y = (j + k) % n;
                int x = (i + (j + k) / n) % m;
                copy[x][y] = grid[i][j];
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < copy.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < copy[i].length; j++) {
                list.add(copy[i][j]);
            }
            res.add(list);
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     *
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [2,5,1,3,4,7], n = 3
     * 输出：[2,3,5,4,1,7]
     * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
     * 示例 2：
     *
     * 输入：nums = [1,2,3,4,4,3,2,1], n = 4
     * 输出：[1,4,2,3,3,2,4,1]
     * 示例 3：
     *
     * 输入：nums = [1,1,2,2], n = 2
     * 输出：[1,2,1,2]
     * 
     *
     * 提示：
     *
     * 1 <= n <= 500
     * nums.length == 2n
     * 1 <= nums[i] <= 10^3
     *
     * solution:
     * 1.常规思路  nums分为两数组  arr1 arr2 ， arr1和 arr2依次插入nums变为新数组  O(N) O(N)
     * 2.copy一个数组   nums[i] = copy[i / 2];
     *             nums[i + 1] = copy[n + i / 2]; i--nums.length i=i+2
     *             O(N) O(N)
     * @Date: 2021/3/22 13:46
     */
    public int[] shuffle(int[] nums, int n) {
        int[] copy = Arrays.copyOf(nums,nums.length);
        for (int i = 0; i < nums.length; i = i + 2) {
            nums[i] = copy[i / 2];
            nums[i + 1] = copy[n + i / 2];
        }
        return nums;
    }

    /**
     * @Description:
     * LeetCode 设计了一款新式键盘，正在测试其可用性。
     * 测试人员将会点击一系列键（总计 n 个），每次一个。
     *
     * 给你一个长度为 n 的字符串 keysPressed
     * 其中 keysPressed[i] 表示测试序列中第 i 个被按下的键。releaseTimes 是一个升序排列的列表，
     * 其中 releaseTimes[i] 表示松开第 i 个键的时间。
     * 字符串和数组的 下标都从 0 开始 。第 0 个键在时间为 0 时被按下，接下来每个键都 恰好 在前一个键松开时被按下。
     *
     * 测试人员想要找出按键 持续时间最长 的键。
     * 第 i 次按键的持续时间为 releaseTimes[i] - releaseTimes[i - 1] ，
     * 第 0 次按键的持续时间为 releaseTimes[0] 。
     *
     * 注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。
     *
     * 请返回按键 持续时间最长 的键，如果有多个这样的键，则返回 按字母顺序排列最大 的那个键。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：releaseTimes = [9,29,49,50], keysPressed = "cbcd"
     * 输出："c"
     * 解释：按键顺序和持续时间如下：
     * 按下 'c' ，持续时间 9（时间 0 按下，时间 9 松开）
     * 按下 'b' ，持续时间 29 - 9 = 20（松开上一个键的时间 9 按下，时间 29 松开）
     * 按下 'c' ，持续时间 49 - 29 = 20（松开上一个键的时间 29 按下，时间 49 松开）
     * 按下 'd' ，持续时间 50 - 49 = 1（松开上一个键的时间 49 按下，时间 50 松开）
     * 按键持续时间最长的键是 'b' 和 'c'（第二次按下时），持续时间都是 20
     * 'c' 按字母顺序排列比 'b' 大，所以答案是 'c'
     * 示例 2：
     *
     * 输入：releaseTimes = [12,23,36,46,62], keysPressed = "spuda"
     * 输出："a"
     * 解释：按键顺序和持续时间如下：
     * 按下 's' ，持续时间 12
     * 按下 'p' ，持续时间 23 - 12 = 11
     * 按下 'u' ，持续时间 36 - 23 = 13
     * 按下 'd' ，持续时间 46 - 36 = 10
     * 按下 'a' ，持续时间 62 - 46 = 16
     * 按键持续时间最长的键是 'a' ，持续时间 16
     * 
     *
     * 提示：
     *
     * releaseTimes.length == n
     * keysPressed.length == n
     * 2 <= n <= 1000
     * 1 <= releaseTimes[i] <= 109
     * releaseTimes[i] < releaseTimes[i+1]
     * keysPressed 仅由小写英文字母组成
     *
     * solution:
     * 1.常规思路  相邻差集，求最大差值  如果相同 也更新下标   最后s.chartAt[index] 返回char
     * O(N) O(1)
     * @Date: 2021/3/22 14:09
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = releaseTimes[0];
        int index = 0;
        for (int i = 1; i < releaseTimes.length; i++) {
            int abs = releaseTimes[i] - releaseTimes[i - 1];
            if (abs == max && keysPressed.charAt(index) < keysPressed.charAt(i)) {
                index = i;
            } else if (abs > max) {
                max = abs;
                index = i;
            }
        }
        return keysPressed.charAt(index);
    }

    /**
     * @Description:
     * 给定一个非负整数数组 A，
     * 返回一个数组，在该数组中，
     * A 的所有偶数元素之后跟着所有奇数元素。
     *
     * 你可以返回满足此条件的任何数组作为答案。
     *
     * 
     *
     * 示例：
     *
     * 输入：[3,1,2,4]
     * 输出：[2,4,3,1]
     * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
     * 
     *
     * 提示：
     *
     * 1 <= A.length <= 5000
     * 0 <= A[i] <= 5000
     *
     * solution:
     * 双指针 把A的元素复制到新数组  跳出条件 i 或者 j ==A.length 先计算偶数有多少个
     * O(N) O(N)
     * @Date: 2021/3/22 14:58
     */
    public int[] sortArrayByParity(int[] A) {
        int[] res = new int[A.length];
        int i = 0;
        for (int value : A) {
            if (value % 2 == 0) {
                res[i++] = value;
            }
        }
        for (int value : A) {
            if (value % 2 == 1) {
                res[i++] = value;
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给定两个排序后的数组 A 和 B，
     * 其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * 初始化A 和 B 的元素数量分别为m 和 n。
     *
     * 示例:
     *
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     *
     * 输出:[1,2,2,3,5,6]
     * 说明:
     *
     * A.length == n + m
     *
     * solution:插入排序
     * 1.B从中间插入  大于该元素的的往后移动
     * 2.B从尾部插入  依次对比大小
     * 3.B全部插入到尾部，然后进行sort排序
     * 我写下第二种
     * 4.双指针  O(M+N) 用时间换取空间
     * @Date: 2021/3/23 11:19
     */
    /*public void merge(int[] A, int m, int[] B, int n) {
        for (int i : B) {
            A[m] = i;
            for (int j = m; j > 0; j--) {
                if (A[j - 1] > A[j]) {
                    int tmp = A[j - 1];
                    A[j - 1] = A[j];
                    A[j] = tmp;
                } else {
                    break;
                }
            }
            m++;
        }
    }*/
    //双指针
    public void merge(int[] A, int m, int[] B, int n) {
        if (n == 0) {
            return;
        }
        int[] array = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m || j < n) {
            if (i == m) {
                array[index++] = B[j];
                j++;
                continue;
            }
            if (j == n) {
                array[index++] = A[i];
                i++;
                continue;
            }
            if (A[i] <= B[j]) {
                array[index++] = A[i];
                i++;
            } else {
                array[index++] = B[j];
                j++;
            }
        }
        for (int k = 0; k < array.length; k++) {
            A[k] = array[k];
        }
    }

    /**
     * @Description:
     * 给你一个非负整数数组 nums 。
     * 如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，
     * 那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
     *
     * 注意： x 不必 是 nums 的中的元素。
     *
     * 如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。
     * 否则，返回 -1 。可以证明的是，
     * 如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [3,5]
     * 输出：2
     * 解释：有 2 个元素（3 和 5）大于或等于 2 。
     * 示例 2：
     *
     * 输入：nums = [0,0]
     * 输出：-1
     * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
     * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
     * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
     * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
     * x 不能取更大的值，因为 nums 中只有两个元素。
     * 示例 3：
     *
     * 输入：nums = [0,4,3,0,4]
     * 输出：3
     * 解释：有 3 个元素大于或等于 3 。
     * 示例 4：
     *
     * 输入：nums = [3,6,7,7,0]
     * 输出：-1
     * 
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     *
     * solution:
     * 分别计算大于n的有多少个
     * 0<n<nums.length 桶排序
     * O(N) O(N)
     * @Date: 2021/3/23 13:39
     */
    public int specialArray(int[] nums) {
        int len = nums.length;
        int[] arr = new int[1001];
        for (int num : nums) {
            arr[num]++;
        }
        int count = 0;
        int pre = len;
        for (int i = 0; i < arr.length; i++) {
            if (i == pre) {
                return pre;
            }
            if (arr[i] > 0) {
                count += arr[i];
                pre = len - count;
            }

        }
        return -1;
    }

    /**
     * @Description:
     * 给你一个大小为 rows x cols 的矩阵 mat，
     * 其中 mat[i][j] 是 0 或 1，请返回 矩阵mat 中特殊位置的数目 。
     *
     * 特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），
     * 则位置 (i, j) 被称为特殊位置。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：mat = [[1,0,0],
     *            [0,0,1],
     *            [1,0,0]]
     * 输出：1
     * 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
     * 示例 2：
     *
     * 输入：mat = [[1,0,0],
     *            [0,1,0],
     *            [0,0,1]]
     * 输出：3
     * 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
     * 示例 3：
     *
     * 输入：mat = [[0,0,0,1],
     *            [1,0,0,0],
     *            [0,1,1,0],
     *            [0,0,0,0]]
     * 输出：2
     * 示例 4：
     *
     * 输入：mat = [[0,0,0,0,0],
     *            [1,0,0,0,0],
     *            [0,1,0,0,0],
     *            [0,0,1,0,0],
     *            [0,0,0,1,1]]
     * 输出：3
     * 
     *
     * 提示：
     *
     * rows == mat.length
     * cols == mat[i].length
     * 1 <= rows, cols <= 100
     * mat[i][j] 是 0 或 1
     *
     * solution:
     * 1.常规思路，对mat数组每一个元素为1的进行 判断，看横纵是否都为0   O(M * (len + wide)) O(1)
     * 2.排除法 记录1的位置 排除横纵元素 set add x:y   return rows * cols - set.size
     * 3.记录所有为1 的坐标位置,利用排除法，去除所有 有横坐标相同或者纵坐标相同的 剩下的数量直接返回  O(n2) O(N)
     * @Date: 2021/3/23 14:11
     */
    public int numSpecial(int[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                boolean flag = true;
                if (mat[i][j] == 1) {
                    //横
                    for(int k = 0; k < j; k++) {
                        if (mat[i][k] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        continue;
                    }
                    for (int k = j + 1; k < mat[i].length; k++) {
                        if (mat[i][k] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        continue;
                    }
                    //纵
                    for(int k = 0; k < i; k++) {
                        if (mat[k][j] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        continue;
                    }
                    for (int k = i + 1; k < mat.length; k++) {
                        if (mat[k][j] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个正整数数组arr，请你计算所有可能的奇数长度子数组的和。
     *
     * 子数组 定义为原数组中的一个连续子序列。
     *
     * 请你返回 arr中 所有奇数长度子数组的和 。
     *
     * 示例 1：
     *
     * 输入：arr = [1,4,2,5,3]
     * 输出：58
     * 解释：所有奇数长度子数组和它们的和为：
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     * 示例 2：
     *
     * 输入：arr = [1,2]
     * 输出：3
     * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
     * 示例 3：
     *
     * 输入：arr = [10,11,12]
     * 输出：66
     * 
     *
     * 提示：
     *
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 1000
     *
     * solution:
     * 1.常规思路 计算左右奇数数组和，然后再相加
     * 2.有数学公式能计算出来 哪个元素该加多少次 这个公式在评论区看到了 O(N) O(1)
     *
     * 对任意的一个数arr[i]:
     * 1.左边选奇数个+选arr[i]+右边选奇数个=奇数个数
     * 2.左边选偶数个+选arr[i]+右边选偶数个=奇数个数
     *
     * 那么问题转化为：
     * 根据左右两边区间大小分别求：选出奇数个的方案数or选出偶数个的方案数
     * 最后排列组合即可。
     * 新思路：滑动窗口计算 奇数和
     *
     * @Date: 2021/3/24 14:57
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = i + 1;
            int right = arr.length - i;
            int left_even = (left + 1) / 2;
            int right_even = (right + 1) / 2;
            int left_odd = left / 2;
            int right_odd = right / 2;
            //上面这五句有点巧妙  实现了  左边偶数*右边偶数 + 左边奇数*右边奇数
            // i+1 left+1 这个比较巧妙，应该是有理论可以验证的
            res += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return res;
    }


    //申论 而我咋地鞥 历史转身 谈一曲古筝 雨纷纷旧故里草木深 我听闻 你始终一个人
    // 斑驳的承蒙盘踞着老鼠哥 石板上回趟的是再等 雨纷纷 旧故里草木深 成交牧笛声 落在那作业村 缘分落地生根是我们
    /**
     * Andy
     * 你有多久没有看过那片海  你到现在对自己究竟多明白 总是不服输 永远要比别人快 在你前方是否有你要的未来
     * 想到我们的过去都让人感慨 希望所有好朋友都能站起来 还有你曾经曾经疯狂爱上的女孩 再过几年是不是依旧难以忘怀
     * 可是andy 活着是不需道理 谁都可能 暂时的失去勇气 外面不安的世界
     * 骚动的心情 不能熄灭你曾经拥有炙热的心 我是真的不会表达我的爱 却很在乎每个人对我的期待
     * 平凡的角色 站在小小的舞台，我要那么勇敢的说出来
     *
     * 想到我们的过去都让人感慨 希望所有好朋友都能站起来  还有你曾经疯狂爱上的女孩  再过几年是不是依旧难以忘怀
     * 可是andy 活着是不需道理 谁都可能 暂时的失去勇气 外面不安的世界
     * 骚动的心情 不能熄灭你曾经拥有炙热的心
     * 我是真的不会表达我的爱 却很在乎每个人对我的期待
     * 平凡的角色 站在小小的舞台 我要那么勇敢的说出来
     *
     * 外面不安的世界 骚动的心情 不能熄灭你曾经拥有制热的心
     * 可是Andy wow Andy wow 外面不安的世界 骚动的心情 不能熄灭曾经你拥有炙热的心
     */

    /**
     * @Description:
     *给出一个整数数组A和一个查询数组queries。
     *
     * 对于第i次查询，有val =queries[i][0], index= queries[i][1]，我们会把val加到A[index]上。
     * 然后，第i次查询的答案是 A 中偶数值的和。
     *
     * （此处给定的index = queries[i][1]是从 0 开始的索引，每次查询都会永久修改数组A。）
     *
     * 返回所有查询的答案。你的答案应当以数组answer给出，answer[i]为第i次查询的答案。
     *
     * 
     *
     * 示例：
     *
     * 输入：A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
     * 输出：[8,6,2,4]
     * 解释：
     * 开始时，数组为 [1,2,3,4]。
     * 将 1 加到 A[0] 上之后，数组为 [2,2,3,4]，偶数值之和为 2 + 2 + 4 = 8。
     * 将 -3 加到 A[1] 上之后，数组为 [2,-1,3,4]，偶数值之和为 2 + 4 = 6。
     * 将 -4 加到 A[0] 上之后，数组为 [-2,-1,3,4]，偶数值之和为 -2 + 4 = 2。
     * 将 2 加到 A[3] 上之后，数组为 [-2,-1,3,6]，偶数值之和为 -2 + 6 = 4。
     * 
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * 1 <= queries.length <= 10000
     * -10000 <= queries[i][0] <= 10000
     * 0 <= queries[i][1] < A.length
     *
     * solution: 
     * 1.先计算sum总和  
     * 2.计算每一步，先看影响的结果，奇数变奇数 不动 奇数变偶数  + 结果  偶数变偶数 -前偶数+现有偶数
     * 3.返回 结果  O(N+M) O(M)
     * @Date: 2021/3/25 14:39
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] res = new int[queries.length];
        int sum = 0;
        for (int i : A) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int value = queries[i][0];
            int index = queries[i][1];
            int tmp = A[index];
            A[index] += value;
            if (tmp % 2 != 0 && A[index] % 2 == 0) {
                sum += A[index];
            }
            if (tmp % 2 == 0 && A[index] % 2 == 0) {
                sum = sum - tmp + A[index];
            }
            if (tmp % 2 == 0 && A[index] % 2 != 0) {
                sum -= tmp;
            }
            res[i] = sum;
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个整数数组nums。数组中唯一元素是那些只出现恰好一次的元素。
     *
     * 请你返回 nums中唯一元素的 和。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,2]
     * 输出：4
     * 解释：唯一元素为 [1,3] ，和为 4 。
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1,1]
     * 输出：0
     * 解释：没有唯一元素，和为 0 。
     * 示例 3 ：
     *
     * 输入：nums = [1,2,3,4,5]
     * 输出：15
     * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
     * 
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     *
     * solution: 桶排序，取 bucket[i] = 1的index相加 O(N) O(N) 后面这个N为101 为桶容量
     * @Date: 2021/3/25 15:35
     */
    public int sumOfUnique(int[] nums) {
        int res = 0;
        int[] bucket = new int[101];
        for (int num : nums) {
            bucket[num]++;
        }
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 1) {
                res += i;
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给定一个无重复元素的有序整数数组 nums 。
     *
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，
     * 并且不存在属于某个范围但不属于 nums 的数字 x 。
     *
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     *
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [0,1,2,4,5,7]
     * 输出：["0->2","4->5","7"]
     * 解释：区间范围是：
     * [0,2] --> "0->2"
     * [4,5] --> "4->5"
     * [7,7] --> "7"
     *
     * 提示：
     *
     * 0 <= nums.length <= 20
     * -231 <= nums[i] <= 231 - 1
     * nums 中的所有值都 互不相同
     * nums 按升序排列
     *
     * solution:
     * 循环遍历数组，用list存放元素
     * 循环条件为 i < nums.length 后一个数比前一个数大一
     * 否则 重新加一个list 得搞一个pre
     * @Date: 2021/3/25 15:46
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;
        int pre = nums[0];
        boolean flag = false;
        StringBuilder str = new StringBuilder();
        //小于2的单独处理
        if (nums.length == 1) {
            res.add(valueOf(nums[0]));
            return res;
        }
        if (nums.length == 2) {
            if (nums[1] - nums[0] > 1) {
                res.add(str.append(nums[0]).toString());
                str = new StringBuilder();
                res.add(str.append(nums[1]).toString());
            } else {
                res.add(str.append(nums[0]).append("->").append(nums[1]).toString());
            }
            return res;
        }
        str.append(pre);
        for (int i = 1; i < nums.length; i++) {
            //3.i == nums.length - 1  单独判断 flag false 直接 append   true 直接append 一个
            if (i == nums.length - 1) {
                if (flag) {
                    str = new StringBuilder();
                    if (nums[i]  > pre + 1) {
                        str.append(nums[i - 1]);
                        res.add(str.toString());
                        str = new StringBuilder();
                        str.append(nums[i]);
                    } else {
                        str.append(pre).append("->").append(nums[i]);
                    }
                } else {
                    if (nums[i]  > pre + 1) {
                        str.append("->").append(nums[i - 1]);
                        res.add(str.toString());
                        str = new StringBuilder();
                        str.append(nums[i]);
                    } else {
                        str.append("->").append(nums[i]);
                    }
                }
                res.add(str.toString());
                continue;
            }
            if (flag) {
                str = new StringBuilder();
                flag = false;
                str.append(pre);
            }
            if (nums[i] != pre + 1) {
                //1.相差1的  append nums[i-1]  2.相差1以上的 不append了
                if (nums[i] - pre > 1 && nums[i - 1] != Integer.parseInt(str.toString())) {
                    str.append("->").append(nums[i - 1]);
                }
                res.add(str.toString());
                flag = true;
            }
            pre = nums[i];
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个大小为m* n的矩阵mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
     * 请你返回矩阵中战斗力最弱的k行的索引，按从最弱到最强排序。
     * 如果第i行的军人数量少于第j行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     *
     * 示例 1：
     *
     * 输入：mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     *
     * 提示：
     *
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] 不是 0 就是 1
     *
     * solution:
     * 1.所有都排序，然后去前k个元素返回 String[]Array 做排序处理 这种时间复杂度有点高的 应为排了所有的序
     * 2.仅排k个数字的序 一个一个的去寻找
     * @Date: 2021/3/26 11:16
     */
    /*public int[] kWeakestRows(int[][] mat, int k) {
        int[] res = new int[k];
        String[] strArray = new String[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int sum = 0;
            for (int r : mat[i]) {
                if (r == 0) {
                    break;
                }
                sum += r;
            }
            strArray[i] = new String(i+"-"+sum);
        }
        Arrays.sort(strArray,new WeakArrayComparator());
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.parseInt(strArray[i].split("-")[0]);
        }
        return res;
    }

    public static class WeakArrayComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            String[] split1 = s1.split("-");
            String[] split2 = s2.split("-");
            int a = Integer.parseInt(split1[0]);
            int b = Integer.parseInt(split1[1]);
            int c = Integer.parseInt(split2[0]);
            int d = Integer.parseInt(split2[1]);
            if (b > d) {
                return 1;
            } else if (b < d) {
                return -1;
            } else {
                return a > c ? 1 : -1;
            }
        }
    }*/

    //第一步不变，求各行的元素累计和  第二步把累加和 * 1000 + 索引值  放入到一个一维数组，排序   取前k个元素  对1000取余，还原为index
    // O(M*N + Log(M))  O(M) 相同数字，比较索引大小的 用这个方式来实现，放大1000倍 排序后，在还原 牛逼
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] res = new int[k];
        int[] largeArr = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int sum = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    break;
                }
                sum += mat[i][j];
            }
            largeArr[i] = sum * 1000 + i;
        }
        Arrays.sort(largeArr);
        for (int i = 0; i < res.length; i++) {
            res[i] = largeArr[i] % 1000;
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：[3, 2, 1]
     * 输出：1
     * 解释：第三大的数是 1 。
     * 示例 2：
     *
     * 输入：[1, 2]
     * 输出：2
     * 解释：第三大的数不存在, 所以返回最大的数 2 。
     * 示例 3：
     *
     * 输入：[2, 2, 3, 1]
     * 输出：1
     * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
     * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
     *
     * 提示：
     *
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     * solution:
     * 1.常规思路 排序，找第三大的数 找不到就返回最大的数  O(logN)
     * 2.找三次 第一次找最大的 第二次找第二大 第三次找第三大 定义一个flag标识是否找到 O(N*3) O(1)
     * @Date: 2021/3/26 13:56
     */
    /*public int thirdMax(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0],nums[1]);
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] != nums[i + 1]) {
                count++;
            }
            if (count == 2) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }*/

    public int thirdMax(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0],nums[1]);
        }
        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;
        boolean secFlag = false;
        boolean thirdFlag = false;
        for (int num : nums) {
           max = Math.max(max,num);
        }
        for (int num : nums) {
            if (num != max) {
                secMax = Math.max(secMax,num);
                secFlag = true;
            }
        }
        for(int num : nums) {
            if (num != max && num != secMax) {
                thirdMax = Math.max(thirdMax,num);
                thirdFlag = true;
            }
        }
        if (!secFlag || !thirdFlag) {
            return max;
        }
        return thirdMax;
    }

    /**
     * @Description:
     * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：arr = [2,6,4,1]
     * 输出：false
     * 解释：不存在连续三个元素都是奇数的情况。
     * 示例 2：
     *
     * 输入：arr = [1,2,34,3,4,5,7,23,12]
     * 输出：true
     * 解释：存在连续三个元素都是奇数的情况，即 [5,7,23] 。
     * 
     *
     * 提示：
     *
     * 1 <= arr.length <= 1000
     * 1 <= arr[i] <= 1000
     *
     * solution: 计数 遍历一遍，然后输出是否有 三个连续的奇数 O(N) O(1) 2.滑动边框解决 O(N) O(N)
     * @Date: 2021/3/29 10:13
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int i : arr) {
            if (i % 2 == 1) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description:
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]。
     * 示例 2：
     *
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * 
     *
     * 提示：
     *
     * 2 <= nums.length <= 103
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     *
     * solution: 寻找  arr[i] target-arr[i]是否存在 加个map   O(N) O(N)
     * 优化
     * @Date: 2021/3/29 10:18
     */
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
                arr[0] = i;
                arr[1] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i],i);
        }
        return arr;
    }

    /**
     * @Description:
     * 找出数组中重复的数字。
     *
     *
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
     * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     *
     * 示例 1：
     *
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * 
     *
     * 限制：
     *
     * 2 <= n <= 100000
     *
     * solution: 1.用list存 然后判断是否有重复数字 O(N) O(N) 2.bucket排序 3.set add添加失败 则返回
     * @Date: 2021/3/29 10:34
     */
    /*public int findRepeatNumber(int[] nums) {
        int[] arr = new int[nums.length];
        for (int num : nums) {
            arr[num]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 1) {
                return i;
            }
        }
        return -1;
    }*/
   /* public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }*/

    //萝卜占坑 评论区解答  判断当前坑位的编号是否与 坑位相等，不等的话 和对应的坑位兑换  在兑换中如果相同 则返回该数字  否则循环完成返回-1
    //好巧妙的方式 解答  奇思妙想
    public int findRepeatNumber(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp=nums[i];
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;
    }

    /**
     * @Description:
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     *
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     *
     * 
     *
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     *
     * solution: compare
     * the elements who in the diagonal  ,
     * first find the rule  how to be the diagonal
     * 在数组范围内，横纵左边依次加1  从左上角横纵遍历两轮
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 20
     * 0 <= matrix[i][j] <= 99
     *
     *
     * @Date: 2021/3/29 11:38
     */
    /*public boolean isToeplitzMatrix(int[][] matrix) {
        int wide = matrix[0].length;
        int len = matrix.length;
        if (wide == 1 || len == 1) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            int tmp = matrix[i][0];
            for (int j = 1; j < wide; j++) { // 横纵左边限制在 wide len内
                if (i + j < len) {
                    if (tmp != matrix[i + j][j]) {
                        return false;
                    }
                }
            }
        }
        for (int i = 0; i < wide; i++) {
            int tmp = matrix[0][i];
            for (int j = 1; j < len; j++) { // 横纵左边限制在 wide len内
                if (i + j < wide) {
                    if (tmp != matrix[j][i + j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }*/
    //官方解答 只需要判断  matrix[i][j] 是否等于  matrix[i-1][j-1]  O(N) O(N)
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @Description:
     * 给定一个已按照 升序排列 的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
     *
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
     * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     *
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 
     * 示例 1：
     *
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     * 示例 2：
     *
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     * 示例 3：
     *
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     * 
     *
     * 提示：
     *
     * 2 <= numbers.length <= 3 * 104
     * -1000 <= numbers[i] <= 1000
     * numbers 按 递增顺序 排列
     * -1000 <= target <= 1000
     * 仅存在一个有效答案
     *
     * solution: map push(value,i)  map.containsKey(target - numbers[i]) return [i,map.get(target - numbers[i])]
     * @Date: 2021/3/29 15:24
     */
    /*public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return i < map.get(target - numbers[i]) ?
                        new int[]{i + 1,map.get(target - numbers[i]) + 1} :
                        new int[]{map.get(target - numbers[i]) + 1, i + 1};
            }
            map.put(numbers[i],i);
        }
        return new int[2];
    }*/
    /*//神奇的双指针  low = 0 high = numbers.length - 1
    // sum > target  high--;
    // sum < target low++;
    // else return
    // while(low < high) 时间复杂度  O(N) O(1)
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                return new int[]{low + 1, high + 1};
            }
        }
        return new int[2];
    }*/

    /**
     * @Description:
     * 给定一个整数数组 arr，如果它是有效的山脉数组就返回true，否则返回 false。
     *
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     *
     * arr.length >= 3
     * 在0 < i< arr.length - 1条件下，存在i使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     *
     * 示例 1：
     *
     * 输入：arr = [2,1]
     * 输出：false
     * 示例 2：
     *
     * 输入：arr = [3,5,5]
     * 输出：false
     * 示例 3：
     *
     * 输入：arr = [0,3,2,1]
     * 输出：true
     * 
     *
     * 提示：
     *
     * 1 <= arr.length <= 104
     * 0 <= arr[i] <= 104
     *
     * solution: 一半递增 一般递减 so easy 3段判断  O(N) O(1)  计数 吧 count > 2
     * @Date: 2021/3/29 15:47
     */
    public boolean validMountainArray(int[] arr) {
        if (arr.length <= 2) return false;
        int count = 0;
        boolean asc = false;
        if (arr[1] < arr[0]) {
            return false;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }
            if (arr[i] > arr[i - 1] && !asc) {
                asc = true;
                count++;
            }
            if (arr[i] < arr[i -1] && asc) {
                asc = false;
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return count == 2;
    }

    /**
     * @Description:
     * 统计一个数字在排序数组中出现的次数。
     *
     * 
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     * 示例2:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     * 
     *
     * 限制：
     *
     * 0 <= 数组长度 <= 50000
     *
     * solution: 遍历nums count计数,相等+1 大于则break
     * @Date: 2021/3/31 10:05
     */
    /*public int search(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num > target) {
                break;
            }
            if (num == target) {
                count++;
            }
        }
        return count;
    }*/

    //二分法 找target的右边界 和 target-1的右边界 前者 - 后者,评论区大佬解答 O(logN) O(1)
    public int search(int[] nums, int target) {
        return helper(nums,target) - helper(nums,target - 1);
    }

    public int helper(int[] nums,int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= target) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    /**
     * @Description:
     * 给定一副牌，每张牌上都写着一个整数。
     *
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     *
     * 每组都有X张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回true。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     * 示例 2：
     *
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 3：
     *
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 4：
     *
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     * 示例 5：
     *
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     *
     * 提示：
     *
     * 1 <= deck.length <= 10000
     * 0 <= deck[i] <10000
     * 
     *
     * solution:
     * 1. deck 检测是否能够分组  , 需要看他们是否有共同的公约数
     * 2.看公约数是否大于2  list存个数 遍历list找最小,
     * @Date: 2021/3/31 10:40
     */
    public boolean hasGroupsSizeX(int[] deck) {
        int[] arr = new int[10001];
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        //计数
        for (int i : deck) {
            arr[i]++;
        }
        //计数添加到list
        for (int j : arr) {
            if (j != 0) {
                list.add(j);
            }
        }
        //寻找最大公约数
        int g = list.get(0);
        if (list.size() == 1) {
            return g >= 2;
        }
        for (Integer integer : list) {
            g = gcd(g, integer);
        }
        return g >= 2;
    }

    public int gcd(int x, int y) {
        //这里有点绕
        return x == 0 ? y : gcd(y % x , x);
    }

    /**
     * @Description:
     *给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
     *
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[1,4,7],[2,5,8],[3,6,9]]
     * 示例 2：
     *
     * 输入：matrix = [[1,2,3],[4,5,6]]
     * 输出：[[1,4],[2,5],[3,6]]
     * 
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 105
     * -109 <= matrix[i][j] <= 109
     *
     * solution: new int[martrix[0].length][martrix.length] 交换行列索引
     * @Date: 2021/3/31 11:34
     */
    public int[][] transpose(int[][] matrix) {
        int[][] res = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }


    public static final List<String> productPlaceList = Arrays.asList("攀钢","攀钢钒");

    @Test
    public void testArray() {
        int[] arr = {1, 2, 3, 4};
        List<Integer> list = instance.addToArrayForm(arr, 47);
        list.forEach(System.out::println);
    }

}
