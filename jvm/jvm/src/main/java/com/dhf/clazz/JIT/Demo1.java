package com.dhf.clazz.JIT;

import java.util.ArrayList;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   10:58
 */
public class Demo1 {

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            long start = System.nanoTime();
            for (int i1 = 0; i1 < 1000; i1++) {
                new Object();
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\n",i,(end-start));
        }
    }

}
