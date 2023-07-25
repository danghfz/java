package com.localhost; /**
 * @author ��
 * @version 1.0
 * 2022/4/18   15:59
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ServletContext1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //���Ի�ȡ��ServletConfig����,����servlet�еĲ��������ļ��е���Ϣ
        ServletConfig servletConfig = getServletConfig();
        ServletContext context = servletConfig.getServletContext();
//        1.��ȡweb.xml���õ������Ĳ���
        String username = context.getInitParameter("username");
        System.out.println("username:" + username);
//        2.��ȡ��ǰ����·��"/��������" ����: /tomcat
        String path = context.getContextPath();
        System.out.println("path:" + path);
//        3.��ȡ���̲�����ڷ�����Ӳ���ϵľ���·��
        /**
         * "/"������������:http://localhost:8080/tomcat(��������)
         * E:\java\javaWeb\tomcat\out\artifacts\tomcat_war_exploded\
         *      ӳ�䵽IDEA��webĿ¼��
         * Using CATALINA_BASE:   "C:\Users\lenvoo\AppData\Local\JetBrains\IntelliJIdea2021.3\tomcat\3af1974e-6c83-4af6-a2cd-53fc7476e5a577930bec-69b1-4453-89e2-f1491fa71c28"
         * ��IDEA����tomcat֮��,Tomcat��������һЩ��������
         *      �Լ�����Ŀ����д����(web·��),tomcat�Ὣ��Ŀ����out\artifacts\��Ŀ��\   [����·��]
         *      ͬʱ,Tomcat�ĸ������ݻᱻ������CATALINA_BASEĿ¼��conf\Catalina\localhost����.xml�ļ�
         *          <Context path="/tomcat" docBase="E:\java\javaWeb\tomcat\out\artifacts\tomcat_war_exploded" />
         * */
        String realPath = context.getRealPath("/");
        System.out.println("realPath:" + realPath);
//        4.��mapһ����ȡ����
        context.setAttribute("user", "dang");
        String user = (String) context.getAttribute("user");
        System.out.println("user:" + user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
