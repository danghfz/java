package com.local.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 党
 * @version 1.0
 * 2022/5/30   15:09
 */
@Controller
public class HelloController {
    /*请求进来，先去找Controller,Controller不能处理再去静态资源找*/
    @RequestMapping("/u.png")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

}
