package com.nio.web.choke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/19/0019 9:01
 */
public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);
    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 8080;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        // 创建客户端
        // 1、创建 Socket
        SocketChannel socketChannel = SocketChannel.open();
        // 2、连接服务器
        boolean connect = socketChannel.connect(new InetSocketAddress(HOSTNAME, PORT));
        if (connect) {
           log.info("连接成功");
           while (sc.hasNextLine()){
                String msg = sc.nextLine();
                socketChannel.write(StandardCharsets.UTF_8.encode(msg));
           }
        }
    }
}
