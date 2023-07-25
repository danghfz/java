package com.dhf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   15:30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByUsername(@Param("username") String username);
}
