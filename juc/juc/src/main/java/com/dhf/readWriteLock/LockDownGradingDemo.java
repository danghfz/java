package com.dhf.readWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author 党
 * @version 1.0
 * 2022/9/12   13:38
 * 锁降级
 *  获取写锁 -> 获取读锁 -> 释放写锁 -> 降级成 读锁
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        writeLock.lock();
        System.out.println("写入上锁");
        readLock.lock();
        System.out.println("读 上锁");
        writeLock.unlock();
        System.out.println("释放写锁");
        System.out.println("成为读锁");
        readLock.unlock();
        System.out.println("释放读锁");
    }
}
