package Leetcode.Tree;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/11 11:50
 **/
public class AddNodeTree {

    int num=0;

    public TreeNode convertBST(TreeNode root) {
        if(root != null){
            convertBST(root.right);
            root.val = root.val+num;
            num=root.val;
            convertBST(root.left);
            return root;
        }
        return null;
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(13);

        AddNodeTree  nodeTree = new AddNodeTree();
        nodeTree.convertBST(treeNode);
        System.out.println(111);
    }


}
