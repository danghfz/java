package com.dhf.atomics;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author 党
 * @version 1.0
 * 2022/8/16   8:55
 */
public class AdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder(); // 创建一个初始为 0 的累加器
        longAdder.increment();// + 1
        longAdder.increment();// + 1
        longAdder.add(10);// + 10
        System.out.println(longAdder.floatValue()); // 返回float 12.0
        System.out.println(longAdder.longValue()); // 相当于 sum()  12
        System.out.println(longAdder.sum());// 返回当前总和  12
        System.out.println(longAdder.sumThenReset());//重置为0  12
        System.out.println(longAdder.longValue()); // 0
    }
}
