package com.request;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   13:54
 */
public class Servlet_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String name = req.getParameter("name");
        System.out.println("servlet_1_name"+name);

        //使用域数据
        req.setAttribute("key","Servlet_1标记");

        //问路servlet_2
        /*
        * 请求转发必须要以 "/"开头,表示 http://localhost:8080/tomcat/
        *  */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");
        //转发
        requestDispatcher.forward(req,resp);
    }
}
