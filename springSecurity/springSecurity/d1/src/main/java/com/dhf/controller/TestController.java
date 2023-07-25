package com.dhf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   13:56
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello security";
    }
}
