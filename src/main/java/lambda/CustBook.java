package lambda;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author maoXin
 * @Description
 * @Date 14:20 2021/4/29
 */
public class CustBook {

    public CustBook(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<CustBook> generateElements() {
        return Stream.generate(() -> new CustBook("lan lian hua"))
                .limit(1000)
                .collect(Collectors.toList());
    }

    public static String call(Spliterator<CustBook> spliterator) {
        int current = 0;
        while (spliterator.tryAdvance(item -> item.setName("test name feeling "))) {
            current++;
        }
        return Thread.currentThread().getName() + ":" + current;
    }

}
