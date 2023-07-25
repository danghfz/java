package com.local.service.impl;

import com.local.bean.Book;
import com.local.bean.Page;
import com.local.dao.impl.BookDaoImpl;
import com.local.service.BookService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   15:52
 */
public class BookServiceImpl implements BookService {
    private final BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBook(Integer id) {
        return bookDao.queryBook(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();
        //总记录
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        bookPage.setPageTotalCount(pageTotalCount);
        //总页数
        Integer pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
        if (pageNo < 1)
            pageNo = 1;
        if (pageNo> pageTotal)
            pageNo = pageTotal;
        bookPage.setPageNo(pageNo);//当前页面
        bookPage.setPageSize(pageSize);//每页数量
        bookPage.setPageTotal(pageTotal);


        //当前页面的数据
        List<Book> list = bookDao.queryForPageItems((pageNo - 1) * pageSize, pageSize);
        bookPage.setItems(list);
        return bookPage;
    }
    public Page<Book> pageByPrice(BigDecimal min,BigDecimal max, int pageNo, int pageSize){
        Page<Book> bookPage = new Page<>();
        //总记录
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        bookPage.setPageTotalCount(pageTotalCount);
        //总页数
        Integer pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
        if (pageNo < 1)
            pageNo = 1;
        if (pageNo> pageTotal)
            pageNo = pageTotal;
        bookPage.setPageNo(pageNo);//当前页面
        bookPage.setPageSize(pageSize);//每页数量
        bookPage.setPageTotal(pageTotal);//总页数


        //当前页面的数据
        List<Book> list = bookDao.queryForPageItemsByPrice(min,max,(pageNo - 1) * pageSize, pageSize);
        bookPage.setItems(list);
        return bookPage;
    }
}
