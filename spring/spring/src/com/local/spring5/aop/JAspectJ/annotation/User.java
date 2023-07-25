package com.local.spring5.aop.JAspectJ.annotation;

import org.springframework.stereotype.Component;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   20:24
 */
//被增强类
@Component(value = "user")
public class User {
    public void add(){
        System.out.println("add...");
    }
}
