package com.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   13:55
 */
public class Servlet_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String name = req.getParameter("name");
        System.out.println("Servlet_2_name:"+name);
        //查看servlet_1写的域数据
        Object key = req.getAttribute("key");
        System.out.println("Servlet_1_2_key:"+key);

        System.out.println("servlet_2执行业务逻辑");


    }
}
