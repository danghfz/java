package com.dhf.clazz;

import java.lang.reflect.Method;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   11:39
 * 反射优化
 */
public class ReflectDemo {
    public static void foo(){
        System.out.println("foo ...");
    }
    public static void main(String[] args) throws Exception{
        Method foo = ReflectDemo.class.getDeclaredMethod("foo");
        for (int i = 0; i <= 16; i++) {
            System.out.printf("%d\t",i);
            foo.invoke(null);
        }
    }
}
