package Leetcode.ListNode;

import org.junit.Assert;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/8 11:22
 **/
public class MiddleListNode {

    /**
     * @Description:
     * solution 快慢指针 找出middleNode
     *
     * @Date: 2021/12/21 10:26
     */
    public ListNode middleNode(ListNode head) {
        if(head.next == null){
            return head;
        }
        if(head.next.next == null){
            return head.next;
        }
        ListNode first = head;
        ListNode second = first.next;
        while(second.next != null){
            first = first.next;
            if(second.next.next == null){
                break;
            }
            second = second.next.next;
        }
        return first.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
       // node.next.next.next.next.next = new ListNode(6);

        MiddleListNode middleListNode = new MiddleListNode();
        ListNode listNode = middleListNode.middleNode(node);
        ListNode expectNode = new ListNode(3);
        Assert.assertEquals(expectNode, listNode);
    }
}
