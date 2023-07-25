package com.netty.channel;

import com.netty.futureAndPromise.NettyPromise;
import com.sun.javafx.util.Logging;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/28/0028 15:57
 */
public class TestChannel {
    private static final Logger log = LoggerFactory.getLogger(TestChannel.class);
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            2,
            5,
            2,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                // 异步非阻塞 main线程调用， 由 nio 线程执行
                .connect("localhost", 8080);
        // 2.1 使用 sync方法同步处理结果，阻塞当前线程，直到连接建立完成
        Channel channel = channelFuture.sync().channel();
        // 2.2 使用 addListener 方法异步处理结果
        /*
        channelFuture.addListener(new ChannelFutureListener() {
            @Override // nio线程 连接建立好后，调用 operationComplete(由nio线程执行)
            public void operationComplete(ChannelFuture future) throws Exception {
                // 由nio执行
                Channel channel = future.channel();
                log.info("channel: {}", channel);
            }
        });*/

        threadPool.execute(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                if ("q".equals(msg)) {
                    // close 是一个异步操作 实际是由nio线程执行
                    channel.close();
                    //log.info("channel关闭之后的操作，该处无法完成");
                    break;
                }
                channel.writeAndFlush(msg);
            }
        });
        //log.info("channel关闭之后的操作，该处无法完成");

        // 获取 CloseFuture 对象， 1.同步处理结果 2.异步处理结果
        ChannelFuture channelFuture1 = channel.closeFuture();
        // 1.同步处理结果，阻塞当前线程，直到 channel 关闭完成
        //channelFuture1.sync();
        // 2.异步处理结果
        channelFuture1.addListener(new ChannelFutureListener() {
            @Override
            // 执行close的线程执行 operationComplete，nio线程
            public void operationComplete(ChannelFuture future) throws Exception {
                threadPool.shutdown();
                // 关闭线程组
                group.shutdownGracefully();
                log.info("channel 关闭完成");
            }
        });

    }
}
