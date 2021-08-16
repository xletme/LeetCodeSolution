package Leetcode.Stack;

import java.util.Stack;

/**
 * @Description
 * @Author maoXin
 * @Date 2020/11/20 10:12
 **/
class MyQueue {

    private Stack<Integer> A;
    private Stack<Integer> B;

    /***
     *@描述
     *  实现一个MyQueue类，该类用两个栈来实现一个队列。
     *  solution :  two stack  A B    A  queue.head   B queue.tail
     *@创建时间 2020/11/20
     */
    /** Initialize your data structure here. */
    public MyQueue() {
        A = new Stack<>();
        B = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        A.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
       peek();
        return B.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!B.isEmpty()) {

        } else if ( A.isEmpty()){

        } else {
            while (!A.isEmpty()){
                B.push(A.pop());
            }
        }
        return B.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return B.isEmpty() && A.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());   // 返回 1
        System.out.println(queue.empty()); // 返回 false
    }
}
