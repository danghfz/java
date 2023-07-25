package com.dhf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 党
 * @version 1.0
 * 2022/8/24   15:07
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/zk")
    public String paymentZk(){
        return "Hello zookeeper port:" + port + " " + UUID.randomUUID();
    }
}
