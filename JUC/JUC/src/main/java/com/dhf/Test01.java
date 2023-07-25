package com.dhf;

import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 党
 * @version 1.0
 * 2022/8/4   21:48
 */
public class Test01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "hello");
            }
        }, "text");
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(
                    Thread.currentThread().getName() + i
            );
        }
    }
}