package com.dhf.service;

import com.dhf.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lenvoo
* @description 针对表【acl_permission(权限)】的数据库操作Service
* @createDate 2022-11-06 07:38:51
*/
public interface PermissionService extends IService<Permission> {
    // 根据用户 id 获取权限
    List<String> selectPermissionValueByUserId(String userId);
    List<Permission> selectPermissionByUserId(String userId);
}
