package com.local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 党
 * @version 1.0
 * 2022/5/21   15:11
 */
@Controller
public class TestController {
    @RequestMapping("/testInterceptor")
    @ResponseBody
    public String testInterceptor(){
        return "success";
    }
    @RequestMapping("/testExceptionHandler")
    public String testExceptionHandler(){
        System.out.println(1/0);
        return "success";
    }
}
