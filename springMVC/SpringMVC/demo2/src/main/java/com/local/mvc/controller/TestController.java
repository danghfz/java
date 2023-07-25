package com.local.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 党
 * @version 1.0
 * 2022/5/17   20:47
 */
@Controller //控制层组件
public class TestController {
    @RequestMapping("/")
    public String index(){//首页
        return "index";
    }
    @RequestMapping("/parameter")
    public String parameter(){
        return "parameter";
    }

}
