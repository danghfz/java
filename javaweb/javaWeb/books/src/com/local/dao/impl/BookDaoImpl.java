package com.local.dao.impl;

import com.local.dao.BaseDao;
import com.local.dao.BookDao;
import com.local.bean.Book;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   15:35
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao  {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book values (?,?,?,?,?,?,?)";
        return update(sql,null,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBook(Integer id) {
        String sql = "select * from t_book where id = ?";
        return queryForOne(sql,Book.class,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(sql,Book.class);
    }
    //总记录数
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Long o = (Long)queryForSingleValue(sql);//Long对象
        return o.intValue();
    }

    //按页查找
    public List<Book> queryForPageItems(int i, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        return queryForList(sql,Book.class,i,pageSize);
    }
    //按价格查找
    public Integer queryForPageTotalCountByPrice(BigDecimal min, BigDecimal max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Long o = (Long)queryForSingleValue(sql,min,max);//Long对象
        return o.intValue();
    }
    //根据价格查找
    public List<Book> queryForPageItemsByPrice(BigDecimal min, BigDecimal max, int i, int pageSize) {
        String sql = "select * from t_book where  price between ? and ? order by price limit ?,?";
        return queryForList(sql,Book.class,min,max,i,pageSize);
    }
}
