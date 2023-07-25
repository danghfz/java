package com.dhf.threadLocal;

import java.lang.ref.WeakReference;
import java.util.concurrent.*;

/**
 * @author 党
 * @version 1.0
 * 2022/9/8   16:51
 */
class MyData{
    public Integer get(){
        return threadLocal.get();
    }
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);
    public void add(){
        threadLocal.set(threadLocal.get() + 1);
    }
}

/**
 * 【强制】必须回收自定义的ThreadLocal 变量，尤其在线程池场景下，线程经常会被复用，
 * 如果不清理自定义的 ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用
 * try-finally块进行回收。
 */
public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 3L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                poolExecutor.execute(() -> {
                    try {
                        Integer before = myData.get();
                        myData.add();
                        Integer after = myData.get();
                        System.out.println(Thread.currentThread().getName()+"\tbefore: "+before+"\tafter: "+after);
                    }finally {
                        myData.threadLocal.remove();
                    }

                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            poolExecutor.shutdown();
        }
    }
}
