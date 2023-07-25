package com.nio.web.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/24/0024 11:34
 */
public class WorkerServer {
    private static final Logger log = LoggerFactory.getLogger(WorkerServer.class);
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        Selector bossSelector = Selector.open();
        serverChannel.register(bossSelector, SelectionKey.OP_ACCEPT);
        serverChannel.bind(new InetSocketAddress(8080));
        Worker worker = new Worker("work-0");
        while (true) {
            bossSelector.select();
            Iterator<SelectionKey> keyIterator = bossSelector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel client = channel.accept();
                    log.info("接收到连接{}", client.getRemoteAddress());
                    client.configureBlocking(false);
                    worker.register(client);
                } else {
                    key.cancel();
                }
                keyIterator.remove();
            }
        }
    }

    static class Worker implements Runnable {
        private final String name;
        private Selector selector;
        private boolean isInit = false;
        private final ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    // 唤醒之后，从队列中取出任务执行
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isReadable()) {
                            try {
                                SocketChannel channel = (SocketChannel) key.channel();
                                ByteBuffer attachment = (ByteBuffer) key.attachment();
                                int read = channel.read(attachment);
                                if (read == -1) {
                                    key.cancel();
                                    channel.close();
                                    log.info("client close");
                                }
                                attachment.flip();
                                log.info("msg {}", StandardCharsets.UTF_8.decode(attachment).toString());
                                attachment.clear();
                            } catch (Exception e) {
                                e.printStackTrace();
                                log.info("client close exception");
                                key.cancel();
                            }
                        } else {
                            key.cancel();
                        }
                        keyIterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Worker (String name) {
            this.name = name;
        }
        public void register(SocketChannel client) throws IOException {
            if (!isInit) {
                Thread thread = new Thread(this, name);
                selector = Selector.open();
                thread.start();
                isInit = true;
            }
            // select 会阻塞注册，所以需要在 select 之前注册，要在work线程注册
            // 完成线程之间的通信，使用线程安全的队列
            queue.add(()->{
                try {
                    client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            // 唤醒阻塞的 select
            selector.wakeup();
        }
    }
}
