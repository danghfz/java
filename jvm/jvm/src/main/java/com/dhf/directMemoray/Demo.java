package com.dhf.directMemoray;

import org.testng.annotations.Test;

import java.io.*;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * @author 党
 * @version 1.0
 * 2022/9/14   15:14
 */
public class Demo {
    // 文件地址
    private static final String FROM = "C:\\Users\\lenvoo\\Videos\\Captures\\yuanshen.mp4";
    private static final int _1Mb = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        directBuffer(); // 34 ms
        io(); // 89 ms
    }

    // nio
    public static void directBuffer() throws FileNotFoundException {
        long startTime = System.nanoTime();
        FileChannel channel = new FileInputStream(FROM).getChannel();
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Mb);
            while (true) {
                int read = channel.read(byteBuffer);
                if (read == -1) {
                    break;
                }
                byteBuffer.flip();
                // 操作
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("directBuffer:用时：" + (end - startTime) / 1000000);
    }

    public static void io() throws Exception {
        long startTime = System.nanoTime();
        FileInputStream fileInputStream = new FileInputStream(FROM);
        byte[] bytes = new byte[_1Mb];
        while (true) {
            int read = fileInputStream.read(bytes);
            if (read == -1) {
                break;
            }
            // do something
        }
        long end = System.nanoTime();
        System.out.println("io:用时：" + (end - startTime) / 1000000);
    }
    @Test
    public void test1(){
        // java.lang.OutOfMemoryError: Direct buffer memory
        ArrayList<Object> objects = new ArrayList<>();
        int i = 0;
        try {
            while (true){
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Mb);
                objects.add(byteBuffer);
                i++;
            }
        }finally {
            System.out.println(i);
        }
    }
}
