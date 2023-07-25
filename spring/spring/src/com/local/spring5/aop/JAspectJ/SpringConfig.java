package com.local.spring5.aop.JAspectJ;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   21:34
 */
@Configuration //������
@ComponentScan //����ע��ɨ��
@EnableAspectJAutoProxy(proxyTargetClass = true) //����AOP����
public class SpringConfig {

}
