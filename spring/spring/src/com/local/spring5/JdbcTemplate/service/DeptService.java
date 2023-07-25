package com.local.spring5.JdbcTemplate.service;

import com.local.spring5.JdbcTemplate.dao.DeptDao;
import com.local.spring5.JdbcTemplate.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author ��
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
        System.out.println("�����"+add+"������");
    }
    public void delete(Integer depton){
        int delete = deptDao.delete(depton);
        System.out.println(delete+"�����ݱ�ɾ��");
    }
    public void queryObject(){
        Object o1 = deptDao.queryCount();
        System.out.println("��"+o1+"������");
    }
}
