package Leetcode.Tree;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/10 11:12
 **/
public class TiltTree {

    int tilt=0;

    //思路：深度优先  遍历整个二叉树的节点  寻求坡度之和
    //List 存储 各节点的坡度，后续在sum 题没理解到当写代码时
    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }

    public int traverse(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt+=Math.abs(left-right);
        return left+right+root.val;
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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        TiltTree tiltTree = new TiltTree();
        System.out.println(tiltTree.findTilt(root));
    }
}
