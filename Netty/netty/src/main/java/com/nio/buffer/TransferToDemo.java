package com.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FilePathConstant;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/18/0018 15:51
 */
public class TransferToDemo {
    private static final Logger logger = LoggerFactory.getLogger(TransferToDemo.class);
    public static final String FROM = FilePathConstant.DATA_FILE_PATH;
    public static final String TO = FilePathConstant.TO_FILE_PATH;

    public static void main(String[] args) throws IOException {
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO).getChannel()
        ) {
            // 一次最多传输2G
            from.transferTo(0, from.size(), to);
            logger.info("TransferToDemo success");

            // transferTo 一次 最大只能传输 2G
            long size = from.size(); // 总大小
            for (long left = size; left> 0;){
                // left 没有传输的数量
                // return 返回实际传输的字节数，
                // transferTo(long position, long count, ... )
                long l = from.transferTo((size - left), left, to);
                left -= l;
            }
        } catch (IOException e) {
            logger.error("TransferToDemo error", e);
        }
    }
}
