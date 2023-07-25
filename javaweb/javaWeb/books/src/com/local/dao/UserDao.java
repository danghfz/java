package com.local.dao;

import com.local.bean.User;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   21:32
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回查询到的用户
     * 如果返回null,说明没有该用户*/
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户*/
    User queryUserByNameAndPw(String username,String password);

    /**
     * 保存用户信息*/
     int saveUser(User user);
}
