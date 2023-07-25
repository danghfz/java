package com.netty.eventLoop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/27/0027 18:06
 */
public class EventLoopServer {
    private static final Logger log = LoggerFactory.getLogger(EventLoopServer.class);

    public static void main(String[] args) {
        //extracted();
        extracted2();
    }

    // eventLoop 的io操作
    private static void extracted() {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    // 连接建立后，会调用 initChannel 方法
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // msg ByteBuf类型
                                ByteBuf buf = (ByteBuf) msg;
                                String string = buf.toString(StandardCharsets.UTF_8);
                                log.info("msg: {}", string);
                            }
                        });
                    }
                })
                .bind(8080);
    }

    // eventLoop 分工细化
    public static void extracted2() {
        /*
         * 细分2. 创建一个独立的 eventLoop 处理耗时任务
         * 如果由 nioEventLoop处理，可能由于数据大，导致影响其他的channel读取，造成拥堵
         * */
        DefaultEventLoopGroup group = new DefaultEventLoopGroup();
        new ServerBootstrap()
                // 细化1: bossGroup 负责处理连接建立，workerGroup 负责处理连接读写
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    // 连接建立后，会调用 initChannel 方法
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 将耗时任务交给独立的 eventLoop 处理
                        ch.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                String string = buf.toString(StandardCharsets.UTF_8);
                                log.info("msg: {}", string);
                                /*
                                 *  交给下一个handler处理
                                 * super.channelRead(ctx, msg);
                                 *   ctx.fireChannelRead(msg);
                                 * */
                                ctx.fireChannelRead(msg);
                            }
                        }).addLast(group, "handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // msg ByteBuf类型
                                ByteBuf buf = (ByteBuf) msg;
                                String string = buf.toString(StandardCharsets.UTF_8);
                                log.info("msg: {}", string);
                            }
                        });
                    }
                })
                .bind(8080);
    }
}
