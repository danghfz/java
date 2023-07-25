package com.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/18/0018 12:02
 */
public class TestByteBufferAllocate {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestByteBufferAllocate.class);

    public static void main(String[] args) {
        Class<? extends ByteBuffer> aClass = ByteBuffer.allocate(1024).getClass();
        Class<? extends ByteBuffer> aClass1 = ByteBuffer.allocateDirect(1024).getClass();
        LOGGER.info("{}", aClass);
        LOGGER.info("{}", aClass1);
        /**
         * - class java.nio.HeapByteBuffer 堆内存
         * - class java.nio.DirectByteBuffer 直接内存
         */
    }
}
