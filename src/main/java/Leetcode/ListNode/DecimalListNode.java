package Leetcode.ListNode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/7 13:54
 **/
public class DecimalListNode {

    //返回该链表所表示数字的 十进制值 。
    //思路：能从尾部遍历，是最优的
    //先转数据存储,倒序遍历数组  求十进制值 O(N) O(N)
    public int getDecimalValue(ListNode head) {
        int k=30;
        int[] arr = new int[30];
        int j=0,sum=0;
        while (head != null){
            arr[j] = head.val;
            head =head.next;
            j++;
        }
        if(j>=1){
            j--;
        }

        for(int i=j;i>=0;i--){
           if(arr[i] == 1){
               sum+=Math.pow(2,j-i);
           }
        }

        return sum;
    }


    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
      //  node.next = new ListNode(0);
       // node.next.next = new ListNode(1);
       // node.next.next.next = new ListNode(1);
        DecimalListNode decimalListNode = new DecimalListNode();
        System.out.println(decimalListNode.getDecimalValue(node));
    }
}
