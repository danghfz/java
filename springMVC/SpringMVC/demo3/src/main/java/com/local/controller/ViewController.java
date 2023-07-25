package com.local.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * @author 党
 * @version 1.0
 * 2022/5/19   11:44
 */
@Controller
public class ViewController {
    @RequestMapping("/view")
    public String view(){
        return "view";
    }
    @RequestMapping("/testThymeleafView")
    public String testThymeleafView() {
        return "success";//Thymeleaf模板引擎解析
    }
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/testThymeleafView";//转发internalResourceView
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){//RedirectView视图
        //因为重定向不能进入WEB-INF里面，所以重定向不能到某个页面，而是到某个请求
        return "redirect:/";//重定向到 / 请求，返回首页
    }
}
