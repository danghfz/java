package com.dhf.client.impl;

import com.dhf.client.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   17:32
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo(Integer id) {
        return "出错了，请稍后再试";
    }
    @Override
    public String error() {
        return "出错了，请稍后再试";
    }
}
