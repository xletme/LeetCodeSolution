package ThreadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author maoXin
 * @Description
 * @Date 16:46 2021/4/27
 */
public class RejectedTestPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        /*if (!executor.isShutdown()) {
            r.run();
        }*/
    }
}
