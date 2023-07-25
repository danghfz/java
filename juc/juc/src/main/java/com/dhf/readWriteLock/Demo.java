package com.dhf.readWriteLock;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 党
 * @version 1.0
 * 2022/9/12   10:45
 */
class Resource {
    Hashtable<String, Object> map = new Hashtable<>();
    // 独占锁
    private final ReentrantLock lock = new ReentrantLock();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String k, Object v) {
        readWriteLock.writeLock().lock();
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "正在写入");
            TimeUnit.MILLISECONDS.sleep(500);
            map.put(k, v);
            System.out.println(thread.getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "正在读");
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(name + "读取完成" + map.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void writeOnly(String k, Object v) {
        lock.lock();
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "正在写入");
            TimeUnit.MILLISECONDS.sleep(500);
            map.put(k, v);
            System.out.println(thread.getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void readOnly(String key) {
        lock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "正在读");
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(name + "读取完成" + map.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class Demo {
    public static void main(String[] args) {

        Resource resource = new Resource();
        // 写
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                resource.writeOnly(String.valueOf(finalI), finalI);
//            }).start();
//        }
//
//        // 读
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                resource.readOnly(String.valueOf(finalI));
//            }).start();
//        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                resource.write(String.valueOf(finalI), finalI);
            }).start();
        }

        // 读
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                resource.read(String.valueOf(finalI));
            }).start();
        }
    }
}

