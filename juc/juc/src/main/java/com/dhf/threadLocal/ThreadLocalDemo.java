package com.dhf.threadLocal;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author 党
 * @version 1.0
 * 2022/9/8   16:14
 */
class House{ // 资源类
    private LongAdder salve = new LongAdder();
    public synchronized void add(){ // 售卖
        salve.increment(); // + 1
    }
    public long get(){
        return salve.sum();
    }
//    ThreadLocal<Integer> salveCount = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue(){ // 设置初始值
//            return 0;
//        }
//    };
    ThreadLocal<Integer> salveCount = ThreadLocal.withInitial(() -> { // 设置初始值
        return 0;
    });
    public void addSalve(){
        salveCount.set(salveCount.get() + 1);
    }
}
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        extracted1();
    }
    // 每个线程的销售额度
    private static void extracted1() throws InterruptedException {
        House house = new House();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int i1 = new Random().nextInt(5);
                for (int i2 = 0; i2 < i1; i2++) {
                    house.add();
                    house.addSalve();
                }
                System.out.println(Thread.currentThread().getName()+"  卖出  " + house.salveCount.get());
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("共计 "+house.get());
    }

    // 需求一、一共销售多少房子(所以线程的和)
    private static void extracted() throws InterruptedException {
        House house = new House();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int i1 = new Random().nextInt(5);
                System.out.println(i1);
                for (int i2 = 0; i2 < i1; i2++) {
                    house.add();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("共计 "+house.get());
    }
}
