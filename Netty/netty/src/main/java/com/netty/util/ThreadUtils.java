package com.netty.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/29/0029 10:56
 */
public class ThreadUtils {
    private ThreadUtils() {
    }
    public static ThreadPoolExecutor getDefaultThreadPool() {
        return new ThreadPoolExecutor(
                2,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
                );
    }
}
