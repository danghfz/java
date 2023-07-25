package com.local.spring5.ioc.xml.service;

import com.local.spring5.ioc.xml.dao.UserDao;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/8   14:46
 */
public class UserService {
    private UserDao userDao;
    public void add(){
        System.out.println("UserService.add()");
        userDao.add();
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("UserService.setUserDao()");
        this.userDao = userDao;
    }
}
