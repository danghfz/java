package com.response;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ��
 * @version 1.0
 * 2022/4/19   17:50
 */
public class Response_old extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("Response_old");
        //��һ�ַ�ʽ
        //������Ӧ״̬��
        resp.setStatus(302);
        //������Ӧͷ,�ض���
        resp.setHeader("Location","http://localhost:8080/tomcat/response_new");

        //�ڶ��ַ�ʽ
//        resp.sendRedirect("http://localhost:8080/tomcat/response_new");
    }
}
