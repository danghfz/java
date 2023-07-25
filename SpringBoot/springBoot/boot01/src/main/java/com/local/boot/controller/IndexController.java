package com.local.boot.controller;

import com.local.boot.pojo.jdbc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/5/26   16:42
 */
//@Controller // 标识为控制器
//@ResponseBody // 标识为返回响应体
@RestController
public class IndexController {
    @RequestMapping("/") // 标识为请求映射
    public String index(){
        return "Hello World";
    }

    @Autowired
    private jdbc jdbc;
    @RequestMapping("/jdbc")
    public String jdbc(){
        return jdbc.toString();
    }
}
