package com.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   16:04
 */
public class Forward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("请求到达");
        req.getRequestDispatcher("/a/b/c.html").forward(req, resp);
    }
}
