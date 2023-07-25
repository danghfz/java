package com.local.dao;

import com.local.bean.Book;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   15:32
 */
public interface BookDao {
    //添加
    int addBook(Book book);
    //删除
    int deleteBookById(Integer id);
    //修改
    int updateBook(Book book);
    //query
    Book queryBook(Integer id);
    //
    List<Book> queryBooks();
}
