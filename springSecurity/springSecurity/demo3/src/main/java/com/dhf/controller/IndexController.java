package com.dhf.controller;

import com.dhf.pojo.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   15:10
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "hello Security";
    }

    @RequestMapping("/Secured")
    @Secured({"ROLE_ADMIN", "ROLE_ALL"})
    public String test() {
        return "@Secured 角色注解";
    }

    @RequestMapping("/PreAuthorize")
    @PreAuthorize("hasAnyAuthority('LUCY')")
    public String test2() {
        return "@PreAuthorize 注解";
    }

    @RequestMapping("/PostAuthorize")
    @PostAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String test3() {
        System.err.println("PostAuthorize.......");
        return "PostAuthorize 注解";
    }

    @RequestMapping("/PostFilter")
    @PostFilter("filterObject.username == 'admin'")
    public String test4() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        return users.toString();
    }
}
