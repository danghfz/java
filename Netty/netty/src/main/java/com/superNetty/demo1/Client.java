package com.superNetty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/7/5/0005 16:17
 */
public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        // 连接上服务器，触发
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            for (int i = 0; i < 10; i++) {
                                ByteBuf buf = ctx.alloc().buffer(16);
                                buf.writeBytes(new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,14,15});
                                ctx.channel().writeAndFlush(buf);
                                buf.clear();
                            }
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(8080)).sync();
            Channel channel = channelFuture.channel();
            ChannelFuture closeFuture = channel.closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        } finally {
            worker.shutdownGracefully();
        }
    }
}
