package com.dhf.controller;

import com.dhf.client.ProClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/28   19:28
 */
@RestController
public class ConController {
    @Autowired
    private ProClient proClient;
    @GetMapping("/hello")
    public String hello(){
        return proClient.hello();
    }
}
