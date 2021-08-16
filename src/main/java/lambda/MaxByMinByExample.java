package lambda;

import org.bouncycastle.math.ec.MixedNafR2LMultiplier;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/9/11 11:00
 **/
public class MaxByMinByExample {

    public static void main(String[] args) {
        Map<Integer, String> collect = Arrays.asList(30, 10, 20, 35).stream().collect(Collectors.toMap(k -> k, v -> v + "ttt"));

        collect.forEach((k,v)->System.out.println("k:"+k+";v:"+v));
    }

}
