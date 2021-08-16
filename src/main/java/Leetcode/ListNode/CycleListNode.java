package Leetcode.ListNode;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/10/9 11:08
 **/
public class CycleListNode {

    /***
     *@描述  查看是否存在环装 链表  pos = -1   pos 索引 -1 0 1 2 3
     * pos 只是方便理解 题意    快慢指针，有环能判断出  没有使用 head->next == null直接break
     * 时间复杂度：O(N)O(N)，其中 NN 是链表中的节点数
     * 空间复杂度：O(1)O(1)。我们只使用了两个指针的额外空间。
     *@参数 [head]
     *@返回值 boolean
     *@创建人 maoXin
     *@创建时间 2020/10/9
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return false;
        }
        // 3->1->2->7->1  pos 1
        ListNode first = head.next;
        ListNode second = head.next.next;
        while (second.next.next != null){
            if(first.val == second.val){
                return true;
            }
            first = first.next;
            second = second.next.next;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(7);

        CycleListNode instance = new CycleListNode();
        System.out.println(instance.hasCycle(node));
    }
}
