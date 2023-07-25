package com.nio.web.no_choke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/19/0019 10:08
 * 非阻塞
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 使用 nio 理解阻塞io
        // 1、创建服务器通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        serverChannel.configureBlocking(false);
        // 2、绑定端口
        serverChannel.bind(new InetSocketAddress(8080));
        List<SocketChannel> channels = new ArrayList<>();
        // 3、accept 建立连接
        while (true) {
            // 非阻塞时，没有连接返回 null
            SocketChannel accept = serverChannel.accept();
            if (accept != null) {
                // 4、获取客户端连接的地址
                SocketAddress remoteAddress = accept.getRemoteAddress();
                log.info("client connect success：{}", remoteAddress);
                // 设置非阻塞
                accept.configureBlocking(false);
                channels.add(accept);
            }
            for (SocketChannel channel : channels) {
                channel.read(buffer);
                buffer.flip();
                String message = StandardCharsets.UTF_8.decode(buffer).toString();
                if (message.length() != 0) {
                    log.info("接收到{}的数据：{}", channel.getRemoteAddress(), message);
                }
                buffer.clear();
            }
        }
    }
}
