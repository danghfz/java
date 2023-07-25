package com.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   11:18
 */
public class HttpServletRequest_Demo extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求资源路径
        String requestURI = request.getRequestURI();
        System.out.println("URI:"+requestURI);
        //获取请求资源的绝对路径
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("URL:"+requestURL);
        //获取客户端ip
        /*
        * 使用(localhost和 127.0.0.1)IDEA中得到的是 127.0.0.1
        * 192.168.137.1[真实ip] 得到 192.168.137.1
        * */
        String ip = request.getRemoteHost();
        System.out.println("ip:"+ip);
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr:"+remoteAddr);
        //获取客户端端口
        int port = request.getRemotePort();
        System.out.println("port:"+port);
        //获取客户端浏览器信息[请求头]
        String userAgent = request.getHeader("user-agent");
        System.out.println("userAgent:"+userAgent);
        //获取请求方式
        String method = request.getMethod();
        System.out.println("method:"+method);

        //获取请求参数
        String name = request.getParameter("name");
        System.out.println("name:"+name);
        String sex = request.getParameter("sex");
        System.out.println("sex:"+sex);
        String age = request.getParameter("age");
        System.out.println("age:"+age);
        String password = request.getParameter("pw");
        System.out.println("password:"+password);
        //兴趣爱好
        String[] hobby = request.getParameterValues("hobby");
        System.out.println("hobby:"+ Arrays.asList(hobby));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求体的字符集,从而解决post请求的乱码问题
        //要在获取请求参数之前使用
        req.setCharacterEncoding("utf-8");
        doGet(req,resp);
    }
}
