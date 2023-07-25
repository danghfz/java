package com.local.boot.controller;

import com.local.boot.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/6/12   16:38
 */
@Controller
public class ResponseController {
    @GetMapping("/person")
    @ResponseBody
    /*
    * 1、如果浏览器发送请求返回xml
    * 2、如果是ajax请求，返回json
    * 3、如果是app发请求，返回自定义类型
    *       属性值1;属性值二;
    *
    * 步骤：
    * 1、添加自定义的MessageConverter进入系统底层
    * 2、系统会统计MessageConverter能操作哪些类型
    * 3、客户端内容协商*/
    public Person getPerson() {
        return new Person("张三", 20, new Date());
    }
}
