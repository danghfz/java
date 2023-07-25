package com.dhf.mapper;

import com.dhf.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenvoo
* @description 针对表【acl_user(用户表)】的数据库操作Mapper
* @createDate 2022-11-06 07:38:51
* @Entity com.dhf.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




