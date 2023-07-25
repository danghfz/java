package com.local.spring5.JdbcTemplate.dao;

import com.local.spring5.JdbcTemplate.pojo.Dept;
import com.local.spring5.aop.JAspectJ.xml.Book;

/**
 * @author ��
 * @version 1.0
 * 2022/5/12   12:35
 */
public interface DeptDao extends BaseDao<Dept> {
    int add(Dept dept);
    int delete(Integer depton);
    Object queryCount();
}
