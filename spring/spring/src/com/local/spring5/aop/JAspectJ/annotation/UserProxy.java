package com.local.spring5.aop.JAspectJ.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   20:26
 */
//��ǿ��
@Component("userProxy")
@Aspect //��ʾ���ɴ������
@Order(1)
public class UserProxy {
    //��ͬ������ȡ
    @Pointcut("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void pointcut() {

    }


    @Before("pointcut()")
    public void before() {
        System.out.println("UserProxyǰ��֪ͨ");
    }

    @After("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void after() {
        System.out.println("����֪ͨ");
    }

    @AfterReturning("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void afterReturning() {
        System.out.println("����֪ͨ");
    }

    @AfterThrowing("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void afterThrowing() {
        System.out.println("�쳣֪ͨ");
    }

    @Around(value = "execution(* com.local.spring5.aop.JAspectJ.annotation.User.add())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("����֪ͨ");
        System.out.println("����֮ǰ");

        //����ǿ�ķ���
        proceedingJoinPoint.proceed();

        System.out.println("����֮��");
    }
}
