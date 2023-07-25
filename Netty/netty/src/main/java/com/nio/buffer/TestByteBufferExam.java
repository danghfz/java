package com.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/18/0018 12:34
 * 粘包、半包 问题
 */
public class TestByteBufferExam {
    private static Logger logger = LoggerFactory.getLogger(TestByteBufferExam.class);

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source, '\n');
        source.put("w are you?\nhaha!\n".getBytes());
        split(source, '\n');
    }

    private static void split(ByteBuffer buffer, char newLine) {
        // 切换到读模式
        buffer.flip();
        int size = buffer.limit();
        // 读到换行符
        // 我希望拿到最后一个换行符
        for (int i = 0; i < size; i++) {
            byte b;
            ByteBuffer target;
            if ((b = buffer.get(i)) == newLine) {
                // 将之前的数据全部存入新的buffer
                // [position, i) 将这部分写入 target
                // 如果是 i + 1则会读取 \n，我不想要
                buffer.limit(i);
                int length = i - buffer.position();
                target = ByteBuffer.allocate(length);
                // put 之后，buffer 的 position 会移动
                target.put(buffer);
                target.flip();
                String message = StandardCharsets.UTF_8.decode(target).toString();
                logger.info("message: {}", message);
                buffer.limit(size);
                // 重新设置 position ，跳过 \n
                buffer.position(i + 1);
            }
        }
        // 切换到写模式
        buffer.compact();
    }
}