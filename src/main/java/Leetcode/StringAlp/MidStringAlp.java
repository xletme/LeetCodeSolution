package Leetcode.StringAlp;

import org.junit.Test;

/**
 * @Author maoXin
 * @Description
 * @Date 10:58 2021/7/12
 */
public class MidStringAlp {

    private static final MidStringAlp instance = new MidStringAlp();
    
    /**
     * @Description:
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 示例 4:
     *
     * 输入: s = ""
     * 输出: 0
     *  
     *
     * 提示：
     *
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     *
     * solution:
     * 遍历字符串s  记录最长的长度 直到最后 当前首 currentFirst 当前尾 currentEnd
     * 如果tmpStr包含当前字符串 ， 找到tmpStr当前字符的位置 firstPosition
     * 截取 subString(firstPosition,currentEnd + 1)
     * 不包含，tmpStr取 subString(currentFirst, currentEnd + 1) 同时更新 maxLength
     * @Date: 2021/7/12 11:03
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int currentFirst = 0;
        int currentEnd = 1;
        String tmpStr = "";
        if (s.length() > 0) {
           tmpStr = s.substring(currentFirst, currentEnd);
            maxLength = tmpStr.length();
        }
        for (int i = 1; i < s.length(); i++) {
            String s1 = String.valueOf(s.charAt(i));
            if (tmpStr.contains(s1)) {
                int firstPosition = tmpStr.indexOf(s1);
                tmpStr = s.substring(currentFirst + firstPosition + 1, currentEnd + 1);
                currentFirst = currentFirst + firstPosition + 1;
            } else {
                tmpStr = s.substring(currentFirst, currentEnd + 1);
            }
            currentEnd += 1;
            maxLength = Math.max(maxLength, tmpStr.length());
        }
        return maxLength;
    }

    /**
     * @Description:
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     * 示例 3：
     *
     * 输入：s = "a"
     * 输出："a"
     * 示例 4：
     *
     * 输入：s = "ac"
     * 输出："a"
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     *
     * solution:
     * 1.穷举法，把所有可能组成的字符串都列举出来 选出最长的 ，可以从最长的开始列举，剪枝 优化判断回文方法
     * 2.动态规划
     * 3.中心扩散
     * @Date: 2021/8/6 10:18
     */
    public String longestPalindrome(String s) {
        if (s.length() < 2){
            return s;
        }
        int len = s.length();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            //左边去除 j个，右边去除 i - j个
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(j, len - 1 - (i - j), charArray)) {
                    return s.substring(j, s.length() - (i - j));
                }
            }
        }
        return null;
    }

    public boolean isPalindrome(int i, int j, char[] charArray) {
        if (j == i) {
            return true;
        }
        while (i < j) {
            if (charArray[i] == charArray[j]) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testMidStringAlp() {
        System.out.println(instance.longestPalindrome(
                "bb"));
        System.out.println("babad".substring(0,4));
    }
}
