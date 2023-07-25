package com.superNetty.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/7/5/0005 16:10
 */
public class Service {
    private static final Logger log = LoggerFactory.getLogger(Service.class);

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup parentLoop = new NioEventLoopGroup(1);
        NioEventLoopGroup childLoop = new NioEventLoopGroup();
        ChannelFuture channelFuture = new ServerBootstrap()
                .group(parentLoop, childLoop)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                    }
                })
                .bind(8080).sync();
        // sync阻塞
        ChannelFuture channelFuture1 = channelFuture.channel().closeFuture().sync();
        // channel关闭后
        parentLoop.shutdownGracefully();
        childLoop.shutdownGracefully();
    }
}
