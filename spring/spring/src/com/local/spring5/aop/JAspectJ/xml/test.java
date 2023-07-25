package com.local.spring5.aop.JAspectJ.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/10   21:30
 */
public class test {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resource/xml/AspectJ_xml.xml");
        Book book = context.getBean("book",Book.class);
        book.buy();
        //before
        //buy...
    }
}
