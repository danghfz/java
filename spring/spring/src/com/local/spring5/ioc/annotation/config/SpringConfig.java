package com.local.spring5.ioc.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   16:34
 */
//������
@Configuration //��Ϊ�����࣬�滻xml�ļ�
@ComponentScan(basePackages = "com.local.spring5.ioc.annotation")//�������ɨ��
public class SpringConfig {
}
