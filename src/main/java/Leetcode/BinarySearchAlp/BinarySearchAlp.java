package Leetcode.BinarySearchAlp;

import org.junit.Test;

/**
 * @Author maoXin
 * @Description
 * @Date 9:53 2021/6/16
 */
public class BinarySearchAlp {

    private static final BinarySearchAlp instance = new BinarySearchAlp();

    /**
     * @Description:
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     *
     * 示例 1：
     *
     * 输入：[3,4,5,1,2]
     * 输出：1
     * 示例 2：
     *
     * 输入：[2,2,2,0,1]
     * 输出：0
     *
     * solution:
     * 题有点没理解到,旋转数组指 1 2 还是 1 2 3 4 5 还是 3 4 5 1 2
     * 首尾 i = 0; j = numbers.length - 1 ; m = (i + j) / 2;
     * numbers[m] > numbers[j] i = m + 1;
     * numbers[m] < numbers[j] j = m;
     * numbers[m] == numbers[j] j--;
     * i == j return numbers[j]  O Log(N)
     * 1.找右边排序数组的第一个元素 返回
     */
    public int minArray(int[] numbers) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j]) {
                i = m + 1;
            } else if (numbers[m] < numbers[j]) {
                j = m;
            } else {
                j--;
            }
        }
        return numbers[j];
    }

    /**
     * @Description:
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     *
     * 示例 1:
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例 2:
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     *  
     *
     * 提示：
     *
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     *
     * solution: 二分法
     * 初始化 i = 0; j = nums.length - 1; m = (i + j) / 2;
     * 循环条件 while(i <= j) 不取等于 取不到边界元素
     * nums[m] < target i = m + 1;
     * nums[m] > target j = m - 1;
     * nums[m] == target return m;
     * 顺利跳出循环 return -1; O(log(N))
     * @Date: 2021/6/16 11:30
     */
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] > target) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * @Description:
     * 符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在 i（0 < i < arr.length - 1）使得：
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * 给你由整数组成的山脉数组 arr ，返回任何满足
     * arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：arr = [0,1,0]
     * 输出：1
     * 示例 2：
     *
     * 输入：arr = [0,2,1,0]
     * 输出：1
     * 示例 3：
     *
     * 输入：arr = [0,10,5,2]
     * 输出：1
     * 示例 4：
     *
     * 输入：arr = [3,4,5,1]
     * 输出：2
     * 示例 5：
     *
     * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
     * 输出：2
     *  
     *
     * 提示：
     *
     * 3 <= arr.length <= 104
     * 0 <= arr[i] <= 106
     * 题目数据保证 arr 是一个山脉数组
     *
     * solution: 二分法找里面最大的数
     * i = 0; j = arr.length - 1; m = (i + j) /2;
     * 循环条件  while i < j
     * arr[m] < arr[j]  i = m + 1;
     * arr[m] > arr[j] j = m;
     * arr[m] == arr[j] j--; return arr[j]
     * @Date: 2021/6/16 11:42
     */
    public int peakIndexInMountainArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (arr[m] > arr[m + 1] && arr[m] > arr[m - 1]) {
                return m;
            } else if (arr[m] < arr[m + 1]) {
                i = m + 1;
            } else if (arr[m] > arr[m + 1]) {
                j = m;
            }
        }
        return -1;
    }

    /**
     * @Description:
     * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
     *
     * 示例1:
     *
     *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
     *  输出：-1
     *  说明: 不存在返回-1。
     * 示例2:
     *
     *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
     *  输出：4
     * 提示:
     *
     * words的长度在[1, 1000000]之间
     *
     * solution:
     * 和排序数组找 对应的字符串是一样的 特殊字符串处理 ""
     * @Date: 2021/6/17 10:56
     */
    public int findString(String[] words, String s) {
        int i = 0;
        int j = words.length - 1;
        while (i <= j) {
            while (words[i].equals("")) i++;
            while (words[j].equals("")) j--;
            int m = (i + j) / 2;
            while (m >= 1 && words[m].equals("")) m--;
            if (words[m].compareTo(s) > 0) {
                j = m - 1;
            } else if (words[m].compareTo(s) < 0) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * @Description:
     * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，
     * 请你寻找在这一有序列表里比目标字母大的最小字母。
     *
     * 在比较时，字母是依序循环出现的。举个例子：
     *
     * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
     *  
     *
     * 示例：
     *
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "a"
     * 输出: "c"
     *
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "c"
     * 输出: "f"
     *
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "d"
     * 输出: "f"
     *
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "g"
     * 输出: "j"
     *
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "j"
     * 输出: "c"
     *
     * 输入:
     * letters = ["c", "f", "j"]
     * target = "k"
     * 输出: "c"
     *  
     *
     * 提示：
     *
     * letters长度范围在[2, 10000]区间内。
     * letters 仅由小写字母组成，最少包含两个不同的字母。
     * 目标字母target 是一个小写字母。
     *
     * solution:
     * i = 0, j = letters.length - 1; m = (i + j) / 2;
     * letters[m] < target i = m + 1;
     * letters[m] > target j = m;
     * == 后一个有取后一个 没有的话取第一个
     * @Date: 2021/6/17 11:16
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int i = 0;
        int j = letters.length - 1;
        if (target >= letters[j]) return letters[0];
        while (i < j) {
            int m = (i + j) / 2;
            if (letters[m] < target) {
                i = m + 1;
            } else if (letters[m] > target) {
                j = m;
            } else {
                if (m == letters.length - 1) {
                    return letters[0];
                } else {
                    int k = m;
                    while (k < letters.length) {
                        if (letters[k] != letters[m]) {
                            return letters[k];
                        }
                        k++;
                    }
                }
            }
        }
        return letters[j];
    }

    @Test
    public void testAlp() {
        char[] arr = new char[] {'e', 'e', 'n','n'};
        System.out.println(instance.nextGreatestLetter(arr, 'e'));
    }

}
