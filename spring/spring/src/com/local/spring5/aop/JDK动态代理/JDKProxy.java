package com.local.spring5.aop.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   19:13
 */
public class JDKProxy {
    public static void main(String[] args) {
        //创建接口实现类的代理对象
        Class[] interfacez = new Class[]{UserDao.class};
        UserDao userDao = new UserDaoImpl();
        //得到代理对象
        UserDao o = (UserDao)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfacez, new UserDaoProxy(userDao));
        int add = o.add(1, 2);
        System.out.println(add);
    }
}
class UserDaoProxy implements InvocationHandler{
    //创建谁的代理对象，把谁传进来
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }

    //增强的逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法之前
        System.out.println(method.getName()+"方法开始执行"+ Arrays.toString(args));
        //被增强的方法
        Object res = method.invoke(obj, args);//反射
        //方法之后
        System.out.println("方法之后"+obj.toString());
        return res;
    }
}