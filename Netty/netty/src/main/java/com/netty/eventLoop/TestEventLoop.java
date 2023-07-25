package com.netty.eventLoop;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/27/0027 17:27
 */
public class TestEventLoop {
    private static final Logger log = LoggerFactory.getLogger(TestEventLoop.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1、一般不直接使用 EventLoop，而是使用 eventLoopGroup
        // io 事件，普通任务，定时任务
        /*
        * 构造方法参数为 线程数，
        * super(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, executor, args);
        * */
        NioEventLoopGroup nioGroup = new NioEventLoopGroup(2);
        // 普通任务，定时任务
        //DefaultEventLoopGroup eventExecutors = new DefaultEventLoopGroup();
        // 2、获取下一个 eventLoop 对象(循环，最后一个的下一个是第一个)
        EventLoop next = nioGroup.next();
        next.next();
        /*
        * next.next();
        * class SingleThreadEventLoop
        *   public EventLoop next()
        *       return (EventLoop) super.next();
        * class AbstractEventExecutor
        *   public EventExecutor next()
        *       return this;
        * */
        // 3、执行普通任务
        /*
        * submit() 方法用于提交带有返回结果的任务，并返回 Future 对象。
        * execute() 方法用于提交没有返回结果的任务，无法获取执行结果。
        * */
        Future<Integer> submit = next.submit(() -> {
            // [nioEventLoopGroup-2-1] INFO com.netty.eventLoop.TestEventLoop - submit task
            log.info("submit task");
            return 100;
        });
        Integer integer = submit.get();
        log.info("submit.get() = {}", integer);
        nioGroup.next().execute(() -> {
            // [nioEventLoopGroup-2-2] INFO com.netty.eventLoop.TestEventLoop - execute task
            log.info("execute task");
        });

        // 4、执行定时任务
        // scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
        // command - 要执行的任务，initialDelay - 首次执行的延迟时间，period - 连续执行之间的周期，unit参数的时间单位
        next.scheduleAtFixedRate(() -> {
            // [nioEventLoopGroup-2-1] INFO com.netty.eventLoop.TestEventLoop - schedule task
            log.info("schedule task");
        }, 0, 2, TimeUnit.SECONDS);
    }
}
