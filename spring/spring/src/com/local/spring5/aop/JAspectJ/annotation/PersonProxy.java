package com.local.spring5.aop.JAspectJ.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   21:18
 */
@Component //创建对象
@Aspect //表示生成代理对象
@Order(2) //指定生成代理对象的顺序
public class PersonProxy {
    @Pointcut("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add(..))")
    public void pointcut(){}
    @Before("pointcut()")
    public void before(){
        System.out.println("PersonProxy前置通知");
    }
}
