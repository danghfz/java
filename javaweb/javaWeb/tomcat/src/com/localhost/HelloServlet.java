package com.localhost;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author 党
 * @version 1.0
 * 2022/4/18   13:35
 */
public class HelloServlet implements Servlet {
    public HelloServlet(){
        System.out.println("1.构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2.init初始化方法");
        //servletConfig可以获取servlet的配置文件信息
        //获取servlet程序别名<servlet-name>
        String servletName = servletConfig.getServletName();
        System.out.println(servletName);
        //获取初始化参数
        String initParam = servletConfig.getInitParameter("url");
        System.out.println(initParam);
        //获取servletContext对象
        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println(servletContext);


    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service()方法专门处理请求和响应的
     * @param servletRequest
     * @param servletResponse*/
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3.service方法");
        //类型转换
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        //获取请求的方式[post,get]
        String method = servletRequest1.getMethod();
        if (method.equals("GET")){
            System.out.println("get请求");

        }else {
            System.out.println("post请求");
        }
//        System.out.println("service被响应");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4.destroy销毁方法");
    }
}
