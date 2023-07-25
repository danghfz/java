package com.response;

import jakarta.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   17:18
 */
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查看服务器编码
        String characterEncoding = resp.getCharacterEncoding();
        //设置服务器编码
        resp.setCharacterEncoding("utf-8");
        //浏览器和服务器编码不一致，需要设置浏览器编码
        //此方法要在获取流对象前调用
        resp.setContentType("text/html;charset=utf-8");//设置浏览器编码 同时也设置服务器的编码
//        resp.setHeader("content-type","text/html;charset=utf-8");//设置浏览器

        //获取输出流
        PrintWriter writer = resp.getWriter();
        writer.write("内容");
//        ServletOutputStream outputStream = resp.getOutputStream();
    }
}
