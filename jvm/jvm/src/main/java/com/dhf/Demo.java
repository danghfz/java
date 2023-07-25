package com.dhf;

import java.lang.ref.Reference;

/**
 * @author 党
 * @version 1.0
 * 2022/9/14   8:59
 */
public class Demo {
    private String name;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                method();
            }).start();
        }
    }

    private static void method() {
        int i = 0;
        for (int i1 = 0; i1 < 500; i1++) {
            i++;
        }
        System.out.println(Thread.currentThread().getName() + "  " + i);
    }
}
