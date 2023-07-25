package com.local.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ��
 * @version 1.0
 * 2022/4/25   21:09
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //������������
        req.setCharacterEncoding("utf-8");
        //��Ӧ��������
        resp.setContentType("text/html;charset=utf-8");
        //��ȡ����
        String action = req.getParameter("action");//����
        try {
            //�������
            this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class).invoke(this,req,resp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
