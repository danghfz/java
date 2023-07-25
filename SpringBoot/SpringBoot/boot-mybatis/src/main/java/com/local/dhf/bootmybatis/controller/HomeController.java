package com.local.dhf.bootmybatis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.local.dhf.bootmybatis.pojo.City;
import com.local.dhf.bootmybatis.pojo.Emp;
import com.local.dhf.bootmybatis.pojo.User;
import com.local.dhf.bootmybatis.service.CityService;
import com.local.dhf.bootmybatis.service.EmpService;
import com.local.dhf.bootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/6/29   17:36
 */
@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    CityService cityService;
    @Autowired
    EmpService empService;

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(@RequestParam("id") String id) {
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping("/getCity")
    public City getCity(@RequestParam("id") Integer id) {
        return cityService.getCityById(id);
    }

    @RequestMapping("/getEmp")
    public ModelAndView getEmp() {
        List<Emp> list = empService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("table");
        return modelAndView;
    }
    @RequestMapping("/getPage")
    public ModelAndView getPage(@RequestParam(value = "page",defaultValue = "1") Long page) {
        //分页查询
        // @param current 当前页
        //@param size    每页显示条数
        //public Page(long current, long size)
        Page<Emp> empPage = new Page<>(page, 2);
        Page<Emp> page1 = empService.page(empPage, null);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("page",page1);
        modelAndView.setViewName("table_page");
        return modelAndView;
    }
    @RequestMapping("/del")
    public ModelAndView del(@RequestParam("id") BigInteger id,@RequestParam("page") Long page) {
        //删除根据id
        empService.removeById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/getPage?page="+page);
        return modelAndView;
    }
}