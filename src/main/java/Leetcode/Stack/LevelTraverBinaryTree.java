package Leetcode.Stack;

import org.junit.Test;

import java.util.*;

/**
 * @Author maoXin
 * @Description
 * @Date 10:50 2021/6/22
 */
public class LevelTraverBinaryTree {

    private static final LevelTraverBinaryTree instance = new LevelTraverBinaryTree();

    /**
     * @Description:
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层序遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     *
     * solution:
     * queue 队列来实现层次遍历,new list 来add 每一层的元素 顺序还是逆序根据flag判断
     * O(N) O(N)
     * @Date: 2021/6/22 10:52
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = true;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0;  i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    if (flag) {
                        list.add(poll.val);
                    } else {
                        list.addFirst(poll.val);
                    }
                    if (poll.left != null)
                    queue.offer(poll.left);
                    if (poll.right != null)
                    queue.offer(poll.right);
                }
            }
            flag = !flag;
            res.add(list);
        }
        return res;
    }

    /**
     * @Description:
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
     * 至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * solution:
     * 1.数据解决 N2遍历 寻找下一个比自身大的元素 记录天数
     * O(N2) O(N)
     * @Date: 2021/6/22 14:34
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j <= temperatures.length; j++) {
                //special handle
                if (j == temperatures.length) {
                    res[i] = 0;
                    break;
                }
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     *
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 
     * 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。
     * 任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
     * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     *
     * 请注意，返回的 规范路径 必须遵循下述格式：
     *
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：path = "/home/"
     * 输出："/home"
     * 解释：注意，最后一个目录名后面没有斜杠。
     * 示例 2：
     *
     * 输入：path = "/../"
     * 输出："/"
     * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
     * 示例 3：
     *
     * 输入：path = "/home//foo/"
     * 输出："/home/foo"
     * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
     * 示例 4：
     *
     * 输入：path = "/a/./b/../../c/"
     * 输出："/c"
     *  
     *
     * 提示：
     *
     * 1 <= path.length <= 3000
     * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
     * path 是一个有效的 Unix 风格绝对路径。
     *
     * solution:
     * 1. 使用stack 遍历path 遇到 . 上一个/ 出栈   .. 出栈上一个 /name/  ...以上则不管
     * 2.last 检查 是否是 / 是的话 不入栈
     * 3.最后拼接String
     * 4.reverse  特殊情况处理不了 最后用的讨论区的答案
     * O(N) O(N)
     * @Date: 2021/6/23 10:01
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] items = path.split("/");
        for (String item : items) {
            if (item.isEmpty() || item.equals(".")) continue;
            if (item.equals("..")) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(item);
            }
        }
        return "/" + String.join("/", stack);
    }


    class TreeNode {
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

    @Test
    public void testLevelTraverBinaryTree() {
        TreeNode root = new TreeNode(3);
        TreeNode root1 = new TreeNode(9);
        TreeNode root2 = new TreeNode(20);
        TreeNode root3 = new TreeNode(15);
        TreeNode root4 = new TreeNode(7);

        root.left = root1;
        root.right = root2;

        root2.left = root3;
        root2.right = root4;

        int[] arr = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(instance.simplifyPath("/a/./b/../../c/"));
    }
}
