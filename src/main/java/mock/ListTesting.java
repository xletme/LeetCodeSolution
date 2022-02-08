package mock;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author maoXin
 * @Description
 * @Date 14:17 2022/1/20
 */
public class ListTesting {

    @BeforeClass
    public static void firstInitClass() {
        System.out.println("before class execute 1");
    }

    @Before
    public void testBefore() {
        System.out.println("before test execute 2");
    }

    @Test
    public void testListInEasyMock() {
        List<String> list  = Arrays.asList("1", "2");
        System.out.println("test mock execute 3");
        Assert.assertEquals("1", list.get(0));
    }

    @After
    public void testAfter() {
        System.out.println("after test execute 4");
    }

    @AfterClass
    public static void endDestroyClass() {
        System.out.println("final method execute 5");
    }

}
