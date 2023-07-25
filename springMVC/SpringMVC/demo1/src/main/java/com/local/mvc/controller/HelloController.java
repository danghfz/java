package com.local.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 党
 * @version 1.0
 * 2022/5/17   18:20
 */
@Controller // 标明这是一个控制器
public class HelloController {
    // "/"-->WEB-INF/templates/index.html
    //请求映射，当请求路径为 / 是，调用方法
    @RequestMapping(value="/")
    public String get(){
        //返回视图名称
        return "index";
        //返回视图被视图解析器解析
    }
    //访问target.html
    @RequestMapping(value="/target")
    public String target(){
        return "target";
    }
}
