package model.Single;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/12   15:58
 * 单例设计模式
 */
public class Single {
}

/**
 * 饿汉式 （静态变量）
 * 这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
 * 在类装载的时候就完成实例化，没有达到 Lazy Loading 的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
 * 这种单例模式可用，可能造成内存浪费
 */
class Single1{
    // 构造方式私有化
    private Single1(){}
    private final static Single1 single1 = new Single1();
    public static Single1 getInstance(){
        return single1;
    }
}
//==========================================================
/**
 * 饿汉式 （静态代码块）
 * 这种单例模式可用，但是可能造成内存浪费
 */
class Single2{
    private Single2(){}
    private final static Single2 single2;
    static {
        single2 = new Single2();
    }
    public static Single2 getInstance(){
        return single2;
    }
}
//==========================================================
/**
 * 懒汉式 线程不安全
 * 起到了 Lazy Loading 的效果，但是只能在单线程下使用。
 * 如果在多线程下，一个线程进入了 if (singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过
 * 了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式
 * 结论：在实际开发中，不要使用这种方式.
 */
class Single3{
    private Single3(){}
    private static Single3 single3;
    public static Single3 getInstance(){
        if (single3 == null){
            single3 = new Single3();
        }
        return single3;
    }
}
//==========================================================
/**
 *  懒汉式(线程安全，同步方法)
 *  解决了线程安全问题
 *  效率太低了，每个线程在想获得类的实例时候，执行 getInstance()方法都要进行同步。而其实这个方法只执行
 * 一次实例化代码就够了，后面的想获得该类实例，直接 return 就行了。方法进行同步效率太低
 */
class Single4{
    private Single4(){}
    private static Single4 single4;
    public synchronized static Single4 getInstance(){
        if (single4 == null){
            single4 = new Single4();
        }
        return single4;
    }
}
//==========================================================

/**
 * 懒汉式 同步代码块
 */
class Single5{
    private Single5(){}
    private static Single5 single5;
    public static Single5 getInstance(){
        if (single5 == null){
            synchronized (Single5.class){
                single5 = new Single5();
            }
        }
        return single5;
    }
}
//==========================================================
/**
 * 双重检查锁 dcl锁
 * 在实际开发中，推荐使用这种单例设计模式
 */
class Single6{
    private Single6(){}
    private static volatile Single6 single6;
    public static Single6 getInstance(){
        if (single6 == null){
            synchronized (Single6.class){
                if (single6 == null){
                    single6 = new Single6();
                }
            }
        }
        return single6;
    }
}
//==========================================================
/**
 * 静态内部类
 * 避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
 * 推荐使用
 */
class Single7{
    private Single7(){}
    private static class SingletonInstance{
        private static final Single7 INSTANCE = new Single7();
    }
    public static Single7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
//==========================================================
/**
 * 枚举
 * 推荐使用
 */
enum Single8{
    single8;
}