package model.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/20 12:57
 * 迭代器模式在 jdk 源码中的使用
 */
public class Jdk {
    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objects.add(i);
        }
        Iterator<Integer> iterator = objects.iterator();
        for (int i = 0; i < 2; i++) {
            Integer next = iterator.next();
            System.out.println(next);
        }
        iterator.remove();
    }
}
