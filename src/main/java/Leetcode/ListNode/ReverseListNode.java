package Leetcode.ListNode;

import java.util.*;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/7 15:24
 **/
public class ReverseListNode {

    public int[] reversePrint(ListNode head) {
       Stack<ListNode> stack = new Stack<>();
       while (head != null){
           stack.push(head);
           head = head.next;
       }
       int size = stack.size();
       int[] print = new int[size];
       //注意：只能使用i < size 不能使用 i < stack.size() 每次pop size会减少
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    //思路:先放入栈中,再取出来构造即可
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        int size = stack.size();
        if(stack.isEmpty()){
            return null;
        }
        ListNode newHead = stack.pop();
        ListNode tailNode = newHead;
        for(int i=0;i<size-1;i++){
            ListNode cur = stack.pop();
            tailNode.next = cur;
            tailNode = cur;
        }
        tailNode.next = null;
        return newHead;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        node.next = new ListNode(3);
        node.next.next = new ListNode(4);
        node.next.next.next = new ListNode(5);

        ReverseListNode reverseListNode = new ReverseListNode();
       reverseListNode.reverseList(node);
    }
}
