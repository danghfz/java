package com.dhf.LockSupportAndThreadInterrupt;

import lombok.val;

import javax.xml.ws.soap.AddressingFeature;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 党
 * @version 1.0
 * 2022/8/6   9:52
 * LockSupport可以阻塞当前线程，并且可以唤醒指定线程。
 */
public class LockSupportDemo {
    private transient volatile int a = 10;
    public static void main(String[] args) throws InterruptedException {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1开始等待");
                System.out.println("线程1结束等待");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println("线程2发出");
            }
        }, "t2").start();
    }
} // wait ，notify

class LockSupportDemo2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition(); // 获取监视器
        new Thread(() -> {
            lock.lock(); // 获取锁
            try {
                System.out.println("线程1开始等待");
                condition.await(); // 等待
                System.out.println("线程1结束等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // 释放锁
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            lock.lock();
            condition.signal();
            System.out.println("线程2发出");
            lock.unlock();
        }, "t2").start();
    }
} // await，signal

class LockSupportDemo3 {
    public static void main(String[] args) throws InterruptedException {
        val thread = new Thread(() -> {
            System.out.println("线程1开始等待");
            LockSupport.park(); // 阻塞当前线程
            LockSupport.park(); // 阻塞当前线程
            System.out.println("线程1结束等待");
        }, "t1");
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            System.out.println("线程2发出");
            LockSupport.unpark(thread); // 唤醒线程1
            LockSupport.unpark(thread);
        },"t2").start();
    }
} // park，unpark