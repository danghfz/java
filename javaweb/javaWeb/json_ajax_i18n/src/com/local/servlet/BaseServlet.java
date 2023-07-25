package com.local.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   17:40
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        //响应乱码
        resp.setContentType("text/html;charset=utf-8");
        //获取请求的参数
        String action = req.getParameter("action");//方法名称
        //使用反射调用方法
        try {
            this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class).invoke(this,req,resp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
