package com.dhf.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.Account;
import com.dhf.service.AccountService;
import com.dhf.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【t_account(账户表)】的数据库操作Service实现
* @createDate 2022-09-03 18:51:10
*/
@Service
@DS("mysql3")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

}




