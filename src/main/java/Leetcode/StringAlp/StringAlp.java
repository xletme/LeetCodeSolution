package Leetcode.StringAlp;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author maoXin
 * @Description
 * @Date 11:04 2021/5/11
 */
public class StringAlp {

    private static int[] numArr = new int[] {0,1,2,3,4,5,6,7,8,9};

    private static final StringAlp instance = new StringAlp();

    /**
     * @Description:
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。 
     *
     * 示例 1：
     *
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     *
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *  
     *
     * 提示：
     *
     * 0 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     *
     * solution:
     * 1.依次遍历strs数组，寻找
     * 2.依次求并集 两种请求退出遍历 a.并集为“” b.数组遍历完成  O(N)
     * @Date: 2021/5/11 11:05
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        String result = strs[0];
        for (int i = 0; i < strs.length - 1; i++) {
            result = commonPre(result, strs[i + 1]);
            if (result.equals("")) {
                return "";
            }
        }
        return result;
    }

    public String commonPre(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
            res.append(str1.charAt(i));
        }
        return res.toString();
    }


    /**
     * @Description:
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     *
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     *
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     *
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
     *
     * 例如，数字字符串 "3322251" 的描述如下图：
     *
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 1
     * 输出："1"
     * 解释：这是一个基本样例。
     * 示例 2：
     *
     * 输入：n = 4
     * 输出："1211"
     * 解释：
     * countAndSay(1) = "1"
     * countAndSay(2) = 读 "1" = 一 个 1 = "11"
     * countAndSay(3) = 读 "11" = 二 个 1 = "21"
     * countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
     *  
     *
     * 提示：
     *
     * 1 <= n <= 30
     *
     * solution:
     * @Date: 2021/5/11 11:25
     */
    public String countAndSay(int n) {
        return null;
    }

    /**
     * @Description:
     * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。
     * 如果不存在最后一个单词，请返回 0 。
     *
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
     *
     * 示例 1：
     *
     * 输入：s = "Hello World"
     * 输出：5
     * 示例 2：
     *
     * 输入：s = " "
     * 输出：0
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 104
     * s 仅有英文字母和空格 ' ' 组成
     *
     * solution: 1.split Array  2. judge array length() 3.input result
     * @Date: 2021/5/12 11:23
     */
    public int lengthOfLastWord(String s) {
        String[] s1 = s.split(" ");
        return s1.length == 0 ? 0 : s1[s1.length - 1].length();
    }


    /**
     * @Description:
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，
     * 该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * 示例 1：
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     *
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     *  
     *
     * 限制：
     *
     * 1 <= k < s.length <= 10000
     *
     * solution: 截取字符串 在进行拼接
     * @Date: 2021/5/12 11:35
     */
    public String reverseLeftWords(String s, int n) {
        String str1 = s.substring(0,n);
        String str2 = s.substring(n);
        return str2 + str1;
    }

