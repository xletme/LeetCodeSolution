package Leetcode.DynamicPlanning;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/10/23 13:45
 **/
public class Ugly {
    public int[] nums = new int[1690];
    Ugly() {
        HashSet<Long> seen = new HashSet();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.add(1L);

        long currUgly, newUgly;
        int[] primes = new int[]{2, 3, 5};
        for(int i = 0; i < 1690; ++i) {
            currUgly = heap.poll();
            nums[i] = (int)currUgly;
            for(int j : primes) {
                newUgly = currUgly * j;
                if (!seen.contains(newUgly)) {
                    seen.add(newUgly);
                    heap.add(newUgly);
                }
            }
        }
    }

    /*public static void main(String[] args) {
        Ugly u = new Ugly();
        System.out.println(u.nums[14]);
    }*/
}

/***
 *@描述 优先级队列 来实现这个最为关键   PriorityQueue   每次heap.poll() 提取的是堆中的最小值
 *@创建时间 2020/10/23
 */
class Solution {
    public static Ugly u = new Ugly();
    public int nthUglyNumber(int n) {
        return u.nums[n - 1];
    }
}
