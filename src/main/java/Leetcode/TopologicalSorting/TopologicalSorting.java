package Leetcode.TopologicalSorting;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/10/30 10:06
 **/
public class TopologicalSorting {

    private static final TopologicalSorting instance = new TopologicalSorting();

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    /***
     *@描述 计算岛屿的周长
     * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
     *
     * Grid cells are connected horizontally/vertically (not diagonally).
     * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
     *
     * The island doesn't have "lakes", meaning the water inside isn't connected to the water
     * around the island. One cell is a square with side length 1. The grid is rectangular,
     * width and height don't exceed 100. Determine the perimeter of the island.
     *
     * 网格中的格子水平和垂直方向相连
     *
     *思路：
     * 一个格子如果靠边界 +1 靠水 +1  4面都是靠水或者靠边界，就不加   计算结果然后返回
     *
     *遍历数组 通过起始位置 判断是否为边界  通过旁边是否有0 判断是否靠水
     * 官方；迭代
     * 循环遍历 grid每一个格子  判断是否为边界和靠水, 统计cnt   ans += cnt
     * 网格中的格子水平和垂直方向相连   这句话很重要,之前没看到 算是没读懂题目 做起来有点难
     * 实现 ：  对 dx dy 分别 向四周衍生一格,   使用 dx < 0 || dx > n || dy < 0 || dy > m || grid[dx][dy] == 0 来作用cnt 值
     * 最后返回 ans
     * 时间复杂度：O(4mn) ~ O(mn)
     * 空间复杂度 : O(1)
     *@创建时间 2020/10/30
     */
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }

    /***
     *@描述
     * Example 1:
     *
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2]
     * 思路:
     * 1.nums1 nums2 both  distinct opt     ,compare size  smaller array  遍历元素 larger array  ,存在就存储在 int[] array
     * 2.nums1 nums2转LinkedList 遍历 nums1 nums2  存在 就存储数据 到list ,然后删除 该元素  最后 list转array 返回
     * 3.set集合
     * 4.sort后使用双指针
     *@创建时间 2020/11/2
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index = 0,index1 =0,index2 =0;
        int[] res = new int[len1+len2];
        while (index1 < len1 && index2 < len2){
            int num1 = nums1[index1];
            int num2 = nums2[index2];
            if(num1 == num2){
                if(index == 0 || num1 != res[index-1]){
                    res[index++] = num1;
                }
                index1++;
                index2++;
            }else if(num1 > num2){
                index2++;
            }else {
                index1++;
            }
        }
        return Arrays.copyOfRange(res,0,index);
    }


    /**
     * @Description
     * @ 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
     *
     * 如果小镇的法官真的存在，那么：
     *
     * 小镇的法官不相信任何人。
     * 每个人（除了小镇法官外）都信任小镇的法官。
     * 只有一个人同时满足属性 1 和属性 2 。
     * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
     *
     * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
     * 输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
     * 输出：3
     *   arr[3] 3        arr[4] 2  n = N - 1 = 3
     *
     * solution: 统计每个人的信任人数，max = n - 1 他就是法官  1.arr桶计数  2.求max  3.判断是否有法官
     **/
    public int findJudge(int N, int[][] trust) {
        if (trust.length == 0 && N == 1) {
            return 1;
        }
        int[] arr = new int[N + 1];
        for (int i = 0; i < trust.length; i++) {
            arr[trust[i][1]]++;
        }
        int max = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                res = i;
            }
        }
        if (max == N - 1) {
            for (int i = 0; i < trust.length; i++) {
                if (trust[i][0] == res) {
                    return -1;
                }
            }
            return res;
        }
        return -1;
    }

    public static void main(String[] args) {
       int[][] arrr = new int[][]{{1,3},{2,3},{3,1}};
        System.out.println(instance.findJudge(3,arrr));
    }
}
