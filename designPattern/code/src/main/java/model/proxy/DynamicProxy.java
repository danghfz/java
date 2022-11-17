package model.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/17   18:05
 * 代理模式 -> 动态代理（Jdk代理，接口代理）
 */
public class DynamicProxy {
    public static void main(String[] args) {
        // 创建目标对象
        StudentDao studentDao = new StudentDao();
        // 创建代理对象
        Object proxyInstance = new StudentDaoProxyFactory(studentDao).getProxyInstance();
        // class model.proxy.$Proxy0
        System.out.println(proxyInstance.getClass());
        IStudent instance = (IStudent) proxyInstance;
        /**
         * Jdk 代理开始 。。。
         * learning ...
         * Jdk 代理提交 。。。
         */
        instance.learn();
    }
}
class StudentDaoProxyFactory{
    /**
     * 维护一个目标对象
     */
    private final Object target;

    public StudentDaoProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象 生成一个代理对象
     * @return 代理对象
     */
    public Object getProxyInstance(){
        /**
         * @param ClassLoader loader 指定当前目标对象的类加载器，获取加载器的方式固定
         * @param Class<?>[] interfaces 目标对象的接口类型，使用泛型方法确认类型
         * @param InvocationHandler h 事情处理，执行目标对象的方法时，会触发事情处理器方法，
         *                          会把当前执行的目标方法作为参数传入
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Jdk 代理开始 。。。");
                        // 反射机制调用目标对象的方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("Jdk 代理提交 。。。");
                        return returnValue;
                    }
                });
    }
}
interface IStudent{
    /**
     * learn
     */
    void learn();
}
class StudentDao implements IStudent{
    @Override
    public void learn() {
        System.out.println("learning ... ");
    }
}