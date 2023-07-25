package com.dhf.mapper;

import com.dhf.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenvoo
* @description 针对表【t_account(账户表)】的数据库操作Mapper
* @createDate 2022-09-03 18:51:10
* @Entity com.dhf.entity.Account
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}




