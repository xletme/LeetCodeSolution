package Leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/9 11:14
 **/
public class AverageOfLevelTree {

    //思路:定义一个Map来存放 每一个level的元素  <Integer,List<Integer>>  最后使用java8 求平均值
    public List<Double> averageOfLevels(TreeNode root) {
        List < Integer > count = new ArrayList< >();
        List < Double > res = new ArrayList < > ();
        average(root,0,res,count);
        for (int i = 0; i < res.size(); i++) {
            res.set(i,res.get(i) / count.get(i));
        }
        return res;
    }

    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if(t==null){
            return;
        }
        if(i<sum.size()){
            //同一层的其他节点
            sum.set(i,sum.get(i)+t.val);
            count.set(i,count.get(i)+1);
        }else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left,i+1,sum,count);
        average(t.right,i+1,sum,count);
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode treeNode = new TreeNode(20);
        root.right = treeNode;
        treeNode.left = new TreeNode(15);
        treeNode.right = new TreeNode(7);

        AverageOfLevelTree averageOfLevelTree = new AverageOfLevelTree();
        averageOfLevelTree.averageOfLevels(root).forEach(System.out::println);
    }
}
