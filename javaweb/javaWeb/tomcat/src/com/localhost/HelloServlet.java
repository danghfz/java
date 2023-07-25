package com.localhost;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author ��
 * @version 1.0
 * 2022/4/18   13:35
 */
public class HelloServlet implements Servlet {
    public HelloServlet(){
        System.out.println("1.����������");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2.init��ʼ������");
        //servletConfig���Ի�ȡservlet�������ļ���Ϣ
        //��ȡservlet�������<servlet-name>
        String servletName = servletConfig.getServletName();
        System.out.println(servletName);
        //��ȡ��ʼ������
        String initParam = servletConfig.getInitParameter("url");
        System.out.println(initParam);
        //��ȡservletContext����
        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println(servletContext);


    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service()����ר�Ŵ����������Ӧ��
     * @param servletRequest
     * @param servletResponse*/
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3.service����");
        //����ת��
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        //��ȡ����ķ�ʽ[post,get]
        String method = servletRequest1.getMethod();
        if (method.equals("GET")){
            System.out.println("get����");

        }else {
            System.out.println("post����");
        }
//        System.out.println("service����Ӧ");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4.destroy���ٷ���");
    }
}
