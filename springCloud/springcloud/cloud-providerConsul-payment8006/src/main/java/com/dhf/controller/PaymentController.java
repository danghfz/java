package com.dhf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 党
 * @version 1.0
 * 2022/8/24   17:21
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/consul/payment")
    public String payment() {
        return "hello consul port " + port + " " + UUID.randomUUID();
    }
}
