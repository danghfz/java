package com.dhf.CAS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 党
 * @version 1.0
 * 2022/8/13   18:44
 */
public class ABADemo {
    public static void main(String[] args) {
        Book book = new Book("疯狂Java讲义");
        // 初始化，(对象，版本号)
        AtomicStampedReference<Book> bookAtomicStampedReference = new AtomicStampedReference<Book>(book,1);
        int stamp = bookAtomicStampedReference.getStamp(); // 获取当前版本号
        System.out.println(bookAtomicStampedReference.getReference()+" "+stamp);

        Book java = new Book("java");
        // 更新，并且更新版本号
        boolean b = bookAtomicStampedReference.compareAndSet(book, java, bookAtomicStampedReference.getStamp(), bookAtomicStampedReference.getStamp() + 1);
        System.out.println(bookAtomicStampedReference.getReference()+" "+bookAtomicStampedReference.getStamp());

        // 换回来
        boolean b1 = bookAtomicStampedReference.compareAndSet(java, book, bookAtomicStampedReference.getStamp(), bookAtomicStampedReference.getStamp()+1);
        System.out.println(bookAtomicStampedReference.getReference()+" "+bookAtomicStampedReference.getStamp());
    }
}
@AllArgsConstructor
@ToString
class Book{
    public String name;
}
class tets{
    public static void main(String[] args) {
        System.out.println("你好");

    }
}