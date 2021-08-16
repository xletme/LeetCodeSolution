package Leetcode.DoublePoint;

import org.junit.Test;

/**
 * @Author maoXin
 * @Description
 * @Date 13:46 2021/4/30
 */
public class DoublePointAlp {

    private static final DoublePointAlp instance = new DoublePointAlp();

    /**
     * @Description:
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
     *
     * solution:
     * 1.处理字符串 s ，只保留字母好数字字符  2.正序和反序遍历 3.判断是否相同 返回 是否是回文
     * 双指针  正反序遍历，根据ascall码保留字母和数字  看 是否相同，判断是否是回文
     * @Date: 2021/4/30 13:47
     */
    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (isUseful(s.charAt(start)) && isUseful(s.charAt(end))) {
                //做判断
                if (s.charAt(start) >= 48 && s.charAt(start) <= 57) {
                    // 判断数字
                    if (s.charAt(start) == s.charAt(end)) {
                        start++;
                        end--;
                        continue;
                    } else {
                        return false;
                    }
                } else {
                    //判断字母
                    if (s.charAt(start) == s.charAt(end) || Math.abs(s.charAt(start) - s.charAt(end)) == 32) {
                        start++;
                        end--;
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            if (!isUseful(s.charAt(start))) {
                start++;
            }
            if (!isUseful(s.charAt(end))) {
                end--;
            }
        }
        return true;
    }

    /**
     * @Description:
     * 你有多久没有看过那片海  你到现在对自己究竟多明白 总是不服输 永远要比别人快 在你前方是否有你要的未来
     *
     * 想到我们的过去都让人感慨 希望所有好朋友都能站起来   还有你曾经疯狂爱上的女孩 再过几年 是不是依旧难以忘怀
     * 可是 Andy 活着是不需道理 谁都可能 暂时的失去勇气 外面不安的世界，骚动的心情 不能熄灭曾经你拥有炙热的心
     *
     * 我是真的不会表达我的爱 却很在乎每个人对我的期待 平凡的角色 站在小小的舞台
     * 我要那么勇敢的说出来
     *
     * 想到我们的过去都让人感慨 希望所有好朋友都能站起来  还有你曾经疯狂爱上的女孩 再过几年 是不是依旧难以忘怀
     * 可是 Andy 活着是不需道理 谁都可能 暂时的失去勇气 外面不安的世界，骚动的心情 不能熄灭曾经你拥有炙热的心
     * 我是真的不会表达我的爱 却很在乎每个人对我的期待  平凡的角色 站在小小的舞台
     * 我要那么勇敢的说出来
     *
     * 外面不安的世界，骚动的心情 不能熄灭曾经拥有炙热的心
     * 可是Andy wow  Andy wow 外面不安的世界，骚动的心情
     * 不能熄灭曾经你拥有炙热的心
     * @Date: 2021/4/30 14:25
     */
    public boolean isUseful(char c) {
        if (c >= 65 && c <= 90) {
            return true;
        }
        if (c >= 97 && c <= 122) {
            return true;
        }
        if (c >= 48 && c <= 57) {
            return true;
        }
        return false;
    }

    /**
     * @Description:
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     *
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *
     * solution: define a start end ,start++ end-- loop break condition (start > end) ,
     * each time swap the both two element
     * @Date: 2021/5/8 15:34
     */
    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * @Description:
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     *
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     *
     * 示例 1：
     *
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     * 示例 2：
     *
     * 输入：name = "saeed", typed = "ssaaedd"
     * 输出：false
     * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     * 示例 3：
     *
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     * 示例 4：
     *
     * 输入：name = "laiden", typed = "laiden"
     * 输出：true
     * 解释：长按名字中的字符并不是必要的。
     *  
     *
     * 提示：
     *
     * name.length <= 1000
     * typed.length <= 1000
     * name 和 typed 的字符都是小写字母。
     *
     * solution: 官方理解 1.分三种情况 双指针  i j 分别代表指针
     * name[i] == typed[j] i++,j++;
     * type[j - ] = typed[j] j++ 前提  j > 0
     * else  return false
     * 最后判断  i == name.length  其实是看 name[i] == type[j]是否实现了 name.length 长度次
     * 跳出循环条件  j < typed.length  && i < name.length
     * @Date: 2021/5/8 15:46
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j - 1) == typed.charAt(j)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }

    /**
     * @Description:
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * 示例 1：
     *
     * 输入："hello"
     * 输出："holle"
     * 示例 2：
     *
     * 输入："leetcode"
     * 输出："leotcede"
     *  
     *
     * 提示：
     *
     * 元音字母不包含字母 "y" 。
     *
     * solution: 翻转 a e i o u
     * 1.统计元音个数
     * 2.双指针分别遍历首位替换
     * @Date: 2021/5/10 10:35
     */
    public String reverseVowels(String s) {
        int start = 0;
        int end = s.length() - 1;
        char pre = 0;
        char post = 0;
        char[] vowelsArray = s.toCharArray();
        while (start <= end) {
            int defineCount = 0;
            pre = vowelsArray[start];
            post = vowelsArray[end];
            if (containVowels(pre)) {
                defineCount++;
            }
            if (containVowels(post)) {
                defineCount++;
            }
            if (defineCount == 2) {
                char tmp = vowelsArray[start];
                vowelsArray[start] = vowelsArray[end];
                vowelsArray[end] = tmp;
                start++;
                end--;
                continue;
            }
            if (!containVowels(pre)) {
                start++;
            }
            if (!containVowels(post)) {
                end--;
            }
        }
       return new String(vowelsArray);
    }

    public boolean containVowels(char c) {
        return c == 'a' || c == 'o' || c == 'e' || c == 'i' || c == 'u' || c == 'A' ||
                c == 'O' || c == 'E' || c == 'I' || c == 'U';
    }

    /**
     * @Description:
     * 给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
     *
     * 子数组是数组中的一个连续数字序列。
     *
     * 已知子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，若对所有 i（l <= i < r），numsi < numsi+1 都成立，则称这一子数组为 升序 子数组。注意，大小为 1 的子数组也视作 升序 子数组。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [10,20,30,5,10,50]
     * 输出：65
     * 解释：[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
     * 示例 2：
     *
     * 输入：nums = [10,20,30,40,50]
     * 输出：150
     * 解释：[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。
     * 示例 3：
     *
     * 输入：nums = [12,17,15,13,10,11,12]
     * 输出：33
     * 解释：[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。
     * 示例 4：
     *
     * 输入：nums = [100,10,1]
     * 输出：100
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     *
     * solution: 1.定义一个最大值 max  找升序数组 求和 如果不是升序数组了 则替换max O(N)
     * @Date: 2021/5/10 17:03
     */
    public int maxAscendingSum(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                sum += nums[i];
            } else {
                max = Math.max(max,sum);
                sum = nums[i];
            }
        }
        max = Math.max(max,sum);
        return max;
    }

    @Test
    public void testDoublePointAlp() {
        System.out.println(instance.reverseVowels("hello"));
    }
}
