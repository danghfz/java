package newFeatures.log4j2;

import com.local.spring5.Transaction.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ��
 * @version 1.0
 * 2022/5/12   17:40
 */
//��ʾlog4j2��ʹ��
public class LogDemo {
    private static final Logger log = LoggerFactory.getLogger(LogDemo.class);
    //�ֶ������־
    public static void main(String[] args) {
        log.debug("hello debug");
        log.error("hello error");
    }
    public void testGenericApplicationContext(){
        //����GenericApplicationContext����
        GenericApplicationContext context = new GenericApplicationContext();
        //ע�����context��������ע��
        context.refresh();//���
        context.registerBean(User.class,()-> new User());
        //��ȡ��spring��ע��Ķ���
        User bean = (User)context.getBean("com.local.spring5.Transaction.pojo.User");
        context.registerBean("user1",User.class,()-> new User());
        User user1 = context.getBean("user1", User.class);
    }
}
