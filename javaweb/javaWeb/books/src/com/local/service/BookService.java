package com.local.service;

import com.local.bean.Book;
import com.local.bean.Page;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   15:51
 */
public interface BookService {
    //添加
    void addBook(Book book);
    //删除
    void deleteBookById(Integer id);
    //修改
    void updateBook(Book book);
    //query
    Book queryBook(Integer id);
    //queryAll
    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);
}
