package Leetcode.StringAlp;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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
     * O(N) O(1)
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
     * 1.穷举法，把所有可能组成的字符串都列举出来 选出最长的 ，
     * 可以从最长的开始列举，剪枝 优化判断回文方法 O（N3） O（1）
     * 2.动态规划 动态方程 p[i,j] = p[i+1, j-1] ^ (si == sj);
     * 给定长度子串 一直从两头往中间筛选 O(N2) O(N2)
     * 3.中心扩散 取奇数或者偶数，一直扩展，找最长的回文子串 ，循环更新 O（N2）O（1）
     * @Date: 2021/8/6 10:18
     */
    /*public String longestPalindrome(String s) {
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
    }*/

    /**
     * 2.动态规划 动态方程 p[i,j] = p[i+1, j-1] ^ (si == sj);
     * O(n2) O(n2)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        //1。特殊判断 长度小于2 2.初始化动态方程
        // 3.长度 和 开头两层循环 具体业务逻辑实现
        //3.1 获取j，即右边界 判断是否越界 3.2 判断两头是否相等 3.3 相等看长度是否转移
        //4.构成回文串 和 最长的比，看是否更新长度和起始位置

        int len = s.length();

        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }


        char[] array = s.toCharArray();

        for (int l = 2; l <= len; l++) {

            for (int i = 0; i < len; i++) {

                int j = l + i - 1;

                if (j >= len) {//3.1
                    break;
                }

                if (array[i] != array[j]) {//3.2
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {//3.3
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
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
        List<String> urlList = new ArrayList<>();
        String url1 = "001_yyzz.jpg";
        String url2 = "001_f_sfz.jpg";
        String url3 = "002_f_sfz.jpg";
        String url4 = "001_j_sfz.jpg";
        String url5 = "002_j_sfz.jpg";
        urlList.add(url1);
        urlList.add(url2);
        urlList.add(url3);
        urlList.add(url4);
        urlList.add(url5);
        for (int i = 0; i < urlList.size(); i++) {
            System.out.println(urlList.get(i));
        }
    }
}
