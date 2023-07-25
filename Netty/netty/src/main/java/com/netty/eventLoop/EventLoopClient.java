package com.netty.eventLoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/27/0027 18:13
 */
public class EventLoopClient {
    private static final Logger log = LoggerFactory.getLogger(EventLoopClient.class);
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("localhost", 8080)
                .sync() // 等待连接建立
                .channel();
        // 发送数据
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            channel.writeAndFlush(line);
            log.info("send msg: {}", line);
        }
    }
}
