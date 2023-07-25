package com.local.conreoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

/**
 * @author 党
 * @version 1.0
 * 2022/5/19   15:30
 */
@Controller
public class UserController {
    /**
     * 使用restful模拟用户资源的增删改查
     *  /user  GET   查询所有用户
     *  /user/id  GET  根据id查询用户
     *  /user   POST   添加用户
     *  /user/id DELETE  删除用户
     *  /user  PUT    修改用户信息
     *  */
    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET
    )
    public ModelAndView query(){//查询所有用户
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","查询所有用户");
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping(
            value = "/user/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView queryForObject(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","查询用户id="+id);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping(
            value = "user",
            method = RequestMethod.POST
    )
    public ModelAndView add(String username,String password){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","添加用户"+username+": "+password);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping(
            value = "user",
            method = RequestMethod.PUT
    )
    public ModelAndView update(String username,String password){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","修改用户"+username+": "+password);
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping(
            value = "/user/{username}",
            method = RequestMethod.DELETE
    )
    public ModelAndView delete(@PathVariable String username){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","删除用户"+username);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
