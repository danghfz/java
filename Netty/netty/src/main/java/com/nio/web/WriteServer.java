package com.nio.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/23/0023 10:42
 */
public class WriteServer {
    private static final Logger log = LoggerFactory.getLogger(WriteServer.class);

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        server.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8080));
        while (true) {
            // 监听事件
            selector.select();
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> iterator = set.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    SelectionKey register = accept.register(selector, SelectionKey.OP_READ);
                    log.info("accept {}", accept);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 3000000; i++) {
                        stringBuilder.append("a");
                    }
                    ByteBuffer buffer = StandardCharsets.UTF_8.encode(stringBuilder.toString());
                    // 在数据量大的时候，不能一次写完，会一直处理 该 消息，类似阻塞
                    //while (buffer.hasRemaining()){
                    //    // 向 channel 写
                    //    // write 实际写的数据，可能一次写不完
                    //    int write = accept.write(buffer);
                    //    log.info("写入 {} byte",write);
                    //}
                    // 1.不要想着一次性写完
                    int write = accept.write(buffer);
                    System.out.println(write);
                    // 2.是否有剩余
                    if (buffer.hasRemaining()) {
                        // 3.在原有时间的基础上关注 可写事件
                        register.interestOps(SelectionKey.OP_WRITE + register.interestOps());
                        // 4..未 写完的数据 挂到 accept
                        register.attach(buffer);
                    }
                } else if (key.isWritable()) {
                    // 缓冲区空出来
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 未写完的数据
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int write = channel.write(buffer);
                    System.out.println(write);
                    // 就算本次还是没有写完，下一会触发可写事件继续写
                    // 数据写完后，移除buffer，清理
                    if (!buffer.hasRemaining()) {
                        // 内容写完，清除buffer
                        key.attach(null);
                        // 不需要关注可写事件
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
                iterator.remove();
            }
        }
    }
}
