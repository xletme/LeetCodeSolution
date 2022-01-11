package Leetcode.Stack;

import java.util.*;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/17 16:15
 **/
public class Solution {

    private static final Solution instance = new Solution();

    /***
     *@描述
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     *  
     *
     * Example 1:
     *
     * Input: s = "()"
     * Output: true
     * Example 2:
     *
     * Input: s = "()[]{}"
     * Output: true
     * Example 3:
     *
     * Input: s = "(]"
     * Output: false
     *  思路 ：
     *  记录左括号 的数量      右括号开始  计数为0 返回false  默认返回true
     *  栈来实现
     *  O(N) O(N)
     *@创建时间 2020/11/17
     */
    public boolean isValid(String s) {
        if (s.isEmpty()){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if(stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }


    /***
     *@描述
     * Given a string S of lowercase letters,
     * a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
     *
     * We repeatedly make duplicate removals on S until we no longer can.
     *
     * Return the final string after all such duplicate removals have been made. 
     * It is guaranteed the answer is unique.
     *
     *  
     *
     * Example 1:
     *
     * Input: "abbaca"
     * Output: "ca"
     * Explanation:
     * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal,
     * and this is the only possible move. 
     * The result of this move is that the string is "aaca", of which only "aa" is possible,
     * so the final string is "ca".
     * solution :
     *   1.first  S to stack  define two stack
     *   2.loop  if(BB)  pop() continue;
     *   3.define  flag   if loop end  ,no BB flag true
     *   4.flag = true break;
     *
     *   another way  : while join the stack,judge the cur and stack top element
     *   O(N) O(N)
     *@创建时间 2020/11/18
     */
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            //判断栈顶元素关键,能做到把 abbaca 消除到 ca
            if (stack.isEmpty() || c != stack.peek()) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }


        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return  result.reverse().toString();
    }

    /***
     *@描述
     * You are given two arrays (without duplicates) nums1 and nums2
     * where nums1’s elements are subset of nums2.
     * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
     *
     * The Next Greater Number of a number x in nums1 is
     * the first greater number to its right in nums2.
     * If it does not exist,
     * output -1 for this number.
     *
     * Example 1:
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * Output: [-1,3,-1]
     * Explanation:
     *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
     *     For number 1 in the first array, the next greater number for it in the second array is 3.
     *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
     * solution:
     *  1.find the result in nums2 one by one and return
     *@创建时间 2020/11/18
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return new int[]{};
        }
        int[] res = new int[nums1.length];
        int index = 0;
        for (int i : nums1) {
            int flag = index;
            for (int j = 0; j < nums2.length; j++) {
                if (i == nums2[j]) {
                    if (j != nums2.length - 1) {
                        for (int k = j + 1; k < nums2.length; k++) {
                            //find the result
                            if (nums2[k] > i){
                                res[index++] = nums2[k];
                                break;
                            }
                        }
                        if(flag == index){
                            res[index++] = -1;
                            break;
                        }
                        break;
                    } else {
                        res[index++] = -1;
                        break;
                    }
                }
            }
        }
        return res;
    }

    /***
     *@描述
     * The Leetcode file system keeps a log each time some user performs a change folder operation.
     *
     * The operations are described below:
     *
     * "../" : Move to the parent folder of the current folder.
     * (If you are already in the main folder, remain in the same folder).
     * "./" : Remain in the same folder.
     * "x/" : Move to the child folder named x
     * (This folder is guaranteed to always exist).
     * You are given a list of strings logs where logs[i] is the operatio
     * n performed by the user at the ith step.
     *
     * The file system starts in the main folder,
     * then the operations in logs are performed.
     *
     * Return the minimum number of operations needed to go back to the main folder
     * after the change folder operations.
     *
     * solution  : record the level  one by one to the end,then return the level
     *  diff ./ ../  x/
     *  1.array
     *  2.stack
     *@创建时间 2020/11/19
     */
    public int minOperations(String[] logs) {
        int res = 0;
        /*for (String log : logs) {
            if (log.startsWith("./")) {

            } else if (log.startsWith("../")) {
               res = Math.max((res - 1), 0);
            } else {
                res++;
            }
        }*/
        Stack<String> stack = new Stack<>();
        for (int i = logs.length - 1; i >= 0; i--) {
            stack.push(logs[i]);
        }
        while (!stack.isEmpty()) {
            String log = stack.pop();
            if (log.startsWith("./")) {

            } else if (log.startsWith("../")) {
                res = Math.max((res - 1), 0);
            } else {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{4,1,2};
        int[] arr2 = new int[]{1,3,4,2};
        String[] logs = new String[]{"d1/","../","../","../"};
        System.out.println(instance.minOperations(logs));
    }
}
