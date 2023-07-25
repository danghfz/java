package newFeatures.log4j2;

import com.local.spring5.Transaction.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author 党
 * @version 1.0
 * 2022/5/12   17:40
 */
//演示log4j2的使用
public class LogDemo {
    private static final Logger log = LoggerFactory.getLogger(LogDemo.class);
    //手动输出日志
    public static void main(String[] args) {
        log.debug("hello debug");
        log.error("hello error");
    }
    public void testGenericApplicationContext(){
        //创建GenericApplicationContext对象·
        GenericApplicationContext context = new GenericApplicationContext();
        //注册调用context方法进行注册
        context.refresh();//清空
        context.registerBean(User.class,()-> new User());
        //获取在spring中注册的对象
        User bean = (User)context.getBean("com.local.spring5.Transaction.pojo.User");
        context.registerBean("user1",User.class,()-> new User());
        User user1 = context.getBean("user1", User.class);
    }
}
