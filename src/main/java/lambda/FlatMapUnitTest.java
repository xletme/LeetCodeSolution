package lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/6 14:57
 **/
public class FlatMapUnitTest {

    public FlatMapUnitTest() {
    }

    public static void main(String[] args) {
        String[][] data = new String[][]{{"a", "f"}, {"d", "c"}, {"e", "f","a","b"}};
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(5);
        list3.add(6);
        list3.add(7);
        res.add(list1);
        res.add(list2);
        res.add(list3);
        List<Integer> collect1 = res.stream().flatMap(Collection::stream).collect(Collectors.toList());
        //collect1.forEach(System.out::println);
        List<String> collect = Arrays.stream(data).flatMap(Arrays::stream).collect(Collectors.toList());
        //collect.forEach(System.out::println);


        List<String> collect2 = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        //collect2.forEach(System.out::println);
    }

    private static List<Object> produceVal(Object val){
        List<Object> res = new LinkedList<>();
        res.add(val);
        return res;
    }
}
