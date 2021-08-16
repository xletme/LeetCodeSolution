package Leetcode.ListNode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/7 15:13
 **/
public class OrderKListNode {

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
