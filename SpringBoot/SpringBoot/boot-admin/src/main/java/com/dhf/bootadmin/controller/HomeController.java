package com.dhf.bootadmin.controller;

import com.dhf.bootadmin.exception.UserTooManyException;
import com.dhf.bootadmin.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author 党
 * @version 1.0
 * 2022/6/18   23:03
 */
@Controller
public class HomeController {
    @GetMapping(value = {"/", "/login"})//首页
    public String home() {
        System.out.println("连接成功！！");
        return "login";//到login.html页面
    }

    @PostMapping("/login")//登录
    public ModelAndView login(User user, ModelAndView mav, HttpSession session) {
        //校验用户名密码
        if (StringUtils.hasLength(user.getUsername()) && StringUtils.hasLength(user.getPassword())) {
            //登录成功，重定向到主页面，防止表单重新提交
            //将用户信息存入session
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/success");//请求
        }
        //登录失败，跳转到登录页面
        mav.setViewName("login");
        return mav;

    }

    @GetMapping("/success")//主页
    public ModelAndView success(HttpSession session) {
//        //判断是否存在user
//        Object user = session.getAttribute("user");
//        if (user != null) {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.setViewName("success");
//            return modelAndView;
//        }
//        ModelAndView modelAndView = new ModelAndView();
//        //重定向到登录页面
//        modelAndView.setViewName("redirect:/login");
//        return modelAndView;

        //上面判断使用拦截器做了，这里不需要再判断
        return new ModelAndView("success");

    }
    @RequestMapping("/errorTest")//错误
    public String error(){
        int i = 1 / 0;
        return "success";
    }
    @RequestMapping("/exception")
    public String exception(int count){
        if (count>3){
            throw new UserTooManyException();
        }

        return "success";
    }

}
