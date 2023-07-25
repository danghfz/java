package com.dhf.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author 党
 * @version 1.0
 * 2022/8/4   22:25
 */
public class MyFuture {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        String str = futureTask.get();
        System.out.println("str:"+str); // 获取返回值
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName());
        return "hello Callable";
    }
}