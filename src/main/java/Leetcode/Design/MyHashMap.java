package Leetcode.Design;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author maoXin
 * @Description:
 * @Date 2020/12/25 11:06
 */
public class MyHashMap {

    private Object[] arr;

    /** Initialize your data structure here. */
    public MyHashMap() {
       arr = new Object[10001];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        arr[hash(key)] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if (arr[hash(key)] == null) {
            return -1;
        }
        return (int)arr[hash(key)];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        arr[hash(key)] = null;
    }

    private int hash(int key) {
        return key % 10000;
    }

    @Test
    public void testMyHashMap() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        Assert.assertEquals(1,hashMap.get(1)); // 返回 1
        Assert.assertEquals(-1,hashMap.get(3)); // 返回 -1 (未找到)
        hashMap.put(2, 1); // 更新已有的值
        Assert.assertEquals(1,hashMap.get(2));// 返回 1
        hashMap.remove(2); // 删除键为2的数据
        Assert.assertEquals(-1, hashMap.get(2));// 返回 -1 (未找到)
    }
}
