package com.local.boot.controller;

import com.local.boot.pojo.Pet;
import com.local.boot.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author 党
 * @version 1.0
 * 2022/6/9   21:13
 */
@Controller
public class ParamController {

    @RequestMapping("/PathVariable/{id}/{name}")
    public ModelAndView getParam(@PathVariable("id") String id,
                                 @PathVariable("name") String name,
                                 @PathVariable Map<String, String> map,
                                 @RequestHeader Map<String, String> header,
                                 @RequestHeader("Accept-Language") String language,
                                 @RequestParam("hobby") List<String> hobby) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("param");
        modelAndView.addObject("id", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("map", map);
        modelAndView.addObject("header", header);
        modelAndView.addObject("language", language);
        modelAndView.addObject("hobby", hobby);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView post(@RequestBody String requestBody) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("param");
        mv.addObject("requestBody", requestBody);
        return mv;
    }

    @RequestMapping("/Attribute")
    public ModelAndView Attribute() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/continue");//请求转发
        modelAndView.addObject("msg", "成功");
        return modelAndView;
    }

    @RequestMapping("/continue")
    public ModelAndView forward(@RequestAttribute(value = "msg", required = false) String msg) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("param");
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }

    //    MatrixVariable/test;user=user+password;hobbies=1,2;hobbies=3
    @RequestMapping("/MatrixVariable/{path}")//参数及其之前的第一个路径要使用{path}
    //SpringBoot默认禁用@MatrixVariable功能
    // 默认urlPathHelper.setRemoveSemicolonContent(false);
    // 在容器中放入自己的WebMvcConfigurerAdapter
    public ModelAndView matrixVariable(@MatrixVariable("user") String user,
                                       @MatrixVariable("hobbies") List<String> hobbies,
                                       @PathVariable("path") String path) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("param");
        modelAndView.addObject("user", user);
        modelAndView.addObject("hobby", hobbies);
        modelAndView.addObject("path", path);
        return modelAndView;
    }
    @PostMapping("/pojo")
    public ModelAndView pojo(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("PoJo");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
