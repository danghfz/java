package com.dhf.LockSupportAndThreadInterrupt;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 党
 * @version 1.0
 * 2022/8/5   21:12
 * 线程中断
 */
public class ThreadInterrupt {
    private volatile static boolean flag = true;
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        executor.execute(() -> {
            while (flag) {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "结束执行" + "flag:" + flag);
        });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false; // 停止执行
        });

    }
}

class AtomicBooleanDemo { // 原子布尔型
    private static AtomicBoolean flag = new AtomicBoolean(true);
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        executor.execute(() -> {
            while (flag.get()) {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "结束执行" + "flag:" + flag);
        });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(false); // 停止执行
        });
    }
}

class InterruptDemo {
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) { // 检查线程是否被中断
                    System.out.println("线程被中断");
                    break;
                }
                System.out.println("线程1执行" + Thread.currentThread().isInterrupted());
            }
            System.out.println("线程1结束执行");
        }, "t1");
        t1.start(); // 线程一开启
        new Thread(t1::interrupt).start(); // 线程二开启，并且协商中断线程一

        TimeUnit.SECONDS.sleep(2);
    }
}