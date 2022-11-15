package model.prototype;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   17:03
 * spring 中使用 原型模式
 */
public class JDK {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");
        // 通过 id 获取
        Object sheep = context.getBean("sheep");
        System.out.println(sheep);
        //Sheep(name=zd, age=18, color=red)
        Object sheep1 = context.getBean("sheep");
        // false
        System.out.println(sheep1 == sheep);
    }
}
