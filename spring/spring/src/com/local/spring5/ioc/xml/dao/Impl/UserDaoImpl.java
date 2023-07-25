package com.local.spring5.ioc.xml.dao.Impl;

import com.local.spring5.ioc.xml.dao.UserDao;

/**
 * @author ��
 * @version 1.0
 * 2022/5/8   14:47
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void add(){
        System.out.println("UserDaoImpl.add()");
    }
}
