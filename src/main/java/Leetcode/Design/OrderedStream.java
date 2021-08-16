package Leetcode.Design;

import java.util.ArrayList;
import java.util.List;

public class OrderedStream {

    /**
     * 使用Array来实现   a 构造方法初始化 ptr  array； b insert 检查是否当前ptr是否有值 没有值，则添加 有值 ，则输出连续数值 并置ptr
     * c 有值返回连续数组 没值返回空
     */
    private int ptr;
    private String[] arr;

    // 5   0 1 2 3
    public OrderedStream(int n) {
        ptr = 0;
        arr = new String[n];
    }

    /**
     * @Description: harry
     * @Date: 2020/12/29 13:56
     */
    public List<String> insert(int id, String value) {
        arr[id - 1] = value;
        ArrayList<String> list = new ArrayList<>();
        if (arr[ptr] != null) {
            for (int i = ptr; i < arr.length; i++) {
                if (arr[ptr] != null) {
                    list.add(arr[ptr]);
                    ptr++;
                } else {
                    break;
                }
            }
        } else {
            return list;
        }
        return list;
    }

    public static void main(String[] args) {
        OrderedStream obj = new OrderedStream(5);
        obj.insert(3,"ccccc");
        obj.insert(1,"aaaaa");
        obj.insert(2,"bbbbb");
        obj.insert(5,"eeeee");
        obj.insert(4,"ddddd");
    }

}
