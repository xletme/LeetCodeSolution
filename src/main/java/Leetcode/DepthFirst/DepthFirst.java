package Leetcode.DepthFirst;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/3 13:58
 **/
public class DepthFirst {

    private static final DepthFirst instance = new DepthFirst();

    private Map<Integer,Employee> map;

    private List<Integer> list = new ArrayList<>();

    private TreeNode cur;

    private boolean flag = false;

    private static final int[][] dirs= new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    /***
     *@描述
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
     * the two subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted array: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     *  思路：
     *  高度平衡的二叉搜索树
     * root.val > root.left.val  root.val < root.right.val
     * 红黑树
     * 升序排列的有序数组
     * 深度优先，保证 左右两边节点数量一致  使用二分法 分成两部分
     *
     * 奇数   ：  取中间的值做为根节点
     * 偶数 ： 取mid +1 的值做为根节点
     * 然后遍历 添加 left  root 节点的值，保证二叉搜索树的特点
     * 但是代码 我发觉写不出来
     * 官方解答：思路和我的差不多
     *使用 递归  + 二分法来实现
     * 奇数取mid  偶数 取  mid -1 为root   （left + right)/2 = mid   每次递归带上 nums数组  直到 left > right
     *  == 这种情况 会构造最左边 和 最右边的叶子节点
     *   int mid = (left+right)/2;   (left + right + rand.nextInt(2))
     *    if(left > right)  这两个是其中最秒的地方
     *
     *    二分法随机  O(N) O(N)
     *@创建时间 2020/11/3
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return constructTree(nums,0,nums.length-1);
    }

    public TreeNode constructTree(int[] nums,int left,int right){
        if(left > right){
            return null;
        }
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructTree(nums,left,mid-1);
        root.right = constructTree(nums,mid+1,right);
        return root;
    }

        static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    /***
     *@描述
     * You are given a data structure of employee information, which includes the employee's unique id,
     * their importance value and their direct subordinates' id.
     *
     * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
     * They have importance value 15, 10 and 5, respectively.
     * Then employee 1 has a data structure like [1, 15, [2]],
     * and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is
     * also a subordinate of employee 1, the relationship is not direct.
     *
     * Now given the employee information of a company, and an employee id, you need to return the total
     * importance value of this employee and all their subordinates.
     *
     * Example 1:
     *
     * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * Output: 11
     * 思路 ：
     *  遍历该员工 的直系下属   使用递归  sum 初始化 self importance  sum += 下属员工的重要值
     *  退出条件  employees == null
     *  递归函数传参数  Employee List<Integer>  int id
     *  把所有员工的 id 下属和他自身的所有员工的 保存到list
     *  O(N) O(N)
     *@创建时间 2020/11/3
     */
    public int getImportance(List<Employee> employees, int id) {
        return dfsImportance(employees,id,0);
    }

    private int dfsImportance(List<Employee> employees ,int id,int sum){
        Employee tmp;
        for (Employee employee : employees) {
            if(employee.id == id){
                tmp = employee;
                sum = tmp.importance;
                for (Integer subordinate : tmp.subordinates) {
                    sum += dfsImportance(employees,subordinate,sum);
                }
                break;
            }
        }

        return sum;
    }

    /**官方实现 把id 员工放入map，做查询
     * 优化我的时间复杂度 空间复杂度 不用去new
     * 空间复杂度也节省了 空间换时间
     * 数据结构 ： hashMap  时间复杂度 O(N) 空间复杂度也是 O(N)   */
    /*public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id,employee);
        }
        return dfs(id);
    }

    private int dfs(int id){
        Employee employee = map.get(id);
        int ans = employee.importance;
        for (Integer subordinate : employee.subordinates) {
            ans += dfs(subordinate);
        }
        return ans;
    }*/

