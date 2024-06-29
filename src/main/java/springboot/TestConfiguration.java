package springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2024/5/6 21:05
 */
public class TestConfiguration {

    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();
        context.regi("myConfig", Myconfig.class);

    }


    static class Myconfig {

        @Bean
        public void getBean1() {

        }

        @Bean
        public void getBean2() {

        }
    }
}
