package com.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author ��
 * @version 1.0
 * 2022/4/19   13:55
 */
public class Servlet_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡ����
        String name = req.getParameter("name");
        System.out.println("Servlet_2_name:"+name);
        //�鿴servlet_1д��������
        Object key = req.getAttribute("key");
        System.out.println("Servlet_1_2_key:"+key);

        System.out.println("servlet_2ִ��ҵ���߼�");


    }
}
