package Leetcode.BinaryTree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/12/10 11:19
 **/
public class Tree {

    private static final Tree instance = new Tree();

    private boolean isSubTree = false;

    private int sum = 0;

    private int count = 0;

    private List<Integer> answer = new ArrayList<>();

    private List<Integer> roundOne = new ArrayList<>();

    private List<Integer> roundTwo = new ArrayList<>();

    private LinkedList<Integer> linkedList = new LinkedList<>();

    private Set<Integer> set = new HashSet<>();

    private boolean flag = true;

    private int maxCount = 0;

    private int base = 0;

    private int abs = 0;

    private int minAbs = 0;

    private int min = 0;

    private int secMin = Integer.MAX_VALUE;

    private int pre = 0;

    private int cur = 0;

    private int res,k;

    private int[] arr;

    private int[] dx = new int[]{0,0,-1,1};
    private int[] dy = new int[]{1,-1,0,0};

    /***
     *@描述
     * Given two binary trees, write a function to check if they are the same or not.
     *
     * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
     *
     * Example 1:
     *
     * Input:     1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * Output: true
     * Example 2:
     *
     * Input:     1         1
     *           /           \
     *          2             2
     *
     *         [1,2],     [1,null,2]
     *
     * Output: false
     * solution :
     * 1.deepFirst  p and q,put their node into the two list, then compare the two list if equals  finally return true or false
     * 2.p and q traver in the same order ,if two elements different return false    else to the end return true
     *@创建时间 2020/12/10
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        traverDeepFirst(p,list1);
        traverDeepFirst(q,list2);
        return list1.equals(list2);
    }

    //这个其实广度优先
    public void traverDeepFirst(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        list.add(root.val);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
                list.add(poll.left.val);
            } else {
                list.add(-1);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
                list.add(poll.right.val);
            } else {
                list.add(-1);
            }
        }
    }

    static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
      }
   }

   static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

   /***
    *@描述
    * Given the root of a binary tree, return its maximum depth.
    *
    * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    *
    *  
    *
    * Example 1:
    *
    *
    * Input: root = [3,9,20,null,null,15,7]
    * Output: 3
    * solution: 层次遍历  breadFirst  the difficulty  : how to judge the every bread end  high++,previous  queue.size
    * O(N) O(N)
    *@创建时间 2020/12/10
    */
    public int maxDepth(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty() && root != null) {
            int size = queue.size();
            for (int i = 0; i< size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null && poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll != null && poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            res++;
        }
        return res;
    }

    /***
     *@描述
     * Invert a binary tree.
     *
     * Example:
     *
     * Input:
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * Output:
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * solution:
     * 1.breadFirst every bread ,then create a new treeNode.
     * 2.directly swap both left and right
     *@创建时间 2020/12/10
     */
    public TreeNode invertTree(TreeNode root) {
        changeInvert(root);
        return root;
    }
    public void changeInvert(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        changeInvert(root.left);
        changeInvert(root.right);
    }

    /***
     *@描述
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     *
     *  
     *
     * Example 1:
     *
     *
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * Output: 6
     * Explanation: The LCA of nodes 2 and 8 is 6.
     * solution:
     * 1.record the list the two node traver order root--->node  ,then find the the most remote same node ,finally return node
     * 2.use the special of bst , root.val > both p q   turn left  ; root.val < both p,q turn right; equals any of two ,return the equals
     * else one blow one above ,return cur root  breadFirst
     *@创建时间 2020/12/10
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = root;
        while (true) {
            if (res.val > p.val && res.val > q.val) {
                res = res.left;
            } else if (res.val < p.val && res.val < q.val) {
                res = res.right;
            } else {
                break;
            }
        }
        return res;
    }
    /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = new TreeNode();
        List<TreeNode> list1 = findPathByDeepFirst(root,p);
        List<TreeNode> list2 = findPathByDeepFirst(root,q);
        int size = Math.min(list1.size(), list2.size());
        for (int i = 0; i < size; i++) {
            if (list1.get(i).equals(list2.get(i))) {
                res = list1.get(i);
            } else {
                break;
            }
        }
        return res;
    }*/

