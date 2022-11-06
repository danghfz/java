package com.dhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.Role;
import com.dhf.service.RoleService;
import com.dhf.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lenvoo
* @description 针对表【acl_role】的数据库操作Service实现
* @createDate 2022-11-06 07:38:51
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Autowired
    private RoleMapper mapper;
    @Override
    public List<Role> selectRoleByUserId(String id) {
        return mapper.selectRoleByUserId(id);
    }
}




