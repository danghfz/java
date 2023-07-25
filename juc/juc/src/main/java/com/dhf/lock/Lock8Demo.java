package com.dhf.lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 党
 * @version 1.0
 * 2022/8/5   15:05
 * 8锁案例
 * 线程 操作 资源类
 */
public class Lock8Demo {
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 10,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

    }
}
// 公平锁和非公平锁
class SaleTicketDemo {
    static class Ticket { // 资源类
        private int ticket = 50;
        ReentrantLock lock = new ReentrantLock(); // 非公平锁
        ReentrantLock lock1 = new ReentrantLock(true); // 公平锁
        public void sale(){
            while (true){
                lock1.lock();
                try {
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + "正在卖第" + (ticket--) + "张票");
                        Thread.sleep(1000);
                    }else {
                        System.out.println("票已卖完");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock1.unlock();
                }
            }
        }
    }
    static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 0; i < 3; i++) { // 三个线程卖票
            executorService.execute(() -> {
                ticket.sale(); // 开始买票
            });
        }
    }
}

// 可重用锁
class RecursionLock {
    static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        final Object o = new Object();
        executorService.execute(()->{
            synchronized (o) {
                System.out.println("线程1");
                synchronized (o){
                    System.out.println("doSomeThing");
                }
            }
        }); //

        ReentrantLock lock = new ReentrantLock();
        executorService.execute(() -> {
            lock.lock(); // 计数器 +1
            try {
                System.out.println("dosomething");
                lock.lock(); // 计数器 +1
                try {
                    System.out.println("dosome");
                }finally {
                    lock.unlock(); // 计数器 -1
                }
            }finally {
                lock.unlock(); // 计数器 -1
            }

        });
    }
}