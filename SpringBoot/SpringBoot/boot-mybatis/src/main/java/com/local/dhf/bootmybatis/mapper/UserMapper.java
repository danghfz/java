package com.local.dhf.bootmybatis.mapper;

import com.local.dhf.bootmybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 党
 * @version 1.0
 * 2022/6/29   17:29
 */
@Mapper
public interface UserMapper {
    User getUser(@Param("id") String id);
}
