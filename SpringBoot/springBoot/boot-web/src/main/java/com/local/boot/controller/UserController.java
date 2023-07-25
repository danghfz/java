package com.local.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/5/30   19:15
 */
@RestController
public class UserController {
    @RequestMapping(value = "/user",
    method = RequestMethod.GET)
    public String getUser(){
        return "获取用户信息成功";
    }
    @RequestMapping(value = "/user",
            method = RequestMethod.DELETE)
    public String deleteUser(){
        return "删除用户信息成功";
    }
    @RequestMapping(value = "/user",
            method = RequestMethod.POST)
    public String postUser(){
        return "保存用户信息成功";
    }
    @RequestMapping(value = "/user",
            method = RequestMethod.PUT)
    public String putUser(){
        return "更新用户信息成功";
    }
}
