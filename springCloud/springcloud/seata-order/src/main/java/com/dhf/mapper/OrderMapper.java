package com.dhf.mapper;

import com.dhf.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenvoo
* @description 针对表【t_order(订单表)】的数据库操作Mapper
* @createDate 2022-09-03 13:03:32
* @Entity com.dhf.entity.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




