package Leetcode.BinaryTree;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/3 14:41
 **/
public class DeleteTree {

    //删除节点  key > root.val 删除右子树  key < root.val 删除左子树
    //有点难
    // 删除分以下几种情况：
    // 1.叶子节点，直接删除 返回null
    // 2.左子树空右子树不空，右子树继承
    // 3.右子树空左子树不空，左子树继承
    // 4.左右子树都不空，右子树找一个最小值来继承  或者 左子树找一个最大值来继承
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {     // 递归到 root 为 null ，说明找不到要删的节点
            return root;
        }
        if (key < root.val) {           // 要删的节点在左子树，删完保持BST特性
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {    // 要删的节点在右子树，删完保持BST特性
            root.right = deleteNode(root.right, key);
        } else {                        // 找到了要删除的节点
            if (root.left == null && root.right == null) {  // 如果是叶子节点，删了返回null给其父节点即可
                return null;
            } else if (root.left != null && root.right == null) {   // 如果左不空而右空，父节点继承它的左子树即可
                return root.left;
            } else if (root.left == null && root.right != null) {   // 如果右不空而左空，父节点继承它的右子树即可
                return root.right;
            } else {                                    // 左右都不空，在右子树寻找一个节点继承左子树
                TreeNode last = null;                   // 在右子树继承root.left子树的节点
                TreeNode currentRoot = root.right;      // 进入右子树
                while (currentRoot != null) {
                    last = currentRoot;
                    currentRoot = currentRoot.left;     // 一直往左走，走到空
                }
                last.left = root.left;      // 继承
                return root.right;          // 父节点继承修改后得到新子树
            }
        }
        return root;
    }
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
