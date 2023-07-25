package com.local.spring5.JdbcTemplate.service;

import com.local.spring5.JdbcTemplate.dao.DeptDao;
import com.local.spring5.JdbcTemplate.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author 党
 * @version 1.0
 * 2022/5/12   12:34
 */
@Component(value = "deptService")
public class DeptService {
    @Autowired
    @Qualifier("deptDaoImpl")
    private DeptDao deptDao;
    public void add(Dept dept){
        int add = deptDao.add(dept);
        System.out.println("添加了"+add+"条数据");
    }
    public void delete(Integer depton){
        int delete = deptDao.delete(depton);
        System.out.println(delete+"条数据被删除");
    }
    public void queryObject(){
        Object o1 = deptDao.queryCount();
        System.out.println("有"+o1+"条数据");
    }
}
