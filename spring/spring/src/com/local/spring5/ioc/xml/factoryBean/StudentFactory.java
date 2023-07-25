package com.local.spring5.ioc.xml.factoryBean;

import com.local.spring5.ioc.xml.bean.Student;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   12:10
 */
public class StudentFactory implements FactoryBean<Student> {
    private StudentFactory(){}
    //定义返回Bean
    @Override
    public Student getObject() throws Exception {
        return new Student();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
