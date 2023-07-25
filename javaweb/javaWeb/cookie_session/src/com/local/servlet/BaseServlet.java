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
 * 2022/4/25   21:09
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求中文乱码
        req.setCharacterEncoding("utf-8");
        //响应中文乱码
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String action = req.getParameter("action");//方法
        try {
            //反射调用
            this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class).invoke(this,req,resp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
