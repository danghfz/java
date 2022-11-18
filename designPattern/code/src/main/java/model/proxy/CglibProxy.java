package model.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/18   7:22
 * cglib 代理
 */
public class CglibProxy {
    public static void main(String[] args) {
        Student student = new Student();
        // 获取代理对象
        Object proxyInstance = new ProxyFactory(student).getProxyInstance();
        Student proxy = (Student) proxyInstance;
        /**
         * 执行代理对象的方法，触发 intercept 方法
         * cglib 开始代理 ...
         * model.proxy.Student@20fa23c1=>read
         * cglib 提交 ...
         */
        proxy.read();
    }
}
class ProxyFactory implements MethodInterceptor {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回一个代理对象 target的代理对象
     * @return object
     */
    public Object getProxyInstance(){
        // 1、创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 2、设置父类i
        enhancer.setSuperclass(target.getClass());
        // 3、设置回调函数
        enhancer.setCallback(this);
        // 4、创建子类对象，即代理对象
        return enhancer.create();
    }

    /**
     * 重写 intercept 会调用目标对象的代理方法
     * Invoke the original method, on a different object of the same type.
     * @param obj the compatible object; recursion will result if you use the object passed as the first
     * argument to the MethodInterceptor (usually not what you want)
     * @param args the arguments passed to the intercepted method; you may substitute a different
     * argument array as long as the types are compatible
     * @see MethodInterceptor#intercept
     * @throws Throwable the bare exceptions thrown by the called method are passed through
     * without wrapping in an <code>InvocationTargetException</code>
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 开始代理 ... ");
        Object returnVal = method.invoke(target, args);
        System.out.println("cglib 提交 ... ");
        return returnVal;
    }
}

/**
 * 被代理对象 没有实现接口
 */
class Student{
    public void read(){
        System.out.println(this + "=>read");
    }
}