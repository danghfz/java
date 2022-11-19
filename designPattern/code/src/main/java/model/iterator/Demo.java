package model.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/19   12:51
 * 迭代器模式
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        Iterator<Object> iterator = objects.iterator();
    }
}

/**
 * 迭代器接口
 * @param <E>
 */
interface IIterator<E>{
    /**
     * 是否还有下一个元素
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     * @return E
     */
    E next();

    /**
     * 移除下一个元素
     */
    void remove();
}
