package com.local.service;

import com.local.bean.User;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   22:06
 */
public interface UserService {
    /**
     * 注册用户*/
    void registerUser(User user);
    /**
     * 登录*/
    User login(User user);

    /**
     * 检查用户名是否可用
     * true用户名存在
     * false代表不存在
     * */
    boolean existsUsername(String username);
}
