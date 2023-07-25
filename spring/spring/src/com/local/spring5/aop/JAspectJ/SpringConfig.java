package com.local.spring5.aop.JAspectJ;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   21:34
 */
@Configuration //配置类
@ComponentScan //开启注解扫描
@EnableAspectJAutoProxy(proxyTargetClass = true) //开启AOP功能
public class SpringConfig {

}
