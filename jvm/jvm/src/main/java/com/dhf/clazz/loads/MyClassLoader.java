package com.dhf.clazz.loads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author 党
 * @version 1.0
 * 2022/9/17   10:42
 */
public class MyClassLoader extends ClassLoader{
    @Override
    /**
     * @Param name 类名称
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "xxx" + name + ".class"; // 类文件路径
        // 读取 二进制 类文件
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Files.copy(Paths.get(path), outputStream);
            // 得到字节数组
            byte[] bytes = outputStream.toByteArray();

            // byte[] -> *.class
            return defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}
