package com.dhf.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author 党
 * @version 1.0
 * 2022/8/14   17:10
 */
public class AtomicArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }
        int andSet = atomicIntegerArray.getAndSet(0, 1022); // 第一个参数是下标
        System.out.println(andSet);
        System.out.println(atomicIntegerArray.get(0)); // 1022
        int andIncrement = atomicIntegerArray.getAndIncrement(0);
        System.out.println(atomicIntegerArray.get(0)); // 1023
    }
}
