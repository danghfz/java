package com.dhf.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dhf.client.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   17:13
 */
@RestController
public class ConController {
    @Autowired
    private ServerClient serverClient;
    @GetMapping("/get/{id}")
    @SentinelResource(value = "get")
    public String get(@PathVariable String id){
        return serverClient.get(id);
    }
}
