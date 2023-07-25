package com.local.spring5.aop.JAspectJ.annotation;

import org.springframework.stereotype.Component;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   20:24
 */
//����ǿ��
@Component(value = "user")
public class User {
    public void add(){
        System.out.println("add...");
    }
}
