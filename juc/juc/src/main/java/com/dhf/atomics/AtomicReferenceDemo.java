package com.dhf.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author 党
 * @version 1.0
 * 2022/8/14   17:18
 */
public class AtomicReferenceDemo {
    // AtomicReference<T>
    // AtomicStampedReference<T>
    /*
     * AtomicStampedReference 修改 根据版本号 version + 1
     * AtomicMarkableReference  是否修改过  true | false
     */
    static AtomicMarkableReference<Integer> reference = new AtomicMarkableReference<>(0,false);
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            boolean marked = reference.isMarked();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 是否修改成功
            boolean b = reference.compareAndSet(0, 1, marked, !marked);
            System.out.println(Thread.currentThread().getName()+"\t"+marked+"\t"+b);
            },"t1").start();
        new Thread(()->{
            boolean marked = reference.isMarked();
            boolean b = reference.compareAndSet(0, 2, marked, !marked);
            System.out.println(Thread.currentThread().getName()+"\t"+marked+"\t"+b);
        },"t2").start();
        Thread.sleep(1000);
        System.out.println(reference.getReference());
    }
}
