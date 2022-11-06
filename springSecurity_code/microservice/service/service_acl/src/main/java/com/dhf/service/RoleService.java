package com.dhf.service;

import com.dhf.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lenvoo
* @description 针对表【acl_role】的数据库操作Service
* @createDate 2022-11-06 07:38:51
*/
public interface RoleService extends IService<Role> {
    List<Role> selectRoleByUserId(String id);
}
