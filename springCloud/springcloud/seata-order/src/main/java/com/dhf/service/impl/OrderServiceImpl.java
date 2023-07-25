package com.dhf.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.entity.Order;
import com.dhf.service.OrderService;
import com.dhf.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【t_order(订单表)】的数据库操作Service实现
* @createDate 2022-09-03 13:03:32
*/
@Service
@DS("mysql1")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




