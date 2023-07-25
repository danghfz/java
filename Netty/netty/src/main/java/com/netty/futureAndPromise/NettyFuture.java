package com.netty.futureAndPromise;

import com.netty.util.ThreadUtils;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/29/0029 10:55
 */
public class NettyFuture {
    public static final ExecutorService threadPool = ThreadUtils.getDefaultThreadPool();
    private static final Logger log = LoggerFactory.getLogger(NettyFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Object> future = eventLoop.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "success";
            }
        });
        //1. 同步获取结果，立即获取
        Object now = future.getNow();
        log.info("res {}", now);
        // 阻塞到获取结果
        Object o = future.get();
        log.info("res {}", o);

        // 2、异步获取结果
        future.addListener(new GenericFutureListener<Future<? super Object>>() {
            @Override
            public void operationComplete(Future<? super Object> future) throws Exception {
                Object o1 = future.get();
            }
        });

        threadPool.shutdown();
    }
}
