package Leetcode;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/2 11:52
 **/
class MyHashSet {

    /** Initialize your data structure here. */
    boolean[] map = new boolean[1000005];
    public MyHashSet() {

    }

    public void add(int key) {
        map[key] = true;
    }

    public void remove(int key) {
        map[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return map[key];
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        System.out.println(myHashSet.contains(2));
    }
}