package com.local.spring5.aop.JDK��̬����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   19:13
 */
public class JDKProxy {
    public static void main(String[] args) {
        //�����ӿ�ʵ����Ĵ������
        Class[] interfacez = new Class[]{UserDao.class};
        UserDao userDao = new UserDaoImpl();
        //�õ��������
        UserDao o = (UserDao)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfacez, new UserDaoProxy(userDao));
        int add = o.add(1, 2);
        System.out.println(add);
    }
}
class UserDaoProxy implements InvocationHandler{
    //����˭�Ĵ�����󣬰�˭������
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }

    //��ǿ���߼�
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //����֮ǰ
        System.out.println(method.getName()+"������ʼִ��"+ Arrays.toString(args));
        //����ǿ�ķ���
        Object res = method.invoke(obj, args);//����
        //����֮��
        System.out.println("����֮��"+obj.toString());
        return res;
    }
}