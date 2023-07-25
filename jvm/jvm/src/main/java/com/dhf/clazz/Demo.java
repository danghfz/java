package com.dhf.clazz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/9/16   9:40
 * 演示 字节码指令 和 操作数栈、常量池的关系
 */
public class Demo {
    public static void main(String[] args) {
//        int a = 10;
//        // 0111 1111 1111 1111
//        int b = Short.MAX_VALUE + 1;
//        int c = a + b;
//        System.out.println(c);
        int a = 10;
        int b = a++ + ++a + a--;
        System.out.println(a); // 11
        System.out.println(b); // 34
    }
}
class demo{
    private String a = "s1";
    {
        b = 20;
    }
    private int b = 10;
    {
        a = "s2";
    }
    public demo(String a,int b){
        this.a = a;
        this.b = b;
    }
    public static void main(String[] args){
        demo demo = new demo("s3",30);
        System.out.println(demo.a); // s3
        System.out.println(demo.b); // 30
    }
}
class synDemo{
    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock){
            System.out.println("hello");
        }
//        List<Integer> list = Arrays.asList(1, 2, 3);
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()){
//            Integer next = iterator.next();
//            System.out.println(next);
//        }
    }
}
class TryWithResource{
    static final String FILE_PATH = "E:\\java\\jvm\\src\\main\\resources\\static\\hello.txt";
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH)){
            System.out.println(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
// 桥接方法演示
class BridgingMethod{
    class A{
        public Number m(){
            return 1;
        }
    }
    class B extends A{
        @Override
        public Integer m() {
            return 2;
        }
    }
}