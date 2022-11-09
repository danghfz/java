package principle.DependencyInversion;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   10:15
 * 依赖倒转原则
 */
public class Dependency1 {
}
class Email{
    public String getInfo(){
        return "电子邮件信息 hello world";
    }
}

/**
 * 问题：
 * 直接依赖具体的类
 * 假设接受其他类型的信息(短信...)，Person也要新加方法
 * 解决方式:
 * 引入一个抽象的接口，与接口进行依赖，让Email等实现该接口,符合依赖倒转原则
 */
class Person{
    // 接收消息
    public void accept(Email email){
        email.getInfo();
    }
}