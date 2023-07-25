package com.local.spring5.aop.JAspectJ.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   20:26
 */
//增强类
@Component("userProxy")
@Aspect //表示生成代理对象
@Order(1)
public class UserProxy {
    //相同切入点抽取
    @Pointcut("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void pointcut() {

    }


    @Before("pointcut()")
    public void before() {
        System.out.println("UserProxy前置通知");
    }

    @After("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void after() {
        System.out.println("后置通知");
    }

    @AfterReturning("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void afterReturning() {
        System.out.println("返回通知");
    }

    @AfterThrowing("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void afterThrowing() {
        System.out.println("异常通知");
    }

    @Around(value = "execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("环绕通知");
        System.out.println("环绕之前");

        //被增强的方法
        proceedingJoinPoint.proceed();

        System.out.println("环绕之后");
    }
}
