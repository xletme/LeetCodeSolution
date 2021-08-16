package Leetcode.Tree;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/9 13:36
 **/
public class BalanceTree {

    //思路:题读错了  每个二叉树的节点都应该去比较一次
    public boolean isBalanced(TreeNode root) {
       if(root == null){
           return true;
       }else {
           return Math.abs(heightTree(root.left) - heightTree(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
       }
    }

    public int heightTree(TreeNode root){
        if(root == null){
            return 0;
        }else {
            return Math.max(heightTree(root.left),heightTree(root.right))+1;
        }
    }


    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
       /*TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode treeNode = new TreeNode(20);
        root.right = treeNode;
        treeNode.left = new TreeNode(15);
        treeNode.right = new TreeNode(7);*/

        TreeNode root = new TreeNode(1);
        TreeNode treeNode = new TreeNode(2);
        root.right = treeNode;
        treeNode.right = new TreeNode(3);

        BalanceTree balanceTree =new BalanceTree();
        System.out.println( balanceTree.isBalanced(root));
    }


}
