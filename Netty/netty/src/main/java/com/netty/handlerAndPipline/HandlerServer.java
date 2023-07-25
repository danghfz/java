package com.netty.handlerAndPipline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/30/0030 10:47
 */
public class HandlerServer {
    private static final Logger log = LoggerFactory.getLogger(HandlerServer.class);
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 入栈处理器 从前往后执行
                        /*
                        * netty 会自动添加两个处理器，head和tail
                        * head -> ,,,  -> tail
                        * addLast() 添加到tail前面
                        * head -> in1 -> in2 -> in3 -> tail
                        * */
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("in {}",1);
                                // ctx.fireChannelRead(msg); 传递给下一个handler
                                super.channelRead(ctx, msg);
                            }
                        }).addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("in {}",2);
                                // ctx.fireChannelRead(msg);
                                super.channelRead(ctx, msg);
                            }
                        }).addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("in {}",3);
                                // ctx.fireChannelRead(msg);
                                super.channelRead(ctx, msg);
                                // ch.writeAndFlush 从tail开始往前找出战处理器
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("hello world".getBytes()));
                                // ctx.writeAndFlush 从当前处理器往前找出战处理器
                                //ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("hello world".getBytes()));
                            }
                        });
                        // 出栈处理器(只有往channel写入数据才会发送)从后往前执行
                        // head -> in1 -> in2 -> in3 -> out1 -> out2 -> out3 -> tail
                        ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("out {}",1);
                                super.write(ctx, msg, promise);
                            }
                        }).addLast(new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("out {}",2);
                                super.write(ctx, msg, promise);
                            }
                        }).addLast(new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("out {}",3);
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                })
                .bind(8080);
    }
}
