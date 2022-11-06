package com.dhf.mapper;

import com.dhf.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenvoo
* @description 针对表【acl_role_permission(角色权限)】的数据库操作Mapper
* @createDate 2022-11-06 07:38:51
* @Entity com.dhf.domain.RolePermission
*/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}




