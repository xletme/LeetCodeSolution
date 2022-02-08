package Leetcode;

import java.util.Arrays;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/17 10:06
 **/
public class UnionFind {

    private static final UnionFind instance = new UnionFind();

    /***
     *@描述
     * We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.
     *
     * Additionally, we are given a cell in that matrix with coordinates (r0, c0).
     *
     * Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)
     *  思路 ：
     *  1.计算是个顶点 到 (r0,c0)的距离,取最大值
     *  2.找出 0-max 之间的坐标就OK  满足 0 <= r < R and 0 <= c < C.  相当于穷举法
     *
     *  1.直接从坐标原点扩散，  距离 1 2  3  4 。。。。
     *  2.直到上下左右全部到边界
     *@创建时间 2020/11/17
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {

        int[][] res = new int[R * C][2];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int p = i * C + j;
                res[p][0] = i;
                res[p][1] = j;
            }
        }

        Arrays.sort(res, (arr1, arr2) -> {
            int d1 = dist(arr1[0],arr1[1],r0,c0);
            int d2 = dist(arr2[0],arr2[1],r0,c0);
            return Integer.compare(d1,d2);
        });

        return res;
    }

    private int dist(int r1,int c1,int r2,int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private int findMax(int[][] arr,int r0,int c0){
        int length = arr.length - 1;
        int width = arr[0].length - 1;

        int a =  r0 + c0;
        int b = Math.abs(width - r0) + Math.abs(-c0);
        int c = Math.abs(-r0) + Math.abs(length - c0);
        int d = Math.abs(width -r0) + Math.abs(length - c0);

        int max = a;
        if (max < b) {
            max = b;
        }
        if (max < c ) {
            max = c;
        }
        if (max < d) {
            max = d;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(instance.allCellsDistOrder(2, 3, 1, 2)));
    }
}
