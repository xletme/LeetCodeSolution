package Leetcode.BreadFirst;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/10 10:01
 **/
public class BreadFirst {

    private static final BreadFirst instance = new BreadFirst();

    private static  int  count = 0;

    /***
     *@描述
     *  Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     *
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * But the following [1,2,2,null,3,null,3] is not:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * Follow up: Solve it both recursively and iteratively.
     * 思路 ：
     * 队列+双端队列实现  队列用来广度优先遍历tree    双端队列用来检测结构是否对称
     * size != 1 && size % 2 != 0 直接false
     * 判断首尾节点是否对称   null 则存储 0
     * O(N)  O(N)
     *
     */
    public boolean isSymmetric(TreeNode root) {
        Deque<Integer> deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            if(size != 1 && size % 2 == 1){
                return false;
            }
            for(int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                if(poll != null) {
                    deque.offerFirst(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }else  {
                    deque.offerFirst(-1);
                }
            }
            while (!deque.isEmpty() && deque.size() != 1){
                Integer first = deque.pollFirst();
                Integer last = deque.pollLast();
                if(!first.equals(last)){
                    return false;
                }
            }
            deque.clear();
        }
        return true;
    }

    //官方 递归
    // 时间 ：O(N) 遍历n个节点   空间 ：栈开辟的空间有关
   /* public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p,TreeNode q){
        if(p == null && q==null){
            return true;
        }else if(p == null || q == null){
            return false;
        }
        return p.val == q.val && check(p.left,q.right) && check(p.right,q.left);
    }*/

   /***
    *@描述
    * Given a n-ary tree, find its maximum depth.
    *
    * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    *
    * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
    *
    * Example 1:
    *
    * Input: root = [1,null,3,2,4,null,5,6]
    * Output: 3
    * 思路 : 广度优先遍历  flag++  queue
    *
    *@创建时间 2020/11/10
    */
    public int maxDepth(Node root) {
        if(root == null)
            return 0;
        int count = 0; //搞不明白 这里为啥定义就可以  定义为static变量就不行
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node poll = queue.poll();
                if(poll != null && poll.children != null) {
                    for (Node child : poll.children) {
                        queue.offer(child);
                    }
                }
            }
            count++;
        }
        return count;
    }


    /***
     *@描述
     * Given a binary tree, find its minimum depth.
     *
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     *Input: root = [3,9,20,null,null,15,7]
     * Output: 2
     * Example 2:
     *
     * Input: root = [2,null,3,null,4,null,5,null,6]
     * Output: 5
     * 思路 ;
     *   深度优先搜索  求最小值  每次对比 一次  取最小值
     *   广度优先搜索，只要有节点 为空，就返回 树的深度  O(N)   O(N)
     *@创建时间 2020/11/11
     */
    /*public int minDepth(TreeNode root) {

        if(root == null){
            return 0;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                if(poll.left == null && poll.right == null){
                    count++;
                    return count;
                }
                if(poll.left !=null) {
                    queue.offer(poll.left);
                }
                if(poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            count++;
        }
        return count;
    }*/

    //答友
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        if(root.left != null && root.right == null){
            return 1 + minDepth(root.left);
        }

        if(root.right != null && root.left == null){
            return 1+minDepth(root.right);
        }

        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }

    /***
     *@描述
     *  In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
     *
     * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
     *
     * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
     *
     * Return true if and only if the nodes corresponding to the values x and y are cousins.
     *
     *   题目：是否为堂兄弟节点
     * 思路 ：深度优先搜索判断 该节点的level，  if((level1 == level2 && level1.parents !=  level2.parents)  true else false
     * root.left.val   root.right.val  root.val
     * use  p1 p2  p1depth p2depth
     *
     *
     *@创建时间 2020/11/11
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int p1depth = 0;
        int p2depth = 0;
        int p1parents = 0;
        int p2parents = 0;
        int count = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                if(poll != null){
                    if(poll.left != null && poll.left.val == x){
                        p1depth = count;
                        p1parents = poll.val;
                    }
                    if(poll.left != null && poll.left.val == y){
                        p2depth = count;
                        p2parents = poll.val;
                    }
                    if(poll.right != null && poll.right.val == x){
                        p1depth = count;
                        p1parents = poll.val;
                    }
                    if(poll.right != null && poll.right.val == y){
                        p2depth = count;
                        p2parents = poll.val;
                    }
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            count++;
        }
        return p1depth == p2depth && p1parents != p2parents;
    }

    /***
     *@描述
     *请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * 思路 ： 1.和之前简单的 广度优先搜索相似，正序列出所有list  奇数层不变，偶数 做reverse操作
     *        2.奇数 循环不变，偶数从右到左循环
     *
     *@创建时间 2020/11/12
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = true;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for(int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                if(flag){
                    list.add(poll.val);
                }else {
                    list.add(0,poll.val);//这个有点高级  巧妙
                }
                if(poll.left != null){
                    queue.offer(poll.left);
                }
                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }
            flag = !flag;
            if(list.size() > 0) {
                res.add(list);
            }
        }
        return res;
    }

    /***
     *@描述
     * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     * Follow up:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
     *  思路 ：
     *  left--> right  right-->nextTree.left  nextTree.left-->nextTree.right     if(==size)  next--> null
     *  广度优先搜索
     *@创建时间 2020/11/12
     */
    public Node connect(Node root) {

        if(root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Node  tmp = new Node();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if(poll != null && poll.left != null && poll.right != null) {
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                    tmp.next = poll.left;
                    poll.left.next = poll.right;
                    if (i == size - 1) {
                        poll.right.next = null;
                        break;
                    }else {
                        tmp = poll.right;
                    }
                }
            }
        }
        return root;
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Node root = new Node(1);

        Node treeNode2 = new Node(2);
        treeNode2.left = new Node(4);
        treeNode2.right = new Node(5);

        Node treeNode3 = new Node(3);
        treeNode3.left = new Node(6);
        treeNode3.right = new Node(7);

        root.left = treeNode2;
        root.right = treeNode3;

        System.out.println(instance.connect(root));

    }
}
