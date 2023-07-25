package com.dhf.jmm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   14:59
 * 可见性
 */

@Slf4j
public class Demo2 {
    private static boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {
                System.out.println();
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
    }
}
