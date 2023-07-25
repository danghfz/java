package com.local.threadLocal;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ��
 * @version 1.0
 * 2022/5/5   14:49
 */
public class ThreadLocalTest {
    /*
    * ThreadLocal�ص㣺
	1.����Ϊ��ǰ�̹߳���һ������(������Mapһ���洢����,KeyΪ��ǰ�߳�)
	2.ÿһ��ThreadLocal����ֻ��Ϊ��ǰ�̹߳���һ�����ݣ����ҪΪ��ǰ�̹߳���������ݣ�����Ҫʹ�ö��ThreadLocal����ʵ��
	3.ÿ��ThreadLocal����ʵ�������ʱ��,һ�㶼��static����
	4.ThreadLocal�б�������,���߳����ٺ�,����JVM�����Զ��ͷ�*/
    //�̰߳�ȫ��HashMap,ConcurrentHashMap,Hashtable
    public static Map<String,Object> map = new ConcurrentHashMap<>();

    public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    public static Random random = new Random();
    public static class Task implements Runnable{
        @Override
        public void run(){
            //��run������,�������һ������(�̹߳���������)
            int i = random.nextInt(1000);
            //��ȡ��ǰ�߳���
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
            System.out.println(name+"�̹߳�����������:"+o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i < 3;i++){
            new Thread(new ThreadLocalTest.Task()).start();
        }
    }
}
