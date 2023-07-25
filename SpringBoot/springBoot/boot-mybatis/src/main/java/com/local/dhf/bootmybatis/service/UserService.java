package com.local.dhf.bootmybatis.service;

import com.local.dhf.bootmybatis.mapper.UserMapper;
import com.local.dhf.bootmybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 党
 * @version 1.0
 * 2022/6/29   17:38
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUser(String id){
        return userMapper.getUser(id);
    }

}
