package Leetcode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/7 17:47
 **/
public class RemoveListNode {

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        //前驱节点
        ListNode pre = head;
        set.add(head.val);
        while(pre.next != null){
            //当前节点
            ListNode cur = pre.next;
            if(set.contains(cur.val)){
                pre.next = pre.next.next;
            }else{
                set.add(cur.val);
                pre = pre.next;
            }
        }
        pre.next = null;
        return head;
    }

    public void removeNodes(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
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
        node.next.next.next = new ListNode(1);
        node.next.next.next.next = new ListNode(2);

        RemoveListNode removeListNode = new RemoveListNode();
        removeListNode.removeDuplicateNodes(node);
    }
}
