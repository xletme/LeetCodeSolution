package lambda;

import org.junit.Test;

/**
 * @Author maoXin
 * @Description
 * @Date 14:40 2021/4/29
 */
public class TestSpliteratorFly {

    @Test
    public void testSpliterator() {
        /*List<CustBook> custBooks = CustBook.generateElements();


        Spliterator<CustBook> split1 = CustBook.generateElements().spliterator();
        Spliterator<CustBook> split2 = split1.trySplit();
        System.out.println("before size split1 : "+split1.estimateSize());
        System.out.println("before character  split1: "+ split1.characteristics());
        System.out.println("before size split2 : "+split2.estimateSize());
        System.out.println("before character split2 : "+ split2.characteristics());
        System.out.println(CustBook.call(split1));
        System.out.println(CustBook.call(split2));
        System.out.println("before size split1 : "+split1.estimateSize());
        System.out.println("before character  split1: "+ split1.characteristics());
        System.out.println("before size split2 : "+split2.estimateSize());
        System.out.println("before character split2 : "+ split2.characteristics());
        System.out.println(Spliterator.ORDERED | Spliterator.SIZED |Spliterator.SUBSIZED);*/



        try(TestAutoClose testAutoClose = new TestAutoClose()) {
            testAutoClose.doSomething();
            System.out.println(testAutoClose.isClosed());
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
