package model.decorator;

import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   14:25
 * IO 中 使用了装饰者模式
 */
public class JDK {
    public static void main(String[] args) {
//        public abstract class InputStream implements Closeable  (drink)
//        class FileInputStream extends InputStream (coffee)
        // 修饰者 (Decorator)
//        public class FilterInputStream extends java.io.InputStream {
//            /**
//             * The input stream to be filtered.
//             */
        // 被修饰者
//            protected volatile java.io.InputStream in;
//        }
//        (糖)
//        DataInputStream 是 FilterInputStream 子类，具体的修饰者
    }
}
