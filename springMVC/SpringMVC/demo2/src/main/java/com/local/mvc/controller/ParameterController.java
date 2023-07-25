package com.local.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author 党
 * @version 1.0
 * 2022/5/18   15:37
 */
@Controller
public class ParameterController {
    @RequestMapping("/testRequestAPI")
    public String servletAPI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        response.getWriter().write("username:" + username);
        return null;
    }
    @RequestMapping("/testParam")
    //形参中的参数名称和请求参数的名称要一致，自动赋值
    public String testParam(String username,HttpServletResponse response) throws IOException {
        response.getWriter().write("username="+username);
        return null;
    }
    @RequestMapping("/testParameter/{username}")
    public String testParameter(@PathVariable("username")String username, HttpServletResponse response) throws IOException {
        response.getWriter().write("username"+username);
        return null;
    }
    @RequestMapping("/testForm")
    public String testForm(String username,String password,String hobby[],HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("username:"+username);
        writer.println("password:"+password);
        writer.println("hobby:"+ Arrays.toString(hobby));
        return null;
    }

}
