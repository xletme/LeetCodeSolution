package Leetcode.Heap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/15 9:56
 **/
public class LeastNumbersHeap {

    private static LeastNumbersHeap heap = new LeastNumbersHeap();

    //最小k个数 ：a.正常情况，arr冒泡排序 一次输出 0-k-1下标的元素  b.arr为空 返回null c.k > arr.size 输出全部arr 时间复杂度 O(N2) 空间复杂度 O(N)
   /* public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length == 0){
            return null;
        }
        int[] sort = sort(arr);
        if(k > sort.length){
            int[] test = new int[sort.length];
            test = sort;
            return test;
        }else {
            int[] test = new int[k];
            for(int i=0;i<k;i++){
                test[i] = sort[i];
            }
            return test;
        }
    }*/

    public int[] sort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for(int j = i+1;j< arr.length; j++){
                if(arr[i] > arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        return arr;
    }

    // PriorityQueue（默认小根堆) 堆实现: 大根堆 从小到大 小根堆 从大到小，比较器符号相反  1.堆元素小于k 直接放数据 2.否则比较,比堆顶元素大 pass ，小则
    //先poll在offer 在取0-k 返回res int[]
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length == 0 || k == 0){
            return null;
        }
        Queue<Integer> queue = new PriorityQueue<>((v1,v2)->(v2-v1));
        for (int i = 0; i < arr.length; i++) {
            if(queue.size() < k){
                queue.offer(arr[i]);
            }else if(!queue.isEmpty() && arr[i] < queue.peek()){
                queue.poll();
                queue.offer(arr[i]);
            }
        }

        int[] res = new int[queue.size()];
        if(queue.size() > 0) {
            for (int i = 0; i < k; i++) {
                res[i] = queue.poll();
            }
        }
        return res;
    }

    //思路:heap impl  a.select big heap  b.every time select top two  a == b ,poll twice  a<b || a>b poll twice offer |a-b|
    // 3.until heap isEmpty or heap is single  4.special stones equals null,return 0;
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 0){
            return 0;
        }
        Queue<Integer> queue = new PriorityQueue<>((v1,v2)->(v2-v1));
        for (int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);//O(N)
        }
        while (!queue.isEmpty() && queue.size() != 1){
            int a = queue.poll();
            int b = queue.poll();
            if(a == b){

            }else {
                queue.offer(Math.abs(a-b));
            }
        }
        if(queue.isEmpty()){
            return 0;
        }else {
            return queue.peek();
        }
    }

    public static void main(String[] args) {
       String totalPrice1 = "107683.1600";
       String totalPrice2 = "111587.4000";

       BigDecimal totalAmt = BigDecimal.ZERO;
       totalAmt = totalAmt.add(new BigDecimal(totalPrice1));
       totalAmt = totalAmt.add(new BigDecimal(totalPrice2));
        System.out.println(new BigDecimal(totalAmt.toString()).setScale(2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(totalPrice1).setScale(2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(totalPrice2).setScale(2, RoundingMode.HALF_UP));
    }
}
