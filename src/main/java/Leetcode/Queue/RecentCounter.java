package Leetcode.Queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/21 11:19
 **/
class RecentCounter {

    private ArrayDeque<Integer> queue = new ArrayDeque<>();

    public RecentCounter() {

    }

    //思路：这道题很难读懂, input time t  return the counts of [t-3000,3000]
    // put the t into queue FIFO ,if queue.peek() < t-3000 move it from queue
    //else add t into queue  finally return the size of queue
    public int ping(int t) {
        while (!queue.isEmpty()){
            Integer peek = queue.peek();
            if(peek < t-3000){
                queue.remove();
            }else break;
        }
        queue.offer(t);
        return queue.size();
    }

    //按照k分组 求出最大值，依次放入新数组，最后返回 分组的时候使用queue

    //官方解答 滑动窗口 单调队列 分形成窗口前和形成窗口后，每次加入元素，删除比加入元素小的所有元素
    //O(N)  O(1)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        //未形成窗口
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
        }
        if (!deque.isEmpty())
        res[0] = deque.peekFirst();
        //形成窗口后
        for (int i = k; i < nums.length; i++) {

            if (!deque.isEmpty() && deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
            if (!deque.isEmpty())
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0){
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k+1; i++) {
            for(int j = i; j < i+k; j++){
                queue.offer(nums[j]);
            }
            //求最大值 并放入队列 ,然后清空队列
            res[i] = maxQueue(queue);
            queue.clear();
        }
        return res;
    }*/

    private int maxQueue(ArrayDeque<Integer> queue){
        int res = 0;
        while (!queue.isEmpty()){
            if(queue.size() == 1){
                res =  queue.peek();
                break;
            }
            Integer pop = queue.pop();
            if(!queue.isEmpty()) {
                Integer peek = queue.peek();
                if (peek < pop) {
                    queue.pop();
                    queue.offer(pop);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int []  nums = new int[]{1,3,-1,-3,5,3,6,7};
        Arrays.stream(recentCounter.maxSlidingWindow(nums, 3)).forEach(System.out::println);


    }
}