    public List<TreeNode> findPathByDeepFirst(TreeNode root,TreeNode target) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode node = root;
        while (node != target) {
           list.add(node);
           if (target.val < node.val) {
               node = node.left;
           } else {
               node = node.right;
           }
        }
        list.add(node);
        return list;
    }

    /***
     *@描述
     * Find the sum of all left leaves in a given binary tree.
     *
     * Example:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     *
     * solution: traver the tree,then sum the val of left node
     *@创建时间 2020/12/11
     */
    public int sumOfLeftLeaves(TreeNode root) {
          preOrderRec(root);
          return sum;
    }

    private void preOrderRec(TreeNode root){
        if(root != null) {  // 1
            if (root.left != null && root.left.left == null && root.left.right == null) { // 2           1 2 is key point  1.是叶子节点  2.是左节点  3.遍历全部节点
                sum += root.left.val;
            }
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    /***
     *@描述
     * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
     * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *  
     *
     * For example:
     * Given BST [1,null,2,2],
     *
     *    1
     *     \
     *      2
     *     /
     *    2
     *  
     *
     * return [2].
     *
     * Note: If a tree has more than one mode, you can return them in any order.
     *
     * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
     * solution:  O(1)基本上不可能   1.中序遍历，把 val : count  存储到 hashMap 最后在来排序 找出 合适的元素   中序遍历  ，自动升序
     * 创建List<Integer> 存储 answer   ,define base :base element    count :element counts  maxCount:max count   inOrder   left--> val --> right
     * 官方说递归需要消耗 O(N) stack space ,  List 不用消耗     //假设由递归产生的隐式调用栈的开销不被计算在内 题目说的嘛
     *@创建时间 2020/12/11
     */
    public int[] findMode(TreeNode root) {
        dfs(root);
        return answer.stream().mapToInt(x->x).toArray();
    }

    private void dfs(TreeNode root) {
        if (root != null) {
            dfs(root.left);
            update(root.val);
            dfs(root.right);
        }
    }

    private void update(int val) {
        if (val == base) {
            count++;
        } else {
            base = val;
            count = 1;
        }
        if (count == maxCount) {
            answer.add(val);
        }
        if (count > maxCount) {
            answer.clear();
            answer.add(val);
            maxCount = count;
        }
    }

    /***
     *@描述
     * 530. 二叉搜索树的最小绝对差
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     *
     *
     *
     * 示例：
     *
     * 输入：
     *
     *    1
     *     \
     *      3
     *     /
     *    2
     *
     * 输出：
     * 1
     *
     * 解释：
     * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     * solution: 1.inOrder traver  calculate near two element , minAbs(a-b)    base cur abs(b-a)  minAbs
     *@创建时间 2020/12/11
     */
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return minAbs;
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            updateMinAbs(root.val);
            inOrder(root.right);
        }
    }

    private void updateMinAbs(int val) {
        if (base == 0) {
            base = val;
            return;
        } else {
            abs = val -base;
            base = val;
        }
       if (minAbs == 0 || abs < minAbs) {
           minAbs = abs;
       }
    }

    /***
     *@描述
     *给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     *
     *  
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     *solution:
     * 1.traver the tree,statistic high left and right different . add both max value ,  record the bigMax , this operation node one by one
     * every step to update the bigMax
     * 2.官方的推到方法  递归实现          a 思路和上面差不多，分别计算左儿子和右儿子的最大长度，然后  return L+R   b.计算树的高度 使用递归，null 返回0
     * 否则 返回 max(L+R)  作为树的高度  c 在计算高度的方法中更新  bigMax的值     最后返回的时候  bigMax - 1
     *@创建时间 2020/12/15
     */
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxCount;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int L = depth(root.left);
        int R = depth(root.right);
        maxCount = Math.max(maxCount,L+R);
        return Math.max(L,R) + 1;
    }

    /***
     *@描述
     *给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     *
     * 示例 1:
     * 给定的树 s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     *
     * solution:  1.t 带入 s去遍历每一个节点，看是否有一个节点都满足   O(N2） O(N） the depth of stack  递归
     *@创建时间 2020/12/15
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
         rootOver(s,t);
         return isSubTree;
    }

    private void rootOver(TreeNode s,TreeNode t) {
        if (s != null) {
            if(check(s,t)) {
                isSubTree = true;
            }
            rootOver(s.left,t);
            rootOver(s.right,t);
        }
    }

    //check is the same tree ,but mine is incorrect  rubbish   check(s.left,t.left) and check(s.right,t.right)
    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }

    /***
     *@描述
     *589. N叉树的前序遍历 迭代法
     *
     *@创建时间 2020/12/15
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preOrderForOne(root,res);
        return res;
    }

    private void preOrderForOne(Node root,List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            for (Node child : root.children) {
                preOrderForOne(child,list);
            }
        }
    }

    /***
     *@描述
     *使用迭代法做后续遍历
     *@创建时间 2020/12/15
     */
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (pop != null) {
                res.addFirst(pop.val);
                for (Node child : pop.children) {
                    stack.push(child);
                }
            }
        }
        return res;
    }

    /***
     *@描述
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     *
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     *输入:
     * 	Tree 1                     Tree 2
     *           1                         2
     *          / \                       / \
     *         3   2                     1   3
     *        /                           \   \
     *       5                             4   7
     * 输出:
     * 合并后的树:
     * 	     3
     * 	    / \
     * 	   4   5
     * 	  / \   \
     * 	 5   4   7
     *
     * solution: traver two tree,node that both have  to add ,else add a new node in condition one have
     * queue breadTraver
     *@创建时间 2020/12/17
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        return breadFirst(t1,t2);
    }

    private TreeNode breadFirst(TreeNode t1,TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        t1.val += t2.val;
        t1.left = breadFirst(t1.left,t2.left);
        t1.right = breadFirst(t1.right,t2.right);
        return t1;
    }

    /***
     *@描述
     *@Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
     *
     * solution:
     * 1.breadFirst then list all the case,convert to find the target sum in the arrayList;
     * 2.bst  preOrder the List is sorted    a traver b findTarget
     *@创建时间 2020/12/17
     */
    public boolean findTarget(TreeNode root, int k) {
        preOrderInfo(root,k);
        return flag;
    }

    private void preOrderInfo(TreeNode root, int k) {
        if (root != null) {
            preOrderInfo(root.left,k);
            if (set.contains(k - root.val)) {
                flag = true;
            } else {
                set.add(root.val);
            }
            answer.add(root.val);
            preOrderInfo(root.right,k);
        }
    }

    /***
     *@描述
     *@Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
     *
     * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
     *
     *  
     *
     * Example 1:
     *
     *
     * Input: root = [1,0,2], low = 1, high = 2
     * Output: [1,null,2]
     *
     * solution: 1.traver the tree ,  left  node.val < low    a  no right tree,directly drop   b have right tree root = root.right
     * right node.val > high a no left tree ,directly drop it else root = root.left   preOrder
     *@创建时间 2020/12/17
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root,low,high);
    }

    private TreeNode dfs(TreeNode root,int low,int high) {
       if (root == null) {
           return null;
       }
       if (root.val < low) {

           return dfs(root.right,low,high);
       }
       if (root.val > high) {
           return dfs(root.left,low,high);
       }
       root.left = dfs(root.left,low,high);
       root.right = dfs(root.right,low,high);
       return root;
    }

    /***
     *@描述
     *Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
     *
     * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
     *
     * If no such second minimum value exists, output -1 instead.
     *
     * solution:  1.use set , if set.size > 1  return second minimum
     *@创建时间 2020/12/17
     */
    public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        nextOrder(root);
        return (secMin == Integer.MAX_VALUE && !flag) ? -1 : secMin;
    }

    private void nextOrder(TreeNode root) {
        if (root != null) {
            nextOrder(root.left);
            if (root.val > min) {
                secMin = Math.min(root.val,secMin);
                flag = true;
            }
            nextOrder(root.right);
        }
    }

    /***
     *@描述
     *Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
     *
     * Example :
     *
     * Input: root = [4,2,6,1,3,null,null]
     * Output: 1
     * Explanation:
     * Note that root is a TreeNode object, not an array.
     *
     * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
     *
     *           4
     *         /   \
     *       2      6
     *      / \
     *     1   3
     *
     * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
     *
     * solution: 1.暴力穷举法，取绝对值最小的   2.  题看错了 是任意两个节点   pre cur
     *@创建时间 2020/12/17
     */
    public int minDiffInBST(TreeNode root) {
        inOrderInfo(root);
        return min;
    }

    private void inOrderInfo(TreeNode root) {
        if (root != null) {
            inOrderInfo(root.left);
            cur = root.val;
            if (pre != 0) {
                min = Math.min(min,cur - pre);
            }
            pre = cur;
            inOrderInfo(root.right);
        }
    }

    /***
     *@描述
     *@给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
     * 输出：32
     *
     * solution: preOrder  to add low and high  O(N) O(N)
     *@创建时间 2020/12/18
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        addPreOrder(root,low,high);
        return count;
    }

    private void addPreOrder(TreeNode root, int low, int high) {
        if (root != null) {
            addPreOrder(root.left,low,high);
            if (root.val >= low && root.val <= high) {
                count += root.val;
            }
            addPreOrder(root.right,low,high);
        }
    }

    /***
     *@描述
     *@ 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
     *
     * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
     *
     *solution: int start = root.val  traver the tree ,if not eq  return false ,default true
     *@创建时间 2020/12/18  O(N) O(1)
     */
    public boolean isUnivalTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int value = root.val;
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                if (pop.val != value) {
                    return false;
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
            }
        }
        return true;
    }


    /***
     *@描述
     * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
     * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
     *
     * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     *
     * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
     *输入：root = [1,0,1,0,1,0,1]
     * 输出：22
     * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     *
     * solution: root left right     cur = cur * 2 + root.val  start cur = 0
     *@创建时间 2020/12/18
     */
    public int sumRootToLeaf(TreeNode root) {
        sumBinary(root,0);
        return sum;
    }

    public void sumBinary(TreeNode root,int cur) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                sum += cur * 2 + root.val;
                return;
            }
            cur = cur * 2 + root.val;
            sumBinary(root.left,cur );
            sumBinary(root.right,cur);
        }
    }

    /***
     *@描述
     *@ 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     * solution: traver the tree ,change the left and right side
     * O(N) O(N)
     *@创建时间 2020/12/21
     */
    public TreeNode mirrorTree(TreeNode root) {
        changeOrder(root);
        return root;
    }

    private void changeOrder(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            changeOrder(root.left);
            changeOrder(root.right);
        }
    }

    /***
     *@描述
     *@ 给定一棵二叉搜索树，请找出其中第k大的节点。
     *
     * solution: preOrder use the list ,return the k node val
     *@创建时间 2020/12/21   1 2 3 4 5 6
     */
   /* public int kthLargest(TreeNode root, int k) {
        kPreOrder(root);
        return answer.get(answer.size() - k + 1);
    }

    private void kPreOrder(TreeNode root) {
        if (root != null) {
            kPreOrder(root.left);
            answer.add(root.val);
            kPreOrder(root.right);
        }
    }*/
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        kthDfs(root);
        return res;
    }

    private void kthDfs(TreeNode root) {
        if (root != null) {
            kthDfs(root.right);
            if (k == 0) {
                return;
            }
            if (--k == 0) {
                res = root.val;
            }
            kthDfs(root.left);
        }
    }

    /***
     *@描述
     *@ 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * 例如：
     *
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3
     *
     * solution: bread traver the tree,record the depth  then return the res  use queue
     *@创建时间 2020/12/21
     */
   /* public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
            res++;
        }
        return res;
    }*/

   /***
    *@描述
    *@ 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
    *
    *solution: use recusion to implement the question,   Math.abs(maxDepth(root.left),maxDepth(root.right))  > 1 ? true : false;
    *@创建时间 2020/12/21
    */
    public boolean isBalanced(TreeNode root) {
       catFlush(root);
       return flag;
    }

    private void catFlush(TreeNode root) {
        if (root != null) {
            if ( Math.abs(maxDepthTwo(root.left) - maxDepthTwo(root.right)) > 1) {
                flag = false;
                return;
            }
            catFlush(root.left);
            catFlush(root.right);
        }
    }

    private int maxDepthTwo(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepthTwo(root.left),maxDepthTwo(root.right)) + 1;
    }

    /***
     *@描述
     *@ 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * solution:  inOrder   and  outOrder  is the same
     *@创建时间 2020/12/21
     */
    //官方解答
    public boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }

    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) return true;
        if (L == null || R == null || L.val != R.val) return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }


    /**
     * @Description
     * @ 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * solution:  有点难 中等难度 用答友的答案，root== p  和 root==q作为条件判断 放在最前面，如果包含一个 则返回root
     * 最后判断： lNode  rNode   a both are not null ,return root  b. lNode null return rNode c.rNode null return lNode
     * use recursion and linkedList
     **/
    public TreeNode lowestCommonAncestorNew(TreeNode root, TreeNode p, TreeNode q) {
      if (root == p || root == q) {
          return root;
      }

      if (root != null) {
          TreeNode lNode = lowestCommonAncestorNew(root.left,p,q);
          TreeNode rNode = lowestCommonAncestorNew(root.right,p,q);
          if (lNode != null && rNode != null) {
              return root;
          } else if (lNode == null) {
              return rNode;
          } else {
              return lNode;
          }
      }
      return null;
    }

    /**
     * @Description
     * @ 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     *
     * 示例:
     * 给定有序数组: [-10,-3,0,5,9],
     *
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     *           0
     *          / \
     *        -3   9
     *        /   /
     *      -10  5
     *
     * solution: every time get the mid element as the root node,  inOrder create the tree     increase
     * 二分法   mid = length / 2;   length = mid     递归
     * 官方思路： 二分法递归创建 左右子树，一次创建一个节点 low high
     * if (low > high)说明空数组无法创建子树 return null ，否则return  new TreeNode(nums[mid])
     * 更新 mid = (high - low ) / 2  + low
     * 左子树  low --- mid -1 右子树  mid + 1 ----high
     * @return Leetcode.BinaryTree.Tree.TreeNode
     **/
    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums,0,nums.length - 1);
    }

    private TreeNode createBST(int[] nums,int low, int high) {
       if (low > high) {
           //over the scope of the array
           return null;
       }
       // mid is the key point , define in the class is not right   must define mid here
       int mid = (high - low) / 2 + low;
       TreeNode root = new TreeNode(nums[mid]);
       root.left = createBST(nums,low,mid - 1);
       root.right = createBST(nums,mid + 1, high);
       return root;
    }

    /**
     * @Description
     * @ 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     *
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     *
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     *
     * 最后返回经过上色渲染后的图像。
     *
     * solution: 这题真读不懂，读懂了 解法肉眼看起来挺简单的  实现起来可能有一定难度
     * image 二维数组其实就是一个矩阵，(sr,sc) 就是矩阵中的坐标，最好本子上画一个图   若(sr,sc)对应的color和newColor一样，就不做变动 否则替换掉
     * 并且把相邻的符合条件的color也变成newColor，一只延伸，直到没有符合的条件返回    image   符合条件 ，指在棋盘类 且color ！= newColor
     * 特殊情况  image[sr][sc] == newColor  不用遍历 直接return image
     **/
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr,sc});
        int x = 0, y = 0, m = 0, n = 0;
        int length = image.length;
        int length1 = image[0].length;
        int color = image[sr][sc];
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            x = poll[0];
            y = poll[1];
            for (int i = 0; i < 4; i++) {
                m = x + dx[i];
                n = y + dy[i];
                if (m >= 0 && m < length && n >= 0 && n < length1 && image[m][n] == color) {
                    queue.offer(new int[]{m,n});
                    image[m][n] = newColor;
                }
            }
        }
        return image;
    }

   @Test
   public void testIsSameTree() {
      TreeNode root3 = new TreeNode(3);

      TreeNode root2 = new TreeNode(2);
      root2.left = new TreeNode(7);
      root2.right = new TreeNode(4);

      TreeNode root1 = new TreeNode(1);
      root1.left = new TreeNode(0);
      root1.right = new TreeNode(8);

      TreeNode root5 = new TreeNode(5);
      root5.left = new TreeNode(6);
      root5.right = root2;

      root3.left = root5;
      root3.right = root1;

      int[] arr = new int[]{-10,-3,0,5,9};

       Assert.assertEquals(root3,instance.sortedArrayToBST(arr));
   }
}
