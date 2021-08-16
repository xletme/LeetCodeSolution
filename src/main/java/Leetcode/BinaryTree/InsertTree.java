package Leetcode.BinaryTree;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/3 14:14
 **/
public class InsertTree {

    //1.空 root.val = val 2.大于root.val 插右边 小于root.val 插左边
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) {
            root.right = insertIntoBST(root.right,val);
        } else {
            root.left = insertIntoBST(root.left,val);
        }
        return root;
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

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.right = new TreeNode(7);
        TreeNode treeNode1 = new TreeNode(2);
        treeNode1.left = new TreeNode(1);
        treeNode1.right = new TreeNode(3);
        treeNode.left = treeNode1;

        InsertTree insertTree = new InsertTree();
        TreeNode treeNode2 = insertTree.insertIntoBST(treeNode, 5);
        System.out.println(2333);
    }
}
