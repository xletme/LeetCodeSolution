package Leetcode.Stack;

import java.util.Stack;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/17 15:07
 **/
class CQueue {

    Stack<Integer> a,b;
    /***
     *@描述
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     *
     * 示例 1：
     *
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     * 思路：初始化 a b 2个空stack ，
     * （appendTail）元素来了先插入a中,
     * (deleteHead)然后删除头部元素的时候，
     * b中有值，返回b.pop
     * b没有值 a没有值 ，返回-1
     * b没有值 a有值 把b.push(a.pop()) 达到反序实现队列 返回 b.pop
     * O(N) O(N)
     *@创建时间 2020/11/17
     */
    public CQueue() {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void appendTail(int value) {
        a.push(value);
    }

    public int deleteHead() {
        if (!b.isEmpty()) {
            return b.pop();
        } else if (a.isEmpty()) {
            return -1;
        } else {
            while (!a.isEmpty()){
                b.push(a.pop());
            }
        }
        return b.pop();
    }
}
