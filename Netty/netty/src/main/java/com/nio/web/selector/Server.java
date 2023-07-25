package com.nio.web.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
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
 * @date 2023/6/19/0019 15:53
 * 使用selector管理多个channel
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws IOException {
        // 1、创建selector 管理多个channel
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        /**
         * selector：表示要注册到的Selector对象。Selector负责监视通道的事件，并在事件发生时通知相应的处理程序。
         *
         * interestOps：表示所关注的事件类型。可以使用以下常量进行设置：
         *
         * SelectionKey.OP_CONNECT：连接就绪事件（客户端通道）
         * SelectionKey.OP_ACCEPT：接受连接事件（服务器通道）
         * SelectionKey.OP_READ：读取数据事件
         * SelectionKey.OP_WRITE：写入数据事件
         * 可以使用按位或（|）操作符组合多个事件类型，如SelectionKey.OP_READ | SelectionKey.OP_WRITE表示关注读取和写入事件。
         *
         * attachment：表示可选的附件对象。可以将一个对象附加到SelectionKey上，在处理事件时获取该附件对象。如果不需要附件，可以传入null
         */
        // 2、注册channel到selector
        // 事件发生后，通过 register
        // 当前的serverChannel仅仅需要关注accept事件 (0 表示不关注任何事件)
        serverChannel.register(selector, SelectionKey.OP_ACCEPT, null);
        log.info("serverChannel register success");
        serverChannel.bind(new InetSocketAddress(DEFAULT_PORT));
        while (true) {
            // 3、select()、让线程阻塞，有事件发生，继续执行
            // select()在事件发生后，要么处理，要么取消，否则会一直存在，不阻塞
            selector.select();
            log.info("The selector detects the event being sent");
            // 4、获取事件  selectedKeys内部包含所有事件
            Set<SelectionKey> set = selector.selectedKeys();
            // 5、遍历事件
            Iterator<SelectionKey> iterator = set.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                // 区分事件类型
                if (next.isAcceptable()) {
                    /*
                     * 事件类型：accept
                     * 事件发生后，需要处理
                     * 如果没有处理，会一直存在，会进入下一次的 selector.select()
                     */
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    // 取消事件（不会进入下一次selector.select()）
                    //next.cancel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    // 注册
                    ByteBuffer att = ByteBuffer.allocate(12);
                    accept.register(selector, SelectionKey.OP_READ, att);
                    log.info("accept:{}", accept);
                } else if (next.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) next.channel();
                        SocketAddress address = channel.getRemoteAddress();
                        ByteBuffer att = (ByteBuffer) next.attachment();
                        /*
                         * 读取数据
                         * 读模式
                         */
                        StringBuffer msg = new StringBuffer();
                        // 防止数据过大，导致一次读取不完
                        int read;
                        while ((read = channel.read(att)) > 0) {
                            // 读模式
                            att.flip();
                            msg.append(StandardCharsets.UTF_8.decode(att).toString());
                            att.compact();
                        }
                        if (read == -1) {
                            // 正常断开
                            log.info("client close");
                            next.cancel();
                        }
                        log.info("ip {}, read:{}", address, msg.toString());
                        channel.close();
                    } catch (IOException e) {
                        // 异常断开
                        log.info("client close");
                        // 客户端断开
                        e.printStackTrace();
                        // 取消事件
                        next.cancel();
                    }
                } else {
                    // 其他事件 取消，暂时不处理其他事件
                    next.cancel();
                }
                /*select 在事件发生后，就会将相关的 key 放入 selectedKeys 集合，但不会在处理完后从 selectedKeys 集合中移除*/
                // 移除事件
                iterator.remove();
            }
        }
    }
}