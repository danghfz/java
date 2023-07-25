package com.dhf.mapper;

import com.dhf.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author lenvoo
* @description 针对表【acl_permission(权限)】的数据库操作Mapper
* @createDate 2022-11-06 07:38:51
* @Entity com.dhf.domain.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> selectPermissionValueByUserId(@Param("userId")String userID);
    List<Permission> selectPermissionByUserId(@Param("userId")String userID);
}




