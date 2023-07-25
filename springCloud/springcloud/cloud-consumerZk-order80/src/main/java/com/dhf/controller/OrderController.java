package com.dhf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 党
 * @version 1.0
 * 2022/8/24   15:57
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://cloud-payment8004/";
    @GetMapping("/payment/zk")
    public String payment(){
        return restTemplate.getForObject(URL+"payment/zk",String.class);
    }
    @GetMapping("/")
    public String hello(){
        return "hello";
    }
}
