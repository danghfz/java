package com.dhf.completableFuture;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author 党
 * @version 1.0
 * 2022/8/4   22:49
 * Future 结合 线程池
 */
public class FuturePool {
    public static void main(String[] args) throws Exception {
        // 线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        // 创建FutureTask任务
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "hello FutureTask";
        });
        threadPoolExecutor.submit(futureTask);// submit( Runnable task )
//        String s = futureTask.get(3, TimeUnit.SECONDS);// get( long timeout, TimeUnit unit )
//        System.out.println(s);
        while (true){
            if(futureTask.isDone()){ // 是否执行完
                System.out.println(futureTask.get());
                System.out.println("over");
                break;
            }else {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("执行中");
            }
        }
        threadPoolExecutor.shutdown();
    }
}
