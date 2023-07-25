package com.local.spring5.Transaction.dao;

import com.local.spring5.Transaction.pojo.User;

import java.util.List;

/**
 * @author ��
 * @version 1.0
 * 2022/5/12   14:36
 */
public interface UserDao {
    int updateMoney(Integer money,String name);
    List<User> queryAll();
}
