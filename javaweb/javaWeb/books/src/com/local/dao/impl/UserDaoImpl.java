package com.local.dao.impl;

import com.local.bean.User;
import com.local.dao.BaseDao;
import com.local.dao.UserDao;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   21:36
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "SELECT `id`, `username`, `password`, `email` FROM `t_user` WHERE `username` = ?";
        return queryForOne(sql, User.class,username);
    }

    @Override
    public User queryUserByNameAndPw(String username, String password) {
        String sql = "SELECT `id`, `username`, `password`, `email` FROM `t_user` WHERE `username` = ? And `password` = ?";
        return queryForOne(sql, User.class,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO `t_user` (`username`, `password`, `email`) VALUES (?, ?, ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
