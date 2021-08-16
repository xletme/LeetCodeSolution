package Leetcode.Stack;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/11 13:39
 **/
public class CompareStackBackSpace {
    private static CompareStackBackSpace test = new CompareStackBackSpace();

    //思路:对字符串,入栈 后得到的结果 做比较
    public boolean backspaceCompare(String S, String T) {
        System.out.println( backSpace(S));
        System.out.println( backSpace(T));
        return backSpace(S).equals(backSpace(T));
    }

    public String backSpace(String s){
        if(s == null){
            return "";
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] != '#'){
                stack.push(chars[i]);
            }else if(!stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack.toString();
    }

    //思路:按照规则,+ D C 整数计算 进行进出栈就OK，最后计算 总和
    public int calPoints(String[] ops) {
        if(ops == null){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ops.length; i++) {
            if(ops[i].equals("+")){
                int a = stack.pop();
                int b = stack.peek();
                stack.push(a);
                stack.push(a+b);
            }else if(ops[i].equals("D")){
                stack.push(stack.peek()*2);
            }else if(ops[i].equals("C")){
                stack.pop();
            }else{
                stack.push(Integer.parseInt(ops[i]));
            }
        }

        int sum = 0;
        while (!stack.isEmpty()){
            sum+=stack.pop();
        }
        return sum;
    }

    //将[1,2,3,...,target[-1]]顺序入栈，谁不在目标数组，谁就出栈。
    //target转list  存在就push 否则 push + pop
    public List<String> buildArray(int[] target, int n) {
        List<String> result = new LinkedList<>();
        List<Integer> list = Arrays.stream(target).boxed().collect(Collectors.toList());
        for(int i=1;i<=target[target.length -1];i++){
               if(list.contains(i)){
                   result.add("Push");
               }else {
                   result.add("Push");
                   result.add("Pop");
               }
        }
        return result;
    }

    //思路：括号规律，出现一次左括号加一 a 出现一次右括号加一 b  ，计数 直到 a==b  ,拆分为一组  拆分完成,分别去除每一组的 first last 在进行组合
    //1. s change char[] 2.new LinkedList 存储数据  3.去除首尾变string 相加 返回 List<List<char>>  sList
    //时间复杂度：O(N2) 空间复杂度 O(N)
    public String removeOuterParentheses(String S) {
        if(S == null){
            return null;
        }
        List<LinkedList<Character>> sList = new LinkedList<>();
        char[] chars = S.toCharArray();
        int a=0;
        int b=0;
        for (int i = 0; i < chars.length; i++) {
            LinkedList<Character> cList = new LinkedList<>();
            boolean flag =false;
            for (int j = i; j < chars.length; j++) {
                if (chars[j] == '(') {
                    cList.add('(');
                    a++;
                } else if (chars[j] == ')') {
                    cList.add(')');
                    b++;
                }
                if (a != 0 && a == b) {
                    i = j;
                    a = 0;
                    b = 0;
                    sList.add(cList);
                    if(j == chars.length -1){
                        flag = true;
                    }
                    break;
                }

            }
            if(flag){
                break;
            }
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < sList.size(); i++) {
            LinkedList<Character> characters = sList.get(i);
            characters.removeFirst();
            characters.removeLast();
            characters.forEach(str::append);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        /*String S = "ab#c";
        String T="ad#c";
        String[] ops = new String[]{"5","2","C","D","+"};
        int[] target = new int[]{2,3,4};
        test.buildArray(target,4).forEach(System.out::println);*/
        System.out.println(test.removeOuterParentheses("(()())(())(()(()))"));
        System.out.println(BigDecimal.ZERO.compareTo(new BigDecimal(1.0)) < 0 );
    }
}
