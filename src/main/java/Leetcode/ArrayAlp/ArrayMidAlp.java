package Leetcode.ArrayAlp;

import org.junit.Test;

import java.util.*;

/**
 * @Author maoXin
 * @Description
 * @Date 13:45 2021/6/23
 */
public class ArrayMidAlp {

    private static final ArrayMidAlp instance = new ArrayMidAlp();

    /**
     * @Description:
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *  
     *
     * 提示：
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     *
     * solution:
     * 先二分法找到该元素，
     * 若没找到 返回 -1,-1
     * 找到了 在前后遍历找边界元素 O(Log(N))
     * @Date: 2021/6/23 13:46
     */
    /*public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int start = 0;
        int end = 0;
        int i = 0;
        int j = nums.length - 1;
        int index = 0;
        boolean flag = false;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                index = mid;
                flag = true;
                break;
            }
        }
        if (!flag) {
            res[0] = -1;
            res[1] = -1;
        } else {
            for (int k = index; k >= 0; k--) {
                if (nums[k] != target) {
                    start = k + 1;
                    break;
                }
            }
            for (int k = index; k < nums.length; k++) {
                if (nums[k] != target) {
                    end = k - 1;
                    break;
                }
                if (k == nums.length - 1) {
                    end = k;
                }
            }
            res[0] = start;
            res[1] = end;
        }
        return res;
    }*/

