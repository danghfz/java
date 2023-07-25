package com.dhf.controller;

import com.dhf.feignClient.PaymentService;
import com.dhf.pojo.Payment;
import com.dhf.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   12:03
 */
@RestController
public class OrderController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/getPaymentById/{id}")
    public Result getPaymentById(@PathVariable String id){
        return paymentService.getPaymentById(id);
    }
    @GetMapping("/create")
    public Result create(@RequestBody Payment payment){
        return paymentService.create(payment);
    }
}
