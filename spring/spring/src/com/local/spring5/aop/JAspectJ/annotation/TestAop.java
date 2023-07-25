package com.local.spring5.aop.JAspectJ.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import com.local.spring5.aop.JAspectJ.annotation.User;
/**
 * @author µ³
 * @version 1.0
 * 2022/5/10   20:39
 */
public class TestAop {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resource/xml/bean.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }
}
