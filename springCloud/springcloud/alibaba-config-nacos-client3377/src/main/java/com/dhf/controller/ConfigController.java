package com.dhf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/31   9:48
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${config.info}")
    private String config;
    @GetMapping("/")
    public String hello(){
        return config;
    }

}
