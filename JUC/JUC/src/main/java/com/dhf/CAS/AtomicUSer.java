package com.dhf.CAS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 党
 * @version 1.0
 * 2022/8/13   18:10
 */
public class AtomicUSer {
    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User old = new User("张三", 18);
        userAtomicReference.set(old);

        boolean b = userAtomicReference.compareAndSet(old,new User("张三", 19));
        System.out.println(userAtomicReference.get());
        // 修改失败
        boolean v = userAtomicReference.compareAndSet(old, new User("张三", 20));
        System.out.println(userAtomicReference.get());
    }
}

@Data
@AllArgsConstructor
class User{
    private String name;
    private int age;
}