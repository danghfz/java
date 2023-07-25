package com.dhf.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * @author 党
 * @version 1.0
 * 2022/8/16   9:01
 * LongAccumulator
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                // 累加器的累加方法 left 当前值  right 增加的值
                return left + right;
            }
        }, 0); // 初始值为 0
        longAccumulator.accumulate(1);// + 1    1
        longAccumulator.accumulate(2);// + 2    3
        System.out.println(longAccumulator.get()); // 返回当前总和  3
    }
}
