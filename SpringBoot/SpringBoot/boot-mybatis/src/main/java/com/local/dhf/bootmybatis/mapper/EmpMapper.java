package com.local.dhf.bootmybatis.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.local.dhf.bootmybatis.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 党
 * @version 1.0
 * 2022/6/30   14:07
 */
@Mapper
//BootMybatisApplication写过@MapperScan("com.local.dhf.bootmybatis.mapper")
public interface EmpMapper extends BaseMapper<Emp> {

}
