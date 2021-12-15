package Leetcode.HashTable;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @Author maoXin
 * @Description
 * @Date 10:13 2021/4/1
 */
public class HashAlp {

    private final static HashAlp instance = new HashAlp();

    /**
     * @Description:
     * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。
     * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
     *
     * 示例 1:
     *
     * 输入: candies = [1,1,2,2,3,3]
     * 输出: 3
     * 解析: 一共有三种种类的糖果，每一种都有两个。
     *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
     * 示例 2 :
     *
     * 输入: candies = [1,1,2,3]
     * 输出: 2
     * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
     * 注意:
     *
     * 数组的长度为[2, 10,000]，并且确定为偶数。
     * 数组中数字的大小在范围[-100,000, 100,000]内。
     *
     * solution: 统计多少种类 count，大于 length/2 返回 length/2  否则返回  count 1.桶排序 2.用set
     * O(N) O(100000)
     * @Date: 2021/4/1 10:16
     */
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        return Math.min(set.size(),candyType.length / 2);
    }

    /**
     * @Description:
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     *  
     *
     * 示例：
     *
     * s = "leetcode"
     * 返回 0
     *
     * s = "loveleetcode"
     * 返回 2
     *  
     *
     * 提示：你可以假定该字符串只包含小写字母。
     *
     * solution: 1.hashMap记录字符的 索引和数量,返回数量为1 的第一个字符的索引
     * @Date: 2021/4/1 10:44
     */
    /*public int firstUniqChar(String s) {
        Map<Character, int[]> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                int[] ints = map.get(chars[i]);
                ints[0] += 1;
                map.put(chars[i],ints);
            } else {
                map.put(chars[i],new int[]{1,i});
            }
        }
        for (Map.Entry<Character,int[]> entry : map.entrySet()) {
            if (entry.getValue()[0] == 1) {
                return entry.getValue()[1];
            }
        }
        return -1;
    }*/
    //记录重复的元素 再取第一个
    /*public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        int[] arr = new int[128];
        for (int i = 0; i < chars.length; i++) {
            arr[chars[i]]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 2) {
                list.add((char)i);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (!list.contains(chars[i])) {
                return i;
            }
        }
        return -1;
    }*/
    //string api
   /* public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (s.indexOf(chars[i]) == s.lastIndexOf(chars[i]))
                return i;
        }
        return -1;
    }*/

    /**
     * @Description:
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     *
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     *
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
     * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     *
     * 示例 1:
     *
     * 输入：s = "egg", t = "add"
     * 输出：true
     * 示例 2：
     *
     * 输入：s = "foo", t = "bar"
     * 输出：false
     * 示例 3：
     *
     * 输入：s = "paper", t = "title"
     * 输出：true
     *  
     *
     * 提示：
     *
     * 可以假设 s 和 t 长度相同。
     *
     * solution:
     * 1.用hash表存储映射关系,检查是否符合题意 map里面存s t的char O(N) O(1)
     * 2.s t 计数 + 排序 ，最后对比list是否相等 O (NlogN) O(N)
     *
     * @Date: 2021/4/1 13:36
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (map.containsKey(s.charAt(i))) {
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
    }

    /**
     * @Description:
     *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 
     *
     * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     *
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     *
     * 示例 1:
     *
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     * 示例 2:
     *
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     * 注意:
     *
     * S 和 J 最多含有50个字母。
     *  J 中的字符不重复。
     *
     * solution: traver S if J.contains(S.charAt.(i)) count++ return count
     * O(N) O(1)
     * @Date: 2021/4/2 9:57
     */
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < jewels.length(); i++) {
            list.add(jewels.charAt(i));
        }
        for (int i = 0; i < stones.length(); i++) {
            if (list.contains(stones.charAt(i))) {
                count++;
            }
        }
        /*for (int i = 0; i < stones.length(); i++) {
            if (jewels.contains(String.valueOf(stones.charAt(i)))) {
                count++;
            }
        }*/
        return count;
    }

    /**
     * @Description:
     * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
     *
     * 美式键盘 中：
     *
     * 第一行由字符 "qwertyuiop" 组成。
     * 第二行由字符 "asdfghjkl" 组成。
     * 第三行由字符 "zxcvbnm" 组成。
     *
     * 示例 1：
     *
     * 输入：words = ["Hello","Alaska","Dad","Peace"]
     * 输出：["Alaska","Dad"]
     * 示例 2：
     *
     * 输入：words = ["omk"]
     * 输出：[]
     * 示例 3：
     *
     * 输入：words = ["adsdf","sfd"]
     * 输出：["adsdf","sfd"]
     *
     * solution: traver the words  judge the each element ,
     * whether the element is combine by the same given words
     * 第一个字符确定所在的 字符串行列，判断该行是否能完全打印 能的话添加 不能的话break
     * O(N*M) N words array.length M word.length O(N) res.size place
     * @Date: 2021/4/2 10:13
     */
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJK";
        String s3 = "zxcvbnmZXCVBNM";
        int index = 0;
        String tmp = "";
        String[] res = new String[words.length];
        for (String word : words) {
            char c = word.charAt(0);
            boolean flag = true;
            if (s1.contains(String.valueOf(c))) {
                tmp = s1;
            } else if (s2.contains(String.valueOf(c))) {
                tmp = s2;
            } else {
                tmp = s3;
            }
            for (int i = 1; i < word.length(); i++) {
                if (!tmp.contains(String.valueOf(word.charAt(i)))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res[index++] = word;
            }
        }
       return Arrays.copyOf(res,index);
    }

    /**
     * @Description:
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     *
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     *
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,2,2,5,2,3,7]
     * 输出：5
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     * 示例 2：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [1,1,1,1]
     * 输出：0
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 2 * 104
     * -109 <= nums[i] <= 109 这是10的8次方 我日
     *
     * solutioin:
     * 先统计个数，找 相减==1的个数和最大的，然后相减 map
     * hashMap可以直接查找 key 和 key + 1的个数
     * O(N) O(N)
     * @Date: 2021/4/2 10:59
     */
    public int findLHS(int[] nums) {
        int res  = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                res = Math.max(map.get(key) + map.get(key + 1),res);
            }
        }

        return res;
    }

    /**
     * @Description:
     * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
     *
     * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。 
     *
     * 示例 1：
     *
     * 输入：text = "nlaebolko"
     * 输出：1
     * 示例 2：
     *
     * 输入：text = "loonbalxballpoon"
     * 输出：2
     * 示例 3：
     *
     * 输入：text = "leetcode"
     * 输出：0
     *  
     *
     * 提示：
     *
     * 1 <= text.length <= 10^4
     * text 全部由小写英文字母组成
     *
     * solution: 统计 balloon在text中 有多少个
     * O(N + M) O(N)
     * @Date: 2021/4/2 11:37
     */
    public int maxNumberOfBalloons(String text) {
        int count = 0;
        int min = Integer.MAX_VALUE;
        String str = "balloon";
        int[] arr = new int[128];
        int length = text.length();
        for (int i = 0; i< length; i++) {
            char c = text.charAt(i);
            if (str.contains(String.valueOf(c))) {
                arr[c]++;
            }
        }
        min = arr['b'];
        min = Math.min(min,arr['a']);
        min = Math.min(min,arr['n']);
        min = Math.min(min,arr['l'] / 2);
        min = Math.min(min,arr['o'] / 2);
        return min;
    }

    /**
     * @Description:
     * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
     *
     * 返回重复了 N 次的那个元素。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[1,2,3,3]
     * 输出：3
     * 示例 2：
     *
     * 输入：[2,1,2,5,3,2]
     * 输出：2
     * 示例 3：
     *
     * 输入：[5,1,5,2,5,3,5,4]
     * 输出：5
     *  
     *
     * 提示：
     *
     * 4 <= A.length <= 10000
     * 0 <= A[i] < 10000
     * A.length 为偶数
     *
     * solution:
     * 方法 2：比较
     * 想法和算法
     *
     * 一旦找到一个重复元素，那么一定就是答案。我们称这个答案为主要元素。
     *
     * 考虑所有长度为 4 的子序列，在子序列中一定至少含有两个主要元素。
     *
     * 这是因为：
     *
     * 长度为 2 的子序列中都是主要元素，或者；
     * 每个长度为 2 的子序列都恰好含有 1 个主要元素，这意味着长度为 4 的子序列一定含有 2 个主要元素。
     * 因此，只需要比较所有距离为 1，2 或者 3 的邻居元素即可。
     *
     * 官方思路：
     * 1。投票法 初始化众数为第1个 count 1  遍历，不是众数 -1 否则 +1 ，count==0更新众数 最后返回众数
     * 特殊情况 1个元素也满足  对半分的情况 也满足 TODO 这个要大于 N/2才能用
     * O(N) O(1)
     *
     *2.排序 取 A[n/2]就是众数
     * O(NlogN) O(NlogN)
     *
     * @Date: 2021/4/2 13:49
     */
    /*public int repeatedNTimes(int[] A) {
        int max  = 0;
        for (int i : A) {
            max = Math.max(max,i);
        }
        int[] arr = new int[max + 1];
        for (int i : A) {
            arr[i]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == A.length / 2) {
                return i;
            }
        }
        return 0;
    }*/
    public int repeatedNTimes(int[] A) {
        for (int k = 1; k <= 3; ++k)
            for (int i = 0; i < A.length - k; ++i)
                if (A[i] == A[i+k])
                    return A[i];

        return 0;
    }

    public int majorityElement(int[] nums) {
        int count = 1;
        int candidate = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            count += (nums[i] == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * @Description:
     * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，
     * 其中 second 紧随 first 出现，third 紧随 second 出现。
     *
     * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
     * 输出：["girl","student"]
     * 示例 2：
     *
     * 输入：text = "we will we will rock you", first = "we", second = "will"
     * 输出：["we","rock"]
     *  
     *
     * 提示：
     *
     * 1 <= text.length <= 1000
     * text 由一些用空格分隔的单词组成，每个单词都由小写英文字母组成
     * 1 <= first.length, second.length <= 10
     * first 和 second 由小写英文字母组成
     *
     * solution:
     * 遍历一次 text.toCharArray  first + second ++2; 判断是否溢出
     *这个简单 都把first和second提供好了
     * O(N) O(N)
     * @Date: 2021/4/2 14:16
     */
    public String[] findOcurrences(String text, String first, String second) {
        String[] s = text.split(" ");
        String[] res = new String[s.length];
        int index = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i].equals(second) && s[i - 1].equals(first)) {
                if (i + 1 < s.length) {
                    res[index++] = s[i + 1];
                }
            }
        }
        return Arrays.copyOf(res,index);
    }

    /**
     * @Description:
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
     * 导致集合 丢失了一个数字 并且 有一个数字重复 。
     *
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     *
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2,4]
     * 输出：[2,3]
     * 示例 2：
     *
     * 输入：nums = [1,1]
     * 输出：[1,2]
     *  
     *
     * 提示：
     *
     * 2 <= nums.length <= 104
     * 1 <= nums[i] <= 104
     *
     * solution:
     * 1.找重复元素
     * 2.找缺失的值
     * 桶排序
     * O(N) O(N)
     * @Date: 2021/4/2 14:26
     */
    public int[] findErrorNums(int[] nums) {
        int[] arr = new int[nums.length + 1];
        for (int num : nums) {
            arr[num]++;
        }
        int a = 0 , b = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                //缺失元素
                a = i;
            }
            if (arr[i] == 2) {
                //重复元素
                b = i;
            }
        }
        return new  int[]{b,a};
    }

    /**
     * @Description:
     * 给定一个字符串牌照 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
     *
     * 如果单词列表（words）中的一个单词包含牌照（licensePlate）中所有的字母，
     * 那么我们称之为 补全词 。在所有完整词中，最短的单词我们称之为 最短补全词 。
     *
     * 单词在匹配牌照中的字母时要：
     *
     * 忽略牌照中的数字和空格。
     * 不区分大小写，比如牌照中的 "P" 依然可以匹配单词中的 "p" 字母。
     * 如果某个字母在牌照中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
     * 例如：licensePlate = "aBc 12c"，那么它由字母 'a'、'b' （忽略大写）和两个 'c' 。可
     * 能的 补全词 是 "abccdef"、"caaacab" 以及 "cbca" 。
     *
     * 题目数据保证一定存在一个最短补全词。
     * 当有多个单词都符合最短补全词的匹配条件时取单词列表中最靠前的一个。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
     * 输出："steps"
     * 说明：最短补全词应该包括 "s"、"p"、"s" 以及 "t"。在匹配过程中我们忽略牌照中的大小写。
     * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
     * "steps" 包含 "t"、"p" 和两个 "s"。
     * "stripe" 缺一个 "s"。
     * "stepple" 缺一个 "s"。
     * 因此，"steps" 是唯一一个包含所有字母的单词，也是本样例的答案。
     *
     * 示例 2：
     *
     * 输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
     * 输出："pest"
     * 说明：存在 3 个包含字母 "s" 且有着最短长度的补全词，"pest"、"stew"、和 "show" 三者长度相同，但我们返回最先出现的补全词 "pest" 。
     *
     * solution:
     * 1.确定licensePlate由小写字母组成的 关键词
     * 2.遍历words删选符合条件的word 并记录长度和对应的word
     * 3.找出符合题意的关键词 返回
     * O(N*M) O(N)
     * @Date: 2021/4/6 10:07
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        StringBuilder str = new StringBuilder();
        int length = licensePlate.length();
        int shortest = Integer.MAX_VALUE;
        Map<Integer,List<String>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (licensePlate.charAt(i) >= 97 && licensePlate.charAt(i) <= 122) {
                str.append(licensePlate.charAt(i));
            } else if (licensePlate.charAt(i) >= 65 && licensePlate.charAt(i) <= 90) {
                str.append(String.valueOf(licensePlate.charAt(i)).toLowerCase());
            }
        }
        String keyWords = str.toString();
        for (String word : words) {
            if (verify(keyWords, word)) {
                shortest = Math.min(shortest, word.length());
                List<String> value = map.getOrDefault(word.length(), new ArrayList<>());
                value.add(word);
                map.put(word.length(), value);
            }
        }
        return map.get(shortest).get(0);
    }

    public boolean verify(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toLowerCase().toCharArray();
        int[] arr1 = new int[128];
        int[] arr2 = new int[128];
        for (char c : chars1) {
            arr1[c]++;
        }
        for (char c : chars2) {
            arr2[c]++;
        }
        for (char c : chars1) {
            if (arr2[c] < arr1[c]) {
                return false;
            }
        }
        return true;
    }


    /**
     * @Description:
     * 一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。作为顶级域名，常用的有"com"，
     * 下一级则有"leetcode.com"，最低的一级为"discuss.leetcode.com"。当我们访问域名"discuss.leetcode.com"时，
     * 也同时访问了其父域名"leetcode.com"以及顶级域名 "com"。
     *
     * 给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.com"。
     *
     * 接下来会给出一组访问次数和域名组合的列表cpdomains 。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。
     *
     * 示例 1:
     * 输入:
     * ["9001 discuss.leetcode.com"]
     * 输出:
     * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
     * 说明:
     * 例子中仅包含一个网站域名："discuss.leetcode.com"。按照前文假设，子域名"leetcode.com"和"com"都会被访问，所以它们都被访问了9001次。
     * 示例 2
     * 输入:
     * ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
     * 输出:
     * ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
     * 说明:
     * 按照假设，会访问"google.mail.com" 900次，"yahoo.com" 50次，"intel.mail.com" 1次，"wiki.org" 5次。
     * 而对于父域名，会访问"mail.com" 900+1 = 901次，"com" 900 + 50 + 1 = 951次，和 "org" 5 次。
     * 注意事项：
     *
     *  cpdomains 的长度小于 100。
     * 每个域名的长度小于100。
     * 每个域名地址包含一个或两个"."符号。
     * 输入中任意一个域名的访问次数都小于10000。
     *
     * solution: 用hashMap统计所有的出现的域名次数   遍历整个 cpdomains
     * 1.按规则拆分 cpdomain
     * 2.map。put
     * 3.map转List  O(N*M) O(N)
     * @Date: 2021/4/6 10:47
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
       Map<String,Integer> map = new HashMap<>();
        for (String domain : cpdomains) {
            fillIpMap(domain,map);
        }
        for (String key : map.keySet()) {
            res.add(map.get(key) + " "+ key);
        }
        return res;
    }

    private void fillIpMap(String domain,Map<String,Integer> map) {
        String[] ipArray = domain.split(" ");
        String[] detailArray = ipArray[1].split("\\.");
        List<String> tmpList = new ArrayList<>();
        if (detailArray.length == 3) {
            tmpList.add(detailArray[2]);
            tmpList.add(detailArray[1]+"."+detailArray[2]);
            tmpList.add(detailArray[0]+"."+detailArray[1]+"."+detailArray[2]);
        } else {
            tmpList.add(detailArray[1]);
            tmpList.add(detailArray[0]+"."+detailArray[1]);
        }
        int num = Integer.parseInt(ipArray[0]);
        for (String s : tmpList) {
            map.put(s,map.getOrDefault(s,0) + num);
        }
    }


    /**
     * @Description:
     * 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
     *
     * 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
     *
     * 返回所有不常用单词的列表。
     *
     * 您可以按任何顺序返回列表。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：A = "this apple is sweet", B = "this apple is sour"
     * 输出：["sweet","sour"]
     * 示例 2：
     *
     * 输入：A = "apple apple", B = "banana"
     * 输出：["banana"]
     *  
     *
     * 提示：
     *
     * 0 <= A.length <= 200
     * 0 <= B.length <= 200
     * A 和 B 都只包含空格和小写字母。
     *
     * solution:
     * 统计A B 中所有单词，返回次数等于1 的 map
     * O(N) O(N)
     * @Date: 2021/4/7 13:42
     */
    public String[] uncommonFromSentences(String A, String B) {
        List<String> list = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        String[] s1 = A.split(" ");
        String[] s2 = B.split(" ");
        for (String s : s1) {
            map.put(s,map.getOrDefault(s,0) + 1);
        }
        for (String s : s2) {
            map.put(s,map.getOrDefault(s,0) + 1);
        }
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                list.add(key);
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * @Description:
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     *
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。 
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     * 示例 2：
     *
     * 输入：arr = [1,2]
     * 输出：false
     * 示例 3：
     *
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     *  
     * 提示：
     *
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     *
     * solution:
     * 计数，看计数后的list有没重复的 重复返回false 否则返回true
     * bucket 两次计数 看是否有大于2的
     * 两次计数是真的顶 真的秒 第一次计数统计arr元素出现次数 第二次计数统计次数有没有重复的 高手
     * O(N) O(N)
     * @Date: 2021/4/7 14:06
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] tmpArr = new int[2001];
        int[] tmpArr2 = new int[1001];
        for (int i : arr) {
            tmpArr[i + 1000]++;
        }
        for (int i : tmpArr) {
            if (i != 0) {
                tmpArr2[i]++;
            }
        }
        for (int i : tmpArr2) {
            if (i > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description:
     * 在字符串 s 中找出第一个只出现一次的字符。
     * 如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * 示例:
     *
     * s = "abaccdeff"
     * 返回 "b"
     *
     * s = ""
     * 返回 " "
     *  
     *
     * 限制：
     *
     * 0 <= s 的长度 <= 50000
     *
     * solution:
     * 桶排序 找 ==1的  找第一个用 hashMap
     * O(N) O(M) M = 32
     * @Date: 2021/4/7 14:15
     */
    public char firstUniqChar(String s) {
        int[] arr = new int[32];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            arr[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i) - 97] == 1) {
                return s.charAt(i);
            }
        }
        return (char)32;
    }

    /**
     * @Description:
     * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
     *
     * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，
     * 只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
     *
     * 示例 1：
     *
     * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * 输出：true
     * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
     * 示例 2：
     *
     * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
     * 输出：false
     * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
     * 示例 3：
     *
     * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
     * 输出：false
     * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，
     * 因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * order.length == 26
     * 在 words[i] 和 order 中的所有字符都是英文小写字母。
     *
     * solution:
     * 依次对 words中的相邻元素比较，看出现的先后顺序是否满足题意
     * O(N * M * K) O(N2)
     * @Date: 2021/4/8 10:29
     */
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 1; i < words.length; i++) {
            if (!validSort(order,words[i - 1],words[i])) {
                return false;
            }
        }
        return true;
    }

    // order.toCharArray  double point   str1 -->pre  str2-->next
    private boolean validSort(String order, String str1,String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        if (str1.contains(str2)) {
            return true;
        }
        //这就是第二种特殊情况  Apple 和App 直接return false
        if (str2.contains(str1)) {
            return false;
        }
        char[] chars = order.toCharArray();
        int len1 = str1.length();
        int len2 = str2.length();
        int len = Math.min(len1,len2);
        int index = 0;
        for (char aChar : chars) {
            if ((aChar == str1.charAt(index) || aChar == str2.charAt(index)) && index < len) {
                if (aChar == str1.charAt(index) && aChar == str2.charAt(index)) {
                    index++;
                } else if (aChar == str2.charAt(index)) {
                    return false;
                } else {
                    return true;
                }
            }
            if (index == len) {
                if (len2 == len) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @Description:
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     *
     * 示例1:
     *
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     * 示例 2:
     *
     * 输入:pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     * 示例 3:
     *
     * 输入: pattern = "aaaa", str = "dog cat cat dog"
     * 输出: false
     * 示例 4:
     *
     * 输入: pattern = "abba", str = "dog dog dog dog"
     * 输出: false
     * 说明:
     * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
     *
     * solution:
     * 1.pattern put each character to a map,
     * 隐含条件  pattern.length = str.split(" ") .length;
     * 2.依次做检验  O(N) O(N)
     * @Date: 2021/4/8 14:13
     */
    public boolean wordPattern(String pattern, String s) {
        char[] chars = pattern.toCharArray();
        String[] s1 = s.split(" ");
        if (chars.length != s1.length) {
            return false;
        }
        HashMap<Character,String> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                //已存在的键值对 和目前的映射对应不上
                if (!s1[i].equals(map.get(chars[i]))) {
                    return false;
                }
            } else {
                //key value 都校验 value不能被其他的key所占取
                if (map.containsValue(s1[i])) {
                    return false;
                }
                map.put(chars[i],s1[i]);
            }
        }
        return true;
    }

    @Test
    public void testHashAlp() {
        String[] arr = new String[] {"hello","leetcode"};
        Assert.assertEquals(Boolean.TRUE, instance.isAlienSorted(arr, "hlabcdefgijkmnopqrstuvwxyz"));
    }

}
