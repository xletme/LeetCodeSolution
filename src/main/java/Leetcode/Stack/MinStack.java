package Leetcode.Stack;

import java.util.Stack;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/14 11:39
 **/
class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    private int min = Integer.MAX_VALUE ;

    /***
     *@描述
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
     * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
     *@创建时间 2020/11/17
     */

    //思路 : 结构题目中已经规划好了,使用 StringBuilder impl O(N) = 1 define min
    //有负数 不能使用str来实现
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if(min >= x){
            min = x;
        }
        stack.push(x);
        minStack.push(min);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
        if(minStack.isEmpty()){
           min = Integer.MAX_VALUE;
        }else {
            min = minStack.peek();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(5);
        minStack.push(4);
        minStack.top();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println( minStack.getMin());
        minStack.top();
    }
}
