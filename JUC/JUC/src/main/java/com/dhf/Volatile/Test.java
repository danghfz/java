package com.dhf.Volatile;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 党
 * @version 1.0
 * 2022/8/7   11:27
 */
public class Test {
    static boolean flag = true;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("线程1开始执行");
            while (flag){
                //System.out.println();
            }
            System.out.println("线程1执行结束");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        flag = false;
    }

}
class Auto{
    volatile static int num = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 100; i1++) {
                    double random = Math.random();
                    try {
                        TimeUnit.MICROSECONDS.sleep((long) random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num++;
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num);
    }
}