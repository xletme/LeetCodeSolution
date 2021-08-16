package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/11 10:12
 **/
public class AveragingDoubleExample {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A","B","X","T");
        String result = list.stream().collect(Collectors.joining(";","",""));
        System.out.println(result);
    }
}
