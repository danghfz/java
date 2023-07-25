package com.dhf.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.Storage;
import com.dhf.service.StorageService;
import com.dhf.mapper.StorageMapper;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【t_storage(库存表)】的数据库操作Service实现
* @createDate 2022-09-03 18:50:55
*/
@Service
@DS("mysql2")
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{

}




