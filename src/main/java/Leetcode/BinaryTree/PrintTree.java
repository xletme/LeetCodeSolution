package Leetcode.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/4 10:24
 **/
public class PrintTree {

    public static final PrintTree instance = new PrintTree();

    //深度优先 先添加路径，在判断是否为叶子节点 1.root为叶子节点直接添加 path到paths中 2.根节点直接添加val，继续遍历左右子树
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        constructPaths(root,"",paths);
        return paths;
    }

    //广度优先 使用队列，上往下依次遍历   1.叶子节点,放入队列 添加到paths中 2.加入队列 循环直至所有节点遍历完成 queue

    public List<String> breadFirstTree(TreeNode root){
        List<String> paths = new ArrayList<>();
        if(root == null){
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(String.valueOf(root.val));

        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if(node.left == null && node.right == null){
                paths.add(path);
            }else {
                if(node.left != null){
                    nodeQueue.offer(node.left);
                    pathQueue.offer(path + "->" + node.left.val);
                }

                if(node.right != null){
                    nodeQueue.offer(node.right);
                    pathQueue.offer(path + "->" + node.right.val);
                }
            }
        }

        return paths;
    }

    public void constructPaths(TreeNode root,String path,List<String> paths){
        if(root != null){
            StringBuffer str = new StringBuffer(path);
            str.append(root.val);
            if(root.left == null && root.right == null){
                //为叶子节点
                paths.add(str.toString());
            }else {
                str.append("->");
                constructPaths(root.left,str.toString(),paths);
                constructPaths(root.right,str.toString(),paths);
            }
        }
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.right =new TreeNode(7);
        TreeNode treeNode1 = new TreeNode(2);
        treeNode1.left = new TreeNode(1);
        treeNode1.right = new TreeNode(3);
        treeNode.left = treeNode1;
        PrintTree test = new PrintTree();
        test.breadFirstTree(treeNode).forEach(System.out::println);
    }
}
