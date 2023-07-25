package com.response;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   17:50
 */
public class Response_old extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("Response_old");
        //第一种方式
        //设置响应状态码
        resp.setStatus(302);
        //设置响应头,重定向
        resp.setHeader("Location","http://localhost:8080/tomcat/response_new");

        //第二种方式
//        resp.sendRedirect("http://localhost:8080/tomcat/response_new");
    }
}
