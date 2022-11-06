package com.dhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.User;
import com.dhf.service.UserService;
import com.dhf.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【acl_user(用户表)】的数据库操作Service实现
* @createDate 2022-11-06 07:38:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




