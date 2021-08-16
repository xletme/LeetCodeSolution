package Leetcode.ListNode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/7 10:42
 **/
public class DeleteListNode {

    //思路 ： 删除当前节点,把下个节点的值和next指针 复制到当前节点
    public void deleteNode(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public void printNode(ListNode node){
        do{
            System.out.println(node.val);
            node = node.next;
        }while (node != null);
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
        DeleteListNode deleteListNode = new DeleteListNode();
        deleteListNode.deleteNode(node.next);
        deleteListNode.printNode(node);
    }
}
