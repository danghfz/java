package com.dhf.controller;

import com.dhf.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   13:39
 */
@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/paymentInfo")
    public String paymentInfo(Integer id){
        return paymentService.paymentInfo(id);
    }
    @GetMapping("/err")
    public String error(){
        return paymentService.error();
    }
    @GetMapping("/paymentCircuitBreaker")
    public String paymentCircuitBreaker(Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
