package com.netty.bytebuf.zeroCopy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netty.bytebuf.TestByteBuf.log;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/7/5/0005 12:53
 */
public class Test {
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        //testSlice();
        testComponents();
    }

    private static void testComponents() {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer()
                .writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer()
                .writeBytes(new byte[]{6, 7, 8, 9, 10});
        ByteBuf buf3 = ByteBufAllocator.DEFAULT
                .buffer(buf1.readableBytes()+buf2.readableBytes());
        buf3.writeBytes(buf1);
        buf3.writeBytes(buf2);
        System.out.println(ByteBufUtil.prettyHexDump(buf3));
        CompositeByteBuf byteBuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        // 第一个参数 ： 是否自动调整读写指针，false，不调整
        // 不调整 (ridx: 0, widx: 0, cap: 10, components=2)
        // true：调整
        byteBuf.addComponents(true,buf1,buf2);
        // CompositeByteBuf(ridx: 0, widx: 10, cap: 10, components=2)
        System.out.println("============================================");
        System.out.println(ByteBufUtil.prettyHexDump(byteBuf));
    }

    /**
     * 对原始 ByteBuf 进行切片成多个 ByteBuf，
     * 切片后的 ByteBuf 并没有发生内存复制，
     * 还是使用原始 ByteBuf 的内存，
     * 切片后的 ByteBuf 维护独立的 read，write 指针
     */
    public static void testSlice() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        // (ridx: 0, widx: 4, cap: 10)
        // 01 02 03 04
        log(buffer);

        // slice(start, length)
        // 切片过程中没有发生 数据复制
        // 切片是在原来buf的内存上进行
        // (ridx: 0, widx: 2, cap: 2/2
        // 01 02
        ByteBuf f1 = buffer.slice(0, 2);
        f1.retain();
        // (ridx: 0, widx: 2, cap: 2/2
        // 03 04
        ByteBuf f2 = buffer.slice(2, 2);
        // 切片的长度不可以改变，会影响到后面的 切片
        log(f1);
        f1.setByte(0, 0);
        log(f2);
        // (ridx: 0, widx: 4, cap: 10)
        //  00 02 03 04
        log(buffer);
        // 释放buffer 内存
        //buffer.release();
        ReferenceCountUtil.release(buffer);
    }
}
