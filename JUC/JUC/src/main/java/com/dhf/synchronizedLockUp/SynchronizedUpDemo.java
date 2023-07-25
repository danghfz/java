package com.dhf.synchronizedLockUp;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 党
 * @version 1.0
 * 2022/9/9   17:55
 *  -XX:BiasedLockingStartupDelay=0 偏向随启动时间 0
 *  -XX:-UseBiasedLocking 关闭偏向锁
 */
class Ticket {
    private int number = 50;
    final Object lock = new Object();
    ReentrantLock reentrantLock = new ReentrantLock(true); // 公平锁
    public void sale() {
        while (true) {
            reentrantLock.lock();
            synchronized (lock) {
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "\t卖出第" + (Math.abs(number - 50) + 1) + "张票");
                    number--;
                } else {
                    break;
                }
            }
        }
    }
}

public class SynchronizedUpDemo {
    public static void main(String[] args) throws InterruptedException {


    }
    // 锁 和 hashcode
    private static void lockAndHashCode() throws InterruptedException {
        //        TimeUnit.SECONDS.sleep(5); // 睡眠 5s 开启偏向锁
//        Object o = new Object();
//        System.out.println("轻量锁：");
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());// 0000 0101
//        o.hashCode(); // 计算hash
//        synchronized (o){
//            System.out.println("变成轻量锁：");
//            System.out.println(ClassLayout.parseInstance(o).toPrintable()); // 。。。1101 1000
//        }
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object(); // 偏向锁
        synchronized (o){
            o.hashCode();
            System.out.println("偏向锁过程中，计算一致性哈希，膨胀成重量锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable()); // 。。。0100 1010
        }
    }

    // 重量级锁
    private static void heavyLock() {
        Object o = new Object();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+":");
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }
            }).start();
        }
    }

    // 轻量级锁
    // -XX:-UseBiasedLocking 关闭偏向锁
    private static void extracted1() {
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    // -XX:-UseBiasedLocking 关闭偏向锁
    // -XX:+UseBiasedLocking 开启偏向锁
    // -XX:BiasedLockingStartupDelay=0 偏向随启动时间 0
    // 偏向锁
    private static void biasedLock() {
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    private static void extracted() {
        Ticket ticket = new Ticket();
        for (int i = 0; i < 3; i++) {
            new Thread(ticket::sale).start();
        }
    }

    // 无锁demo
    private static void noLock() {
        Object o = new Object(); // Object heard
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(o.hashCode());
        System.out.println("---------------- 调用hashcode ---------------------");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
