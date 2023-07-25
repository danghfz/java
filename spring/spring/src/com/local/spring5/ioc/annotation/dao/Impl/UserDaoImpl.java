package com.local.spring5.ioc.annotation.dao.Impl;

import com.local.spring5.ioc.annotation.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/10   16:08
 */
@Repository
public class UserDaoImpl implements UserDao {
    public void add(){
        System.out.println("UserDaoImpl.add()");
    }
}
