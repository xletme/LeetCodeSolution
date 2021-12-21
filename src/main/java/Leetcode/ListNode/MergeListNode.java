package Leetcode.ListNode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/8 16:18
 **/
public class MergeListNode {

    /**
     * @Description:
     * 递归 前提 l1和l2是有序的，l1或者l2为null，直接返回不为null的一方
     * l1.val < l2.val 合并l1.next 和 l2 ，合并完成后的结果赋值给l1.next
     * 否则合并l1和l2.next，合并后的值赋值给l2.next
     * 递归思路 O(N) O(N)
     * @Date: 2021/12/21 10:18
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * @Description:
     * @Date: 2021/6/18 10:07
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);


        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println(11);
    }
}
