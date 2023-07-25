package com.dhf.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 党
 * @version 1.0
 * 2022/8/13   17:28
 */
public class CASDemo {
    public static void main(String[] args) {
        // 创建一个原子类型的变量
         AtomicInteger atomicInteger = new AtomicInteger();
        // public final boolean compareAndSet(int expect, int update)
        // expect : 期望的值  update : 更新的值
        boolean b = atomicInteger.compareAndSet(0, 2022);
        System.out.println(atomicInteger.get()); // 输出 : 2022
        // 第一次修改后，原来的值变成 2022 了，export = 5 ，return false 修改失败
        boolean b1 = atomicInteger.compareAndSet(5, 2023);
        System.out.println(atomicInteger.get()); // 输出 : 2022
        int andIncrement = atomicInteger.getAndIncrement(); // 获取并加1，返回的是修改前的值
        System.out.println(andIncrement); // 输出 : 2022
        System.out.println(atomicInteger.get()); // 输出 : 2023
    }
}
