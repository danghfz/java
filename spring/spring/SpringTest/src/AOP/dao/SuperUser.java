package AOP.dao;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 党
 * @version 1.0
 * 2022/5/15   14:22
 */
@Component
@Aspect //增强
public class SuperUser {
    @Before("execution(* AOP.dao.User.add(..))")
    public void before(){
        System.out.println("前置通知");
    }
    @After("execution(* AOP.dao.User.add(..))")
    public void after(){
        System.out.println("后置通知");
    }
}
