package com.local.threadLocal;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   14:49
 */
public class ThreadLocalTest {
    /*
    * ThreadLocal特点：
	1.可以为当前线程关联一个数据(可以像Map一样存储数据,Key为当前线程)
	2.每一个ThreadLocal对象，只能为当前线程关联一个数据，如果要为当前线程关连多个数据，就需要使用多个ThreadLocal对象实例
	3.每个ThreadLocal对象实例定义的时候,一般都是static类型
	4.ThreadLocal中保存数据,在线程销毁后,会由JVM虚拟自动释放*/
    //线程安全的HashMap,ConcurrentHashMap,Hashtable
    public static Map<String,Object> map = new ConcurrentHashMap<>();

    public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    public static Random random = new Random();
    public static class Task implements Runnable{
        @Override
        public void run(){
            //在run方法中,随机生成一个变量(线程关联的数据)
            int i = random.nextInt(1000);
            //获取当前线程名
            String name = Thread.currentThread().getName();
            map.put(name,i);
            threadLocal.set(i);
            try {
                Thread.sleep(2000);// 2s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new OrderService().creatOrder();
            Object o = threadLocal.get();
//            Object o = map.get(name);
            System.out.println(name+"线程关联的数据是:"+o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i < 3;i++){
            new Thread(new ThreadLocalTest.Task()).start();
        }
    }
}
