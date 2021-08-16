package Leetcode.BinaryTree;


/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/3 13:52
 **/
public class SearchTree {

    //给定二叉搜索树（BST）的根节点和一个值 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL
    //规律：根节点的值大于左子树 小于右子树
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) return root;

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(2);
        treeNode1.left = new TreeNode(1);
        treeNode1.right = new TreeNode(3);
        treeNode.left = treeNode1;
        treeNode.right = new TreeNode(7);

        SearchTree searchTree = new SearchTree();
        TreeNode treeNode2 = searchTree.searchBST(treeNode, 2);
    }
}
