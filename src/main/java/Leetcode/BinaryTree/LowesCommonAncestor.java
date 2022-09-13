package Leetcode.BinaryTree;

/**
 * 找二叉树两个节点 p 和 q的最近公共祖先
 * p q存在且唯一
 * 思路：
 * 1.左子树包含 && 右子树包含
 * 2.当前节点是 p或者q && （左子树包含 ｜｜ 右子树包含）
 * 最后返回当前节点
 * O(N) O(N)
 */
public class LowesCommonAncestor {

    private TreeNode ans;

    public LowesCommonAncestor(TreeNode ans) {
        this.ans = ans;
    }

    /**
     * root 是否包含p或者q
     * @param root
     * @param p
     * @param q
     * @return
     */
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean flson = dfs(root.left, p, q);
        boolean frson = dfs(root.right, p, q);
        if ((flson && frson) || ((root.val == p.val || root.val == q.val) && (flson || frson))) {
            ans = root;
        }
        return flson || frson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestorNew(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return this.ans;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