    /**
     * @Description:
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，
     * 则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     *
     * 示例1:
     *
     *  输入："aabcccccaaa"
     *  输出："a2b1c5a3"
     * 示例2:
     *
     *  输入："abbccd"
     *  输出："abbccd"
     *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
     * 提示：
     *
     * 字符串长度在[0, 50000]范围内。
     *
     * solution: 1.执行压缩操作 2.如果变短 返回压缩后字符串 否则返回原字符串
     * @Date: 2021/5/12 11:40
     */
    public String compressString(String S) {
        if (S.length() == 0) return S;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(S.charAt(0));
        count++;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i - 1)) {
                count++;
            } else {
                builder.append(count);
                builder.append(S.charAt(i));
                count = 1;
            }
        }
        builder.append(count);
        if (S.length() > builder.toString().length()) {
            return builder.toString();
        } else {
            return S;
        }
    }

    /**
     * @Description:
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     *  
     *
     * 示例：
     *
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *  
     *
     * 提示：
     *
     * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * solution:
     * 1.split to Array
     * 2.each element do the reverse opt
     * 3.make up to the String  O(N) O(N)
     * @Date: 2021/5/13 10:35
     */
    /*public String reverseWords(String s) {
        StringBuilder resBuilder = new StringBuilder();
        String[] strArr = s.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            StringBuilder builder = new StringBuilder(strArr[i]);
            resBuilder.append(builder.reverse());
            if (i != strArr.length - 1) {
                resBuilder.append(" ");
            }
        }
        return resBuilder.toString();
    }*/

    /**
     * @Description:
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     *  
     *
     * 提示：
     *
     * num1 和num2 的长度都小于 5100
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
     *
     * solution:
     * 1.相同位相加  循环 Math.min(num1.length,num2.length)次 O(N)  O(1)
     * 2.找一个进位符
     * 3.最后reverse
     * @Date: 2021/5/13 10:45
     */
    public String addStrings(String num1, String num2) {
        int maxLen = Math.max(num1.length(),num2.length());
        int a = 0;
        int b = 0;
        StringBuilder resBuilder = new StringBuilder();
        boolean carryFlag = false;
        for (int i = 0; i < maxLen; i++) {
            if (i < num1.length()) {
                a = convertToIntNum(num1.charAt(num1.length() - i - 1));
            } else {
                a = 0;
            }
            if ( i < num2.length()) {
                b = convertToIntNum(num2.charAt(num2.length() - i - 1));
            } else {
                b = 0;
            }
            int sum = carryFlag ? a + b + 1 : a + b;
            if (sum >= 10) {
                carryFlag = true;
                resBuilder.append(sum % 10);
            } else {
                carryFlag = false;
                resBuilder.append(sum);
            }
        }
        if (carryFlag) {
            resBuilder.append("1");
        }
        return resBuilder.reverse().toString();
    }

    public int convertToIntNum(char c) {
        return numArr[c - 48];
    }

    /**
     * @Description:
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     *
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     *
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *  
     *
     * 说明：
     *
     * 无空格字符构成一个单词。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * solution:
     * @Date: 2021/5/13 11:11
     */
    public String reverseWords(String s) {
        //将传进来的字符串以空格拆分
        String[] strings = s.trim().split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        //从尾巴开始遍历
        for (int i = strings.length - 1; i >= 0; i--) {
            if (strings[i].equals("")) {
                continue;
            }
            //到头了，append然后去空格
            if (i == 0) {
                stringBuffer.append(strings[i].trim());
            } else {
                // 怕有多余的空格，去掉，再加上去
                stringBuffer.append(strings[i].trim()).append(" ");
            }
        }
        //输出String 完事，安排！
        return stringBuffer.toString();
    }

    /**
     * @Description:
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * 示例 1:
     *
     * 输入: "aba"
     * 输出: True
     * 示例 2:
     *
     * 输入: "abca"
     * 输出: True
     * 解释: 你可以删除c字符。
     * 注意:
     *
     * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
     *
     * solution:
     * 1.暴力模式 删除每一次，检测是否回文  O(N2) O(N)
     * 2.校验回文  toCharArray 首尾对着校验，
     * 有一个不合适 截取对应的字符串 用string api判断是否是回文
     * @Date: 2021/5/14 14:32
     */
    public boolean validPalindrome(String s) {
        if (s.length() <= 2) return true;
        if (s.equals(new StringBuilder(s).reverse().toString())) {
            return true;
        }
        int j = 0;
        int k = 0;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                if ((i + 1) * 2 == s.length()) {
                    return true;
                } else {
                    j = i;
                    k = s.length() - i;
                    break;
                }
            }
        }
        String str1 = s.substring(j,k - 1);
        String str2 = s.substring(j + 1, k);
        return str1.equals(new StringBuilder(str1).reverse().toString())
                || str2.equals(new StringBuilder(str2).reverse().toString());
    }

    /**
     * @Description:
     * 对于字符串 S 和 T，只有在 S = T + ... + T（T 自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
     *
     * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：str1 = "ABCABC", str2 = "ABC"
     * 输出："ABC"
     * 示例 2：
     *
     * 输入：str1 = "ABABAB", str2 = "ABAB"
     * 输出："AB"
     * 示例 3：
     *
     * 输入：str1 = "LEET", str2 = "CODE"
     * 输出：""
     *  
     *
     * 提示：
     *
     * 1 <= str1.length <= 1000
     * 1 <= str2.length <= 1000
     * str1[i] 和 str2[i] 为大写英文字母
     *
     * solution:
     * 1.官方解答  找出 str1 str2 长度的最大公约数 ，然后截取字符串看是否满足题意
     * 2.check 是否是重复的字符串样式  前面1 2都得经过推理才能得到
     * @Date: 2021/5/14 14:56
     */
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        for (int i = Math.min(len1,len2); i >= 1; i--) {
            if (len1 % i == 0 && len2 % i == 0) {
                String str = str1.substring(0, i);
                if (calculateRepeat(str,str1) && calculateRepeat(str,str2)) {
                    return str;
                }
            }
        }
        return "";
    }

    /**
     * @Description:
     * 只为能靠近你 哪怕一厘米 熬上你 是我落下的恶心爱情不惧岁月的更替 往后的朝夕 不论粉鱼是你就足以
     * you can be just the one  youwant be
     * doctor actor law
     * @Date: 2021/5/14 16:45
     */
    public boolean calculateRepeat(String s, String str) {
        int num = str.length() / s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < num; i++) {
            builder.append(s);
        }
        return builder.toString().equals(str);
    }

    /**
     * @Description:
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     *
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
     * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     *
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。
     * 此外，假设每次移动机器人的移动幅度相同。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
     * 示例 2:
     *
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。
     * 我们返回 false，因为它在移动结束时没有返回原点。
     *
     * solution:  上 == 下  左==右 就是原点 其他不是
     * @Date: 2021/5/17 10:46
     */
    public boolean judgeCircle(String moves) {
        int countR = 0;
        int countL = 0;
        int countU = 0;
        int countD = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 82) countR++;
            if (moves.charAt(i) == 76) countL++;
            if (moves.charAt(i) == 85) countU++;
            if (moves.charAt(i) == 68) countD++;
        }
        return countD == countU && countR == countL;
    }

    /**
     * @Description:
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
     * 给定的字符串只含有小写英文字母，并且长度不超过10000。
     *
     * 示例 1:
     *
     * 输入: "abab"
     *
     * 输出: True
     *
     * 解释: 可由子字符串 "ab" 重复两次构成。
     * 示例 2:
     *
     * 输入: "aba"
     *
     * 输出: False
     * 示例 3:
     *
     * 输入: "abcabcabcabc"
     *
     * 输出: True
     *
     * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     *
     * solution:
     * 1. 找个pre字符串
     * 2.用substring来判断接下来的字符串是否满足题意  不满足 更新pre字符串
     * 3.遍历整个s
     *
     * 二 、 找出s.length的因子，依次按照因子分段  检测是否是重复的子序列
     * 官方思路太流弊了  ，通过移动字符串 1 ———— s.length-1次 看字符串是否和s相同，有相同的则证明有重复的子序列
     * 简化  Str = s + s Str去除首尾字符串，其实就包含了 所有的移位情况  在判断是否包含s就OK了
     * O(N) O(1)
     * @Date: 2021/5/17 11:07
     */
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1,str.length() - 1).contains(s);
    }


    /**
     * @Description:
     * 给定一个单词，你需要判断单词的大写使用是否正确。
     *
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     *
     * 全部字母都是大写，比如"USA"。
     * 单词中所有字母都不是大写，比如"leetcode"。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     * 否则，我们定义这个单词没有正确使用大写字母。
     *
     * 示例 1:
     *
     * 输入: "USA"
     * 输出: True
     * 示例 2:
     *
     * 输入: "FlaG"
     * 输出: False
     * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
     *
     * solution:
     * 1.统计大写字母个数 count
     * 2. count = word.length || (count==1 && 首字母大写) || count == 0
     * O(N) O(1)
     * @Date: 2021/5/18 10:20
     */
    public boolean detectCapitalUse(String word) {
        int count = 0;
        char first = word.charAt(0);
        boolean firstUpFlag = (first >= 65 && first <= 90);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 65 && word.charAt(i) <= 90) {
                count++;
            }
        }
        return count == 0 || count == word.length() || (count == 1 && firstUpFlag);
    }


    /**
     * @Description:
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
     * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
     * 如果可以构成，返回 true ；否则返回 false。
     *
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。
     * 杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     *
     *  
     *
     * 示例 1：
     *
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * 示例 2：
     *
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     * 示例 3：
     *
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     *  
     *
     * 提示：
     *
     * 你可以假设两个字符串均只含有小写字母。
     *
     * solution:
     * 1.magazine 桶排序
     * 2.遍历ransomNote 进行删除 , <0 return false
     * O(N+M) O(N)
     * @Date: 2021/5/18 10:35
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[122 - magazine.charAt(i)]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int tmp = 122 - ransomNote.charAt(i);
            arr[tmp]--;
            if (arr[122 - ransomNote.charAt(i)] < 0) return false;
        }
        return true;
    }

    /**
     * @Description:
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，
     * 并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。 
     *
     * 示例 1：
     *
     * 输入: "Hello"
     * 输出: "hello"
     * 示例 2：
     *
     * 输入: "here"
     * 输出: "here"
     * 示例 3：
     *
     * 输入: "LOVELY"
     * 输出: "lovely"
     *
     * solution:
     * 1.str to CharArray
     * 2.转小写
     * 3.变回 String  O(N) O(N)
     * @Date: 2021/5/18 10:59
     */
    public String toLowerCase(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                charArray[i] += 32;
            }
        }
        return new String(charArray);
    }

    /**
     * @Description:
     * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
     *
     * 请注意，你可以假定字符串里不包括任何不可打印的字符。
     *
     * 示例:
     *
     * 输入: "Hello, my name is John"
     * 输出: 5
     * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
     *
     * solution:
     * @Date: 2021/5/18 11:12
     */
    public int countSegments(String s) {
        String[] s1 = s.split(" ");
        int count = 0;
        for (String str : s1) {
            if (!str.equals("")) count++;
        }
        return count;
    }

    /**
     * @Description:
     * 给定一个字符串 s，计算具有相同数量 0 和 1 的非空（连续）子字符串的数量，
     * 并且这些子字符串中的所有 0 和所有 1 都是连续的。
     *
     * 重复出现的子串要计算它们出现的次数。
     *
     * 示例 1 :
     *
     * 输入: "00110011"
     * 输出: 6
     * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     *
     * 请注意，一些重复出现的子串要计算它们出现的次数。
     *
     * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
     * 示例 2 :
     *
     * 输入: "10101"
     * 输出: 4
     * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
     *  
     *
     * 提示：
     *
     * s.length 在1到50,000之间。
     * s 只包含“0”或“1”字符。
     *
     * solution:  没思路,官方解答
     * 1.按0和1连续段分组
     * 2.找相邻两个数的最小值（最小值是能组成满足题意的所有数目） 一直累加  O(N)
     * @Date: 2021/5/18 11:18
     */
    public int countBinarySubstrings(String s) {
        int res = 0;
        List<Integer> list = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                list.add(count);
                count = 1;
            }
        }
        list.add(count);
        for (int i = 1; i < list.size(); i++) {
            res += Math.min(list.get(i),list.get(i - 1));
        }
        return res;
    }

    /**
     * @Description:
     * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *  
     *
     * 示例:
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacdfeg"
     *  
     *
     * 提示：
     *
     * 该字符串只包含小写英文字母。
     * 给定字符串的长度和 k 在 [1, 10000] 范围内。
     *
     * solution:
     * 1.根据题意翻转字符串，添加到list
     * 2.最后拼接list里面的字符串 toString (优化 一边翻转一边StringBuilder添加字符串）
     * O(N) O(N)
     * @Date: 2021/5/19 10:24
     */
    public String reverseStr(String s, int k) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i+= 2 * k) {
            if (i + k > s.length()) {
                //少于K个
                builder.append(new StringBuilder(s.substring(i)).reverse());
            } else if (i + 2 * k > s.length()) {
                //2K 与 K之间
                builder.append(new StringBuilder(s.substring(i,i + k)).reverse());
                builder.append(s.substring(i + k));
            } else {
              // 大于2K
                builder.append(new StringBuilder(s.substring(i,i + k)).reverse());
                builder.append(s, i + k, i + 2 * k);
            }
        }
        return builder.toString();
    }

    /**
     * @Description:
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     *
     *  
     *
     * 示例 1：
     *
     * 输入："ab-cd"
     * 输出："dc-ba"
     * 示例 2：
     *
     * 输入："a-bC-dEf-ghIj"
     * 输出："j-Ih-gfE-dCba"
     * 示例 3：
     *
     * 输入："Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     *  
     *
     * 提示：
     *
     * S.length <= 100
     * 33 <= S[i].ASCIIcode <= 122 
     * S 中不包含 \ or "
     *
     * solution:
     * 1.把字母全部弄出来排序翻转
     * 2.然后遍历原字符串 进行替换   O(N) O(N)
     * @Date: 2021/5/19 11:06
     */
    public String reverseOnlyLetters(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (isLetter(charArray[i])) {
                builder.append(s.charAt(i));
            }
        }
        builder.reverse();
        int index = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (isLetter(charArray[i])) {
                charArray[i] = builder.charAt(index++);
            }
        }
        return new String(charArray);
    }

    public boolean isLetter(char c) {
        if (c >= 65 && c <= 90) {
            return true;
        } else if (c >= 97 && c <= 122) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description:
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，
     * 并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     *  
     *
     * 示例 1：
     *
     * 输入："Mr John Smith    ", 13
     * 输出："Mr%20John%20Smith"
     * 示例 2：
     *
     * 输入："               ", 5
     * 输出："%20%20%20%20%20"
     *  
     *
     * 提示：
     *
     * 字符串长度在 [0, 500000] 范围内。
     *
     * solution: 1.S.replace(" ","%20");
     * @Date: 2021/5/19 13:41
     */
    public String replaceSpaces(String S, int length) {
        return S.substring(0,length).replace(" ","%20");
    }

    /**
     * @Description:
     * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 
     * 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
     *
     * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
     *
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。
     * 例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + ".-" + "-..." 字符串的结合)。
     * 我们将这样一个连接过程称作单词翻译。
     *
     * 返回我们可以获得所有词不同单词翻译的数量。
     *  g  --.    i ..     n  -.
     * 例如:
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * 输出: 2
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     *
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     *  
     *
     * 注意:
     *
     * 单词列表words 的长度不会超过 100。
     * 每个单词 words[i]的长度范围为 [1, 12]。
     * 每个单词 words[i]只包含小写字母。
     *
     * solution:
     * 1.暴力法  找齐所有翻译后的单词 ，依次比较看有几个不同 可用contains 实现 2.也可以用set来实现
     *
     * O(N*M) M[1,12] O(N)
     * @Date: 2021/5/20 9:47
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] strArr = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> res = new HashSet<>();
        for (String word : words) {
            res.add(encodeMorse(word,strArr));
        }
        return res.size();
    }

    public String encodeMorse(String str, String[] strArr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            builder.append(strArr[str.charAt(i) - 97]);
        }
        return builder.toString();
    }

    /**
     * @Description:
     * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
     *
     * 'A' : Absent，缺勤
     * 'L' : Late，迟到
     * 'P' : Present，到场
     * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
     *
     * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
     *
     * 示例 1:
     *
     * 输入: "PPALLP"
     * 输出: True
     * 示例 2:
     *
     * 输入: "PPALLL"
     * 输出: False
     *
     * solution:
     * 1.统计A的个数 大于1 直接false
     * 2.统计连续L的个数 大于2 直接false O(N) O(1)
     * @Date: 2021/5/20 10:25
     */
    public boolean checkRecord(String s) {
       int aCount = 0;
       int lCount = 0;
       for (int i = 0; i < s.length(); i++) {
           if (s.charAt(i) == 65) {
               aCount++;
           } else if (s.charAt(i) == 76) {
               if (i != 0) {
                   if (s.charAt(i - 1) == 76) {
                       lCount++;
                   } else {
                       lCount = 1;
                   }
               } else {
                   lCount = 1;
               }
           }
           if (aCount > 1) {
               return false;
           }
           if (lCount > 2) {
               return false;
           }
       }
       return true;
    }

    /**
     * @Description:
     * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中
     * paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。
     * 请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
     *
     * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
     * 输出："Sao Paulo"
     * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
     * 示例 2：
     *
     * 输入：paths = [["B","C"],["D","B"],["C","A"]]
     * 输出："A"
     * 解释：所有可能的线路是：
     * "D" -> "B" -> "C" -> "A". 
     * "B" -> "C" -> "A". 
     * "C" -> "A". 
     * "A". 
     * 显然，旅行终点站是 "A" 。
     * 示例 3：
     *
     * 输入：paths = [["A","Z"]]
     * 输出："Z"
     *  
     *
     * 提示：
     *
     * 1 <= paths.length <= 100
     * paths[i].length == 2
     * 1 <= cityAi.length, cityBi.length <= 10
     * cityAi != cityBi
     * 所有字符串均由大小写英文字母和空格字符组成。
     *
     * solution:
     * 1.起点站一个集合 终点站一个集合 ，终点站集合 - 起点站集合 剩下的那个就是 终点 稳不稳
     * 2.具体实现  2个list  O(N) O(N)
     * @Date: 2021/5/20 10:54
     */
    public String destCity(List<List<String>> paths) {
        List<String> startList = new ArrayList<>();
        List<String> endList = new ArrayList<>();
        for (List<String> path : paths) {
            startList.add(path.get(0));
            endList.add(path.get(1));
        }
        int size = startList.size();
        for (int i = 0; i < size; i++) {
            endList.remove(startList.get(i));
        }
        return endList.get(0);
    }

    /**
     * @Description:
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
     * （比如，waterbottle是erbottlewat旋转后的字符串）。
     *
     * 示例1:
     *
     *  输入：s1 = "waterbottle", s2 = "erbottlewat"
     *  输出：True
     * 示例2:
     *
     *  输入：s1 = "aa", s2 = "aba"
     *  输出：False
     * 提示：
     *
     * 字符串长度在[0, 100000]范围内。
     * 说明:
     *
     * 你能只调用一次检查子串的方法吗？
     *
     * solution: 和之前的题类似  s2 + s2 .subString(0,s2.length) .contains()来实现
     * @Date: 2021/5/21 11:02
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0) return true;
        if (s1.length() == 0) return false;
        if (s2.length() == 0) return false;
        return (s1 + s1).substring(0,s1.length() * 2).contains(s2);
    }


    /**
     * @Description:
     * 给定两个由小写字母构成的字符串 A 和 B ，
     * 只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
     *
     * 交换字母的定义是取两个下标 i 和 j （下标从 0 开始），
     * 只要 i!=j 就交换 A[i] 和 A[j] 处的字符。
     * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入： A = "ab", B = "ba"
     * 输出： true
     * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。
     * 示例 2：
     *
     * 输入： A = "ab", B = "ab"
     * 输出： false
     * 解释： 你只能交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 不相等。
     * 示例 3:
     *
     * 输入： A = "aa", B = "aa"
     * 输出： true
     * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'a' 生成 "aa"，此时 A 和 B 相等。
     * 示例 4：
     *
     * 输入： A = "aaaaaaabc", B = "aaaaaaacb"
     * 输出： true
     * 示例 5：
     *
     * 输入： A = "", B = "aa"
     * 输出： false
     *  
     *
     * 提示：
     *
     * 0 <= A.length <= 20000
     * 0 <= B.length <= 20000
     * A 和 B 仅由小写字母构成。
     *
     * solution:
     * 1.a b 长度不相等，直接pass
     * 2.遍历 a b 统计ab不同的地方diffCount ,单体重复的最大值 repeatCount
     * （diffCount = 2 && 2处总体相同）  || （diffCount = 0 && repeatCount >= 2）
     * O(N) O(N)  后面时间复杂度可以优化  在于交换的那一步
     * @Date: 2021/5/21 11:16
     */
    public boolean buddyStrings(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diffCount = 0;
        int repeatCount = 0;
        boolean flag;
        int[] arrA = new int[26];
        int[] arrB = new int[26];
        for (int i = 0; i < a.length(); i++) {
            arrA[122 - a.charAt(i)]++;
            arrB[122 - b.charAt(i)]++;
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
            }
        }
        flag = Arrays.equals(arrA, arrB);
        repeatCount = Arrays.stream(arrA).max().getAsInt();
        return (diffCount == 2 && flag) || (diffCount == 0 && repeatCount >= 2);
    }

    /**
     * @Description:
     * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。
     * 如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
     *
     * 请你返回 words 数组中 一致字符串 的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
     * 输出：2
     * 解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
     * 示例 2：
     *
     * 输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
     * 输出：7
     * 解释：所有字符串都是一致的。
     * 示例 3：
     *
     * 输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
     * 输出：4
     * 解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 104
     * 1 <= allowed.length <= 26
     * 1 <= words[i].length <= 10
     * allowed 中的字符 互不相同 。
     * words[i] 和 allowed 只包含小写英文字母。
     *
     * solution:
     * 1.暴力解法 遍历words数组,每有一个满足条件的 count++ 最后返回count
     * 2.words数组先去重,接着使用contains来判断是否满足 set
     * @Date: 2021/5/24 11:02
     */
    public int countConsistentStrings(String allowed, String[] words) {
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }
        for (String word : words) {
            boolean flag = true;
            for (int j = 0; j < word.length(); j++) {
                if (!set.contains(word.charAt(j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        return count;
    }

    //lambda表达式解法
    /*public int countConsistentStrings(String allowed, String[] words) {
        return (int) Arrays.stream(words)
                .filter(
                        word -> Arrays.stream(word.split(""))
                                .allMatch(allowed::contains))
                .count();
    }*/

    /*public String cancelRepeatStr(String str) {
        StringBuilder builder = new StringBuilder();
        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            arr[122 - str.charAt(i)]++;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > 0) {
                builder.append((char)(122 - i));
            }
        }
        return new String(builder);
    }*/

    /**
     * @Description:
     * 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。
     * 我们希望按下述规则将 s 映射为一些小写英文字符：
     *
     * 字符（'a' - 'i'）分别用（'1' - '9'）表示。
     * 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 
     * 返回映射之后形成的新字符串。
     *
     * 题目数据保证映射始终唯一。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "10#11#12"
     * 输出："jkab"
     * 解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
     * 示例 2：
     *
     * 输入：s = "1326#"
     * 输出："acz"
     * 示例 3：
     *
     * 输入：s = "25#"
     * 输出："y"
     * 示例 4：
     *
     * 输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
     * 输出："abcdefghijklmnopqrstuvwxyz"
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s[i] 只包含数字（'0'-'9'）和 '#' 字符。
     * s 是映射始终存在的有效字符串。
     *
     * solution:
     * 1.取i和i后两位字符（前提存在）判断 ，看是否为#
     * 2.不为# 直接转换 a-i i++
     * 3.为# 连续转三位 j-z i += 3 结果用builder.toString 接收 O(N) O(1)
     * //下面是根据ascll码表来做合理转换
     * @Date: 2021/5/24 13:55
     */
    public String freqAlphabets(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length();) {
            if (i + 2 < s.length()) {
                if (s.charAt(i + 2) != '#') {
                    builder.append((char)( s.charAt(i) + 48));
                    i++;
                } else {
                    int decade = s.charAt(i) == 49 ? 1 : 2;
                    builder.append((char)(decade * 10 + s.charAt(i + 1) + 48));
                    i +=3;
                }
            } else {
                builder.append((char)(s.charAt(i) + 48));
                i++;
            }
        }
        return new String(builder);
    }

    /**
     * @Description:
     * 如果字符串满足以下条件之一，则可以称之为 有效括号字符串
     * （valid parentheses string，可以简写为 VPS）：
     *
     * 字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
     * 字符串可以写为 AB（A 与 B 字符串连接），其中 A 和 B 都是 有效括号字符串 。
     * 字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
     * 类似地，可以定义任何有效括号字符串 S 的 嵌套深度 depth(S)：
     *
     * depth("") = 0
     * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
     * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
     * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
     * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
     *
     * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "(1+(2*3)+((8)/4))+1"
     * 输出：3
     * 解释：数字 8 在嵌套的 3 层括号中。
     * 示例 2：
     *
     * 输入：s = "(1)+((2))+(((3)))"
     * 输出：3
     * 示例 3：
     *
     * 输入：s = "1+(2*3)/(2-1)"
     * 输出：1
     * 示例 4：
     *
     * 输入：s = "1"
     * 输出：0
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 100
     * s 由数字 0-9 和字符 '+'、'-'、'*'、'/'、'('、')' 组成
     * 题目数据保证括号表达式 s 是 有效的括号表达式
     *
     * solution: 左括号 +1 右括号-1 找最大值 遍历整个s字符串 O(N) O(1)
     * @Date: 2021/5/25 10:02
     */
    public int maxDepth(String s) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count += 1;
                max = Math.max(max,count);
            } else if (s.charAt(i) == ')') {
                count -= 1;
            }
        }
        return max;
    }

    /**
     * @Description:
     * 请你设计一个可以解释字符串 command 的 Goal 解析器 。
     * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。
     * Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)"
     * 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
     *
     * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：command = "G()(al)"
     * 输出："Goal"
     * 解释：Goal 解析器解释命令的步骤如下所示：
     * G -> G
     * () -> o
     * (al) -> al
     * 最后连接得到的结果是 "Goal"
     * 示例 2：
     *
     * 输入：command = "G()()()()(al)"
     * 输出："Gooooal"
     * 示例 3：
     *
     * 输入：command = "(al)G(al)()()G"
     * 输出："alGalooG"
     *  
     *
     * 提示：
     *
     * 1 <= command.length <= 100
     * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成
     *
     * solution:
     * 遍历command字符串，进行字符串映射转换  StringBuilder startWith
     * O(N) O(1)
     * @Date: 2021/5/25 10:24
     */
    public String interpret(String command) {
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < command.length();) {
            if (command.substring(i).startsWith("(al)")) {
                res.append("al");
                i += 4;
            }else if (command.substring(i).startsWith("()")) {
                res.append("o");
                i += 2;
            } else {
                res.append("G");
                i +=1;
            }
        }
        return new String(res);
    }

    /**
     * @Description:
     * 给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；
     * 否则，返回 false 。
     *
     * 数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
     * 输出：true
     * 解释：
     * word1 表示的字符串为 "ab" + "c" -> "abc"
     * word2 表示的字符串为 "a" + "bc" -> "abc"
     * 两个字符串相同，返回 true
     * 示例 2：
     *
     * 输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
     * 输出：false
     * 示例 3：
     *
     * 输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
     * 输出：true
     *  
     *
     * 提示：
     *
     * 1 <= word1.length, word2.length <= 103
     * 1 <= word1[i].length, word2[i].length <= 103
     * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
     * word1[i] 和 word2[i] 由小写字母组成
     *
     * solution:
     * 1.全部拼接成str equals比较
     * 2.数组遍历依次比较
     * @Date: 2021/5/25 10:38
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for (String s : word1) {
            str1.append(s);
        }
        for (String s : word2) {
            str2.append(s);
        }
        return new String(str1).equals(new String(str2));
    }


    /**
     * @Description:
     * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?'
     * 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
     *
     * 注意：你 不能 修改非 '?' 字符。
     *
     * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
     *
     * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，
     * 请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "?zs"
     * 输出："azs"
     * 解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
     * 示例 2：
     *
     * 输入：s = "ubv?w"
     * 输出："ubvaw"
     * 解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
     * 示例 3：
     *
     * 输入：s = "j?qg??b"
     * 输出："jaqgacb"
     * 示例 4：
     *
     * 输入：s = "??yw?ipkj?"
     * 输出："acywaipkja"
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 100
     *
     * s 仅包含小写英文字母和 '?' 字符
     *
     * solution:
     * 只需要保持 ？ 与前后（前后都存在的情况）不同就OK了 遍历字符串s，逐个进行替换
     * O(N *26) O(N)
     * @Date: 2021/5/26 10:09
     */
    public String modifyString(String s) {
        if (s.equals("?")) {
            return "a";
        }
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '?') {
                if (i == 0) {
                    //上边界
                    if (i + 1 < charArr.length) {
                        for (int j = 97; j <= 122; j++) {
                            if (charArr[i + 1] != j) {
                                charArr[i] = (char)j;
                            }
                        }
                    }
                } else if (i == s.length() - 1) {
                    //下边界
                    for (int j = 97; j <= 122; j++) {
                        if (charArr[i - 1] != j) {
                            charArr[i] = (char)j;
                        }
                    }
                } else {
                    for (int j = 97; j <= 122; j++) {
                        if (charArr[i - 1] != j && charArr[i + 1] != j) {
                            charArr[i] = (char)j;
                        }
                    }
                }
            }
        }
        return new String(charArr);
    }

    /**
     * @Description:
     * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
     *
     * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
     *
     * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
     *
     *  
     *
     * 示例：
     *
     * 输入:
     * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
     * banned = ["hit"]
     * 输出: "ball"
     * 解释:
     * "hit" 出现了3次，但它是一个禁用的单词。
     * "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
     * 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
     * "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
     *  
     *
     * 提示：
     *
     * 1 <= 段落长度 <= 1000
     * 0 <= 禁用单词个数 <= 100
     * 1 <= 禁用单词长度 <= 10
     * 答案是唯一的, 且都是小写字母 (即使在 paragraph 里是大写的，即使是一些特定的名词，答案都是小写的。)
     * paragraph 只包含字母、空格和下列标点符号!?',;.
     * 不存在没有连字符或者带有连字符的单词。
     * 单词里只包含字母，不会出现省略号或者其他标点符号。
     *
     * solution:
     * 1.找出不在banned数组,且出现次数最多的单词   遍历一次banned数组 计数count，paragraph split
     *
     * @Date: 2021/5/26 10:46
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        int max = 0;
        paragraph = paragraph.replaceAll("!"," ");
        paragraph = paragraph.replaceAll("\\?"," ");
        paragraph = paragraph.replaceAll("'"," ");
        paragraph = paragraph.replaceAll(","," ");
        paragraph = paragraph.replaceAll(";"," ");
        paragraph = paragraph.replaceAll("\\."," ");
        paragraph = paragraph.toLowerCase();
        List<String> ban = Arrays.stream(banned).map(String::toLowerCase).collect(Collectors.toList());
        String[] paraArr = paragraph.split(" ");
        Map<String,Integer> map = new HashMap<>();
        for (String s : paraArr) {
            if (!ban.contains(s) && !s.equals("")) {
                map.put(s,map.getOrDefault(s,0) + 1);
                max = Math.max(max,map.get(s));
            }
        }
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * @Description:
     * 给定一个由空格分割单词的句子 S。每个单词只包含大写或小写字母。
     *
     * 我们要将句子转换为 “Goat Latin”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。
     *
     * 山羊拉丁文的规则如下：
     *
     * 如果单词以元音开头（a, e, i, o, u），在单词后添加"ma"。
     * 例如，单词"apple"变为"applema"。
     *
     * 如果单词以辅音字母开头（即非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
     * 例如，单词"goat"变为"oatgma"。
     *
     * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始。
     * 例如，在第一个单词后添加"a"，在第二个单词后添加"aa"，以此类推。
     * 返回将 S 转换为山羊拉丁文后的句子。
     *
     * 示例 1:
     *
     * 输入: "I speak Goat Latin"
     * 输出: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
     * 示例 2:
     *
     * 输入: "The quick brown fox jumped over the lazy dog"
     * 输出: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
     * 说明:
     *
     * S 中仅包含大小写字母和空格。单词间有且仅有一个空格。
     * 1 <= S.length <= 150。
     *
     * solution: 1.就按照题意来实现就可以了
     * @Date: 2021/5/28 10:11
     */
    public String toGoatLatin(String sentence) {
        String[] sentenceArr = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sentenceArr.length; i++) {
            if (sentenceArr[i].startsWith("A")
                    || sentenceArr[i].startsWith("E")
                    || sentenceArr[i].startsWith("I")
                    ||sentenceArr[i].startsWith("O")
                    ||sentenceArr[i].startsWith("U")
                    || sentenceArr[i].startsWith("a")
                    || sentenceArr[i].startsWith("e")
                    ||sentenceArr[i].startsWith("i")
                    ||sentenceArr[i].startsWith("o")
                    ||sentenceArr[i].startsWith("u")) {
                sentenceArr[i] += "ma";
            } else {
                sentenceArr[i] = sentenceArr[i].substring(1) + sentenceArr[i].charAt(0) + "ma";
            }

            for (int j = 0; j < i + 1; j++) {
                sentenceArr[i] += "a";
            }
            if (i == sentenceArr.length - 1) {
                builder.append(sentenceArr[i]);
                continue;
            }
            builder.append(sentenceArr[i]).append(" ");
        }
        return builder.toString();
    }

    /**
     * @Description:
     * 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
     *
     * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 4
     * 输出："pppz"
     * 解释："pppz" 是一个满足题目要求的字符串，因为 'p' 出现 3 次，且 'z' 出现 1 次。
     * 当然，还有很多其他字符串也满足题目要求，比如："ohhh" 和 "love"。
     * 示例 2：
     *
     * 输入：n = 2
     * 输出："xy"
     * 解释："xy" 是一个满足题目要求的字符串，因为 'x' 和 'y' 各出现 1 次。
     * 当然，还有很多其他字符串也满足题目要求，比如："ag" 和 "ur"。
     * 示例 3：
     *
     * 输入：n = 7
     * 输出："holasss"
     *  
     *
     * 提示：
     *
     * 1 <= n <= 500
     *
     * solution: 奇数 n 个 a ,偶数  1个 a, n-1个b
     * @Date: 2021/5/28 10:44
     */
    public String generateTheString(int n) {
        StringBuilder res = new StringBuilder();
        if (n % 2 == 1) {
           for (int i = 0; i < n ; i++) {
               res.append("a");
           }
        } else {
            res.append("a");
            for (int i = 0; i < n - 1; i++) {
                res.append("b");
            }
        }
        return res.toString();
    }

    /**
     * @Description:
     * 1.先按照 -( 拆分 若拆分后数组有两个元素,则为带括号的字符串
     * 2.若只有一个数组,则按 - 进行拆分,取首尾和中间的返回
     * str 包含如下：
     * 攀钢-1.0*1250*C-ST12,
     * 攀钢-5.5*1500*C-Q235B,
     * 攀钢-5.75*1500*C-Q235B,
     * 威钢-Φ18-22mm-HRB400E,
     * 昆钢-Φ18-22mm-HRB400,
     * 国义-150*150-(Q195-Q235)
     * @Date: 2021/5/28 14:03
     */
    public static String[] convertContractField(String str) {
        String[] res = new String[3];

        if (str.length() == 0) {
            return null;
        }
        String[] fieldArr = str.split("-\\(");
        if (fieldArr.length ==2) {
            res[0] = fieldArr[0].split("-")[0];
            res[1] = fieldArr[0].split("-")[1];
            res[2] = fieldArr[1].substring(0,fieldArr[1].length() - 1);
        } else {
            String[] normalArr = str.split("-");
            if (normalArr.length != 3 && normalArr.length != 4) {
                return null;
            }
            res[0] = normalArr[0];
            res[1] = str.substring(normalArr[0].length() + 1,
                    str.length() - normalArr[normalArr.length - 1].length() - 1);
            res[2] = normalArr[normalArr.length - 1];
        }
        return res;
    }

    /**
     * @Description:
     * 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
     *
     * 例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
     *
     * 除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
     *
     * 如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），
     * 则发往那里的邮件将会转发到本地名称中没有点的同一地址。例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 
     * 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
     *
     * 如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，
     * 例如 m.y+name@email.com 将转发到 my@email.com。 （同样，此规则不适用于域名。）
     *
     * 可以同时使用这两个规则。
     *
     * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
     *
     *  
     *
     * 示例：
     *
     * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
     * 输出：2
     * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
     *  
     *
     * 提示：
     *
     * 1 <= emails[i].length <= 100
     * 1 <= emails.length <= 100
     * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
     *
     * solution: 遍历emails数组,用规则 划分出每一个需要发送的邮箱,
     * 然后放入set,最后返回set.size() O(N*M) O(N)
     * @Date: 2021/5/31 10:50
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> emailSet = new HashSet<>();
        for (String email : emails) {
            emailSet.add(pickUpEmail(email));
        }
        return emailSet.size();
    }

    private String pickUpEmail(String str) {
        String[] emailStr = str.split("@");
        int i = emailStr[0].indexOf("+");
        String tmpStr = "";
        if (i != -1) {
            tmpStr = emailStr[0].substring(0, i);
        } else {
            tmpStr = emailStr[0];
        }
        tmpStr = tmpStr.replaceAll("\\.", "");
        return tmpStr + "@" + emailStr[1];
    }

    /**
     * @Description:
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     *
     * 请你返回字符串的能量。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "leetcode"
     * 输出：2
     * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
     * 示例 2：
     *
     * 输入：s = "abbcccddddeeeeedcba"
     * 输出：5
     * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
     * 示例 3：
     *
     * 输入：s = "triplepillooooow"
     * 输出：5
     * 示例 4：
     *
     * 输入：s = "hooraaaaaaaaaaay"
     * 输出：11
     * 示例 5：
     *
     * 输入：s = "tourist"
     * 输出：1
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 500
     * s 只包含小写英文字母。
     *
     * solution:
     * 1.遍历 计数,最后返回max  O(N) O(1)
     * @Date: 2021/5/31 14:00
     */
    public int maxPower(String s) {
        int max = 1;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
           if (s.charAt(i) == s.charAt(i - 1)) {
               count++;
           } else {
               max = Math.max(max,count);
               count = 1;
           }
        }
        max = Math.max(max,count);
        return max;
    }

    /**
     * @Description:
     * 给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。
     *
     * 如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：words = ["mass","as","hero","superhero"]
     * 输出：["as","hero"]
     * 解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
     * ["hero","as"] 也是有效的答案。
     * 示例 2：
     *
     * 输入：words = ["leetcode","et","code"]
     * 输出：["et","code"]
     * 解释："et" 和 "code" 都是 "leetcode" 的子字符串。
     * 示例 3：
     *
     * 输入：words = ["blue","green","bu"]
     * 输出：[]
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 30
     * words[i] 仅包含小写英文字母。
     * 题目数据 保证 每个 words[i] 都是独一无二的。
     *
     * solution:
     * words数组除自己本身之外的所有元素,逐个做包含校验,如果包含则加入list 最后返回这个list O(N2) O(1)
     * @Date: 2021/5/31 14:12
     */
    public List<String> stringMatching(String[] words) {
        Set<String> res = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (j != i && words[j].length() >= words[i].length()) {
                    if (words[j].contains(words[i])) {
                        res.add(words[i]);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    /**
     * @Description:
     * 给你一个字符串 sentence 作为句子并指定检索词为 searchWord ，其中句子由若干用 单个空格 分隔的单词组成。
     *
     * 请你检查检索词 searchWord 是否为句子 sentence 中任意单词的前缀。
     *
     * 如果 searchWord 是某一个单词的前缀，则返回句子 sentence 中该单词所对应的下标（下标从 1 开始）。
     * 如果 searchWord 是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。
     * 如果 searchWord 不是任何单词的前缀，则返回 -1 。
     * 字符串 S 的 前缀 是 S 的任何前导连续子字符串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：sentence = "i love eating burger", searchWord = "burg"
     * 输出：4
     * 解释："burg" 是 "burger" 的前缀，而 "burger" 是句子中第 4 个单词。
     * 示例 2：
     *
     * 输入：sentence = "this problem is an easy problem", searchWord = "pro"
     * 输出：2
     * 解释："pro" 是 "problem" 的前缀，而 "problem" 是句子中第 2 个也是第 6 个单词，但是应该返回最小下标 2 。
     * 示例 3：
     *
     * 输入：sentence = "i am tired", searchWord = "you"
     * 输出：-1
     * 解释："you" 不是句子中任何单词的前缀。
     * 示例 4：
     *
     * 输入：sentence = "i use triple pillow", searchWord = "pill"
     * 输出：4
     * 示例 5：
     *
     * 输入：sentence = "hello from the other side", searchWord = "they"
     * 输出：-1
     *  
     *
     * 提示：
     *
     * 1 <= sentence.length <= 100
     * 1 <= searchWord.length <= 10
     * sentence 由小写英文字母和空格组成。
     * searchWord 由小写英文字母组成。
     * 前缀就是紧密附着于词根的语素，中间不能插入其它成分，并且它的位置是固定的——-位于词根之前。（引用自 前缀_百度百科 ）
     *
     * solution:
     * 1.遍历整个sentence(sentence 先进行split分组Array) 检测到返回下标 +1 ,最后没有检测到就返回 -1 O(N) O(N)
     * @Date: 2021/5/31 14:31
     */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] sentenceArr = sentence.split(" ");
        for (int i = 0; i < sentenceArr.length; i++) {
            if (sentenceArr[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * @Description:
     * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
     *
     * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
     *
     * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "a0b1c2"
     * 输出："0a1b2c"
     * 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
     * 示例 2：
     *
     * 输入：s = "leetcode"
     * 输出：""
     * 解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
     * 示例 3：
     *
     * 输入：s = "1229857369"
     * 输出：""
     * 解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
     * 示例 4：
     *
     * 输入：s = "covid2019"
     * 输出："c2o0v1i9d"
     * 示例 5：
     *
     * 输入：s = "ab123"
     * 输出："1a2b3"
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 500
     * s 仅由小写英文字母和/或数字组成
     *
     * solution:
     * 1.统计小写字母和数字的个数,用arr存储
     * 2.若两者arr.length 相差小于等于1 则按照 length大的依次填写
     * 3.否则返回""
     * @Date: 2021/5/31 14:40
     */
    public String reformat(String s) {
        StringBuilder builder = new StringBuilder();
        int numCount = 0;
        int englishCount = 0;
        char[] tmpArr = s.toCharArray();
        for (char c : tmpArr) {
            if (c >= 97 && c <= 122) englishCount++;
            if (c >= 48 && c <= 57) numCount++;
        }
        if (Math.abs(numCount - englishCount) > 1) {
            return "";
        } else {
            int index1 = 0, index2 = 0;
            char[] numArr = new char[numCount];
            char[] englishArr = new char[englishCount];
            for (char c : tmpArr) {
                if (c >= 97 && c <= 122) englishArr[index1++] = c;
                if (c >= 48 && c <= 57) numArr[index2++] = c;
            }
            int count = Math.min(numCount,englishCount);
            for (int i = 0; i < count; i++) {
                if (numCount >= englishCount) {
                    builder.append(numArr[i]).append(englishArr[i]);
                } else {
                    builder.append(englishArr[i]).append(numArr[i]);
                }
            }
            if (numCount - englishCount == 1) {
                builder.append(numArr[count]);
            }else if (numCount - englishCount == -1) {
                builder.append(englishArr[count]);
            }
        }
        return builder.toString();
    }


    /**
     * @Description:
     * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）
     * 所能获得的最大得分。
     *
     * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "011101"
     * 输出：5
     * 解释：
     * 将字符串 s 划分为两个非空子字符串的可行方案有：
     * 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
     * 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
     * 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
     * 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
     * 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
     * 示例 2：
     *
     * 输入：s = "00111"
     * 输出：5
     * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
     * 示例 3：
     *
     * 输入：s = "1111"
     * 输出：3
     *  
     *
     * 提示：
     *
     * 2 <= s.length <= 500
     * 字符串 s 仅由字符 '0' 和 '1' 组成。
     *
     * solution:
     * 1.暴力
     * 2.找规律  官方解答，
     * a 统计出1的个数  oneCount
     * b.遍历s 0 --> s.length - 1，每次遍历一个数 其实就是模拟切割一次字符串  更新 max = Math.max(max,oneCount+zeroCount)
     * @Date: 2021/6/1 9:50
     */
    public int maxScore(String s) {
        int oneCount = 0;
        int zeroCount = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') oneCount++;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                oneCount--;
            }
            max = Math.max(max,oneCount + zeroCount);
        }
        return max;
    }


    /**
     * @Description:
     * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，
     * 且和 X 不同的数。要求每位数字都要被旋转。
     *
     * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。
     * 0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，
     * 它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
     *
     * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
     *
     *  
     *
     * 示例：
     *
     * 输入: 10
     * 输出: 4
     * 解释:
     * 在[1, 10]中有四个好数： 2, 5, 6, 9。
     * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
     *  
     *
     * 提示：
     *
     * N 的取值范围是 [1, 10000]。
     *
     * solution:
     * 遍历 1 --> n ,检查有效数字  有 2 5  6 9  && 没有  3 4 7  O(N*M) O(1)
     * @Date: 2021/6/1 10:11
     */
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1 ; i <= n; i++) {
            if (isEffective(i)) count++;
        }
        return count;
    }

    public boolean isEffective(int num) {
        String s = String.valueOf(num);
        boolean flag2569 = false;
        boolean flag347 = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '7') {
                flag347 = true;
            } else if (s.charAt(i) == '2' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '9') {
                flag2569 = true;
            }
        }
        return flag2569 && !flag347;
    }


    /**
     * @Description:
     * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。
     * 如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
     *
     * 返回 合并后的字符串 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：word1 = "abc", word2 = "pqr"
     * 输出："apbqcr"
     * 解释：字符串合并情况如下所示：
     * word1：  a   b   c
     * word2：    p   q   r
     * 合并后：  a p b q c r
     * 示例 2：
     *
     * 输入：word1 = "ab", word2 = "pqrs"
     * 输出："apbqrs"
     * 解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
     * word1：  a   b
     * word2：    p   q   r   s
     * 合并后：  a p b q   r   s
     * 示例 3：
     *
     * 输入：word1 = "abcd", word2 = "pq"
     * 输出："apbqcd"
     * 解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
     * word1：  a   b   c   d
     * word2：    p   q
     * 合并后：  a p b q c   d
     *
     * solution:  这种题没意思 O(N) O(1)
     * @Date: 2021/6/1 10:43
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder builder = new StringBuilder();
        int len1 = word1.length();
        int len2 = word2.length();
        int min = Math.min(len1,len2);
        for (int i = 0; i < min; i++) {
            builder.append(word1.charAt(i));
            builder.append(word2.charAt(i));
        }
        if (len1 > len2) {
            for (int i = min; i < len1; i++) {
                builder.append(word1.charAt(i));
            }
        } else if (len1 < len2) {
            for (int i = min; i < len2; i++) {
                builder.append(word2.charAt(i));
            }
        }
        return new String(builder);
    }

    /**
     * @Description:
     * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
     *
     * 子字符串 是字符串中的一个连续字符序列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "aa"
     * 输出：0
     * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
     * 示例 2：
     *
     * 输入：s = "abca"
     * 输出：2
     * 解释：最优的子字符串是 "bc" 。
     * 示例 3：
     *
     * 输入：s = "cbzxy"
     * 输出：-1
     * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
     * 示例 4：
     *
     * 输入：s = "cabbac"
     * 输出：4
     * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 300
     * s 只含小写英文字母
     *
     * solution: 遍历每一次字符串,使用String Api indexOf lastIndexOf来解决 s.last - s.index 更新max
     * @Date: 2021/6/2 9:56
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            int start = s.indexOf(s.charAt(i));
            int end = s.lastIndexOf(s.charAt(i));
            if (start != end) {
                max = Math.max(max,end - start - 1);
            }
        }
        return max;
    }

    /**
     * @Description:
     * 全字母句 指包含英语字母表中每个字母至少一次的句子。
     *
     * 给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。
     *
     * 如果是，返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：sentence = "thequickbrownfoxjumpsoverthelazydog"
     * 输出：true
     * 解释：sentence 包含英语字母表中每个字母至少一次。
     * 示例 2：
     *
     * 输入：sentence = "leetcode"
     * 输出：false
     *  
     *
     * 提示：
     *
     * 1 <= sentence.length <= 1000
     * sentence 由小写英语字母组成
     *
     * solution:
     * 1.桶排序 求不为空的个数  O(N) O(26)
     * 2.往set里面丢 返回set.size == 26 O(N) O(N)
     * @Date: 2021/6/2 10:29
     */
    /*public boolean checkIfPangram(String sentence) {
        int[] arr = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            arr[122 - sentence.charAt(i)]++;
        }
        int count = 0;
        for (int i : arr) {
            if (i > 0) count++;
        }
        return count == 26;
    }*/
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < sentence.length(); i++) {
            set.add(sentence.charAt(i));
        }
        return set.size() == 26;
    }

    /**
     * @Description:
     * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
     *
     * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。
     * 注意，s 可能同时含有大写和小写字母。
     *
     * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "book"
     * 输出：true
     * 解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
     * 示例 2：
     *
     * 输入：s = "textbook"
     * 输出：false
     * 解释：a = "text" 且 b = "book" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
     * 注意，元音 o 在 b 中出现两次，记为 2 个。
     * 示例 3：
     *
     * 输入：s = "MerryChristmas"
     * 输出：false
     * 示例 4：
     *
     * 输入：s = "AbCdEfGh"
     * 输出：true
     *  
     *
     * 提示：
     *
     * 2 <= s.length <= 1000
     * s.length 是偶数
     * s 由 大写和小写 字母组成
     *
     * solution: 统计总的元音字母和一半的元音字母 看是否是2倍关系
     * @Date: 2021/6/2 10:38
     */
    public boolean halvesAreAlike(String s) {
        int half = 0;
        int all = 0;
        Character[] arr = new Character[] {'a','e','i','o','u','A','E','I','O','U'};
        List<Character> list = Arrays.asList(arr);
        for (int i = 0; i < s.length() / 2; i++) {
            if (list.contains(s.charAt(i))) {
                half++;
                all++;
            }
        }
        for (int i = s.length() / 2; i < s.length(); i++) {
            if (list.contains(s.charAt(i))) {
                all++;
            }
        }
        return all == half * 2;
    }

    /**
     * @Description:
     * 给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。
     *
     * 机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。
     *
     * 如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。
     *
     *  
     *
     * 示例 1：
     *
     *
     *
     * 输入：path = "NES"
     * 输出：false
     * 解释：该路径没有在任何位置相交。
     * 示例 2：
     *
     *
     *
     * 输入：path = "NESWW"
     * 输出：true
     * 解释：该路径经过原点两次。
     *  
     *
     * 提示：
     *
     * 1 <= path.length <= 10^4
     * path 仅由 {'N', 'S', 'E', 'W} 中的字符组成
     *
     * solution: 搞个坐标x y , 移动记录x y的值 每次判断 x y 是否在之前出现过
     * 之前的用set存起来 是 返回true 遍历完成 返回false
     * O(N) O(1)
     * @Date: 2021/6/3 10:06
     */
    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        Set<String> set = new HashSet<>();
        set.add("0:0");
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == 'N') {
                y++;
            } else if (c == 'S') {
                y--;
            } else if (c == 'E') {
                x++;
            } else {
                x--;
            }
            if (!set.add(x + ":" + y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description:
     * 给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 987
     * 输出："987"
     * 示例 2：
     *
     * 输入：n = 1234
     * 输出："1.234"
     * 示例 3：
     *
     * 输入：n = 123456789
     * 输出："123.456.789"
     * 示例 4：
     *
     * 输入：n = 0
     * 输出："0"
     *  
     *
     * 提示：
     *
     * 0 <= n < 2^31
     *
     * solution:
     * 1.计算需要添加多少个. new charArr 2.按题目来实现 3.最后返回  toCharArray StringBuilder
     * @Date: 2021/6/3 10:46
     */
    public String thousandSeparator(int n) {
        String str = String.valueOf(n);
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            builder.append(str.charAt(i));
            count++;
            if (count % 3 == 0 && i != 0) {
                builder.append(".");
            }
        }
        builder.reverse();
        return builder.toString();
    }

    /**
     * @Description:
     * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     *
     * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     *
     * 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：time = "2?:?0"
     * 输出："23:50"
     * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
     * 示例 2：
     *
     * 输入：time = "0?:3?"
     * 输出："09:39"
     * 示例 3：
     *
     * 输入：time = "1?:22"
     * 输出："19:22"
     *  
     *
     * 提示：
     *
     * time 的格式为 hh:mm
     * 题目数据保证你可以由输入的字符串生成有效的时间
     *
     * solution: 根据时间规则，返回就OK 看问号的位置 和问号的个数，分类来解决 题友区的解答
     * @Date: 2021/6/4 11:30
     */
    public String maximumTime(String time) {
        char[] times = new char[4];
        for(int i=0;i<4;i++){
            times[i] = time.charAt(i<2?i:i+1);
        }
        String res = "";
        for(int i=0;i<4;i++){
            if(times[i]=='?'){
                switch(i){
                    case 0 : times[0] = times[1]>='4'&&times[1]<='9' ? '1' : '2';break;
                    case 1 : times[1] = times[0]=='2' ? '3' : '9';break;
                    case 2 : times[2] = '5';break;
                    case 3 : times[3] = '9';break;
                }
            }
            res += times[i];
            if(i==1) res += ":";
        }
        return res;
    }


    /**
     * @Description:
     * 给你一个坐标 coordinates ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。
     *
     *
     *
     * 如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。
     *
     * 给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：coordinates = "a1"
     * 输出：false
     * 解释：如上图棋盘所示，"a1" 坐标的格子是黑色的，所以返回 false 。
     * 示例 2：
     *
     * 输入：coordinates = "h3"
     * 输出：true
     * 解释：如上图棋盘所示，"h3" 坐标的格子是白色的，所以返回 true 。
     * 示例 3：
     *
     * 输入：coordinates = "c7"
     * 输出：false
     *  
     *
     * 提示：
     *
     * coordinates.length == 2
     * 'a' <= coordinates[0] <= 'h'
     * '1' <= coordinates[1] <= '8'
     *
     * solution: a-h转成  1-8 ,black:全为奇数 || 全为偶数  while: 一个为奇数 一个为偶数 coordinates拆分求解
     * @Date: 2021/6/4 13:33
     */
    public boolean squareIsWhite(String coordinates) {
        int one = coordinates.charAt(0) - 96;
        int two = coordinates.charAt(1) - 48;
        return (one % 2 == 0 && two % 2 != 0) || (one % 2 != 0 && two % 2 == 0);
    }

    /**
     * @Description:
     * 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
     *
     * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
     *
     * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
     *
     * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "ababa"
     * 输出：1
     * 解释：字符串本身就是回文序列，只需要删除一次。
     * 示例 2：
     *
     * 输入：s = "abb"
     * 输出：2
     * 解释："abb" -> "bb" -> "".
     * 先删除回文子序列 "a"，然后再删除 "bb"。
     * 示例 3：
     *
     * 输入：s = "baabb"
     * 输出：2
     * 解释："baabb" -> "b" -> "".
     * 先删除回文子序列 "baab"，然后再删除 "b"。
     * 示例 4：
     *
     * 输入：s = ""
     * 输出：0
     *  
     *
     * 提示：
     *
     * 0 <= s.length <= 1000
     * s 仅包含字母 'a'  和 'b'
     *
     * solution:
     * @Date: 2021/6/4 13:46
     */
    public int removePalindromeSub(String s) {
        return s.length() == 0 ? 0 : new StringBuilder(s).reverse().toString().equals(s) ? 1 : 2;
    }

    /**
     * @Description:
     * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），
     * 并交换这两个下标所对应的字符。
     *
     * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s1 = "bank", s2 = "kanb"
     * 输出：true
     * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
     * 示例 2：
     *
     * 输入：s1 = "attack", s2 = "defend"
     * 输出：false
     * 解释：一次字符串交换无法使两个字符串相等
     * 示例 3：
     *
     * 输入：s1 = "kelb", s2 = "kelb"
     * 输出：true
     * 解释：两个字符串已经相等，所以不需要进行字符串交换
     * 示例 4：
     *
     * 输入：s1 = "abcd", s2 = "dcba"
     * 输出：false
     *  
     *
     * 提示：
     *
     * 1 <= s1.length, s2.length <= 100
     * s1.length == s2.length
     * s1 和 s2 仅由小写英文字母组成
     *
     * solution: s1 s2 toCharArray   ,diffCount count num
     * diffCount == 0 || ( diffCount == 2 && (a[i] == b[j] && a[j] == b[i]))
     * @Date: 2021/6/4 13:55
     */
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int i = 0;
        int j = 0;
        int diffCount = 0;
        char[] strArr1 = s1.toCharArray();
        char[] strArr2 = s2.toCharArray();
        for (int a = 0; a < strArr1.length; a++) {
            if (strArr1[a] != strArr2[a]) {
                diffCount++;
                if (diffCount == 1) i = a;
                if (diffCount == 2) j = a;
            }
            if (diffCount == 3) return false;
        }
        return diffCount == 0
                || (diffCount == 2 &&
                ((strArr1[i] == strArr2[j]) && (strArr1[j] == strArr2[i])));
    }

    /**
     *
     * @Description:
     * 给你一个字符串形式的电话号码 number 。number 由数字、空格 ' '、和破折号 '-' 组成。
     *
     * 请你按下述方式重新格式化电话号码。
     *
     * 首先，删除 所有的空格和破折号。
     * 其次，将数组从左到右 每 3 个一组 分块，直到 剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
     * 2 个数字：单个含 2 个数字的块。
     * 3 个数字：单个含 3 个数字的块。
     * 4 个数字：两个分别含 2 个数字的块。
     * 最后用破折号将这些块连接起来。注意，重新格式化过程中 不应该 生成仅含 1 个数字的块，并且 最多 生成两个含 2 个数字的块。
     *
     * 返回格式化后的电话号码。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：number = "1-23-45 6"
     * 输出："123-456"
     * 解释：数字是 "123456"
     * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
     * 步骤 2：剩下 3 个数字，将它们放入单个含 3 个数字的块。第 2 个块是 "456" 。
     * 连接这些块后得到 "123-456" 。
     * 示例 2：
     *
     * 输入：number = "123 4-567"
     * 输出："123-45-67"
     * 解释：数字是 "1234567".
     * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
     * 步骤 2：剩下 4 个数字，所以将它们分成两个含 2 个数字的块。这 2 块分别是 "45" 和 "67" 。
     * 连接这些块后得到 "123-45-67" 。
     * 示例 3：
     *
     * 输入：number = "123 4-5678"
     * 输出："123-456-78"
     * 解释：数字是 "12345678" 。
     * 步骤 1：第 1 个块 "123" 。
     * 步骤 2：第 2 个块 "456" 。
     * 步骤 3：剩下 2 个数字，将它们放入单个含 2 个数字的块。第 3 个块是 "78" 。
     * 连接这些块后得到 "123-456-78" 。
     * 示例 4：
     *
     * 输入：number = "12"
     * 输出："12"
     * 示例 5：
     *
     * 输入：number = "--17-5 229 35-39475 "
     * 输出："175-229-353-94-75"
     *  
     *
     * 提示：
     *
     * 2 <= number.length <= 100
     * number 由数字和字符 '-' 及 ' ' 组成。
     * number 中至少含 2 个数字。
     *
     * solution:
     * 1.删除 空格 破折号
     * 2.遍历字符串  特殊处理  仅剩下4 3 2 1个数字的时候 O(N) O(1)
     * @Date: 2021/6/7 17:12
     */
    public String reformatNumber(String number) {
        StringBuilder res = new StringBuilder();
        number = number.replaceAll(" ", "");
        number = number.replaceAll("-", "");
        int len = number.length();
        for (int i = 0; i < len; ) {
            int tmp = len - i;
            if (tmp > 4) {
                res.append(number, i, i + 3);
                res.append("-");
                i += 3;
            } else if (tmp == 4) {
                res.append(number, i, i + 2);
                res.append("-");
                i = i + 2;
                res.append(number, i, i + 2);
                i = i + 2;
            } else if (tmp == 3) {
                res.append(number, i, i + 3);
                i += 3;
            } else if (tmp == 2) {
               res.append(number, i, i + 2);
               i += 2;
            }
        }
        return res.toString();
    }

    /**
     * @Description:
     * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
     *
     * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
     * 给你一个句子 s​​​​​​ 和一个整数 k​​​​​​ ，请你将 s​​ 截断 ​，​​​使截断后的句子仅含 前 k​​​​​​ 个单词。返回 截断 s​​​​​​ 后得到的句子。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "Hello how are you Contestant", k = 4
     * 输出："Hello how are you"
     * 解释：
     * s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
     * 前 4 个单词为 ["Hello", "how", "are", "you"]
     * 因此，应当返回 "Hello how are you"
     * 示例 2：
     *
     * 输入：s = "What is the solution to this problem", k = 4
     * 输出："What is the solution"
     * 解释：
     * s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
     * 前 4 个单词为 ["What", "is", "the", "solution"]
     * 因此，应当返回 "What is the solution"
     * 示例 3：
     *
     * 输入：s = "chopper is not a tanuki", k = 5
     * 输出："chopper is not a tanuki"
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 500
     * k 的取值范围是 [1,  s 中单词的数目]
     * s 仅由大小写英文字母和空格组成
     * s 中的单词之间由单个空格隔开
     * 不存在前导或尾随空格
     *
     * solution:
     * 1.split to Array
     * 2.取前k个组合起来
     * 第二种思路 找到k截断的位置,直接使用subString来实现
     * @Date: 2021/6/7 17:29
     */
    public String truncateSentence(String s, int k) {
        StringBuilder res = new StringBuilder();
        String[] strArr = s.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            res.append(strArr[i]);
            if (i < k - 1) {
                res.append(" ");
            } else if (i == k - 1) {
                break;
            }
        }
        return new String(res);
    }

    /**
     * @Description:
     * 给你一个字符串 date ，它的格式为 Day Month Year ，其中：
     *
     * Day 是集合 {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"} 中的一个元素。
     * Month 是集合 {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}
     *  中的一个元素。
     * Year 的范围在 ​[1900, 2100] 之间。
     * 请你将字符串转变为 YYYY-MM-DD 的格式，其中：
     *
     * YYYY 表示 4 位的年份。
     * MM 表示 2 位的月份。
     * DD 表示 2 位的天数。
     *  
     *
     * 示例 1：
     *
     * 输入：date = "20th Oct 2052"
     * 输出："2052-10-20"
     * 示例 2：
     *
     * 输入：date = "6th Jun 1933"
     * 输出："1933-06-06"
     * 示例 3：
     *
     * 输入：date = "26th May 1960"
     * 输出："1960-05-26"
     *  
     *
     * 提示：
     *
     * 给定日期保证是合法的，所以不需要处理异常输入。
     *
     * solution:
     * 1.先拆分，定义三个数组 分别对三个元素做转换
     * a.第一个截取0--最后两位的位置  b. 这个数组做转换  c.直接取  最后倒序组合
     * @Date: 2021/6/8 10:14
     */
    public String reformatDate(String date) {
        String[] monthArr = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] dateArr = date.split(" ");
        String str1 = dateArr[0].substring(0,dateArr[0].length() - 2);
        if (Integer.parseInt(str1) < 10) {
            str1 = 0 + str1;
        }
        String str2 = "";
        for (int i = 0; i < monthArr.length; i++) {
            if (monthArr[i].equals(dateArr[1])) {
                if (i >= 9) {
                    str2 = String.valueOf(i + 1);
                } else {
                    str2 = 0 + String.valueOf(i + 1);
                }
            }
        }
        String str3 = dateArr[2];
        return str3 + "-" +str2 + "-" +str1;
    }

    /**
     * @Description:
     * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
     *
     * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。
     * 注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
     *
     * 返回对 word 完成替换后形成的 不同 整数的数目。
     *
     * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：word = "a123bc34d8ef34"
     * 输出：3
     * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
     * 示例 2：
     *
     * 输入：word = "leet1234code234"
     * 输出：2
     * 示例 3：
     *
     * 输入：word = "a1b01c001"
     * 输出：1
     * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
     *  
     *
     * 提示：
     *
     * 1 <= word.length <= 1000
     * word 由数字和小写英文字母组成
     *
     * solution；
     * 1.把整数拆出来，Integer.parseInt 然后往set里面丢  最后返回set的size
     * 是不是可以按照正则表达式替换  下面这个解答是评论区的
     * @Date: 2021/6/8 10:36
     */
    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        String s = "";
        for (char c : word.toCharArray()) {
            if (c >= '0' && c <= '9') {
                if (s.equals("")) {
                    s += c;
                } else if (c == '0' && s.equals("0")) {
                    continue;
                } else if (c > '0' && s.equals("0")) {
                    s = "" + c;
                } else {
                    s += c;
                }
            } else if (s.length() > 0) {
                set.add(s);
                s = "";
            }
        }
        if (s.length() > 0) {
            set.add(s);
        }
        return set.size();
    }

    /**
     * @Description:
     * 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
     *
     * 有两种不同类型的日志：
     *
     * 字母日志：除标识符之外，所有字均由小写字母组成
     * 数字日志：除标识符之外，所有字均由数字组成
     * 请按下述规则将日志重新排序：
     *
     * 所有 字母日志 都排在 数字日志 之前。
     * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
     * 数字日志 应该保留原来的相对顺序。
     * 返回日志的最终顺序。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
     * 输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
     * 解释：
     * 字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
     * 数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
     * 示例 2：
     *
     * 输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
     * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
     *  
     *
     * 提示：
     *
     * 1 <= logs.length <= 100
     * 3 <= logs[i].length <= 100
     * logs[i] 中，字与字之间都用 单个 空格分隔
     * 题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
     *
     * solution:
     * 1.找出数字和字母日志的个数，新建两个数组来存放
     * 2.字母数组重写compare方法 排序
     * 3.最后按照顺序把 字母和数字数组放进去
     * @Date: 2021/6/9 9:56
     */
    public String[] reorderLogFiles(String[] logs) {
        String[] res = new String[logs.length];
        int alpCount = 0;
        int numCount = 0;
        for (int i = 0; i < logs.length; i++) {
            char c = logs[i].charAt(logs[i].length() - 1);
            if (c >= 'a' && c <= 'z') {
                alpCount++;
            } else {
                numCount++;
            }
        }
        String[] strArr = new String[alpCount];
        String[] numArr = new String[numCount];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < logs.length; i++) {
            char c = logs[i].charAt(logs[i].length() - 1);
            if (c >= 'a' && c <= 'z') {
                strArr[index1++] = logs[i];
            } else {
                numArr[index2++] = logs[i];
            }
        }
        Arrays.sort(strArr, new AlpComparator());
        if (strArr.length >= 0) System.arraycopy(strArr, 0, res, 0, strArr.length);
        if (numArr.length >= 0) System.arraycopy(numArr, 0, res, strArr.length, numArr.length);
        return res;
    }

    static class AlpComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            String[] str1Arr = str1.split(" ");
            String[] str2Arr = str2.split(" ");
            String tmpStr1 = str1.substring(str1Arr[0].length());
            String tmpStr2 = str2.substring(str2Arr[0].length());
            if (tmpStr1.compareTo(tmpStr2) == 0) {
                return str1Arr[0].compareTo(str2Arr[0]);
            } else {
                return tmpStr1.compareTo(tmpStr2);
            }
        }
    }

    /**
     * @Description:
     * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，
     * 并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
     *
     * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。
     * 如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
     *
     * 返回 重新排列空格后的字符串 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：text = "  this   is  a sentence "
     * 输出："this   is   a   sentence"
     * 解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
     * 示例 2：
     *
     * 输入：text = " practice   makes   perfect"
     * 输出："practice   makes   perfect "
     * 解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
     * 示例 3：
     *
     * 输入：text = "hello   world"
     * 输出："hello   world"
     * 示例 4：
     *
     * 输入：text = "  walks  udp package   into  bar a"
     * 输出："walks  udp  package  into  bar  a "
     * 示例 5：
     *
     * 输入：text = "a"
     * 输出："a"
     *  
     *
     * 提示：
     *
     * 1 <= text.length <= 100
     * text 由小写英文字母和 ' ' 组成
     * text 中至少包含一个单词
     *
     * solution:
     * 1.统计空格和单词的数量
     * 2.用公式 space.num / (word.num - 1) 来计算单词之间的空格数 和 最后单词的空格数
     * 3.最后执行拼接
     * 
     * @Date: 2021/6/9 10:53
     */
    public String reorderSpaces(String text) {
        StringBuilder res = new StringBuilder();
        int spaceCount = 0;
        int wordCount = 0;
        int nearCount = 0;
        int lastCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 32) spaceCount++;
        }
        String[] strArr = text.split(" ");
        for (String str : strArr) {
           if (!str.equals("")) {
               wordCount++;
           }
        }
        if (wordCount != 1) {
            nearCount = spaceCount / (wordCount - 1);
            lastCount = spaceCount % (wordCount - 1);
        }
        StringBuilder strNear = new StringBuilder();
        StringBuilder strLast = new StringBuilder();
        if (wordCount == 1) {
            for (int i = 0; i < spaceCount; i++) {
                strLast.append(" ");
            }
            for (String str : strArr) {
                if (!str.equals("")) {
                    res.append(str).append(strLast);
                    return new String(res);
                }
            }
        }
        for (int i = 0; i < nearCount; i++) {
            strNear.append(" ");
        }
        for (int i = 0; i < lastCount; i++) {
            strLast.append(" ");
        }
        for (String str : strArr) {
            if (!str.equals("")) {
                res.append(str);
                wordCount--;
                if (wordCount == 0) {
                    res.append(strLast);
                } else {
                    res.append(strNear);
                }
            }

        }
        return new String(res);
    }

    /**
     * @Description:
     * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 
     * sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 
     * 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
     *
     * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：sequence = "ababc", word = "ab"
     * 输出：2
     * 解释："abab" 是 "ababc" 的子字符串。
     * 示例 2：
     *
     * 输入：sequence = "ababc", word = "ba"
     * 输出：1
     * 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
     * 示例 3：
     *
     * 输入：sequence = "ababc", word = "ac"
     * 输出：0
     * 解释："ac" 不是 "ababc" 的子字符串。
     *  
     *
     * 提示：
     *
     * 1 <= sequence.length <= 100
     * 1 <= word.length <= 100
     * sequence 和 word 都只包含小写英文字母。
     *
     * solution:题意上说的是连续重复，我日了
     * @Date: 2021/6/10 10:20
     */
    public int maxRepeating(String sequence, String word) {
        int count = 0;
        StringBuilder sb = new StringBuilder(word);
        while(sequence.contains(sb)) {
            count++;
            sb.append(word);
        }
        return count;
    }

    /**
     * @Description:
     * 一个 句子 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。
     *
     * 我们可以给一个句子添加 从 1 开始的单词位置索引 ，并且将句子中所有单词 打乱顺序 。
     *
     * 比方说，句子 "This is a sentence" 可以被打乱顺序得到 "sentence4 a3 is2 This1" 
     * 或者 "is2 sentence4 This1 a3" 。
     * 给你一个 打乱顺序 的句子 s ，它包含的单词不超过 9 个，请你重新构造并得到原本顺序的句子。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "is2 sentence4 This1 a3"
     * 输出："This is a sentence"
     * 解释：将 s 中的单词按照初始位置排序，得到 "This1 is2 a3 sentence4" ，然后删除数字。
     * 示例 2：
     *
     * 输入：s = "Myself2 Me1 I4 and3"
     * 输出："Me Myself and I"
     * 解释：将 s 中的单词按照初始位置排序，得到 "Me1 Myself2 and3 I4" ，然后删除数字。
     *  
     *
     * 提示：
     *
     * 2 <= s.length <= 200
     * s 只包含小写和大写英文字母、空格以及从 1 到 9 的数字。
     * s 中单词数目为 1 到 9 个。
     * s 中的单词由单个空格分隔。
     * s 不包含任何前导或者后缀空格。
     *
     * solution: 这个简单，按 空格拆分 取最后一个数字为索引,放入hashMap 最后在按照数字顺序取出来 记录max
     * O(N) O(N)
     * @Date: 2021/6/10 10:44
     */
    public String sortSentence(String s) {
        int max = 0;
        String[] sentenceArr = s.split(" ");
        Map<Integer,String> map = new HashMap<>();
        for (String sentence : sentenceArr) {
            int tmp = sentence.charAt(sentence.length() - 1) - 48;
            String tmpStr = sentence.substring(0, sentence.length() - 1);
            max = Math.max(max,tmp);
            map.put(tmp,tmpStr);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= max; i++) {
            res.append(map.get(i));
            if (i != max) {
                res.append(" ");
            }
        }
        return new String(res);
    }

    /**
     * @Description:
     * 你将得到一个字符串数组 A。
     *
     * 每次移动都可以交换 S 的任意两个偶数下标的字符或任意两个奇数下标的字符。
     *
     * 如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是 特殊等价 的。
     *
     * 例如，S = "zzxy" 和 T = "xyzz" 是一对特殊等价字符串，因为可以先交换 S[0] 和 S[2]，
     * 然后交换 S[1] 和 S[3]，使得 "zzxy" -> "xzzy" -> "xyzz" 。
     *
     * 现在规定，A 的 一组特殊等价字符串 就是 A 的一个同时满足下述条件的非空子集：
     *
     * 该组中的每一对字符串都是 特殊等价 的
     * 该组字符串已经涵盖了该类别中的所有特殊等价字符串，
     * 容量达到理论上的最大值（也就是说，如果一个字符串不在该组中，那么这个字符串就 不会 与该组内任何字符串特殊等价）
     * 返回 A 中特殊等价字符串组的数量。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
     * 输出：3
     * 解释：
     * 其中一组为 ["abcd", "cdab", "cbad"]，因为它们是成对的特殊等价字符串，且没有其他字符串与这些字符串特殊等价。
     * 另外两组分别是 ["xyzz", "zzxy"] 和 ["zzyx"]。特别需要注意的是，"zzxy" 不与 "zzyx" 特殊等价。
     * 示例 2：
     *
     * 输入：["abc","acb","bac","bca","cab","cba"]
     * 输出：3
     * 解释：3 组 ["abc","cba"]，["acb","bca"]，["bac","cab"]
     *  
     *
     * 提示：
     *
     * 1 <= A.length <= 1000
     * 1 <= A[i].length <= 20
     * 所有 A[i] 都具有相同的长度。
     * 所有 A[i] 都只由小写字母组成。
     *
     * solution:
     * 1.暴力法则 O(N2)复杂度去 比较每两个元素看是否符合题意  写一个判断是否等价的方法
     * 2.判断等价方法 奇偶数拆分 ，排序比较是否相同
     * 没读懂这题目  真的辣鸡
     * @Date: 2021/6/10 11:02
     */
    public int numSpecialEquivGroups(String[] words) {
        Set<String> seen = new HashSet<>();
        for (String S: words) {
            int[] count = new int[52];
            for (int i = 0; i < S.length(); ++i)
                count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
            seen.add(Arrays.toString(count));
        }
        return seen.size();
    }

    private boolean verifyIsEqualStr(String str1, String str2) {
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();
        StringBuilder builder4 = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            if (i % 2 ==0 ) {
                builder1.append(str1.charAt(i));
                builder3.append(str2.charAt(i));
            } else {
                builder2.append(str1.charAt(i));
                builder4.append(str2.charAt(i));
            }
        }
        char[] arr1 = builder1.toString().toCharArray();
        char[] arr2 = builder2.toString().toCharArray();
        char[] arr3 = builder3.toString().toCharArray();
        char[] arr4 = builder4.toString().toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);
        Arrays.sort(arr4);
        return Arrays.equals(arr1, arr3) && Arrays.equals(arr2, arr4);
    }


    /**
     * @Description:
     * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
     *
     * 混合字符串 由小写英文字母和数字组成。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "dfa12321afd"
     * 输出：2
     * 解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
     * 示例 2：
     *
     * 输入：s = "abc1111"
     * 输出：-1
     * 解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 500
     * s 只包含小写英文字母和（或）数字。
     *
     * solution:  max  secondMax  O(N) O(1)
     * @Date: 2021/6/11 10:15
     */
    public int secondHighest(String s) {
        int max = -1;
        int secondMax = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = c - 48;
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (num > max) {
                    secondMax = max;
                    max = num;
                }
                if (num < max && num > secondMax) {
                    secondMax = num;
                }
            }
        }
        return secondMax;
    }

    /**
     * @Description:
     * 给你一个下标从 0 开始的字符串 s ，它的 偶数 下标处为小写英文字母，奇数 下标处为数字。
     *
     * 定义一个函数 shift(c, x) ，其中 c 是一个字符且 x 是一个数字，函数返回字母表中 c 后面第 x 个字符。
     *
     * 比方说，shift('a', 5) = 'f' 和 shift('x', 0) = 'x' 。
     * 对于每个 奇数 下标 i ，你需要将数字 s[i] 用 shift(s[i-1], s[i]) 替换。
     *
     * 请你替换所有数字以后，将字符串 s 返回。题目 保证 shift(s[i-1], s[i]) 不会超过 'z' 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "a1c1e1"
     * 输出："abcdef"
     * 解释：数字被替换结果如下：
     * - s[1] -> shift('a',1) = 'b'
     * - s[3] -> shift('c',1) = 'd'
     * - s[5] -> shift('e',1) = 'f'
     * 示例 2：
     *
     * 输入：s = "a1b2c3d4e"
     * 输出："abbdcfdhe"
     * 解释：数字被替换结果如下：
     * - s[1] -> shift('a',1) = 'b'
     * - s[3] -> shift('b',2) = 'd'
     * - s[5] -> shift('c',3) = 'f'
     * - s[7] -> shift('d',4) = 'h'
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 100
     * s 只包含小写英文字母和数字。
     * 对所有 奇数 下标处的 i ，满足 shift(s[i-1], s[i]) <= 'z' 。
     *
     * solution: 按照题意实现 OK toCharArray处理 O(N) O(N)
     * @Date: 2021/6/11 10:47
     */
    public String replaceDigits(String s) {
        char[] strArr = s.toCharArray();
        for (int i = 1; i < strArr.length; i = i + 2) {
            strArr[i] = (char)(strArr[i - 1] + (strArr[i] - 48));
        }
        return new String(strArr);
    }

    /**
     * @Description:
     * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，
     * 就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，
     * 因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。
     * 然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
     *
     * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。
     * 如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "YazaAay"
     * 输出："aAa"
     * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
     * "aAa" 是最长的美好子字符串。
     * 示例 2：
     *
     * 输入：s = "Bb"
     * 输出："Bb"
     * 解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
     * 示例 3：
     *
     * 输入：s = "c"
     * 输出：""
     * 解释：没有美好子字符串。
     * 示例 4：
     *
     * 输入：s = "dDzeE"
     * 输出："dD"
     * 解释："dD" 和 "eE" 都是最长美好子字符串。
     * 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 100
     * s 只包含大写和小写英文字母。
     *
     * solution:
     * 1.暴力法列出所有组合  判断是否有完美字符串 谁最长 谁最先出现
     * 2.暂时没想到法子 去评论区借鉴一个
     * @Date: 2021/6/11 10:56
     */
    public String longestNiceSubstring(String s) {
        int len = s.length();
        if (len < 2) {
            return "";
        }
        String res = "";
        for (int i = 2; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                String subString = s.substring(j, j + i);
                if (subString.length() > res.length() && isPerfectStr(subString)) {
                    res = subString;
                }
            }
        }
        return res;
    }

    //大小写 set 对比
    public boolean isPerfectStr(String str) {
        Set<Character> upperSet = new HashSet<>();
        Set<Character> lowerSet = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                upperSet.add(str.charAt(i));
            } else {
                lowerSet.add(Character.toUpperCase(str.charAt(i)));
            }
        }
        if (upperSet.size() != lowerSet.size()) {
            return false;
        }
        upperSet.removeAll(lowerSet);
        return upperSet.size() == 0;
    }

    /**
     * @Description:
     * 字母的 字母值 取决于字母在字母表中的位置，从 0 开始 计数。即，'a' -> 0、'b' -> 1、'c' -> 2，以此类推。
     *
     * 对某个由小写字母组成的字符串 s 而言，其 数值 就等于将 s 中每个字母的 字母值 按顺序 连接 并 转换 成对应整数。
     *
     * 例如，s = "acb" ，依次连接每个字母的字母值可以得到 "021" ，转换为整数得到 21 。
     * 给你三个字符串 firstWord、secondWord 和 targetWord ，
     * 每个字符串都由从 'a' 到 'j' （含 'a' 和 'j' ）的小写英文字母组成。
     *
     * 如果 firstWord 和 secondWord 的 数值之和 等于 targetWord 的数值，返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：firstWord = "acb", secondWord = "cba", targetWord = "cdb"
     * 输出：true
     * 解释：
     * firstWord 的数值为 "acb" -> "021" -> 21
     * secondWord 的数值为 "cba" -> "210" -> 210
     * targetWord 的数值为 "cdb" -> "231" -> 231
     * 由于 21 + 210 == 231 ，返回 true
     * 示例 2：
     *
     * 输入：firstWord = "aaa", secondWord = "a", targetWord = "aab"
     * 输出：false
     * 解释：
     * firstWord 的数值为 "aaa" -> "000" -> 0
     * secondWord 的数值为 "a" -> "0" -> 0
     * targetWord 的数值为 "aab" -> "001" -> 1
     * 由于 0 + 0 != 1 ，返回 false
     * 示例 3：
     *
     * 输入：firstWord = "aaa", secondWord = "a", targetWord = "aaaa"
     * 输出：true
     * 解释：
     * firstWord 的数值为 "aaa" -> "000" -> 0
     * secondWord 的数值为 "a" -> "0" -> 0
     * targetWord 的数值为 "aaaa" -> "0000" -> 0
     * 由于 0 + 0 == 0 ，返回 true
     *  
     *
     * 提示：
     *
     * 1 <= firstWord.length, secondWord.length, targetWord.length <= 8
     * firstWord、secondWord 和 targetWord 仅由从 'a' 到 'j' （含 'a' 和 'j' ）的小写英文字母组成。
     *
     * solution:
     * 1.根据题意 转换 firstWord secondWord targetWord 看是否满足题意
     * @Date: 2021/6/15 10:18
     */
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return convertAlpToInt(firstWord) + convertAlpToInt(secondWord) == convertAlpToInt(targetWord);
    }

    public int convertAlpToInt(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            builder.append(word.charAt(i) - 97);
        }
        return Integer.parseInt(builder.toString());
    }

    /**
     * @Description:
     * 如果一个字符串不含有任何重复字符，我们称这个字符串为 好 字符串。
     *
     * 给你一个字符串 s ，请你返回 s 中长度为 3 的 好子字符串 的数量。
     *
     * 注意，如果相同的好子字符串出现多次，每一次都应该被记入答案之中。
     *
     * 子字符串 是一个字符串中连续的字符序列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "xyzzaz"
     * 输出：1
     * 解释：总共有 4 个长度为 3 的子字符串："xyz"，"yzz"，"zza" 和 "zaz" 。
     * 唯一的长度为 3 的好子字符串是 "xyz" 。
     * 示例 2：
     *
     * 输入：s = "aababcabc"
     * 输出：4
     * 解释：总共有 7 个长度为 3 的子字符串："aab"，"aba"，"bab"，"abc"，"bca"，"cab" 和 "abc" 。
     * 好子字符串包括 "abc"，"bca"，"cab" 和 "abc" 。
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 100
     * s​​​​​​ 只包含小写英文字母。
     *
     * solution:
     * 1.截取三个 字符串，来判断是否是 好字符串  O(N) O(1)
     * @Date: 2021/6/15 10:28
     */
    public int countGoodSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            String tmpStr = s.substring(i, i + 3);
            String[] split = tmpStr.split("");
            if (!split[0].equals(split[1]) && !split[0].equals(split[2]) && !split[1].equals(split[2])) {
                count++;
            }
        }
        return count;
    }

    /**
     * @Description:
     * 给你一个字符串数组 words（下标 从 0 开始 计数）。
     *
     * 在一步操作中，需先选出两个 不同 下标 i 和 j，其中 words[i] 是一个非空字符串，
     * 接着将 words[i] 中的 任一 字符移动到 words[j] 中的 任一 位置上。
     *
     * 如果执行任意步操作可以使 words 中的每个字符串都相等，返回 true ；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：words = ["abc","aabc","bc"]
     * 输出：true
     * 解释：将 words[1] 中的第一个 'a' 移动到 words[2] 的最前面。
     * 使 words[1] = "abc" 且 words[2] = "abc" 。
     * 所有字符串都等于 "abc" ，所以返回 true 。
     * 示例 2：
     *
     * 输入：words = ["ab","a"]
     * 输出：false
     * 解释：执行操作无法使所有字符串都相等。
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] 由小写英文字母组成
     *
     * solution:
     * 1.求words数组的平均长度
     * 2.统计不和平均长度相等的word(长度相等判断是否相等 不相等直接pass)  word数量大于2直接pass
     * 3.word数量为0 或者 word数量为2 且 str2.removeAll(str1) 剩下两个相同字符串且 字符串不包含在str1内
     * @Date: 2021/6/15 10:45
     */
    public boolean makeEqual(String[] words) {
        int averageLen = 0;
        int totalLen = 0;
        int count = 0;
        String standardWord = "";
        String str1 = "";
        String str2 = "";
        //1.求words数组的平均长度
        for (String word : words) {
            totalLen += word.length();
        }
        if (totalLen % words.length != 0) return false;
        averageLen = totalLen / words.length;
        for (String word : words) {
            if (word.length() == averageLen) {
                standardWord = word;
                break;
            }
        }
        if (standardWord.equals("")) return false;
        //2.统计不和平均长度相等的word(长度相等判断是否相等 不相等直接pass)
        for (String word : words) {
            if (word.length() == averageLen && !word.equals(standardWord)) {
                return false;
            }
            if (word.length() != averageLen) {
                if (str1.equals("")) {
                    str1 = word;
                } else {
                    str2 = word;
                }
                count++;
                if (count > 2) return false;
            }
        }
        if (count == 0) return true;
        //3.word数量为0 或者 word数量为2 且 str2.removeAll(str1) 剩下两个相同字符串且 字符串不包含在str1内
        if (str2.length() < str1.length()) {
            String tmp = str2;
            str2 = str1;
            str1 = tmp;
        }
        for (int i = 0; i < str1.length(); i++) {
            str2 = str2.replace(String.valueOf(str1.charAt(i)), "");
        }
        return str2.charAt(0) == str2.charAt(1) && !str1.contains(String.valueOf(str2.charAt(0)));
    }

    public boolean isNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public int getAge(Student student) {
        return Optional.of(student).map(Student::getAge).orElse(null);
    }

    @Test
    public void testStringAlp() {
       Consumer c = System.out::println;

       Function<Integer, Integer> f1 = o -> o + o;
       Function<Integer, Integer> f2 = o -> o * o;
       c.accept(f1.andThen(f2).apply(3));

       Student student = new Student(12, "小红");
        System.out.println(1f == 0.99999999f);
    }

    static class Student{

        private int age;

        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