    //真正的二分  接上面的题
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1, -1};
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);
        return res;
    }

    public int binarySearch(int[] nums, int target, boolean isLeft) {
        int res = -1;
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                res = mid;
                if (isLeft) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器。
     *
     *  
     *
     * 示例 1：
     *
     *
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例 2：
     *
     * 输入：height = [1,1]
     * 输出：1
     * 示例 3：
     *
     * 输入：height = [4,3,2,1,4]
     * 输出：16
     * 示例 4：
     *
     * 输入：height = [1,2,1]
     * 输出：2
     *  
     *
     * 提示：
     *
     * n = height.length
     * 2 <= n <= 3 * 104
     * 0 <= height[i] <= 3 * 104
     *
     * solution:
     * 1.穷举法 area = Math.abs(j - i) * Math.min(ai, aj);
     * 2.maxArea = Math.max(0, area); update maxArea O(N2) 超时了
     * 3.双指针解法  计算公式一样，从左右边界移动，ai 和 aj 较小的往中间移动 ，
     * 因为距离相同 Math.min(ai,aj) 可以变大 结果就会变大 O(N)
     * @Date: 2021/6/29 13:54
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int area = Math.abs(j - i) * Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, area);
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    /**
     * @Description:
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     *
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：[]
     *  
     *
     * 提示：
     *
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     *
     * solution:
     * 1.穷举法 nums所有元素放入 map key：下标 value：nums[下标]  N2遍历  判断下标不相同,符合条件的小标放入List
     * 下次符合条件的从list中做去重校验
     * 2. 先判断是三个数相加是否为 0（为 0 升序 a:b:c 放入List中) 后面为0的检测是否是已经出现过，没出现就往res中添加 O(N3)
     * @Date: 2021/6/29 14:26
     */
    /*public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<String> noRepeatList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i != j && i != k && j != k) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            String tmpStr = convertWithAsc(nums[i] , nums[j] , nums[k]);
                            if (!noRepeatList.contains(tmpStr)) {
                                noRepeatList.add(tmpStr);
                                List<Integer> innerList = new ArrayList<>();
                                innerList.add(nums[i]);
                                innerList.add(nums[j]);
                                innerList.add(nums[k]);
                                res.add(innerList);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }*/
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public String convertWithAsc(int i, int j, int k) {
        int first = 0;
        int second = 0;
        int third = 0;
        if (i >= j && i >= k) {
            first = i;
            second = Math.max(j, k);
            third = Math.min(j, k);
        } else if (j >= i && j >= k) {
            first = j;
            second = Math.max(i, k);
            third = Math.min(i, k);
        } else {
            first = k;
            second = Math.max(i, j);
            third = Math.min(i, j);
        }
        return first + ":" + second + ":" + third;
    }

    /**
     * @Description:
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
     * 返回这三个数的和。假定每组输入只存在唯一答案。
     *
     *  
     *
     * 示例：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     *  
     *
     * 提示：
     *
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     *
     * solution:
     * 官方思路 ： 排序 + 双指针
     * 先排序 ，再从下标0开始 定位 a  [i+1,n)找 b c ,
     * b初始化 nums[i+1] c -->nums[n-1]
     * 像水容积题一样，往中间缩小范围  找出  b+c 和 target - a 最接近的元素
     * 时间复杂度  O(N2)  Olog(N)+双指针 O(N)
     * @Date: 2021/7/13 14:08
     */
    public int threeSumClosest(int[] nums, int target) {
        int abs = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            int a = i;
            int b = i + 1;
            int c = nums.length - 1;
            while (b < c && c > a && b > a) {
                if (Math.abs(target - (nums[a] + nums[b] + nums[c])) < abs) {
                    abs = Math.abs(target - (nums[a] + nums[b] + nums[c]));
                    res = nums[a] + nums[b] + nums[c];
                }
                if ((nums[a] + nums[b] + nums[c]) < target) {
                    b++;
                } else if ((nums[a] + nums[b] + nums[c]) > target) {
                    c--;
                } else {
                    return target;
                }
            }
        }
        return res;
    }

    /**
     * @Description:
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2：
     *
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *  
     *
     * 提示：
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     *
     * solution:
     * 官方解答  回溯递归 + 排序剪枝
     * @Date: 2021/7/13 15:41
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();

        dfs(candidates, 0, len, target, path, res);

        return res;
    }

    /**
     * @Description: dfs method
     * @Date: 2021/7/20 10:22
     */
    private void dfs(int[] candidates, int begin, int len,
                     int target, Deque<Integer> path, List<List<Integer>> res) {
        // when target < 0 ,not generate a new child node
        if (target < 0) {
            return;
        }

        // find it, return
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            //reduce a lot of calculator 剪枝的前提数组要排序
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);

            //each element can repeat, so the next begin also is i
            dfs(candidates, i, len, target - candidates[i], path, res);

            // recover the status
            path.removeLast();
        }
    }

    /**
     * @Description:
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中
     * 是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
     * 找出所有满足条件且不重复的四元组。
     *
     * 注意：答案中不可以包含重复的四元组。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * 示例 2：
     *
     * 输入：nums = [], target = 0
     * 输出：[]
     *  
     *
     * 提示：
     *
     * 0 <= nums.length <= 200
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     *
     * solution:
     * @Date: 2021/7/20 11:14
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) {
            return res;
        }

        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                threeSum(nums, i, target - nums[i], res);
            }
        }
        return res;
    }

    public void threeSum(int[] nums, int i, int target, List<List<Integer>> res) {
        int n = nums.length;
        // 枚举 a
        for (int first = i + 1; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > i + 1 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int tmpTarget = target - nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > tmpTarget) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == tmpTarget) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    System.out.println(i + ":" + first + ":" + second + ":" + third);
                    if (!res.contains(list)) {
                        res.add(list);
                    }
                }
            }
        }
    }

    /**
     * @Description:
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     *
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 示例 3：
     *
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     * 示例 4：
     *
     * 输入：nums = [1]
     * 输出：[1]
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     *
     * solution: 1.从后往前遍历 nums数组  ,
     * 直到下标 nums[i] > nums[i - 1]
     * 从 i  到 nums.length 找出一个刚好比nums[i - 1]大的 ，剩下的数做升序排列 依次替换 返回
     * 这里找数  从 nums.length --- i 遍历
     * 2，一直没找到，说明当前数为最大
     * @Date: 2021/7/22 10:52
     */
    public void nextPermutation(int[] nums) {
        boolean isOver = false;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i > 0 && nums[i] > nums[i - 1]) {
                for (int j = nums.length - 1; j >= i; j--) {
                    if (nums[j] > nums[i - 1]) {
                        //做交换
                        int tmp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = tmp;
                        int index = 1;
                        //处理余下数组
                        for (int k = i; k < (nums.length - i) / 2 + i; k++) {
                            int tmpNum = nums[k];
                            nums[k] = nums[nums.length - index];
                            nums[nums.length - index] = tmpNum;
                            index++;
                        }
                        //做完跳出去
                        isOver = true;
                        break;
                    }
                }
                //继续跳
                if (isOver) {
                    break;
                }
            }
        }
        if (!isOver) {
            for (int i = 0; i < nums.length / 2; i++) {
                int tmp = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = tmp;
            }
        }
    }

    /**
     * @Description:
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * 示例 3：
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -10^4 <= target <= 10^4
     *
     * 你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
     *
     * solution:
     * 总体思路二分  0  nums.length - 1 可用  left right 替代
     * 1.nums[0] 左边最小  ，nums[nums.length - 1] 右边最大   nums[mid]最中间元素
     * 2.判断 是否 <= nums[nums.length -1] >=nums[0] 否则返回 -1
     * 3. nums[mid]分两种情况
     * a 比nums[0]大 升序 ，判断是否在nums[0]--nums[mid]区间  ,不在区间 left = mid++;
     * b 比nums[0]小 判断是否在 nums[mid]---nums[nums.length - 1],不在区间 right = mid--;
     * 继续轮询 直到找到区间为止   ，找到区间使用二分法求解
     *
     *
     * @Date: 2021/7/23 10:46
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left  = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == nums[mid]) return mid;
            if (nums[left] < nums[right]) {
                //升序
                return binarySearch(nums, target, left, right);
            }
            if (target > nums[right] || target < nums[left]) {
                if (nums[mid] > nums[left]) {
                    if (target > nums[left] && target < nums[mid]) {
                        //在左边 升序 进行二分查找
                        return binarySearch(nums, target, left, mid);
                    } else {
                        //继续查找
                        left = ++mid;
                    }
                } else if (nums[mid] < nums[left]) {
                    if (target > nums[mid] && target < nums[right]) {
                        //在右边 升序 进行二分查找
                        return binarySearch(nums, target, mid, right);
                    } else {
                        //继续查找
                        right = --mid;
                    }
                } else {
                    return -1;
                }
            } else {
                //找不到
                return -1;
            }
        }
        return -1;
    }

    public int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * @Description:
     * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     *
     * 注意：
     * 输入：board =
     * [["5","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * 输出：true
     *
     * 示例 2：
     *
     * 输入：board =
     * [["8","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * 输出：false
     * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
     *  
     *
     * 提示：
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] 是一位数字或者 '.'
     *
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     *
     * solution:
     * 1. 按照题意解答  横 不重复  纵不重复  3*3表格不重复 最后返回true or false
     * 2. 具体实现 可用 set 看set.add是否成功  HashSet去重
     * @Date: 2021/7/26 10:22
     */
    public boolean isValidSudoku(char[][] board) {
        //横 纵 3×3表格
        for (int i = 0; i < 9; i++) {
            Set<Character> xSet = new HashSet<>();
            Set<Character> ySet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!xSet.add(board[i][j])) {
                        return false;
                    }
                }
                if (board[j][i] != '.') {
                    if (!ySet.add(board[j][i])) {
                        return false;
                    }
                }
                if (i % 3 == 0 && j % 3 == 0) {
                    Set<Character> threeSet = new HashSet<>();
                    for (int k = 0; k < 3; k++) {
                        for (int t = 0; t < 3; t++) {
                            if (board[i + k][j + t] != '.') {
                                if (!threeSet.add(board[i + k][j + t])) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * @Description:
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 注意：解集不能包含重复的组合。 
     *
     *  
     *
     * 示例 1:
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     *  
     *
     * 提示:
     *
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     *
     * solution:
     * 1.回溯 + 剪枝 先排序
     * //去重 这巨关键
     *             if (i > begin && candidates[i] == candidates[i - 1]) {
     *                 continue;
     *             }
     * @Date: 2021/7/26 11:15
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;

        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();

        sum2Dfs(candidates, 0, len, target, path, res);

        return res;
    }

    public void sum2Dfs(int[] candidates, int begin, int len, int target,
                        Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) {
                break;
            }

            //去重 这巨关键
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            sum2Dfs(candidates, i + 1, len, target - candidates[i], path, res);
            path.removeLast();
        }

    }


    /**
     * @Description:
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 假设你总是可以到达数组的最后一个位置。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 示例 2:
     *
     * 输入: nums = [2,3,0,1,4]
     * 输出: 2
     *  
     *
     * 提示:
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 1000
     *
     * solution: 官方思路
     * 1.取最左边的能到达终点的元素作为起点
     * 2.把该元素作为终点，继续重复1操作 直到 position <= 0
     * 为啥去最左边能到达终点的呢，因为即使有更大的数，也可以先到达最左边，在到终点
     * 贪心算法，从后面开始
     * @Date: 2021/7/28 11:17
     */
    /*public int jump(int[] nums) {
        int res = 0;
        int position = nums.length - 1;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    res++;
                    break;
                }
            }
        }
        return res;
    }*/
    // 另一种思路，从前面遍历
    // 1.每次取 i + nums[i] 当前所在位置 + 可跳跃最大步数 = 可到达最远的位置
    // 2.在当前起点可到达步数内，更新最远的位置
    // 3. 当遍历到当前最远的位置时，更新 end为最远的位置 的小标 step++
    // 时间复杂度 0(N) 空间复杂度 O(1)
    public int jump(int[] nums) {
        int step = 0;
        int end = 0;
        int maxPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    /**
     * @Description:
     * 47 全排列
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * 示例 2：
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     *
     * solution:
     * 1.穷举法，列出所有排列 去重
     * 2.找规律 按照 不同的字符列举 出一共有多少种排列 用排列组合法来解决
     * 排序 + 回溯 + dfs + 剪枝 O(N2)
     * @Date: 2021/8/2 17:18
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        visit = new boolean[nums.length];
        Arrays.sort(nums);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(0, nums.length, nums, res, path);
        return res;
    }

    private static boolean[] visit;

    // path.size == len 跳出循环
    // 有重复的 只取 下标升序
    public void dfs(int start,int len, int[] nums, List<List<Integer>> res,
                    Deque<Integer> path) {

        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {

            //这里很关键   等下慢慢理解下
            if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) {
                continue;
            }

            path.add(nums[i]);
            visit[i] = true;
            dfs(start + 1, len, nums, res, path);
            visit[i] = false;
            path.removeLast();
        }
    }


    /**
     * @Description: 48. 旋转图像
     *
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     *  
     *
     *输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     *
     * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     *
     * 示例 3：
     *
     * 输入：matrix = [[1]]
     * 输出：[[1]]
     * 示例 4：
     *
     * 输入：matrix = [[1,2],[3,4]]
     * 输出：[[3,1],[4,2]]
     *
     * 提示：
     *
     * matrix.length == n
     * matrix[i].length == n
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     *
     * solution:
     * 1.对角线翻转
     * 2.y轴翻转
     * @Date: 2021/8/4 10:11
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //对角线翻转 matrix[i][j] <=> matrix[j][i];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        //y轴翻转 matrix[i][j] <=> matrix[i][n - j];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    /**
     * @Description:
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     *
     *
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *  
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     *
     * solution:
     * 1.搞个visit[][]数组 来标识是否被访问过
     * 2.定义4个方向  每个方向做不同的操作
     * 3.循环，直到所有元素都被访问 O(N) O(N)
     * @Date: 2021/8/9 10:05
     */
    private static String[] direct = new String[] {"right", "down", "left", "up"};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visit = new boolean[m][n];
        int count = 0;
        int x = 0;
        int y = 0;
        String condition = direct[0];
        while (count != m * n) {
            switch (condition) {
                case "right":
                    int i = y;
                    for ( ;i < n; i++) {
                        if (!visit[x][i]) {
                            res.add(matrix[x][i]);
                            visit[x][i] = true;
                            count++;
                        } else {
                            break;
                        }
                    }
                    y = --i;
                    x = x + 1;
                    condition = direct[1];
                    break;
                case "down":
                    i = x;
                    for (; i < m; i++) {
                        if (!visit[i][y]) {
                            res.add(matrix[i][y]);
                            visit[i][y] = true;
                            count++;
                        } else {
                            break;
                        }
                    }
                    y = y - 1;
                    x = --i;
                    condition = direct[2];
                    break;
                case "left":
                    i = y;
                    for (; i >= 0; i--) {
                        if (!visit[x][i]) {
                            res.add(matrix[x][i]);
                            visit[x][i] = true;
                            count++;
                        } else {
                            break;
                        }
                    }
                    y = ++i;
                    x = x - 1;
                    condition = direct[3];
                    break;
                case "up":
                    i = x;
                    for (; i >= 0; i--) {
                        if (!visit[i][y]) {
                            res.add(matrix[i][y]);
                            visit[i][y] = true;
                            count++;
                        } else {
                            break;
                        }
                    }
                    y = y + 1;
                    x = ++i;
                    condition = direct[0];
                    break;
                default:
                    throw new RuntimeException("传入参数错误, 请检查");
            }
            //元素已经收集完毕
            if (count == m * n) {
                break;
            }
        }
        return res;
    }

    /**
     * @Description:
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2：
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *  
     *
     * 提示：
     *
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     *
     * solution:
     * 1.数组按照第一个元素升序排列
     * 2.开始区间合并，从左往右合并  直到不能合并，把元素加入res
     * 3.最开始参与合并的是第一个元素，不能合并后，
     * a 加入左边的元素入 res
     * b 右边的元素作为新的合并元素
     * O (log(N)) O(N)
     * @Date: 2021/8/9 14:41
     */
    public int[][] merge(int[][] intervals) {
        int m = intervals.length;
        if (m <= 1) {
            return intervals;
        }
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, new ComparatorTwoArray());
        int[] initial = intervals[0];
        for (int i = 1; i < m; i++) {
            int start1 = initial[0];
            int end1 = initial[1];
            int start2 = intervals[i][0];
            int end2 = intervals[i][1];
            if (end1 < start2) {
                res.add(initial);
                initial = intervals[i];
            } else {
                initial[1] = Math.max(end1, end2);
            }
            if (i == m - 1) {
                res.add(initial);
            }
        }

        return res.toArray(new int[res.size()][2]);
    }

    /**
     * @Description:
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     *  
     *
     * 提示：
     *
     * 1 <= k <= nums.length <= 104
     * -104 <= nums[i] <= 104
     *
     * solution:
     * 1.排序 + 直接取值  O(logN)
     * 2.遍历一次，每次找最大的值  遍历N次  O(N*N) ==> O(N2)
     * 3.使用优先级队列  初始化k个元素，最后遍历返回
     * @Date: 2021/8/10 11:12
     */
    /*public int findKthLargest(int[] nums, int k) {
        Queue<Integer> priorityQueue = new PriorityQueue<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < nums.length - k - 1; i++) {
            if (priorityQueue.size() > 0) {
                res = priorityQueue.poll();
            }
        }
        return res;
    }*/
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k + 1];
    }

    @Test
    public void testArrayMidAlp() {
        int[][] arr = new int[][] {{1,4},{4,5}};
        System.out.println((1<<16) - 1);
        System.out.println(0x0000FFFF & 1);

    }

    /**
     * @Description:
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     *
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *  
     *
     * 提示：
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     *
     * solution:
     * 1.找到为1的坐标，向四周延伸，把元素为1 的转为2  （这里采用递归思路）true  count++
     * 2.最后遍历完整个grid二维数组 返回count  题友讨论区的思路
     * 这里为啥要转为2  ： 下次遍历左边的时候，如果是 2 ，直接返回  先把数值为1 的处理为2，方便后续遍历  相当于缩脚 缩到最后只剩一个1
     *  每个数遍历4次  O(4N)== O(N) 空间复杂度 主要是递归的消耗 O(N)
     * @Date: 2021/8/11 10:26
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void infect(char[][] grid, int i, int j) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[i].length - 1 || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }

    static class ComparatorTwoArray implements Comparator<int[]> {

        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] == b[0]) {
                return 0;
            }

            return a[0] > b[0] ? 1 : -1;
        }
    }

}
