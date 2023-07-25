package com.local.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/28   16:15
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pw = req.getParameter("pw");
        if (username.equals("dhf")&&pw.equals("123456")){
            //登录成功
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*60*24*7);//一周时间
            resp.addCookie(cookie);
            //请求转发
            req.getRequestDispatcher("/success.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/fail.jsp").forward(req,resp);
        }
    }
}
