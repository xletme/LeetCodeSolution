package Leetcode.BrainTeaser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

/**
 * @Author maoXin
 * @Description
 * @Date 16:34 2021/1/5
 */
public class BrainTeaser {

    private static final BrainTeaser instance = new BrainTeaser();

    /**
     * @Description:
     *  Given two strings a and b, find the length of the longest uncommon subsequence between them.
     *
     * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s. For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
     *
     * An uncommon subsequence between two strings is a string that is a subsequence of one but not the other.
     *
     * Return the length of the longest uncommon subsequence between a and b. If the longest uncommon subsequence doesn't exist, return -1.
     *
     *  
     *
     * Example 1:
     *
     * Input: a = "aba", b = "cdc"
     * Output: 3
     * Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of "aba" but not "cdc".
     * Note that "cdc" is also a longest uncommon subsequence.
     *
     * solution:真没看懂题目说明  ，看答友的解释才懂的 author：何去何从gw
     * 看程序自然懂
     * @Date: 2021/1/5 16:41
     */
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(),b.length());
    }

    /**
     * @Description:
     *  三枚石子放置在数轴上，位置分别为 a，b，c。
     *
     * 每一回合，我们假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。
     *
     * 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
     *
     * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
     *
     *  
     *
     * 示例 1：
     *
     * 输入：a = 1, b = 2, c = 5
     * 输出：[1, 2]
     * 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
     * 示例 2：
     *
     * 输入：a = 4, b = 3, c = 2
     * 输出：[0, 0]
     * 解释：我们无法进行任何移动。
     *
     * solution: max (b-a-1) + (c-b-1) =  c-a -2   min 0  or 1 or 2
     *
     * 被坑了  1 3 5 特殊情况    需要一步    1移动到4位置 或者 5移动到2位置 1步即可完成，ct
     * @Date: 2021/1/5 16:46
     */
    public int[] numMovesStones(int a, int b, int c) {

        int[] res = new int[2];
        int[] arr = new int[]{a, b, c};
        arr = Arrays.stream(arr).sorted().toArray();
        a = arr[0];
        b = arr[1];
        c = arr[2];
        res[1] = Math.abs(a - c) -2;
        if (Math.abs(a - b) == 2 || Math.abs(c - b) == 2) {
            res[0] = 1;
        } else {
            if (Math.abs(a - b) != 1 && Math.abs(c - b) != 1) {
                res[0] = 2;
            }
            if (Math.abs(a - b) == 1 && Math.abs(c - b) != 1) {
                res[0] = 1;
            }
            if (Math.abs(a - b) != 1 && Math.abs(c - b) == 1) {
                res[0] = 1;
            }
        }
        return res;
    }

    /**
     * @Description:
     *  你和你的朋友，两个人一起玩 Nim 游戏：
     *
     * 桌子上有一堆石头。
     * 你们轮流进行自己的回合，你作为先手。
     * 每一回合，轮到的人拿掉 1 - 3 块石头。
     * 拿掉最后一块石头的人就是获胜者。
     * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 4
     * 输出：false
     * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     *      因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
     *
     * solution:  找规律          1  win  2 win 3 win 4 lose 5 win 6 win  7 win  8 lose 9 win 10 win  11 win  12 lose
     * 1 2 3 5 6 7 9 10 11
     * 4 8 12  4 * n  if n % 4 == 0 lose  else win
     *
     * @Date: 2021/1/6 10:05
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }


    /**
     * @Description:
     *  在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
     *
     * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     *
     * solution:   先求出最小的 坐标差 ,后续的坐标依次 做运算  判断true of false
     * if coordinates.length == 2 true
     * @Date: 2021/1/6 10:17
     */
    /*public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) {
            return true;
        }
        int x = coordinates[0][0];
        int y = coordinates[0][1];
        int tmpX = 0;
        int tmpY = 0;
        int[] minNote = minNote(coordinates[0], coordinates[1]);
        if (minNote[0] == 0) {
            for (int[] coordinate : coordinates) {
               if (coordinate[0] - x != 0) {
                   return false;
               }
            }
            return true;
        }
        if (minNote[1] == 0) {
            for (int[] coordinate : coordinates) {
                if (coordinate[1] - y != 0) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 1; i < coordinates.length; i++) {
            tmpX = coordinates[i][0] - x;
            tmpY = coordinates[i][1] - y;
            if ((tmpX / minNote[0]) != (tmpY / minNote[1])) {
                return false;
            }

        }
        return true;
    }

    private int[] minNote(int[] arr1,int[] arr2) {
        int[] res = new int[2];
        int x = Math.abs(arr2[0] - arr1[0]);
        int y = Math.abs(arr2[1] - arr1[1]);
        int gcd = getGCD(x, y);
        System.out.println(gcd);
        res[0] = x / gcd;
        res[1] = y / gcd;
        return res;
    }


    public static int getGCD(int a, int b) {
        if (a < 0 || b < 0) {
            return -1; // 数学上不考虑负数的约数
        }
        if (b == 0) {
            return a;
        }
        return a % b == 0 ? b : getGCD(b, a % b);
    }*/

    //只能说官方解答 牛逼  找规律1 号吉尔牛皮
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 =coordinates[1][0]-coordinates[0][0];
        int y1 =coordinates[1][1]-coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int x2 =coordinates[i][0]-coordinates[0][0];
            int y2 =coordinates[i][1]-coordinates[0][1];
            if (x1 * y2 != x2 * y1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description:
     *  平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
     *
     * 你可以按照下面的规则在平面上移动：
     *
     * 每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
     * 必须按照数组中出现的顺序来访问这些点。
     *  
     *
     * solution: 先走斜边  然后在走 直边，这样子是最短的
     *  (1 1)  (3 4)  （-1 0）    （2,3） = （2 2） + （0 1） 3   （3 4） （-1 0） （-4，-4） = （-4，-4） 4  request abs
     *  （n n) n     (0 n) n   (m,n)   min(m,n) +(m-n)   O(N2)
     * @Date: 2021/1/7 10:21
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length - 1; i++) {
            res += calculateDistance(points[i],points[i + 1]);
        }
        return res;
    }

    private int calculateDistance(int[] arr1, int[] arr2) {
        int m = Math.abs(arr2[0] - arr1[0]);
        int n = Math.abs(arr2[1] - arr1[1]);
        /*if (m == n) {
            return m;
        } else {
            return Math.min(m,n) + Math.abs(m -n);
        }*/
        return Math.max(m,n);
    }

    /**
     * @Description:
     *  在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
     *
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
     *
     * 请你返回最终形体的表面积。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[[2]]
     * 输出：10
     * 示例 2：
     *
     * 输入：[[1,2],[3,4]]
     * 输出：34
     *
     * solution:
     *  2 * 1 * 4 + 1 *1 * 2  = 8 + 2 = 10
     *
     * 6 + 6  =12
     *
     * 12 -2 = 10 * 6  = 60
     *
     * 2 + 4 + 6 + 2 + 2 +4  + 6 =26
     *
     * 34  就是这么算出来的   60 - 26 = 34  60 本来有的面积   26 被覆盖的面积
     * 难点：在于计算上下左右重复的次数   面对面的次数  * 2  res = sum * 6 - count * 2
     * sum = sum(grid)  求count
     * @Date: 2021/1/7 10:51
     */
    public int surfaceArea(int[][] grid) {
        int res;
        int count = 0;
        int sum = 0;
        int m, n, p, q;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sum += grid[i][j];
                m = i - 1;
                n = i + 1;
                p = j - 1;
                q = j + 1;
                if (m >= 0) {
                    count += Math.min(grid[i][j] , grid[m][j]);
                }
                if (n <= grid.length - 1) {
                    count += Math.min(grid[i][j] , grid[n][j]);
                }
                if (p >= 0) {
                    count += Math.min(grid[i][j] , grid[i][p]);
                }
                if (q <= grid[0].length - 1) {
                    count += Math.min(grid[i][j] , grid[i][q]);
                }
                if (grid[i][j] > 1) {
                    count += (grid[i][j] - 1) * 2;
                }
            }
        }
        res = sum * 6 - count;
        return res;
    }


    @Test
    public void testNumMoves(){
        int[] arr1 = new int[] {2, 4};
        int[] arr2 = new int[] {2, 5};
        int[][] arr = new int[][] {{1,2},{3,4}};
       Assert.assertEquals(34,instance.surfaceArea(arr));
    }
}
