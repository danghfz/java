package com.dhf.threadLocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author 党
 * @version 1.0
 * 2022/9/8   18:06
 * 强软弱虚引用
 */
public class ReferenceDemo {
    @Override
    protected void finalize() throws Throwable {
        System.out.println(" ---- 垃圾回收");
    }

    public static void main(String[] args) throws InterruptedException {
        // 引用对象
        ReferenceDemo referenceDemo = new ReferenceDemo();
        // 引用队列
        ReferenceQueue<ReferenceDemo> queue = new ReferenceQueue<>();
        PhantomReference<ReferenceDemo> phantomReference = new PhantomReference<>(referenceDemo, queue);
        // null get()不能获取虚引用对象
        ArrayList<Object> objects = new ArrayList<>();
        System.out.println(phantomReference.get());
        new Thread(() -> {
            while (true) {
                byte[] bytes = new byte[1024 * 1024]; // 1MB
                objects.add(bytes);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
        new Thread(() -> {
            while (true){
                Reference<? extends ReferenceDemo> poll = queue.poll();
                if (poll != null){
                    System.out.println("有虚对象加入队列");
                    break;
                }
            }
        },"t2").start();
    }

    // 弱引用 demo
    private static void weakReference() {
        WeakReference<ReferenceDemo> weakReference = new WeakReference<>(new ReferenceDemo());
        // com.dhf.threadLocal.ReferenceDemo@1540e19d
        System.out.println("before gc " + weakReference.get());
        System.gc();
        // null
        System.out.println("after gc " + weakReference.get());
    }

    // 软引用 demo
    private static void softReference() throws InterruptedException {
        SoftReference<ReferenceDemo> softReference = new SoftReference<ReferenceDemo>(new ReferenceDemo());
        // com.dhf.threadLocal.ReferenceDemo@1540e19d
        System.out.println("-----softReference " + softReference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        //  com.dhf.threadLocal.ReferenceDemo@1540e19d
        System.out.println("-----after gc【内存充足】 " + softReference.get());
        try {
            byte[] bytes = new byte[20 * 1024 * 1024]; // 20MB
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // null
            System.out.println("-----after gc【内存不够】 " + softReference.get());
        }
    }

    // 强引用 demo
    private static void strongReference() {
        // 强引用，不会被gc回收
        ReferenceDemo referenceDemo = new ReferenceDemo();
        // 置为 null，可以被回收
        referenceDemo = null;
        System.gc();
    }
}

