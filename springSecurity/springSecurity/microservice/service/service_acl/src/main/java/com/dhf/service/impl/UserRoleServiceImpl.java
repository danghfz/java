package com.dhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.UserRole;
import com.dhf.service.UserRoleService;
import com.dhf.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【acl_user_role】的数据库操作Service实现
* @createDate 2022-11-06 07:38:51
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




