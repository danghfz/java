package com.netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/25/0025 10:58
 */
public class HelloServer {
    private static final Logger log = LoggerFactory.getLogger(HelloServer.class);

    public static void main(String[] args) {
        // 1.启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                // 2.组装两大线程组，负责处理连接以及数据传输
                // BossEventLoop，WorkerEventLoop(selector,thread)
                .group(new NioEventLoopGroup())
                // 3、选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4、worker(child) 告诉服务器Channel worker如何处理读写业务 handler处理器
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    // 5、channel代表和客户端进行数据读写的通道 Initializer初始化
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        //  6、添加handler
                        // 将接收到的ByteBuf转换成字符串
                        channel.pipeline().addLast(new StringDecoder());
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("Thread:{}, msg:{}", Thread.currentThread().getName(), msg);
                            }
                        });
                    }
                })
                // 7、绑定监听端口
                .bind(8080);

    }
}
