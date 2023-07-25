package com.dhf.synchronizedLockUp;

/**
 * @author 党
 * @version 1.0
 * 2022/9/10   17:10
 * 锁粗化
 * 假如方法中首尾相接，前后相邻的都是同一个锁对象，
 * 那JIT编译器就会把这几个synchronized块合并成一个大块,加粗加大范围，
 * 一次申请锁使用即可，避免次次的申请和释放锁，提升了性能
 */
public class LockBigDemo {
    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock){
                System.out.println("hello1");
            }
            synchronized (lock){
                System.out.println("hello2");
            }
            synchronized (lock){
                System.out.println("hello3");
            }
        }).start();
    }
}
