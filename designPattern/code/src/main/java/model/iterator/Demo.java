package model.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author danghf
 * @version 1.0
 * @date 2022/11/19 12:51
 * 迭代器模式
 */
public class Demo {
    public static void main(String[] args) {
        FacultyOfScience facultyOfScience = new FacultyOfScience();
        IIterator<Department> iterator = facultyOfScience.iterator();
        while (iterator.hasNext()){
            Department next = iterator.next();
            System.out.println(next);
        }
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

/**
 * 系
 */
class Department{
    private String name;

    public Department(String name) {
        this.name = name;
    }
}
/**
 * 理学院
 */
class FacultyOfScience{
    /**
     * 数组形式数据
     */
    private Department[] departments;
    {
        departments = new Department[3];
        departments[0] = new Department("数学系");
        departments[1] = new Department("物理系");
        departments[2] = new Department("光电");
    }

    /**
     * 返回迭代器
     * @return 返回迭代器
     */
    public IIterator<Department> iterator(){
        return new IIterator<Department>() {
            // 要返回下一个元素的索引
            private int index = 0;
            // 返回的最后一个元素的索引;如果没有，则为-1
            private int lastRes = -1;
            private int size = departments.length;
            @Override
            public boolean hasNext() {
                return index != size;
            }

            @Override
            public Department next() {
                if (hasNext()){
                    lastRes = index++;
                    return departments[lastRes];
                }else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {

            }
        };
    }
}
/**
 * 计算机学院
 */
class ComputerScience{
    private ArrayList<Department> departments;
    {
        departments = new ArrayList<>(7);
        departments.add(new Department("C++"));
        departments.add(new Department("C"));
        departments.add(new Department("Java"));
        departments.add(new Department("C#"));
        departments.add(new Department("PHP"));
        departments.add(new Department("Python"));
        departments.add(new Department("Js"));
    }

    /**
     * 返回迭代器
     * @return 返回迭代器
     */
    public IIterator<Department> iterator(){
        return new IIterator<Department>() {
            // 要返回下一个元素的索引
            private int index = 0;
            // 返回的最后一个元素的索引;如果没有，则为-1
            private int lastRes = -1;
            private int size = departments.size();
            @Override
            public boolean hasNext() {
                return index != size;
            }

            @Override
            public Department next() {
                if (hasNext()){
                    lastRes = index++;
                    return departments.get(lastRes);
                }else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {

            }
        };
    }
}