    /***
     *@描述
     * Given a binary search tree, rearrange the tree in in-order so that the leftmost node
     * in the tree is now the root of the tree, and every node has no left child and only 1 right child.
     *Example 1:
     * Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]
     *
     *        5
     *       / \
     *     3    6
     *    / \    \
     *   2   4    8
     *  /        / \
     * 1        7   9
     *
     * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     *  1
     *   \
     *    2
     *     \
     *      3
     *       \
     *        4
     *         \
     *          5
     *           \
     *            6
     *             \
     *              7
     *               \
     *                8
     *                 \
     *                  9
     *
     * 思路 ：1种：中序遍历列出所有节点，重新组合树
     * ans.right  inOrderRec(TreeNode root) 这俩很关键
     * 2种：直接在之前的树上 做变动
     * 传每一个节点 ，做以下操作
     * O(2N)  O(N)
     *@创建时间 2020/11/4
     */
    /*public TreeNode increasingBST(TreeNode root) {
        inOrderRec(root);
        TreeNode ans = new TreeNode(0),cur = ans;
        for (Integer i : list) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        return ans.right;
    }

    private void inOrderRec(TreeNode root){
        if(root != null){
            inOrderRec(root.left);
            list.add(root.val);
            inOrderRec(root.right);
        }
    }*/

    /**  这些人是真的牛逼  对知识点掌握得太好了
     * 2种直接在  root上改,
     * 初始化一个 ans  = new TreeNode(0) ans  cur
     * 依次添加中序遍历的右孩子
     * 核心代码  root.left = null ; cur.right = root; cur = root; */
    public TreeNode increasingBST(TreeNode root){
        TreeNode ans = new TreeNode(0);
         cur = ans;
        inOrderRec(cur);
        return ans.right;//为啥用定义ans和cur ans用来保存整体的树  cur用来做中间拼接树
    }

    private void inOrderRec(TreeNode root){
        if (root != null) {
           inOrderRec(root.left);
           root.left = null;//这里不知道为啥这样子搞 总体思路和我想的一样 inOrder left and right ，
            // 顺便create tree
           cur.right = root;
           cur = root;
           inOrderRec(root.right);
        }
    }

    private void preOrderRec(TreeNode root){
        if(root != null) {
            System.out.println(root.val);
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    /***
     *@描述
     * Consider all the leaves of a binary tree, from left to right order,
     * the values of those leaves form a leaf value sequence.
     *
     * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
     *
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     *
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     *
     * Example 2:
     *
     * Input: root1 = [1], root2 = [1]
     * Output: true
     * Example 3:
     *
     * Input: root1 = [1], root2 = [2]
     * Output: false
     *
     * Example 4:
     *
     * Input: root1 = [1,2], root2 = [2,2]
     * Output: true
     *
     * 思路：
     * 1.列举所有的叶子节点，对比是否一样 包括值和顺序
     * 初始化两个list  ,先序遍历  用list存放  最后对比 list
     * O(N) O(N)
     *2.同时遍历两棵树，得到叶子节点就比较  不一样，直接pass 返回false
     *
     *@创建时间 2020/11/5
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> list1 = new LinkedList<>();
            List<Integer> list2 = new LinkedList<>();
            list1 = preOrderRec(root1,list1);
            list2 = preOrderRec(root2,list2);
            return  list1.equals(list2);
    }

    private List<Integer> preOrderRec(TreeNode root,List<Integer> list){
        if(root != null) {
            if(root.left == null && root.right == null) {
                list.add(root.val);
            }
            preOrderRec(root.left,list);
            preOrderRec(root.right, list);
        }
        return list;
    }

    /***
     *@描述
     * Given a binary tree and a sum,
     * determine if the tree has a root-to-leaf path such that
     * adding up all the values along the path equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     *思路：
     * 中序遍历，看是否有值等于sum  int tree
     *@创建时间 2020/11/5
     */
    /*public boolean hasPathSum(TreeNode root, int sum) {
        inOrderRec(root,0,sum);
        return flag;
    }*/

    private void inOrderRec(TreeNode root,int ans,int sum){
        if(root != null){
            ans += root.val;
            if(root.left == null && root.right == null && ans == sum){
                flag = true;
            }
            inOrderRec(root.left,ans,sum);
            inOrderRec(root.right,ans,sum);
        }
    }

    //官方解法  sum -val  问题拆分  把树拆分成小二叉树
    // 最后 判断  val equals  sum  每次  sum = sum -val  O(N) O(N)空间复杂度是递归所消耗的栈空间
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return sum == root.val;
        }
        return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
        //这个|| 就用的很有意思 ，其中有一个就返回true
    }




