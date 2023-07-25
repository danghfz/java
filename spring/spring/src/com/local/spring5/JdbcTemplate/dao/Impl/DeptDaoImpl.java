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
 * @author ��
 * @version 1.0
 * 2022/5/12   12:32
 */
@Component("deptDaoImpl")
public class DeptDaoImpl implements DeptDao {
    //ע��JdbcTemplate,��xml�����ļ���
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

    //��ѯ��������
    @Override
    public Object queryCount() {
        String sql = "select count(*) from dept";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }

    //��ѯ��������
    public Object queryForOne(Integer depton) {
        String sql = "select * from dept where depton=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), depton);
    }

    //��������ѯ
    public List<Dept> queryForList() {
        String sql = "select * from dept";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    //======================��������
    //�������
    public void batchAdd(List<Object[]> list) {
        jdbcTemplate.batchUpdate("insert into dept values(?,?,?)", list);
    }

    //����ɾ��
    public int[] batchDelete(List<Object[]> list) {
        int[] ints = jdbcTemplate.batchUpdate("delete from dept where depton=?", list);
        return ints;
    }

    //��������
    public int[] batchUpdate(List<Object[]> batchArgs) {
        int[] ints = jdbcTemplate.batchUpdate("update dept set dname=?,loc=? where depton=?", batchArgs);
        return ints;
    }

}
