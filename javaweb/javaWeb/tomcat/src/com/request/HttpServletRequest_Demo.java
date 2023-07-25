package com.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author ��
 * @version 1.0
 * 2022/4/19   11:18
 */
public class HttpServletRequest_Demo extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡ������Դ·��
        String requestURI = request.getRequestURI();
        System.out.println("URI:"+requestURI);
        //��ȡ������Դ�ľ���·��
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("URL:"+requestURL);
        //��ȡ�ͻ���ip
        /*
        * ʹ��(localhost�� 127.0.0.1)IDEA�еõ����� 127.0.0.1
        * 192.168.137.1[��ʵip] �õ� 192.168.137.1
        * */
        String ip = request.getRemoteHost();
        System.out.println("ip:"+ip);
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr:"+remoteAddr);
        //��ȡ�ͻ��˶˿�
        int port = request.getRemotePort();
        System.out.println("port:"+port);
        //��ȡ�ͻ����������Ϣ[����ͷ]
        String userAgent = request.getHeader("user-agent");
        System.out.println("userAgent:"+userAgent);
        //��ȡ����ʽ
        String method = request.getMethod();
        System.out.println("method:"+method);

        //��ȡ�������
        String name = request.getParameter("name");
        System.out.println("name:"+name);
        String sex = request.getParameter("sex");
        System.out.println("sex:"+sex);
        String age = request.getParameter("age");
        System.out.println("age:"+age);
        String password = request.getParameter("pw");
        System.out.println("password:"+password);
        //��Ȥ����
        String[] hobby = request.getParameterValues("hobby");
        System.out.println("hobby:"+ Arrays.asList(hobby));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //������������ַ���,�Ӷ����post�������������
        //Ҫ�ڻ�ȡ�������֮ǰʹ��
        req.setCharacterEncoding("utf-8");
        doGet(req,resp);
    }
}
