package com.local.spring5.aop.JAspectJ.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   21:18
 */
@Component //��������
@Aspect //��ʾ���ɴ������
@Order(2) //ָ�����ɴ�������˳��
public class PersonProxy {
    @Pointcut("execution(* com.local.spring5.aop.JAspectJ.annotation.User.add(..))")
    public void pointcut(){}
    @Before("pointcut()")
    public void before(){
        System.out.println("PersonProxyǰ��֪ͨ");
    }
}
