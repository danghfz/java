package com.dhf.atomics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author 党
 * @version 1.0
 * 2022/8/14   17:40
 * 对象属性修改原子类
 */
public class AtomicFieldUpdater {
    public static volatile User user = new User("张三", 0);

    public static void main(String[] args) throws Exception {
        //修改年龄
        AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    atomicIntegerFieldUpdater.getAndIncrement(user);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(user.getAge());
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    public volatile int age;
}
class Res{
    public volatile Boolean flag = false;
}
class Demo2{
    public static void main(String[] args) {
        Res res = new Res();
        AtomicReferenceFieldUpdater<Res,Boolean> reference =
                AtomicReferenceFieldUpdater.newUpdater(Res.class,Boolean.class,"flag");
        //public abstract boolean compareAndSet(T obj, V expect, V update);
        boolean b = reference.compareAndSet(res, false, true);
        System.out.println(b+"\t"+res.flag);
    }
}