package com.dhf.mapper;

import com.dhf.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author lenvoo
* @description 针对表【acl_role】的数据库操作Mapper
* @createDate 2022-11-06 07:38:51
* @Entity com.dhf.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRoleByUserId(@Param("id")String id);
}




