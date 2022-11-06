package com.dhf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.Permission;
import com.dhf.entity.Role;
import com.dhf.entity.UserRole;
import com.dhf.mapper.UserRoleMapper;
import com.dhf.service.PermissionService;
import com.dhf.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author lenvoo
* @description 针对表【acl_permission(权限)】的数据库操作Service实现
* @createDate 2022-11-06 07:38:51
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<String> selectPermissionValueByUserId(String userId) {
        return permissionMapper.selectPermissionValueByUserId(userId);
    }

    @Override
    public List<Permission> selectPermissionByUserId(String userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }
}




