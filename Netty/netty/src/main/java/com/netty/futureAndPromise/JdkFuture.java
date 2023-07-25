package com.netty.futureAndPromise;

import com.netty.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/29/0029 10:26
 */
public class JdkFuture {
    public static final ExecutorService threadPool = ThreadUtils.getDefaultThreadPool();
    private static final Logger log = LoggerFactory.getLogger(JdkFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Object> future = threadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "success";
            }
        });
        // 阻塞等待结果
        Object o = future.get();
        JdkFuture.log.info("result:{}", o);
        threadPool.shutdown();
    }
}
