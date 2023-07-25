package com.dhf.mapper;

import com.dhf.pojo.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lenvoo
* @description 针对表【payment】的数据库操作Mapper
* @createDate 2022-08-22 22:10:41
* @Entity com.dhf.pojoi.Payment
*/
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}




