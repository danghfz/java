package com.dhf.controller;

import com.dhf.pojo.Payment;
import com.dhf.service.PaymentService;
import com.dhf.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 党
 * @version 1.0
 * 2022/8/22   22:14
 */
@RestController
@RequestMapping("/payment/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/create")
    public Result create(@RequestBody Payment payment) {
        boolean save = paymentService.save(payment);
        if (save) {
            return Result.ok().setMessage("插入成功").setDate("payment", payment);
        } else {
            return Result.error().setMessage("插入失败");
        }
    }
    @GetMapping("/getPaymentById/{id}")
    public Result getPaymentById(@PathVariable String id){
        Payment payment = paymentService.getById(id);
        if (payment == null)
            return Result.error().setMessage("空");
        return Result.ok().setMessage("查询成功").setDate("payment", payment).setDate("port",serverPort);
    }
}
