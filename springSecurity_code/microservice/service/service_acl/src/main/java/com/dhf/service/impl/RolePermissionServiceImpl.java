package com.dhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.RolePermission;
import com.dhf.service.RolePermissionService;
import com.dhf.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【acl_role_permission(角色权限)】的数据库操作Service实现
* @createDate 2022-11-06 07:38:51
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




