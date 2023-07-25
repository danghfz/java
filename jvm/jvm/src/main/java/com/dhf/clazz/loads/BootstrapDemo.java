package com.dhf.clazz.loads;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   9:53
 * 启动类加载器
 */
public class BootstrapDemo {
    public static void main(String[] args) throws ClassNotFoundException{
        Class<?> clazz = Class.forName("com.dhf.clazz.loads.F");
        // 获得类加载器
        System.out.println(clazz.getClassLoader());
    }
}
class F{
    static {
        System.out.println("bootstrap F init");
    }
}