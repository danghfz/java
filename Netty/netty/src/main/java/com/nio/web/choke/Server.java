package com.nio.web.choke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/19/0019 9:01
 * 阻塞
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 使用 nio 理解阻塞io
        // 1、创建服务器通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 2、绑定端口
        serverChannel.bind(new InetSocketAddress(8080));
        // 3、accept 建立连接
        while (true) {
            log.info("waiting for client connect...");
            // 阻塞方法
            SocketChannel accept = serverChannel.accept();
            new Thread(new ServerThread(accept)).start();
            // 4、获取客户端连接的地址
            //SocketAddress remoteAddress = accept.getRemoteAddress();
            //log.info("client connect success：{}", remoteAddress);
            //// 阻塞方法
            //accept.read(buffer);
            //buffer.flip();
            //String message = StandardCharsets.UTF_8.decode(buffer).toString();
            //log.info("接收到客户端的数据：{}", message);
            //buffer.clear();
        }
    }

    static class ServerThread implements Runnable {
        private final SocketChannel socketChannel;

        public ServerThread(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            log.warn("thread name: {}", Thread.currentThread().getName());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                SocketAddress remoteAddress = socketChannel.getRemoteAddress();
                log.info("client connect success：{}", remoteAddress);
                // 阻塞方法
                socketChannel.read(buffer);
                buffer.flip();
                String message = StandardCharsets.UTF_8.decode(buffer).toString();
                log.info("接收到客户端的数据：{}", message);
                buffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
