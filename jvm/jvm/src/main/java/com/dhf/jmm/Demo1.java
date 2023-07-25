package com.dhf.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   14:55
 * 原子性
 */
public class Demo1 {
    private static int num = 0;
    private static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
//                synchronized (lock){
                    num++;
//                }
                countDownLatch.countDown();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
//                synchronized (lock){
                    num--;
//                }
                countDownLatch.countDown();
            }
        }).start();
        countDownLatch.await();
        System.out.println(num);
    }
}
