package com.local.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.local.boot.pojo.Pet;
import com.local.boot.pojo.User;
import com.local.boot.pojo.jdbc;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 党
 * @version 1.0
 * 2022/5/26   17:48
 */
/*
 * proxyBeanMethods default true，代理bean方法
 * 外部无论调用多少次组件中的方法获取的都是容器中的单实例对象
 *     FULL(proxyBeanMethods=true)保证每个@Bean方法被调用多少次返回的组件都是单实例的
 *     Lite(proxyBeanMethods=false)
 *     组件依赖
 *  如果容器中有组件依赖，使用full模式，proxyBeanMethods = true，其余使用lite模式，proxyBeanMethods = false
 * */
/*
 * @Import给容器中自动创建组件
 * @Impart创建的组件,默认名称为全类名
 * com.local.boot.pojo.User
 * ch.qos.logback.core.db.DBHelper@410e94e*/
/*
* @Conditional+??
*  Class<?>[] value() default {};
*  String[] name() default {};
*  在类上，当存在或者不存在**时，该配置类才生效
*  在组件上，当存在或者不存在**时，该组件才注册在容器中
* */

/**
* EnableConfigurationProperties
 * Class<?>[] value() default {};
 * 1、开启属性配置功能
 * 2、将该组件自动注册容器中*/
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = false) //配置类
@ImportResource("classpath:bean.xml") //配置文件
@EnableConfigurationProperties({jdbc.class}) //开启属性配置功能

public class MyConfig { // 配置类本身也是组件


    //给容器中添加组件
    @Bean //标识为组件 方法名为id
    @ConditionalOnBean(name = {"tomcat"}) //条件注解
    /*String[] name组件名字，class<?> value组件类别*/
    public User user01() {// 默认单实例对象
        return new User("张三", 20);
    }

    @Bean
    public Pet tomcat() {
        return new Pet("Tomcat");
    }

}
