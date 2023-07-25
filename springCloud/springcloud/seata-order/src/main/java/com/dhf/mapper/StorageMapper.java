package com.dhf.mapper;

import com.dhf.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenvoo
* @description 针对表【t_storage(库存表)】的数据库操作Mapper
* @createDate 2022-09-03 18:50:55
* @Entity com.dhf.entity.Storage
*/
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

}




