package com.dhf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   18:59
 */
@RestController
public class ProController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello(){
        return "hello nacos discovery in port " + port;
    }
}
