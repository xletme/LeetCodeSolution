package Leetcode.Stack;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/14 10:08
 **/
public class MakeGoodStack {

    //思路:s 拆成数组入栈, pop 结合 peek 进行筛选 最后再把stack倒序  stack.pop() - stack.peek() = 32
    //特殊情况 : s.length = 0 return ""; 1 return s; 2个stack来实现  2个 O(N)
    /*public String makeGood(String s) {
        int length = s.length();
        if(length == 0){
            return "";
        }
        if(length == 1){
            return s;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            Character cur = chars[i];
            if(stack.isEmpty()){
                //栈为空，直接入栈，continue
                stack.push(chars[i]);
                continue;
            }
            Character peek = stack.peek();
            if(isSimilar(peek,cur)){
                stack.pop();
            }else {
                stack.push(cur);
            }
        }

        StringBuffer str = new StringBuffer();
        while (!stack.isEmpty()){
            str.append(stack.pop());
        }
        return str.reverse().toString();
    }*/

    //数组实现思路：双指针 满足 |a -b| = 32 移除两个元素，数组移除元素 复杂度高消耗内存 这里采用str  StringBuilder 实现
    //关键在于 元素移除后，得重新遍历 把 i置为0   直至没有满足条件的元素 定义一个flag
    public String makeGood(String s) {
       StringBuilder builder = new StringBuilder(s);
       int len = 0 ;
       while (len != builder.length()){
           len = builder.length();
           for(int i=0;i<builder.length()-1;i++){
               if(isSimilar(builder.charAt(i) , builder.charAt(i+1))){
                   builder.delete(i,i+2);
                   break;
               }
           }
       }

       return builder.toString();

    }

    private boolean isSimilar(Character a,Character b){
        return (a-b==32 || b-a ==32);
    }

    public static void main(String[] args) {
        MakeGoodStack makeGoodStack = new MakeGoodStack();
        System.out.println(makeGoodStack.makeGood("leEeetcode"));
    }
}
