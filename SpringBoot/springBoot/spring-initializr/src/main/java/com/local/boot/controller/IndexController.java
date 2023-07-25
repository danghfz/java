package com.local.boot.controller;

import com.local.boot.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/5/30   12:09
 */
// @RestController = @Controller + @ResponseBody
@Controller
@Slf4j
public class IndexController {
    @Autowired
    Person person;


    @RequestMapping("/")
    public String index() {
        return person.toString();
    }
    @ResponseBody // 返回json数据
    @RequestMapping("/person")
    public Person person() {
        return person;
    }
}
