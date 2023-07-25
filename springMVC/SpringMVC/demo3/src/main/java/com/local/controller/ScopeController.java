package com.local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 党
 * @version 1.0
 * 2022/5/19   10:10
 */
@Controller
public class ScopeController {
    @RequestMapping("/testRequestByServletAPI")
    //使用servletRequest域对象传递数据
    public String testRequestByServletAPI(HttpServletRequest request) {
        request.setAttribute("msg", "servletAPI");
        return "success";//请求转发DispatcherServlet
    }

    //ModelAndView向request域对象传递数据
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        /**
         * ModelAndView 有Model 和View功能
         * Model主要用于向请求域共享数据
         *View主要用于设置视图，实现页面跳转*/
        ModelAndView modelAndView = new ModelAndView();
        //向请求域共享数据
        modelAndView.addObject("msg", "ModelAndView");
        //设置视图，跳转到success.html
        modelAndView.setViewName("success");
        return modelAndView;
    }

    //测试Model
    @RequestMapping("/testModel")
    public String testModel(Model model) {
        model.addAttribute("msg", "Model");
        return "success";
    }

    @RequestMapping("/testMap")
    //测试map
    public String testMap(Map<String, Object> map) {
        map.put("msg", "Map");
        return "success";
    }

    @RequestMapping("/testModelMap")
    //测试ModelMap
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("msg", "ModelMap");
        return "success";
    }

    @RequestMapping("/testSession")
    //向session域传递数据
    public String testSession(HttpSession session){
        session.setAttribute("msg","session");
        return "success";
    }
    @RequestMapping("/testApplication")
    //向application域传递数据
    public String testServletContext(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("msg","application");
        return "success";
    }
}