   /***
    *@描述
    * Given a matrix consists of 0 and 1,
    * find the distance of the nearest 0 for each cell.
    *
    *     The distance between two adjacent cells is 1.
    *
    *              
    *
    *     Example 1:
    *
    *     Input:
    *             [[0,0,0],
    *             [0,1,0],
    *             [0,0,0]]
    *
    *     Output:
    *             [[0,0,0],
    *              [0,1,0],
    *              [0,0,0]]
    *             思路：对1 双指针循环，直到碰到0位置  距离 就是 横纵坐标 相减绝对值  0不变
    *             一次移动一个位置，去查找0  这步要如何去做（key） 定义一个  flag 标志 东南西北四个方位，来确定移动位置
    *             2.填充 n轮，第i轮填充i + 1
    *               每一轮 遍历n  * 4  *n
    *              官方思路 ：0放入队列 从 0 开始遍历，寻找周围的 可以变动的 数字，把0从队列中移除,接着把 刚变动的数字放入队列
    *              建立 up down left right  获取上下左右4个位置，用 seen [][] 对应的二维数组 记录该元素是否访问过
    *              通过  x y  seen  来判断是否对元素做update  已queen  .isEmpty()为条件来作为终止条件
    *
    *              O(N)应该是接近4-5n O(N)res seen queue的初始化空间
    *@创建时间 2020/11/6
    */
    public int[][] updateMatrix(int[][] matrix) {
        int len1 = matrix.length;
        int len2 = matrix[0].length;
        int[][]  res = new int[len1][len2];
        boolean[][] seen = new boolean[len1][len2];
        Queue<int[]> queue = new LinkedList<>();

        //1.0 入队
        for (int i = 0; i < len1; i++) {
           for(int j = 0; j < len2; j++){
               if(matrix[i][j] == 0){
                   queue.offer(new int[]{i,j});
                   seen[i][j] = true;
               }
           }
        }

        //2.遍历 queue
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            for(int i = 0; i < 4 ;i++){
                int x = poll[0] + dirs[i][0];
                int y = poll[1] +  dirs[i][1];
                if(x >= 0 && x < len1 && y >= 0 && y < len2 && !seen[x][y] ){//判断条件是否满足 做update
                    queue.offer(new int[]{x,y});
                    seen[x][y] = true;
                    res[x][y] = res[poll[0]][poll[1]] + 1;
                    //这里是精髓  xy是新加入的1坐标
                    // 可以用来确定后面被1包围的1  第一步先把0上下左右的都给处理了
                    // 并且使用queue队列 加入后来的1数组
                    // !seen[x][y]这个东西可以避免重复的累加

                }
            }
        }
        return res;
    }

    /***
     *@描述
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     *  
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *   思路 : 广度优先搜索  使用队列  根节点存储队列 queue， 先 poll依次遍历队列中的值到 list中.  在offer加入根节点
     *   跳出条件 queue.isEmpty()   level 缺少
     *@创建时间 2020/11/9
     */
   /* public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for(int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    list.add(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            if(list.size() != 0) {
                res.add(list);
            }
        }

        return res;
    }*/

    public int[] levelOrder(TreeNode root) {
        int[] res = new int[1000];
        int index = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if (poll != null) {
                res[index++] = poll.val;
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
        }
        return  Arrays.copyOfRange(res,0,index);
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        right.left =    new TreeNode(15);
        right.right = new TreeNode(7);
        treeNode.right = right;
        Arrays.stream(instance.levelOrder(treeNode)).forEach(System.out::println);
    }
}
