package com.request;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author ��
 * @version 1.0
 * 2022/4/19   13:54
 */
public class Servlet_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ�������
        String name = req.getParameter("name");
        System.out.println("servlet_1_name"+name);

        //ʹ��������
        req.setAttribute("key","Servlet_1���");

        //��·servlet_2
        /*
        * ����ת������Ҫ�� "/"��ͷ,��ʾ http://localhost:8080/tomcat/
        *  */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");
        //ת��
        requestDispatcher.forward(req,resp);
    }
}
