package com.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static utils.ByteBufferUtil.debugAll;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/18/0018 12:16
 */
public class ByteBufferAndStringTran {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final Logger log = LoggerFactory.getLogger(ByteBufferAndStringTran.class);
    public static void main(String[] args) {
        // 1、String -> ByteBuffer
        String hello = "hello";
        byte[] bytes = hello.getBytes(UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(bytes);
        // position: [5], limit: [10] 写模式
        debugAll(buffer);

        // 2、Charset
        // position: [0], limit: [5] 自动切换到读模式
        ByteBuffer encode = UTF_8.encode(hello);

        // 3、ByteBuffer.wrap()
        // position: [0], limit: [5] 自动切换到读模式
        ByteBuffer wrap = ByteBuffer.wrap(hello.getBytes(UTF_8));

        // byteBuffer -> str
        // 解码 decode 需要 wrap 是 读模式，否则失败
        buffer.flip();
        String decode = StandardCharsets.UTF_8.decode(buffer).toString();
        log.info("decode: {}" + decode);
    }
}
