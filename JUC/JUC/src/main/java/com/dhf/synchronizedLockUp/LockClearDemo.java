package com.dhf.synchronizedLockUp;

import com.sun.corba.se.spi.ior.ObjectKey;

/**
 * @author 党
 * @version 1.0
 * 2022/9/10   17:02
 * 锁消除
 */
public class LockClearDemo {
    public final Object lock = new Object();
    public void m(){
//        synchronized (lock){
//            System.out.println("hello ----- Lock");
//        }
        // 锁消除问题，JIT编译器会无视它，
        Object o = new Object();
        synchronized (o){
            System.out.println("hello ----- Lock"+" -- "+o.hashCode()+" -- "+lock.hashCode());
        }
    }
    public static void main(String[] args) {
        LockClearDemo lockClearDemo = new LockClearDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lockClearDemo.m();
            }).start();
        }
    }
}
