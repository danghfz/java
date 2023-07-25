package com.local.service.impl;

import com.local.bean.User;
import com.local.dao.impl.UserDaoImpl;
import com.local.service.UserService;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   22:09
 */
public class UserServerImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {//注册用户update
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {//登录
        return userDao.queryUserByNameAndPw(
                user.getUsername(), user.getPassword());
    }

    @Override
    /**
     * 找到返回true,代表已经存在该用户*/
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
