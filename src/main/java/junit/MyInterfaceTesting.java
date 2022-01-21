package junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Author maoXin
 * @Description
 * @Date 17:34 2022/1/19
 */
@RunWith(Parameterized.class)
public class MyInterfaceTesting {

    public MyInterface myInterface;

    public MyInterfaceTesting(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    @Test
    public final void testMyMethod_True() {
        Assert.assertTrue(myInterface.myMethod(true));
    }

    @Test
    public final void testMyMethod_False() {
        Assert.assertFalse(myInterface.myMethod(false));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instanceToTest() {
        return Arrays.asList(
                new Object[] {new MyClass1()},
                new Object[] {new MyClass2()}
        );
    }
}
