package com.local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author 党
 * @version 1.0
 * 2022/5/22   10:11
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
