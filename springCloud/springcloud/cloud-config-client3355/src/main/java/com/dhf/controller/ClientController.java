package com.dhf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/26   18:15
 */
@RestController
@RefreshScope
public class ClientController {
    @Value("${config.info}")
    private String info;
    @GetMapping("/")
    public String hello(){
        return info;
    }
}
