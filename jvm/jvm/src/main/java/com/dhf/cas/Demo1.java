package com.dhf.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   17:46
 */
public class Demo1 {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        while (true){
            int oldV = atomicInteger.get();// 获取当前值
            int newV = oldV + 2;
            if(atomicInteger.compareAndSet(oldV,newV)){
                break;
            }
        }
        System.out.println(atomicInteger.get());
    }
    public void test(){
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                atomicInteger.getAndIncrement();
                countDownLatch.countDown();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                atomicInteger.getAndIncrement();
                countDownLatch.countDown();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());
    }
}
