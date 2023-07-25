package com.local.dhf.bootmybatis.mapper;

import com.local.dhf.bootmybatis.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 党
 * @version 1.0
 * 2022/6/30   13:22
 */
@Mapper
public interface CityMapper {
    @Select("select * from city where id = #{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    City getCityById(@Param("id") Integer id);
}
