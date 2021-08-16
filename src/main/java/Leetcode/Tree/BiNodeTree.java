package Leetcode.Tree;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/9 15:34
 **/
public class BiNodeTree {

    TreeNode head = new TreeNode(-1);   // 为了返回单向链表的头节点而多设的一个节点
    TreeNode perv = null;               // 指向当前节点的前一个节点
    public TreeNode convertBiNode(TreeNode root) {
        helper(root);
        return head.right;
    }

    public void helper(TreeNode root) {
        if (root == null) { return;}
        helper(root.left);
        if (perv == null) {     // 第一个节点
            perv = root;        // 记录第一个节点
            head.right = root;  // 记录它，它将作为单链表的表头
        } else {                // 第一个节点之后的节点
            perv.right = root;  // 前一个节点的右指针指向当前节点
            perv = root;        // 更新perv指向
        }
        root.left = null;       // 当前节点的左指针设为null
        helper(root.right);
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = new TreeNode(3);
        treeNode3.left = new TreeNode(0);
        treeNode2.right = new TreeNode(6);

        BiNodeTree biNodeTree = new BiNodeTree();
        TreeNode treeNode4 = biNodeTree.convertBiNode(treeNode);
        System.out.println("success");

    }

}
