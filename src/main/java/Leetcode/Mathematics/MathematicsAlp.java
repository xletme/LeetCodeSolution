package Leetcode.Mathematics;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author maoXin
 * @Description
 * @Date 11:17 2021/4/12
 */
public class MathematicsAlp {

    private static final MathematicsAlp instance = new MathematicsAlp();

    /**
     * @Description:
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     * 示例 1:
     *
     * 输入: a = "11", b = "1"   3+1 =4
     * 输出: "100"
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"  10 + 11 = 21
     * 输出: "10101" 21
     * 提示：
     *
     * 每个字符串仅由字符 '0' 或 '1' 组成。
     * 1 <= a.length, b.length <= 10^4
     * 字符串如果不是 "0" ，就都不含前导零。
     *
     * solution:
     * 1.a+b转十进制 算结果，再转二进制
     * 2.直接进行二进制加减(位运算  找进位左移，异或找到不进位 当 不进位的时候 就结束)  二进制转10进制 10二进制相加
     * 3. a b先转 CharArray  每一位相加   存一个pre是否进位 每次取 a b的对应位进行计算
     * O(N) O(N)
     * @Date: 2021/4/12 11:18
     */
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int len1 = a.length();
        int len2 = b.length();
        int len = Math.max(len1,len2);
        int curA = 0;
        int curB = 0;
        int pre = 0;
        String str = "";
        for (int i = 0 ; i < len; i++) {
            if (len1 - 1 - i >=0 ) {
                curA = a.charAt(len1 - 1 - i) == '1' ? 1 : 0;
            } else {
                curA = 0;
            }
            if (len2 - 1 - i >= 0) {
                curB = b.charAt(len2 - 1 - i) == '1' ? 1 : 0;
            } else {
                curB = 0;
            }
            str = curA + curB + pre >= 2 ?
                    String.valueOf((curA + curB + pre) % 2) :
                    String.valueOf(curA + curB + pre);
            result.append(str);
            pre = curA + curB + pre >= 2 ? 1 : 0;
        }
        if (pre == 1) {
            result.append("1");
        }
        return result.reverse().toString();
    }

    /**
     * @Description:
     * a^b求不进位 (a & b)求进位 b!=0不进位则返回a
     * O(N) O(1)
     * @Date: 2021/12/21 10:50
     */
    public long twoBinaryAdd(long a, long b) {
        long sum = 0;
        long c = 0;
        while (b != 0) {
            sum  = a ^ b;
            c = (a & b) << 1;
            a = sum;
            b = c;
        }
        return a;
    }

    /**
     * @Description:
     * sum += s.charAt(i) * 2的n次方；
     * O(N) O(1)
     * @Date: 2021/12/21 10:52
     */
    public long binary2Decimal(String str) {
      double sum = 0;
      int length = str.length();
      for (int i = length - 1; i >= 0; i--) {
          sum = str.charAt(i) == '1' ?  sum + Math.pow(2,length - 1 - i) : sum;
      }
      return (long)sum;
    }

    /**
     * @Description:
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * 示例:
     *
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     * 进阶:
     * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     *
     * solution:
     * @Date: 2021/4/14 13:47
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

    /**
     * @Description:
     * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
     *
     * 给定一个数字 n，找出可形成完整阶梯行的总行数。
     *
     * n 是一个非负整数，并且在32位有符号整型的范围内。
     *
     * 示例 1:
     *
     * n = 5
     *
     * 硬币可排列成以下几行:
     * ¤
     * ¤ ¤
     * ¤ ¤
     *
     * 因为第三行不完整，所以返回2.
     * 示例 2:
     *
     * n = 8
     *
     * 硬币可排列成以下几行:
     * ¤
     * ¤ ¤
     * ¤ ¤ ¤
     * ¤ ¤
     *
     * 因为第四行不完整，所以返回3.
     *
     * solution:
     * 伏波拉契数列取上一个数和下一个数中间为n的，返回上一个数
     * @Date: 2021/4/14 14:02
     */
    public int arrangeCoins(int n) {
        long sum = 0;
        long k = 0;
        while (sum <= n) {
            k++;
            sum = k * (k + 1) / 2;
        }
        return (int)k - 1;
    }


    /**
     * @Description:
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *  
     *
     * 示例 1：
     *
     * 输入：x = 123
     * 输出：321
     * 示例 2：
     *
     * 输入：x = -123
     * 输出：-321
     * 示例 3：
     *
     * 输入：x = 120
     * 输出：21
     * 示例 4：
     *
     * 输入：x =
     *
     * solution:
     * 1.x转string后进行翻转  不在整数范围返回0 a x.toString  b.reverse c.compare 是否在范围内
     * 时间复杂度  O(N *M) N是x的位数 M是 erverse parseDouble 至少是2次
     * 空间复杂度  O(N)   StringBuilder的耗时
     * 2.
     * @Date: 2021/4/15 9:57
     */
    /*public int reverse(int x) {
        String str = Integer.toString(x);
        boolean flag = x < 0;
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.reverse();
        if (flag) {
            stringBuilder.replace(str.length() - 1,str.length(),"");
        }
        String tmpStr = stringBuilder.toString();
        if (flag) {
            tmpStr = "-" + tmpStr;
        }
        double resInt = Double.parseDouble(tmpStr);
        if (resInt > (Math.pow(2,31) - 1) || resInt < -Math.pow(2,31)) {
            return 0;
        }
        return (int)resInt;
    }*/

    /**
     * @Description:  这就是来自数学大神的魅力
     * x的每一位数字，依次循环，去10的余数
     * 和 整除10还原为10的倍数  直到 除完为0为止，
     * 最后在int强转看是否 相等，不相等则不再范围内 返回0
     * 时间复杂度 O(N) N是x的位数  空间复杂度  O(1) 没使用任何空间
     * @Date: 2021/4/15 11:56
     */
    public int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }

    /**
     * @Description:
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     *
     * 示例 1：
     *
     * 输入：x = 121
     * 输出：true
     * 示例 2：
     *
     * 输入：x = -121
     * 输出：false
     * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3：
     *
     * 输入：x = 10
     * 输出：false
     * 解释：从右向左读, 为 01 。因此它不是一个回文数。
     * 示例 4：
     *
     * 输入：x = -101
     * 输出：false
     *  
     *
     * 提示：
     *
     * -231 <= x <= 231 - 1
     *
     * solution: 用翻转的方法 看是否相等
     * @Date: 2021/4/15 13:41
     */
    public boolean isPalindrome(int x) {
        if (x < 0 ) {
            return false;
        }
        return x == reverse(x);
    }

    /**
     * @Description:
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
     * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
     * 这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: "III"
     * 输出: 3
     * 示例 2:
     *
     * 输入: "IV"
     * 输出: 4
     * 示例 3:
     *
     * 输入: "IX"
     * 输出: 9
     * 示例 4:
     *
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     *
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 15
     * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
     * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
     * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
     *
     * solution: 1.s.toCharArray 每次截取前两位 看是否在枚举列表中,如果在跳2位 否则跳1位
     * 枚举  特殊字符串 ,求和 特殊字符串6个 4 9 40 90 400 900 搞个map存储  常规字符串  7个
     * O(N) O(N)
     * @Date: 2021/4/15 13:46
     */
    public int romanToInt(String s) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("IV",4);
        map.put("IX",9);
        map.put("XL",40);
        map.put("XC",90);
        map.put("CD",400);
        map.put("CM",900);
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        int res = 0;
        for (int i = 0; i < s.length(); ) {
            String str = "" + s.charAt(i);
            String tmp = str;
            if (i + 1 < s.length()) {
                str += s.charAt(i + 1);
            }
            if (map.containsKey(str)) {
                res += map.get(str);
                i = i + str.length();
            } else {
                res += map.get(tmp);
                i = i + 1;
            }
        }
        return res;
    }

    /**
     * @Description:
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * solution:
     * 1.直接思路 遍历1--》x  寻找  i的平方< x < i+1的平方 O(N)
     * @Date: 2021/4/19 16:43
     */
    /*public int mySqrt(int x) {
        if (x == 1) return 1;
        int tail = x;
        int head = 0;
        long mid;
        while (head < tail && ((tail - head ) != 1)) {
            mid = (tail + head) / 2;
            if (x > mid * mid) {
                head = (int)mid;
            } else if (x < mid * mid) {
                tail = (int)mid;
            } else {
                return (int)mid;
            }
        }
        return head;
    }*/

    //数学公式
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int ans = (int)Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * @Description:
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     *
     * 例如，
     *
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     * 示例 1:
     *
     * 输入: 1
     * 输出: "A"
     * 示例 2:
     *
     * 输入: 28
     * 输出: "AB"
     * 示例 3:
     *
     * 输入: 701
     * 输出: "ZY"
     *
     * solution:
     * 1.除以26取余 知道 被除数 <= 26
     * 2.列数组 1.。。26 O(N) O(N)
     * @Date: 2021/4/19 17:21
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder str = new StringBuilder();
        String[] arr = new String[]{"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q",
                "R","S","T","U","V","W","X","Y","Z"};
        int col = columnNumber;
        int ave;
        while (col > 26) {
            ave = col % 26;
            if (ave == 0) {
                ave = 26;
                str.append(arr[26]);
            } else {
                str.append(arr[ave]);
            }
            col = (col - ave) / 26;
        }
        str.append(arr[col]);
        return str.reverse().toString();
    }

    /**
     * @Description:
     * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
     *
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     *
     * 示例 1：
     *
     * 输入：n = 6
     * 输出：true
     * 解释：6 = 2 × 3
     * 示例 2：
     *
     * 输入：n = 8
     * 输出：true
     * 解释：8 = 2 × 2 × 2
     * 示例 3：
     *
     * 输入：n = 14
     * 输出：false
     * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
     * 示例 4：
     *
     * 输入：n = 1
     * 输出：true
     * 解释：1 通常被视为丑数。
     *  
     *
     * 提示：
     *
     * -231 <= n <= 231 - 1
     *
     * solution:
     * 1.循环 对 2 3 5 取余 都不能取余的时候，
     * 取余前先判断 是否是 2 3 5其中的一个 是就返回true 3个都不能取余 返回 false 1特殊处理
     * O(N） O(1)
     * @Date: 2021/4/20 9:57
     */
    /*public boolean isUgly(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        List<Integer> list = Arrays.asList(2,3,5);
        boolean flag = false;
        while (!flag) {
            if (n % 2 == 0) {
                n /= 2;
            } else if (n % 3 == 0) {
                n /= 3;
            } else if (n % 5 == 0) {
                n /= 5;
            } else {
                flag = true;
            }
        }
        return n == 1;
    }*/

    //官方解答  O(N） O(1)
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        //这个思路确实牛逼  循环除 2 3 5判断最后是否等于1  不能除就跳出
        return n == 1;
    }

    /**
     * @Description:
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
     * 示例 1：
     *
     * 输入：n = 27
     * 输出：true
     * 示例 2：
     *
     * 输入：n = 0
     * 输出：false
     * 示例 3：
     *
     * 输入：n = 9
     * 输出：true
     * 示例 4：
     *
     * 输入：n = 45
     * 输出：false
     *  
     *
     * 提示：
     *
     * -231 <= n <= 231 - 1
     *  
     * 进阶：你能不使用循环或者递归来完成本题吗？
     * solution ： 循环除以 3 看结果是否为 1  如果《=0 false
     * @Date: 2021/4/20 10:37
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n  % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * @Description:
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     *
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     *
     * 示例 1：
     *
     * 输入：num = 16
     * 输出：true
     * 示例 2：
     *
     * 输入：num = 14
     * 输出：false
     *  
     *
     * 提示：
     *
     * 1 <= num <= 2^31 - 1
     *
     * solution: 1.二分法 0.。。num  求平方  O(logN)
     * @Date: 2021/4/20 14:13
     */
    public boolean isPerfectSquare(int num) {
        long left = 0;
        long right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (num < mid * mid) {
                right = mid - 1;
            } else if (num > mid * mid) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description:
     * 给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。找出让数组所有元素相等的最小操作次数。
     *
     * 示例：
     *
     * 输入：
     * [1,2,3]
     * 输出：
     * 3
     * 解释：
     * 只需要3次操作（注意每次操作会增加两个元素的值）：
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     *
     * solution :
     * 数学法，n-1个数加一（排除最大的数） 等同于 最大的数每次减一
     * 所以 找出数组中最小值,然后每个数做 减法求和
     * O(N) O(1)
     * @Date: 2021/4/20 14:29
     */
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min,num);
        }
        int res  = 0;
        for (int num : nums) {
            res += num - min;
        }
        return res;
    }

    /**
     * @Description:
     * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，
     * 你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
     *
     * 1. 你设计的矩形页面必须等于给定的目标面积。
     *
     * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
     *
     * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
     * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
     *
     * 示例：
     *
     * 输入: 4
     * 输出: [2, 2]
     * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
     * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
     * 说明:
     *
     * 给定的面积不大于 10,000,000 且为正整数。
     * 你设计的页面的长度和宽度必须都是正整数。
     *
     * solution:
     * 1.找齐所有 按规则筛选
     * 2.边找边塞选  L >= W  abs = L - W  ; L * W = area ; 求abs最小的 L W
     * 3.数学解答方式  由 L >= W 而且 L和W能被area整除  W*W <= L*W = area ==> W <= 根号area
     * W就该取 根号area的向下的最大值  L就该取根号area向上的最小值
     * O(N) O(1)
     * @Date: 2021/4/21 10:11
     */
    public int[] constructRectangle(int area) {
        int[] res = new int[2];
        int sqrt = (int)Math.sqrt(area);
        int i = sqrt;
        for ( ;i <= area; i++) {
            if (area % i == 0) {
                break;
            }
        }
        res[0] = i;
        res[1] =area / i;
        return res;
    }

    /**
     * @Description:
     * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
     *
     * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
     *
     * 示例 1：
     *
     * 输入：28
     * 输出：True
     * 解释：28 = 1 + 2 + 4 + 7 + 14
     * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
     * 示例 2：
     *
     * 输入：num = 6
     * 输出：true
     * 示例 3：
     *
     * 输入：num = 496
     * 输出：true
     * 示例 4：
     *
     * 输入：num = 8128
     * 输出：true
     * 示例 5：
     *
     * 输入：num = 2
     * 输出：false
     *  
     *
     * 提示：
     *
     * 1 <= num <= 108
     *
     * solution:
     * 1.求因子和 然后和num对比 相同为true 相异为false O(N) 1--根号 num
     * 所有因子加两遍 如果i * i= num 特殊处理
     * O(N) O(1)
     * @Date: 2021/4/21 14:02
     */
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }

    /**
     * @Description:
     * 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
     *
     * 操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，
     * 含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。
     *
     * 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
     *
     * 示例 1:
     *
     * 输入:
     * m = 3, n = 3
     * operations = [[2,2],[3,3]]
     * 输出: 4
     * 解释:
     * 初始状态, M =
     * [[0, 0, 0],
     *  [0, 0, 0],
     *  [0, 0, 0]]
     *
     * 执行完操作 [2,2] 后, M =
     * [[1, 1, 0],
     *  [1, 1, 0],
     *  [0, 0, 0]]
     *
     * 执行完操作 [3,3] 后, M =
     * [[2, 2, 1],
     *  [2, 2, 1],
     *  [1, 1, 1]]
     *
     * M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
     * 注意:
     *
     * m 和 n 的范围是 [1,40000]。
     * a 的范围是 [1,m]，b 的范围是 [1,n]。
     * 操作数目不超过 10000。
     *
     * [[16,1],[14,3],[14,2],[4,1],[10,1],[11,1],[8,3],[16,2],[13,1],[8,3],[2,2],[9,1],[3,1],[2,2],[6,3]]
     *
     * solution:
     * 1.找最小的范围数返回  ops里面的操作数来判断 最后求 ops数组最小数乘积
     * O(N) O(1) 纯属于找规律
     * @Date: 2021/4/22 10:05
     */
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) return m * n;
        int minM = ops[0][0];
        int minN = ops[0][1];
        for (int i = 1; i < ops.length; i++) {
            minM = Math.min(minM,ops[i][0]);
            minN = Math.min(minN,ops[i][1]);
        }
        return minM * minN;
    }

    /**
     * @Description:
     * 自除数 是指可以被它包含的每一位数除尽的数。
     *
     * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
     *
     * 还有，自除数不允许包含 0 。
     *
     * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
     *
     * 示例 1：
     *
     * 输入：
     * 上边界left = 1, 下边界right = 22
     * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
     * 注意：
     *
     * 每个输入参数的边界满足 1 <= left <= right <= 10000。
     *
     * solution:1.在 left 和 right内 判断该数是否是自除数
     * 1.用 % 10获取尾数
     * 2./10 获取下一个数
     * 3.tmp != 0标识循环结束
     * O(N) O(1)
     * @Date: 2021/4/22 10:25
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumber(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isSelfDividingNumber(int num) {
        int tmp = num;
        while (tmp != 0) {
            int remainder = tmp % 10;
            if (remainder == 0) {
                return false;
            }
            if (num % remainder != 0) {
                return false;
            }
            tmp /= 10;
        }
        return true;
    }

    /**
     * @Description:
     * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
     *
     * 示例:
     * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
     * 输出: 2
     * 解释:
     * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
     *
     *
     * 注意:
     *
     * 3 <= points.length <= 50.
     * 不存在重复的点。
     *  -50 <= points[i][j] <= 50.
     * 结果误差值在 10^-6 以内都认为是正确答案。
     *
     * solution:
     * 1.两个条件 能组成三角形且面积最大 三重for循环，传入所在点的横纵坐标 利用公式计算面积
     * O(N3) O(1)
     * @Date: 2021/4/22 11:18
     */
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }

    /**
     * @Description:
     * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
     * 矩形的上下边平行于 x 轴，左右边平行于 y 轴。
     *
     * 如果相交的面积为 正 ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
     *
     * 给出两个矩形 rec1 和 rec2 。如果它们重叠，返回 true；否则，返回 false 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
     * 输出：true
     * 示例 2：
     *
     * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
     * 输出：false
     * 示例 3：
     *
     * 输入：rec1 = [0,0,1,1], rec2 = [2,2,3,3]
     * 输出：false
     *  
     *
     * 提示：
     *
     * rect1.length == 4
     * rect2.length == 4
     * -109 <= rec1[i], rec2[i] <= 109
     * rec1[0] <= rec1[2] 且 rec1[1] <= rec1[3]
     * rec2[0] <= rec2[2] 且 rec2[1] <= rec2[3]
     *
     * solution: 官方思路  ：
     * 1 判断是否为矩形
     * 2.排查法把 四周不想交的情况选出来，其他就是有重叠的
     * O(1) O(1)
     * @Date: 2021/4/23 10:15
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }


    /**
     * @Description:
     * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。
     * 如果不存在两个相邻的 1，返回 0 。
     *
     * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。
     * 两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3 。 
     *
     * 示例 1：
     *
     * 输入：n = 22
     * 输出：2
     * 解释：
     * 22 的二进制是 "10110" 。
     * 在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
     * 第一对相邻的 1 中，两个 1 之间的距离为 2 。
     * 第二对相邻的 1 中，两个 1 之间的距离为 1 。
     * 答案取两个距离之中最大的，也就是 2 。
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：2
     * 解释：
     * 5 的二进制是 "101" 。
     * 示例 3：
     *
     * 输入：n = 6
     * 输出：1
     * 解释：
     * 6 的二进制是 "110" 。
     * 示例 4：
     *
     * 输入：n = 8
     * 输出：0
     * 解释：
     * 8 的二进制是 "1000" 。
     * 在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
     * 示例 5：
     *
     * 输入：n = 1
     * 输出：0
     *  
     *
     * 提示：
     *
     * 1 <= N <= 10^9
     *
     * solution:
     * 1. 找特殊情况  全为0 全为 1 只包含一个1 特殊处理
     * 2.while  循环遍历n 计数 取max
     * O(N) O(1)
     * @Date: 2021/4/23 11:41
     */
    public int binaryGap(int n) {
        int res = 0;
        if (Integer.bitCount(n) == 1) {
            return res;
        }
        int max = 0;
        int count = 0;
        boolean isOne = false;
        while (n != 0) {
            if ((n & 1) == 1) {
                if (isOne) {
                    //每次取到相邻1的时候，更新距离最大值
                    max = Math.max(max,count);
                }
                isOne = true;
                count = 1;
            } else {
                if (isOne) {
                    //只要前面有1 isOne都为true  count计数++
                    count++;
                }
            }
            n >>= 1;
        }
       return max;
    }

    /**
     * @Description:
     * 排排坐，分糖果。
     *
     * 我们买了一些糖果 candies
     *
     * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
     *
     * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，
     * 依此类推，直到给最后一个小朋友 2 * n 颗糖果。
     *
     * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），
     * 直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），
     * 这些糖果也会全部发给当前的小朋友。
     *
     * 返回一个长度为 num_people、元素之和为 candies 的数组，
     * 以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：candies = 7, num_people = 4
     * 输出：[1,2,3,1]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
     * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
     * 示例 2：
     *
     * 输入：candies = 10, num_people = 3
     * 输出：[5,2,3]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3]。
     * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
     *  
     *
     * 提示：
     *
     * 1 <= candies <= 10^9
     * 1 <= num_people <= 1000
     *
     * solution:
     * 1.按照题意发糖果，循环直到糖果为空  O(N) O(N)
     * 2.数学公式直接计算 每个人该得糖果数量
     * @Date: 2021/4/26 9:58
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int index = 0;
        int count = 1;
        while (candies > 0) {
            if (candies <= count) {
                res[index] += candies;
                break;//糖果发完了
            }
            res[index++] += count;
            candies -= count;
            count++;
            if (index == res.length) {
                index = 0;
            }
        }

        return res;
    }

    /**
     * @Description:
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
     * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * 示例 1:
     *
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     *  
     *
     * 说明：
     *
     * 用返回一个整数列表来代替打印
     * n 为正整数
     *
     * solution:
     * 直接 1....Math.pow(10,n) - 1
     * O(N) O(N)
     * @Date: 2021/4/26 11:47
     */
    public int[] printNumbers(int n) {
        int pow = (int)Math.pow(10, n) - 1;
        int[] res = new int[pow];
        int i = 1;
        int index = 0;
        while (i <= pow) {
            res[index++] = i++;
        }
        return res;
    }


    /**
     * @Description:
     * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 234
     * 输出：15
     * 解释：
     * 各位数之积 = 2 * 3 * 4 = 24
     * 各位数之和 = 2 + 3 + 4 = 9
     * 结果 = 24 - 9 = 15
     * 示例 2：
     *
     * 输入：n = 4421
     * 输出：21
     * 解释：
     * 各位数之积 = 4 * 4 * 2 * 1 = 32
     * 各位数之和 = 4 + 4 + 2 + 1 = 11
     * 结果 = 32 - 11 = 21
     *  
     *
     * 提示：
     *
     * 1 <= n <= 10^5
     *
     * solution:
     * 1. 除10余求各数字
     * 2.求 乘积和 和之差
     * O(N) O(1)
     * @Date: 2021/4/26 13:37
     */
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int store = 1;
        while (n != 0) {
            sum += n % 10;
            store *= n % 10;
            n /= 10;
        }
        return store - sum;
    }

    /**
     * @Description:
     * 给你一个仅由数字 6 和 9 组成的正整数 num。
     *
     * 你最多只能翻转一位数字，将 6 变成 9，或者把 9 变成 6 。
     *
     * 请返回你可以得到的最大数字。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：num = 9669
     * 输出：9969
     * 解释：
     * 改变第一位数字可以得到 6669 。
     * 改变第二位数字可以得到 9969 。
     * 改变第三位数字可以得到 9699 。
     * 改变第四位数字可以得到 9666 。
     * 其中最大的数字是 9969 。
     * 示例 2：
     *
     * 输入：num = 9996
     * 输出：9999
     * 解释：将最后一位从 6 变到 9，其结果 9999 是最大的数。
     * 示例 3：
     *
     * 输入：num = 9999
     * 输出：9999
     * 解释：无需改变就已经是最大的数字了。
     *  
     *
     * 提示：
     *
     * 1 <= num <= 10^4
     * num 每一位上的数字都是 6 或者 9 。
     *
     * solution:
     * 1.只需要翻转首位 6 如果没有，就不翻转  int to String  replaceFirst6 O(N) O(1)
     * 2.除10取余判断6的位置 然后+3* 10的n次方
     * O(1) O(1)
     * @Date: 2021/4/26 13:44
     */
    public int maximum69Number (int num) {
        String str = String.valueOf(num);
        str = str.replaceFirst("6","9");
        return Integer.parseInt(str);
    }

    /**
     * @Description:
     * 给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。
     *
     * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。
     * 每个月的天数与现行公元纪年法（格里高利历）一致。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：date = "2019-01-09"
     * 输出：9
     * 示例 2：
     *
     * 输入：date = "2019-02-10"
     * 输出：41  31 + 10
     * 示例 3：
     *
     * 输入：date = "2003-03-01"
     * 输出：60  31+28+1
     * 示例 4：
     *
     * 输入：date = "2004-03-01"
     * 输出：61 31+29+1
     *  
     *
     * 提示：
     *
     * date.length == 10
     * date[4] == date[7] == '-'，其他的 date[i] 都是数字。
     * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日。
     *
     * solution:
     * A 1.拆分date按 - 2.判断是否是闰年 3.求实际天数
     * B date解析为日期 Calendar计算天数
     * 直接使用api
     *  O(1) O(1)
     * @Date: 2021/4/26 13:52
     */
    /*public int dayOfYear(String date) {
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        boolean isLeapYear = false;
        if ((year % 100 == 0 && year % 4 == 0) || (year % 100 != 0 && year % 4 == 0)) {
            isLeapYear = true;
        }
        int[] arr = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
        int res = day;
        for (int i = 1; i < month; i++) {
            res += arr[i - 1];
        }
        if (isLeapYear && month >= 3) {
            res += 1;
        }
        return res;
    }*/

    public int dayOfYear(String date) {
        try {
            Date dataCalendar = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataCalendar);
            return calendar.get(Calendar.DAY_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @Description:
     * 每个非负整数 N 都有其二进制表示。例如， 5 可以被表示为二进制 "101"，11 可以用二进制 "1011" 表示，
     * 依此类推。注意，除 N = 0 外，任何二进制表示中都不含前导零。
     *
     * 二进制的反码表示是将每个 1 改为 0 且每个 0 变为 1。例如，二进制数 "101" 的二进制反码为 "010"。
     *
     * 给你一个十进制数 N，请你返回其二进制表示的反码所对应的十进制整数。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：5
     * 输出：2
     * 解释：5 的二进制表示为 "101"，其二进制反码为 "010"，也就是十进制中的 2 。
     * 示例 2：
     *
     * 输入：7
     * 输出：0
     * 解释：7 的二进制表示为 "111"，其二进制反码为 "000"，也就是十进制中的 0 。
     * 示例 3：
     *
     * 输入：10
     * 输出：5
     * 解释：10 的二进制表示为 "1010"，其二进制反码为 "0101"，也就是十进制中的 5 。
     *  
     *
     * 提示：
     *
     * 0 <= N < 10^9
     * 本题与 476：https://leetcode-cn.com/problems/number-complement/ 相同
     * 通过次数13,523提交次数22,805
     *
     * solution:
     * 1. N ^ oxffff  找出N的最高二进制位
     * 求出2的多少你次方 然后做异或操作
     * highestValue * 2 - 1 获取N的最高二进制位  1111111..1111
     * O(1) O(1)
     * @Date: 2021/4/26 14:17
     */
    public int bitwiseComplement(int N) {
        if (N == 0) return 1;
        int highestValue = Integer.highestOneBit(N);
     return (highestValue * 2 - 1) ^ N;
    }

    /**
     * @Description:
     * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
     *
     * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
     *
     * 如果 S[i] == "I"，那么 A[i] < A[i+1]
     * 如果 S[i] == "D"，那么 A[i] > A[i+1]
     *  
     *
     * 示例 1：
     *
     * 输入："IDID"
     * 输出：[0,4,1,3,2]
     * 示例 2：
     *
     * 输入："III"
     * 输出：[0,1,2,3]
     * 示例 3：
     *
     * 输入："DDI"
     * 输出：[3,2,0,1]
     *  
     *
     * 提示：
     *
     * 1 <= S.length <= 10000
     * S 只包含字符 "I" 或 "D"。
     *
     * solution:
     * I 0...X 升序
     * D Y...X降序 首先确定
     * Y = s.length;
     * O(N) O(1)
     * @Date: 2021/4/27 9:58
     */
    public int[] diStringMatch(String s) {
        int y = s.length();
        int[] res = new int[y + 1];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'D') {
                res[i] = y--;
            } else {
                res[i] = index++;
            }
        }
        //last element must be the index you can do a example
        res[s.length()] = index;
        return res;
    }

    /**
     * @Description:
     * 「无零整数」是十进制表示中 不含任何 0 的正整数。
     *
     * 给你一个整数 n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
     *
     * A 和 B 都是无零整数
     * A + B = n
     * 题目数据保证至少有一个有效的解决方案。
     *
     * 如果存在多个有效解决方案，你可以返回其中任意一个。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：[1,1]
     * 解释：A = 1, B = 1. A + B = n 并且 A 和 B 的十进制表示形式都不包含任何 0 。
     * 示例 2：
     *
     * 输入：n = 11
     * 输出：[2,9]
     * 示例 3：
     *
     * 输入：n = 10000
     * 输出：[1,9999]
     * 示例 4：
     *
     * 输入：n = 69
     * 输出：[1,68]
     * 示例 5：
     *
     * 输入：n = 1010
     * 输出：[11,999]
     *  
     *
     * 提示：
     *
     * 2 <= n <= 10^4
     *
     * solution: A + B = N  A and B  both are not the zero decimal Integer
     * O(N) O(1)
     * @Date: 2021/4/27 11:08
     */
    public int[] getNoZeroIntegers(int n) {
        int[] res = new int[2];
        for (int i = 1; i < n; i++) {
            if (isNoZero(i) && isNoZero(n - i)) {
                res[0] = i;
                res[1] = n - i;
            }
        }
        return res;
    }

    private boolean isNoZero(int num) {
        while (num != 0) {
            if (num % 10 == 0) {
                return false;
            }
            num /= 10;
        }
        return true;
    }

    /**
     * @Description:
     * 给你一个整数数组 A，请你给数组中的每个元素 A[i] 都加上一个任意数字 x （-K <= x <= K），
     * 从而得到一个新数组 B 。
     *
     * 返回数组 B 的最大值和最小值之间可能存在的最小差值。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：A = [1], K = 0
     * 输出：0
     * 解释：B = [1]
     * 示例 2：
     *
     * 输入：A = [0,10], K = 2
     * 输出：6
     * 解释：B = [2,8]
     * 示例 3：
     *
     * 输入：A = [1,3,6], K = 3
     * 输出：0
     * 解释：B = [3,3,3] 或 B = [4,4,4]
     *  
     *
     * 提示：
     *
     * 1 <= A.length <= 10000
     * 0 <= A[i] <= 10000
     * 0 <= K <= 10000
     *
     * solution:
     * 1.找出原数组中最大最小值查  和 2 * K做对比  if great 2k   diffValue - 2 * k else 0
     * O(N) O(1)
     * @Date: 2021/4/27 11:16
     */
    public int smallestRangeI(int[] A, int K) {
        int min = A[0];
        int max = A[0];
        for (int i : A) {
            min = Math.min(min,i);
            max = Math.max(max,i);
        }
        return (max - min) > 2 * K ? (max - min) - 2 * K : 0;
    }

    /**
     * @Description:
     * 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：low = 3, high = 7
     * 输出：3
     * 解释：3 到 7 之间奇数数字为 [3,5,7] 。
     * 示例 2：
     *
     * 输入：low = 8, high = 10
     * 输出：1
     * 解释：8 到 10 之间奇数数字为 [9] 。
     *  
     *
     * 提示：
     *
     * 0 <= low <= high <= 10^9
     *
     * solution：
     * 1.一个一个数
     * 2.用数学公式
     *high/2  - low/2 + high is odd ? 1 : 0
     * O(N) O(1)
     * @Date: 2021/4/27 11:26
     */
    /*public int countOdds(int low, int high) {
        if (low == high) return 0;
        return high / 2 - low / 2 + (high % 2 == 1 ? 1 : 0);
    }*/

    public int countOdds(int low, int high) {
        return countZeroToX(high) - countZeroToX(low - 1);
    }

    public int countZeroToX(int x) {
        return (x + 1) >> 1;
    }

    /**
     * @Description:
     * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
     *
     * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。
     * 在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
     *
     * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 4
     * 输出：10
     * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
     * 示例 2：
     *
     * 输入：n = 10
     * 输出：37
     * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
     * 示例 3：
     *
     * 输入：n = 20
     * 输出：96
     * 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
     *  28 * 3 + 7 + 7 - 9  =
     *
     *
     * 提示：
     *
     * 1 <= n <= 1000
     *
     * solution:
     * 1.n 计算有几周  n/7
     * 2.按照规则算 看有没有数学公式可以用
     * O(1) O(1)
     * @Date: 2021/4/28 10:03
     */
    public int totalMoney(int n) {
        int oneBaseWeek = 28;
        int week = n / 7;
        int startDay = week + 1;

        int result  = oneBaseWeek * week + (week * (week - 1)) / 2 * 7;
        for (int i = week *  7 ; i < n; i++) {
            result += startDay++;
        }
        return result;
    }

    /**
     * @Description:
     * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
     *
     * 如果 x 是正数，返回 1 。
     * 如果 x 是负数，返回 -1 。
     * 如果 x 是等于 0 ，返回 0 。
     * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
     *
     * 返回 signFunc(product) 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [-1,-2,-3,-4,3,2,1]
     * 输出：1
     * 解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1
     * 示例 2：
     *
     * 输入：nums = [1,5,0,2,-3]
     * 输出：0
     * 解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0
     * 示例 3：
     *
     * 输入：nums = [-1,1,-1,1,-1]
     * 输出：-1
     * 解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 1000
     * -100 <= nums[i] <= 100
     *
     * solution：计算 结果是正 负 还是 0  按结果返回 计算负数的个数来判断 如果有0则为 0
     * O(N) O(1)
     * @Date: 2021/4/28 10:58
     */
    public int arraySign(int[] nums) {
        boolean zeroFlag = false;
        int negativeCount = 0;
        for (int num : nums) {
            if (num < 0) {
                negativeCount++;
            }
            if (num == 0) {
                zeroFlag = true;
                break;
            }
        }
        if (zeroFlag) {
            return 0;
        }
        return negativeCount % 2 == 0 ? 1 : -1;
    }

    /**
     * @Description:
     * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
     *
     * 给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。
     *
     * 示例 1：
     *
     * 输入：[[1,1],[2,3],[3,2]]
     * 输出：true
     * 示例 2：
     *
     * 输入：[[1,1],[2,2],[3,3]]
     * 输出：false
     *  
     *
     * 提示：
     *
     * points.length == 3
     * points[i].length == 2
     * 0 <= points[i][j] <= 100
     *
     * solution:
     * 1.判断3个点 不在同一条直线上  他们斜率不相等  1 2  1 3 斜率不相等
     * O(1) O(1)
     * @Date: 2021/4/28 11:05
     */
    public boolean isBoomerang(int[][] points) {
       return  (points[1][0] - points[0][0]) * (points[2][1] - points[0][1]) !=
               (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]);
    }

    /**
     * @Description:
     * 设计一个算法，算出 n 阶乘有多少个尾随零。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     * 示例 2:
     *
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * 说明: 你算法的时间复杂度应为 O(log n) 。
     *
     * solution:
     * 1.阶乘计算公式  用string计算结尾有几个0
     * 2。特殊算法  偶数 和 5 10 相乘，找规律 看有几个0
     * O(N) O(1)
     * @Date: 2021/4/28 11:37
     */
    public int trailingZeroes(int n) {
        int res = 0;
        while (n >= 5)
        {
            res += n/5;
            n /= 5;
        }
        return res;
    }

    /**
     * @Description:
     * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；
     * 你需要返回可能的方案总数。
     *
     * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
     *
     * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
     *
     * 示例 1：
     *
     * 输入：n = 5
     * 输出：12
     * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，
     * 因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
     * 示例 2：
     *
     * 输入：n = 100
     * 输出：682289015
     *  
     *
     * 提示：
     *
     * 1 <= n <= 100
     *
     * solution:
     * 找出n以内有多少个质数p  非质数就是 n-p   排列  App * A(n-p)(n-p)
     * 1.统计质数
     * 2.数学公式计算结果
     * O(N*M) O(1)
     * @Date: 2021/4/29 10:22
     */
    public int numPrimeArrangements(int n) {
        int countPrime = 0 ;
        int bigNum = (int)Math.pow(10,9) + 7;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) countPrime++;
        }
        int restCount = n - countPrime;
        long primeProduct = 1;
        long primeNoProduct = 1;
        for (int i = 1; i <= countPrime; i++) {
            if (primeProduct < bigNum / i) {
                primeProduct *= i;
            } else {
                primeProduct = primeProduct * i % bigNum;
            }
        }
        for (int i = 1; i<= restCount; i++) {
            if (primeNoProduct < bigNum / i) {
                primeNoProduct *= i;
            } else {
                primeNoProduct = primeNoProduct * i % bigNum;
            }
        }
        int result = 0;
        result += (primeProduct * primeNoProduct) % bigNum;
        return result;
    }
    private boolean isPrime(int num) {
        if (num == 1) return false;
        if (num == 2) return true;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

   /* public int numPrimeArrangements(int n) {
        int[] zhishu={
                2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,
                83,89,97
        };
        //计算质数个数
        int nzhishu=0;
        for(int c:zhishu){
            if(n>=c){
                nzhishu++;
            }
        }
        //计算非质数的个数
        int nfeizhishu=n-nzhishu;
        long ans=1;
        for(int i=nfeizhishu;i>1;i--){
            if(ans<1000000007/i){
                ans*=i;
            }
            else{
                ans=ans*i%1000000007;
            }
        }
        for(int i=nzhishu;i>1;i--){
            if(ans<1000000007/i){
                ans*=i;
            }
            else{
                ans=ans*i%1000000007;
            }
        }
        return (int)ans%1000000007;
    }*/

    /**
     * @Description:
     * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
     *
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
     *
     * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
     *
     * 投影就像影子，将三维形体映射到一个二维平面上。
     *
     * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
     *
     * 返回所有三个投影的总面积。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[[2]]
     * 输出：5
     * 示例 2：
     *
     * 输入：[[1,2],[3,4]]
     * 输出：17
     * 解释：
     * 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
     *
     * 示例 3：
     *
     * 输入：[[1,0],[0,2]]
     * 输出：8
     * 示例 4：
     *
     * 输入：[[1,1,1],[1,0,1],[1,1,1]]
     * 输出：14
     * 示例 5：
     *
     * 输入：[[2,2,2],[2,1,2],[2,2,2]]
     * 输出：21
     *  
     *
     * 提示：
     *
     * 1 <= grid.length = grid[0].length <= 50
     * 0 <= grid[i][j] <= 50
     *
     * solution:  官方解答 从顶部看所有不为0的值之和 从侧面看每一行的最大值 从前面看每一列的最大值
     * O(N2） O(N)
     * @Date: 2021/4/30 11:05
     */
    public int projectionArea(int[][] grid) {
        int N = grid.length;
        int ans = 0;

        for (int i = 0; i < N;  ++i) {
            int bestRow = 0;  // largest of grid[i][j]
            int bestCol = 0;  // largest of grid[j][i]
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] > 0) ans++;  // top shadow
                bestRow = Math.max(bestRow, grid[i][j]); //行最大值
                bestCol = Math.max(bestCol, grid[j][i]);//列最大值
            }
            ans += bestRow + bestCol;
        }

        return ans;
    }

    /**
     * @Description:
     * 给你一个整数 n（10 进制）和一个基数 k ，请你将 n 从 10 进制表示转换为 k 进制表示，
     * 计算并返回转换后各位数字的 总和 。
     *
     * 转换后，各位数字应当视作是 10 进制数字，且它们的总和也应当按 10 进制表示返回。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 34, k = 6
     * 输出：9
     * 解释：34 (10 进制) 在 6 进制下表示为 54 。5 + 4 = 9 。
     * 示例 2：
     *
     * 输入：n = 10, k = 10
     * 输出：1
     * 解释：n 本身就是 10 进制。 1 + 0 = 1 。
     *  
     *
     * 提示：
     *
     * 1 <= n <= 100
     * 2 <= k <= 10
     *
     * solution: n转换为k进制  余数相加  O(N) O(1)
     * @Date: 2021/4/30 13:35
     */
    public int sumBase(int n, int k) {
        int sum = 0 ;
        while (k <= n) {
            sum += n % k;
            n /= k;
        }
        return sum + n;
    }

    @Test
    public void testMathematicsAlp() {
        OptionalInt first = Arrays.stream(instance.distributeCandies(11, 3)).findFirst();
        if (first.isPresent()) {
            System.out.println(first.getAsInt());
        }
    }
}
