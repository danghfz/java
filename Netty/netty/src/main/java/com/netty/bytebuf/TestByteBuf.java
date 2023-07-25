package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/30/0030 11:50
 */
public class TestByteBuf {
    private static final Logger log = LoggerFactory.getLogger(TestByteBuf.class);
    public static void main(String[] args) {
        // ByteBuf buffer(int initialCapacity);初始容量
        // ByteBuf buffer(int initialCapacity, int maxCapacity);初始容量 最大容量
        // default initialCapacity = 256; default maxCapacity = Integer.MAX_VALUE;
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        // buffer:PooledUnsafeDirectByteBuf(ridx: 0, widx: 0, cap: 256)
        log.info("buffer:{}", buf);
        // 堆内存
        //ByteBufAllocator.DEFAULT.heapBuffer();
        // 直接内存
        //ByteBufAllocator.DEFAULT.directBuffer();

        /*
         * 写入
         */
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        log(buf);

    }
    public static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex())
                .append(" capacity:").append(buffer.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf, buffer);
        System.out.println(buf.toString());
    }
}
