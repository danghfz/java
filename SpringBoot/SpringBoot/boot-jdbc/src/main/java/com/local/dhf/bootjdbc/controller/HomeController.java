package com.local.dhf.bootjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import com.local.dhf.bootjdbc.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/6/29   12:23
 */
@Controller
public class HomeController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @ResponseBody
    @RequestMapping("/getUser")
    public List<User> getUserByList(){
        String sql = "select * from test";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return list;
    }
}
