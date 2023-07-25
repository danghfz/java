package com.local.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/28   16:33
 */
public class SessionServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void createSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建和获取Session对象
        HttpSession session = req.getSession();
        boolean aNew = session.isNew();
        session.setMaxInactiveInterval(3);//10s后销毁
        String id = session.getId();
        resp.getWriter().write("Session获取成功，id为：" + id + "，是否是新创建的：" + aNew);
    }
    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("key","session");
        resp.getWriter().write("Session设置成功");
    }
    protected void getAttribute(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        HttpSession session = request.getSession();
        Object key = session.getAttribute("key");
        response.getWriter().write("Session获取成功，key为：" + key);
    }
    protected void defaultLife(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        HttpSession session = request.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        response.getWriter().write("Session默认最大时间为：" + maxInactiveInterval+"s");
    }
    protected void deleteSession(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        HttpSession session = request.getSession();
        session.invalidate();
        response.getWriter().write("Session删除成功");
    }
}
