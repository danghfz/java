package com.dhf.CAS;

import sun.security.krb5.internal.TGSRep;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 党
 * @version 1.0
 * 2022/8/13   18:22
 * 自旋锁
 */

/*
题目:实现一个自旋锁,复习CAS思想
自旋锁好处:循环比较获取没有类似wait的阻塞。
通过cAs操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，
B随后进来后发现当前有线程持有锁，所以只能通过自旋等待，直到A释放锁郈B随后抢到。
*/

public class SpinLockDemo {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void lock(){ // 获取锁
        // 获取当前线程
        Thread currentThread = Thread.currentThread();
        // 如果当前为空，直接获取锁，否则自选
        while (!atomicReference.compareAndSet(null,currentThread)){
            // 一直获取
        }
    }
    public void unlock(){ // 释放锁
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
    }
    public static void main(String[] args) {
        SpinLockDemo lock = new SpinLockDemo();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("A获取锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("A释放锁");
        },"A").start();

        new  Thread(()->{
            lock.lock();
            try {
                System.out.println("B获取锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("B释放锁");
        },"B").start();
    }
}
