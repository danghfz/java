package com.local.spring5.JdbcTemplate.dao.Impl;

import com.local.spring5.JdbcTemplate.dao.DeptDao;
import com.local.spring5.JdbcTemplate.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/5/12   12:32
 */
@Component("deptDaoImpl")
public class DeptDaoImpl implements DeptDao {
    //注入JdbcTemplate,在xml配置文件中
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int add(Dept dept) {
        String sql = "insert into dept values(?,?,?)";
        return jdbcTemplate.update(sql, dept.getDepton(), dept.getDname(), dept.getLoc());
    }

    @Override
    public int delete(Integer depton) {
        String sql = "delete from dept where depton=?";
        return jdbcTemplate.update(sql, depton);
    }

    //查询单个数据
    @Override
    public Object queryCount() {
        String sql = "select count(*) from dept";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }

    //查询单个对象
    public Object queryForOne(Integer depton) {
        String sql = "select * from dept where depton=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), depton);
    }

    //多个对象查询
    public List<Dept> queryForList() {
        String sql = "select * from dept";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    //======================批量操作
    //批量添加
    public void batchAdd(List<Object[]> list) {
        jdbcTemplate.batchUpdate("insert into dept values(?,?,?)", list);
    }

    //批量删除
    public int[] batchDelete(List<Object[]> list) {
        int[] ints = jdbcTemplate.batchUpdate("delete from dept where depton=?", list);
        return ints;
    }

    //批量更新
    public int[] batchUpdate(List<Object[]> batchArgs) {
        int[] ints = jdbcTemplate.batchUpdate("update dept set dname=?,loc=? where depton=?", batchArgs);
        return ints;
    }

}
