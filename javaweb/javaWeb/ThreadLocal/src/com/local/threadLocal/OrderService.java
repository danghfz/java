package com.local.threadLocal;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/5   14:59
 */
public class OrderService {
    public void creatOrder(){
        String name = Thread.currentThread().getName();
        System.out.println(name+"Êý¾Ý"+ThreadLocalTest.threadLocal.get());
    }
}
