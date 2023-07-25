package com.local.dhf.bootprofile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/7/6   15:01
 */
@Profile("test")
@RestController
public class HomeController {
    @Value("${person.name:李四}")
    private String name;
    @RequestMapping("/hello")
    public String hello(){
        return name;
    }
}
