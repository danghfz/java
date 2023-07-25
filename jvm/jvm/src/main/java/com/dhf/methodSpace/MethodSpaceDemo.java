package com.dhf.methodSpace;

import com.dhf.Demo;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author 党
 * @version 1.0
 * 2022/9/14   10:45
 * 演示方法区内存溢出
 * 方法区：1.8后在元空间
 * -XX:MaxMetaspaceSize=8m
 */
public class MethodSpaceDemo extends ClassLoader { // 继承类加载器
    public static void main(String[] args) throws FileNotFoundException {
        int j = 0;
        try {
            MethodSpaceDemo demo = new MethodSpaceDemo();
            for (int i = 0; i < 10000; i++, j++) {
                // classWriter 生成 类的 二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                // 版本号，修饰符，类名，包名，父类，实现接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "class" + i, null, "java/lang/Object", null);
                // 返回 byte[]
                byte[] bytes = classWriter.toByteArray();
                // 执行类的加载
                demo.defineClass("class" + i, bytes, 0, bytes.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(j);
        }

    }
    @Test
    public void testIntern(){
        String ab = new String("a") + new String("b");
        String intern = ab.intern();
        String str = "ab";
//        String intern = ab.intern();
//        System.out.println(ab == intern); // true
        System.out.println(str == ab); // true
    }
}
