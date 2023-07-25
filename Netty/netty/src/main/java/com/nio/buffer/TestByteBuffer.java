package com.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FilePathConstant;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/18/0018 10:25
 */
public class TestByteBuffer {
    private static final Logger logger = LoggerFactory.getLogger(TestByteBuffer.class);
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) {
        //RandomAccessFile
        //使用 FileInputStream 获取 FileChannel
        ByteBuffer buffer;
        try (FileChannel channel = new FileInputStream(FilePathConstant.DATA_FILE_PATH).getChannel()) {
            // 缓冲区
            // buffer = ByteBuffer.allocateDirect(MB); // 开辟直接内存
            buffer = ByteBuffer.allocate(3);
            // channel.read(buffer) != -1 代表还有数据
            // channel 读取数据 写入 buffer
            while (channel.read(buffer) != -1) {
                // buffer切换到读模式
                buffer.flip();
                // 是否还有剩余的数据
                while (buffer.hasRemaining()) {
                    logger.info("{}", (char) buffer.get());
                }
                // 切换到写模式
                buffer.clear();

            }
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
    }
}
