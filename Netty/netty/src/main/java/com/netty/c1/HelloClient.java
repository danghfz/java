package com.netty.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/25/0025 11:49
 */
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        // 1、创建启动器
        new Bootstrap()
                // 2、添加eventLoop
                .group(new NioEventLoopGroup())
                // 3、选择客户端channel实现
                .channel(NioSocketChannel.class)
                // 4、添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    // 连接建立后被调用
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        // 编码器，将字符串转换成ByteBuf
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 5、连接到服务器
                .connect(new InetSocketAddress(8080))
                .sync()
                .channel()
                // 6、向服务器发送数据
                .writeAndFlush("hello,world");

    }
}
