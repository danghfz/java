package com.local.boot;

import ch.qos.logback.core.db.DBHelper;
import com.local.boot.config.MyConfig;
import com.local.boot.pojo.Pet;
import com.local.boot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 党
 * @version 1.0
 * 2022/5/26   16:41
 */

/**
 * springBoot程序
 */
@SpringBootApplication(scanBasePackages = "com.local.boot")
@Slf4j // 日志 Lombok
public class MainApplication {
    public static void main(String[] args) {
        log.info("开始启动");//日志
        // 返回 IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        // 查看容器里面的组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        //从容器中获取组件
        //默认单实例对象
        Pet tomcat = run.getBean("tomcat", Pet.class);
        Pet tomcat1 = run.getBean("tomcat", Pet.class);
        System.out.println(tomcat == tomcat1);// true，
        //@Configuration(proxyBeanMethods = true)
        //代理对象调用方法，SpringBoot总会检查这个组件是否在容器中
        //保证单实例
        MyConfig bean = run.getBean(MyConfig.class);
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);// true


        //测试获取组件
        System.out.println("==============");
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        DBHelper bean1 = run.getBean(DBHelper.class);
        System.out.println(bean1);
        System.out.println("====测试@ImportResource===");
        boolean userMax = run.containsBean("userMax");
        System.out.println(userMax);


    }
}
