package Leetcode.ListNode;

import org.junit.Test;

/**
 * @Author maoXin
 * @Description
 * @Date 10:56 2021/4/9
 */
public class IntersectionNode {

    private static final IntersectionNode instance = new IntersectionNode();

    /**
     * @Description:
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，
     * 链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，
     * 所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     *
     * 注意：
     *
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     *
     * solution: 1.两个链表要相交，必定有一截是相同的Node
     * 我们可以从长度短的链表开始遍历，如果有，就能找到相交的点
     * 2.这里用了一个巧妙的方法，A B 从头开始遍历，A遍历完从B遍历 B遍历完从A遍历  因为 A+B =A+B
     * 到第二轮，就是现实了1中的需求 找到了起始点   最后没找到都为 null 也能退出循环
     * @Date: 2021/4/9 10:58
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode her = headA;
        ListNode him = headB;
        while (her != him) {
            her = her == null ? headB : her.next;
            him = him == null ? headA : him.next;
        }
        return her;
    }

    /**
     * @Description:
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * solution:
     * 1.写一遍到queue 和 stack ，分别取出来对比 看是否一致  O(N) O(N)
     * 2.遍历一次 记录单双数 ，双数中间分开 两边一样  单数 ，中间数除外，两边一样 O(N + N) O(N/2)
     * @Date: 2021/4/9 13:44
     */
    /*public boolean isPalindrome(ListNode head) {
        ListNode pre = head;
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        while (pre != null) {
            stack.push(pre.val);
            queue.offer(pre.val);
            pre = pre.next;
        }
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (!stack.pop().equals(queue.poll())) {
                return false;
            }
        }
        return true;
    }*/

    // 1.找前半部分尾节点 2.翻转后半部分 3.依次对照前后部分，得出结果
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode secHalfNode = findSecHalfNode(head);
        ListNode reverseSecHalNode = reverseListNode(secHalfNode);
        ListNode node1 = head;
        ListNode node2 = reverseSecHalNode;
        while (node2 != null) {
            if (node1.val != node2.val) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return true;
    }

    /**
     * @Description:
     * 要求：找链表前半部分结尾的尾节点
     * 思路：快慢指针 next next.next
     * @Date: 2021/4/9 14:30
     */
    public ListNode findSecHalfNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.next;
    }

    /**
     * @Description:
     * 要求：翻转listNode  用一个新的ListNode pre来依次取代 head翻转 比如 1->2->3->4->null
     * 1.pre 1->null  2.pre 2->1->null 3.pre 3->2->1->null 4.pre 4->3->2->1->null  O(N) O(1)
     * @Date: 2021/4/9 14:30
     */
    public ListNode reverseListNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * @Description:
     *
     * @Date: 2021/4/12 10:24给你一个链表的头节点 head 和一个整数 val ，
     * 请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * 示例 1：
     *
     *
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * 示例 2：
     *
     * 输入：head = [], val = 1
     * 输出：[]
     * 示例 3：
     *
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     * 提示：
     *
     * 列表中的节点在范围 [0, 104] 内
     * 1 <= Node.val <= 50
     * 0 <= k <= 50
     *
     * solution: 直接写  node.val == val，就删除 该节点
     * 1.头节点 cur = cur.next
     * 2.中间节点 pre.next = cur.next
     * 3.尾节点 pre.next = null
     */
    /*public ListNode removeElements(ListNode head, int val) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null && head != null) {
            if (cur.val == val) {
                if (pre == null){
                    if (head.next != null) {
                        head = head.next;
                    } else {
                        head = null;
                    }
                    cur = cur.next;
                    continue;
                } else if (cur.next == null) {
                    pre.next = null;
                } else {
                    pre.next = cur.next;
                    cur = cur.next;
                    continue;
                }
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }*/

    /**
     * @Description:
     * 官方的哨兵模式，在head头结点 加一个哨兵 是 链表用不为空  只用判断 cur.val 是否等于 val
     * O(N) O(1)
     * @Date: 2021/4/12 10:57
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(2);
        sentinel.next = head;
        ListNode pre = sentinel;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return sentinel.next;
    }

    /**
     * @Description:
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * solution:  遍历ListNode 初始化 pre = null
     * 每次 cur.next =  pre pre = cur 这两步得连在一起
     * cur = cur.next  得找个tmp保存cur.next 每次更新 pre = cur 最后返回 pre
     * @Date: 2021/4/12 11:05
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    //上面那题也要 哨兵删除法
    public ListNode deleteNode(ListNode head, int val) {
        ListNode sentinel = new ListNode(2);
        sentinel.next = head;
        ListNode pre = sentinel;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return sentinel.next;
    }

   static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    @Test
    public void testListNode() {

        ListNode listNode = new ListNode(0);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);

        listNode.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        System.out.println(instance.removeElements(listNode,4));
    }
}
