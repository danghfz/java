package com.dhf.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 党
 * @version 1.0
 * 2022/8/14   16:58
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadSize = 50;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(threadSize); // 计数，当所有线程都执行完毕后，才执行后面的其他内容
        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    atomicInteger.getAndIncrement();
                }
                // 每个线程做完
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(atomicInteger.get());
    }
}
