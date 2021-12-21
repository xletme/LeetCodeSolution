package Leetcode.ListNode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/7 15:13
 **/
public class OrderKListNode {

    /**
     * @Description:
     * solution
     * 滑动窗口
     * p1 和 p2 始终保持k个距离, p2滑到底的时候,p1.val应该就是距离最终位置为kth的值
     * @Date: 2021/12/21 10:28
     */
    public int kthToLast(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head.next;
        for(int i =0;i<k-1;i++){
            p2 = p2.next;
        }
        if(k == 0){
            p1 = head;
        }
        while(p2 != null){
            p2=p2.next;
            p1=p1.next;
        }
        return p1.val;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
