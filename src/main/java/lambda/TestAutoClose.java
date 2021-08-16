package lambda;

/**
 * @Author maoXin
 * @Description
 * @Date 14:56 2021/4/29
 */
public class TestAutoClose  implements  AutoCloseable{

    private boolean closed = false;

    @Override
    public void close() throws IllegalArgumentException  {
        closed = true;
        // .... close soured operation
        System.out.println("success");
    }

    public void doSomething () {
        System.out.println("i am doing some urge job");
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
