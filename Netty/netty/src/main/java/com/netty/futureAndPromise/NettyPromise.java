package com.netty.futureAndPromise;

import com.netty.util.ThreadUtils;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/30/0030 10:03
 */
public class NettyPromise {
    private static final Logger log = LoggerFactory.getLogger(NettyFuture.class);
    private static final ThreadPoolExecutor threadPool = ThreadUtils.getDefaultThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // 创建 promise 装载结果的容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);
        threadPool.submit(() -> {
            log.info("任意线程执行计算");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                // 设置失败结果
                promise.setFailure(e);
            }
            // 设置成功结果
            promise.setSuccess(80);
        });
        // 接收结果
        Object res = promise.get();
        log.info("result {}", res);
        threadPool.shutdown();
        // 异步获取结果
        /*
        promise.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                Object object = future.get();
            }
        });
        */
    }
}
