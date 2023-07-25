package com.dhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhf.pojo.Payment;
import com.dhf.service.PaymentService;
import com.dhf.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author lenvoo
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2022-08-22 22:10:41
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService{
    @Autowired
    private PaymentMapper paymentMapper;
}




