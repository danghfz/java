package com.localhost; /**
 * @author 党
 * @version 1.0
 * 2022/4/18   15:59
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ServletContext1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //可以获取到ServletConfig对象,访问servlet中的参数配置文件中的信息
        ServletConfig servletConfig = getServletConfig();
        ServletContext context = servletConfig.getServletContext();
//        1.获取web.xml配置的上下文参数
        String username = context.getInitParameter("username");
        System.out.println("username:" + username);
//        2.获取当前工程路径"/工程名称" 例如: /tomcat
        String path = context.getContextPath();
        System.out.println("path:" + path);
//        3.获取工程部署后在服务器硬盘上的绝对路径
        /**
         * "/"被服务器解析:http://localhost:8080/tomcat(工程名称)
         * E:\java\javaWeb\tomcat\out\artifacts\tomcat_war_exploded\
         *      映射到IDEA的web目录中
         * Using CATALINA_BASE:   "C:\Users\lenvoo\AppData\Local\JetBrains\IntelliJIdea2021.3\tomcat\3af1974e-6c83-4af6-a2cd-53fc7476e5a577930bec-69b1-4453-89e2-f1491fa71c28"
         * 是IDEA整合tomcat之后,Tomcat被拷贝的一些副本内容
         *      自己在项目里面写代码(web路径),tomcat会将项目部署到out\artifacts\项目名\   [绝对路径]
         *      同时,Tomcat的副本内容会被拷贝到CATALINA_BASE目录下conf\Catalina\localhost产生.xml文件
         *          <Context path="/tomcat" docBase="E:\java\javaWeb\tomcat\out\artifacts\tomcat_war_exploded" />
         * */
        String realPath = context.getRealPath("/");
        System.out.println("realPath:" + realPath);
//        4.像map一样存取数据
        context.setAttribute("user", "dang");
        String user = (String) context.getAttribute("user");
        System.out.println("user:" + user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
