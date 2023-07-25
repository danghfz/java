package com.dhf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 党
 * @version 1.0
 * 2022/8/24   17:33
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    private String URL = "http://cloud-consul-payment8006/";

    @GetMapping("/consul/payment")
    public String payment() {
        return restTemplate.getForObject(URL + "consul/payment", String.class);
    }

}
