package com.local.spring5.ioc.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   16:34
 */
//配置类
@Configuration //作为配置类，替换xml文件
@ComponentScan(basePackages = "com.local.spring5.ioc.annotation")//开启组件扫描
public class SpringConfig {
}
